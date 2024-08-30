package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {

    private Set<String> stopwords;
    private TreeMap<String, Integer> words = new TreeMap<>();
    //private HashMap<String, Integer> words = new HashMap<>();


    public GeneralWordCounter(Set<String> stopwords) {
        this.stopwords = stopwords;
    }

    @Override
    public void process(String w) {
        if (stopwords.contains(w)) {
            return; // Ignore stopwords
        }
    
        if (! words.containsKey(w)) {
            words.put(w, 1); // new word
        } else {
            words.put(w, words.get(w) + 1); // words exists, increment by 1
        }
    }

    @Override
    public void report() {
        Set<Map.Entry<String, Integer>> wordSet = words.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet); // convert to arraylist

        wordList.sort((w1, w2) -> {

            int wordComp = w2.getValue() - w1.getValue(); // frequency

            if (wordComp != 0) {
                return wordComp;
            } else {
                return w1.getKey().compareTo(w2.getKey()); // alphabetically
            }
        });

        for (int i = 0; i < 20; i++) {
            System.out.println(wordList.get(i).getKey() + " " + wordList.get(i).getValue());
        }
    }

    /**
     * Returnerar en Lista av typ Map.Entry med alla ord
     * 
     * @return returns a list of type Map.Entry(String, Integer) with all words
     */
    public List<Map.Entry<String, Integer>> getWordList() {
        return new ArrayList<Map.Entry<String, Integer>>(words.entrySet());
    }
}