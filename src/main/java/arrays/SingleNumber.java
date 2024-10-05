package arrays;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class SingleNumber {

  public int singleNumber(int[] nums) {
    return Arrays.stream(nums).boxed()
        .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
        .entrySet().stream()
        .filter(e -> e.getValue() == 1L)
        .findFirst()
        .map(Map.Entry::getKey)
        .get();
  }

  public static void main(String[] args) {
    var a = new SingleNumber();

    var answer = a.singleNumber(new int[]{4, 1, 2, 1, 2});

    System.out.printf("answer: %s%n", answer);
  }

}
