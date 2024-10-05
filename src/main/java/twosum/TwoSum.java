package twosum;

import java.util.HashMap;

public class TwoSum {

  public static int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      var remaining = target - nums[i];
      for (int j = 0; j < nums.length; j++) {
        if (i != j && remaining - nums[j] == 0) {
          return new int[]{i, j};
        }
      }
    }
    return null;
  }

}
