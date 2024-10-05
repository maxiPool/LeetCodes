package arrays;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.*;

/**
 * https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/
 */
public class kthSmallestMatrix {
  /*
    Kotlin Pure Functional Solution:
    fun kthSmallest(mat: Array<IntArray>, k: Int): Int =
            mat.drop(1)
               .fold(mat.first().toList()) {  // in '()' there's a mapping before doing the reduction of fold
               // first is like take(1) and so we take the 1st row and map it into a list
                   best, row -> // java: (acc, current) ->
                       best.flatMap { x -> row.map { y -> x + y } }
                          .sorted()
                          .take(k)
                   }[k - 1]
   */

  public int kthSmallest(int[][] mat, int k) {
    int[] reduce = stream(mat)
        .skip(1)
        // reduces (m - 1) times
        .reduce(mat[0],
            (best, currentRow) -> getKSmallestSums(k, best, currentRow));
    return reduce[k - 1];
  }

  /**
   * The idea is to start from the 1st row as the accumulator (best),
   * take each element of the accumulator and add it to each element of the current row.
   *
   * Then use this accumulator/subtotal for the next row when reduce iterates to the next row.
   *
   * Why this works?
   * It sums the 1st row against the 2nd in all possible ways
   * It sums the subtotal or r1 & r2 (3*3 possibilities, sorted & limited to 7) onto r3.
   * To do it on r3 it does: the 7 from r1 & r2 subtotal TIMES 3 (number of elements in r3)
   * in other words: it takes r3, for each element in the accumulator it will add that element onto r3's elements. This results in an array of 3 * 7 elements, that we sort and keep the 7 best.
   *
   * accumulatorLength (always k) * rowLength (n) ... and we do this matrixLength (m) times minus 1 since we already start with m[0] in the accumulator
   * time: O(k * n * (m - 1))
   * space: O(k)
   */
  private int[] getKSmallestSums(int k, int[] best, int[] currentRow) {
    var mappedAccElements = stream(best).boxed()
        // map each element of the accumulator to a row (adds it to each element of that row)
        // maps k * n times (because we use .limit(k), otherwise it would be [(m-1)*n as k] * n times
        .map(accumulatorElements -> addItToCurrentRowIts(currentRow, accumulatorElements))
        .flatMap(Collection::stream).toList();
    System.out.println(mappedAccElements);
    System.out.println(mappedAccElements.stream().sorted().limit(k).toList());
    int[] ints = mappedAccElements.stream()
//        .flatMap(x -> getIntegerStream(currentRow, x))
        .sorted()
        .limit(k)
        .mapToInt(Integer::intValue)
        .toArray();
    return ints;
  }

  private Stream<Integer> getIntegerStream(int[] currentRow, Integer x) {
    return stream(currentRow).boxed().map(y -> x + y);
  }

  private List<Integer> addItToCurrentRowIts(int[] currentRow, Integer x) {
    var integers = stream(currentRow).boxed().map(y -> x + y).toList();
    return integers;
  }

  public static void main(String[] args) {
  /*
    Input: mat = [[1,3,11],[2,4,6]], k = 5
    Output: 7
    Explanation: Choosing one element from each row, the first k smallest sum are:
    [1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.
   */
    var instance = new kthSmallestMatrix();

//    var mat = new int[][]{{1, 3, 11}, {2, 4, 6}};
//    int i = instance.kthSmallest(mat, 5);
//    System.out.printf("Expected: 7, Actual: %d%n", i);

    /*
      Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
      Output: 9
      Explanation: Choosing one element from each row, the first k smallest sum are:
      [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.
     */

    var mat = new int[][]{{1, 10, 10}, {1, 4, 5}, {2, 3, 6}};
    int i = instance.kthSmallest(mat, 7);
    System.out.printf("Expected: 9, Actual: %d%n", i);
  }

}
