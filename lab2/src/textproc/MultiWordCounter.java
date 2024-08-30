package textproc;

import java.util.TreeMap;


public class MultiWordCounter implements TextProcessor {

    private TreeMap<String, Integer> words = new TreeMap<>();
    //private HashMap<String, Integer> words = new HashMap<>();

    public MultiWordCounter(String[] wordArray) {
        for (String w : wordArray) {
            words.put(w, 0);
        }
    }

    @Override
    public void process(String w) {
        if (words.containsKey(w)) {
            words.put(w, words.get(w) + 1);
        }
    }

    @Override
    public void report() {
        words.forEach((k, v) -> System.out.println(k + " " + v));
    }
}