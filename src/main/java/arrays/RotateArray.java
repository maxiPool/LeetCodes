package arrays;

public class RotateArray {

  /**
   * Given an array, rotate the array to the right by k steps, where k is non-negative.
   * https://leetcode.com/explore/featured/card/top-interview-questions-easy/92/array/646/
   */
  public static void rotate(int[] nums, int k) {
    // imagine a pointer, k is the pointer
    // if it says k = 1, then the pointer goes 1 to the left from index 0,
    // which means: var index = (nums.length - k) % nums.length is the new starting index
    // then just iterate for(i = index; counter from 0 to < nums.length; i = (i + 1) % nums.length )

    var remainder = k % nums.length;
    if (remainder == 0) return;
    var pointer = nums.length - remainder;
    var copy = nums.clone();

    for (int i = 0; i < nums.length; i++) {
      nums[i] = copy[pointer];
      pointer = (pointer + 1) % nums.length;
    }
  }

  public static void main(String[] args) {
    rotate(new int[]{1}, 0);
  }

}
