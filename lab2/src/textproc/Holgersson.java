package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne",
			"småland", "södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland",
			"ångermanland", "öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("lab2\\undantagsord.txt"), "utf-8");
		Set<String> stopwords = new HashSet<>();
		while (scan.hasNext()) {
			stopwords.add(scan.next().toLowerCase());
		}
		scan.close();

		ArrayList<TextProcessor> processors = new ArrayList<>();

		processors.add(new SingleWordCounter("nils"));
		processors.add(new SingleWordCounter("norge"));
		processors.add(new MultiWordCounter(REGIONS));
		processors.add(new GeneralWordCounter(stopwords));

		Scanner s = new Scanner(new File("lab2\\nilsholg.txt"), "utf-8");
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		long t0 = System.nanoTime();

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			for (TextProcessor p : processors) {
				p.process(word);
			}
		}

		long t1 = System.nanoTime();
		
		s.close();

		for (TextProcessor p : processors) {
			p.report();
		}

		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
		
	}
}