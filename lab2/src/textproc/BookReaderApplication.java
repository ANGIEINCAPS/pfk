package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class BookReaderApplication {

    public static void main(String[] args) throws FileNotFoundException {

		Scanner scan = new Scanner(new File("lab2\\undantagsord.txt")); 
		Set<String> stopwords = new TreeSet<>();
		while (scan.hasNext()) {
			String word = scan.next().toLowerCase();
			stopwords.add(word);
		}

		GeneralWordCounter counter = new GeneralWordCounter(stopwords);

		Scanner s = new Scanner(new File("lab2\\nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			counter.process(word);

		}

		s.close();

		BookReaderController controller = new BookReaderController(counter);
    }
}