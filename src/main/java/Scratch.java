import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.ToIntFunction;

import static java.util.Collections.emptyList;
import static java.util.Map.Entry;
import static java.util.stream.IntStream.range;

public class Scratch {

  /**
   * Write a method that are going to receive a text (one String) and return the word that appears the most in the text.
   * If there are two words that appears the most, return the word that appeared first in the text.
   *
   * @param input example: "aaa bbb ccc ddd aaa ddd bbb ddd bbb"
   * @return example: "bbb"
   */
  /*
  What do we want?
  Return the word that appears the most
  If two appear the same number of times, then return the one which appeared first.

  What will we need?
  1) The frequency of appearance of each word
  2) the index of the first appearance of a word
  3) a key = the word

   */
  public static String getFirstMostFrequentSimple(String input) {
    if (input == null) return null;
    var split = safeList(Arrays.asList(input.split(" ")));

    return range(0, split.size()).boxed()
        .reduce(new HashMap<>(),
            (sub, next) -> toFrequenciesMap(split, sub, next),
            Scratch::mergeMaps)
        .entrySet().stream()
        .sorted(sortDescendingFrequency()
            .thenComparingInt(sortAscendingAppearance()))
        .map(Entry::getValue)
        .map(WordStats::getWord)
        .limit(1L)
        .findFirst()
        .orElse("null - two");
  }

  /**
   * ChatGpt3 openAi's solution
   */
  public static String mostFrequentWord(String text) {
    // Split the text into a list of words
    String[] words = text.split("\\s+");

    // Create a map to store the frequency of each word
    Map<String, Integer> wordCounts = new HashMap<>();

    // Iterate through the list of words and count the frequency of each word
    for (String word : words) {
      if (wordCounts.containsKey(word)) {
        wordCounts.put(word, wordCounts.get(word) + 1);
      } else {
        wordCounts.put(word, 1);
      }
    }

    // Find the word with the highest frequency
    String mostFrequent = words[0];
    int highestCount = 0;
    for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
      if (entry.getValue() > highestCount) {
        mostFrequent = entry.getKey();
        highestCount = entry.getValue();
      }
    }

    return mostFrequent;
  }

  private static <T> List<T> safeList(List<T> unsafe) {
    return unsafe == null ? emptyList() : unsafe;
  }

  @NotNull
  private static Map<String, WordStats> mergeMaps(Map<String, WordStats> a,
                                                  Map<String, WordStats> b) {
    a.putAll(b);
    return a;
  }

  @NotNull
  private static Map<String, WordStats> toFrequenciesMap(List<String> split, Map<String, WordStats> sub, Integer next) {
    var word = split.get(next);
    sub.compute(word, (__, v) -> v == null
        ? new WordStats(word, next)
        : v.setFrequency(v.getFrequency() + 1));
    return sub;
  }

  @NotNull
  private static ToIntFunction<Entry<String, WordStats>> sortAscendingAppearance() {
    return e -> e.getValue().getFirstAppearanceIndex();
  }

  @NotNull
  private static Comparator<Entry<String, WordStats>> sortDescendingFrequency() {
    return (a, b) -> b.getValue().getFrequency().compareTo(a.getValue().getFrequency());
  }

  private static class WordStats {
    private Integer frequency;
    private final Integer firstAppearanceIndex;
    private final String word;

    public WordStats(String word, int firstAppearanceIndex) {
      this.word = word;
      this.frequency = 1;
      this.firstAppearanceIndex = firstAppearanceIndex;
    }

    public Integer getFrequency() {
      return frequency;
    }

    public WordStats setFrequency(int frequency) {
      this.frequency = frequency;
      return this;
    }

    public Integer getFirstAppearanceIndex() {
      return firstAppearanceIndex;
    }

    public String getWord() {
      return word;
    }
  }


  public static void main(String[] args) {
    String input = "aaa bbb ccc ddd aaa ddd bbb ddd bbb";
    var a = getFirstMostFrequentSimple(input);
    System.out.println(a);

    var b = getFirstMostFrequentSimple(null);
    System.out.println(b);

    var c = getFirstMostFrequentSimple("  ");
    System.out.println(c);

    var chatGpt1 = mostFrequentWord(input);
    System.out.println(chatGpt1);

    var chatGpt2 = mostFrequentWord("b b b a a a");
    System.out.println(chatGpt2);
    // ChatGPT is wrong because it doesn't return the firstMostFrequent; it gives any most frequent
  }

}
