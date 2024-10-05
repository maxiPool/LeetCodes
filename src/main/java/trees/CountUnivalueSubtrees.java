package trees;

import trees.model.TreeNode;

import static trees.Utils.TREE_UNI_VALUE_5;
import static trees.Utils.TREE_UNI_VALUE_51;

/**
 * Given the root of a binary tree, return the number of uni-value subtrees.
 * A uni-value subtree means all nodes of the subtree have the same value.
 */
public class CountUnivalueSubtrees {


  public static int countUnivalSubtrees(TreeNode root) {
    var resultRef = new int[]{0};
    helper(root, resultRef);
    return resultRef[0];
  }

  /**
   * Start counting from the leaves.
   * Keep/return the value of the leaf to compare to
   */
  private static int helper(TreeNode root, int[] countRef) {
    if (isLeaf(root)) {
      countRef[0]++;
      return root.val;
    }

    Integer leftChildVal = null;
    if (root.left != null) {
      leftChildVal = helper(root.left, countRef);
    }

    Integer rightChildVal = null;
    if (root.right != null) {
      rightChildVal = helper(root.right, countRef);
    }

    // if the left & right children values are the same AND root's value is the same, then we can count a uni-value subtree
    if (rightChildVal != null && leftChildVal != null && rightChildVal.equals(leftChildVal) && rightChildVal == root.val
    || leftChildVal != null && root.val == leftChildVal && rightChildVal == null
    || rightChildVal != null && root.val == rightChildVal && leftChildVal == null) {
      countRef[0]++;
      return root.val;
    }
    return -1;
  }

  private static boolean isLeaf(TreeNode root) {
    return root.left == null && root.right == null;
  }

  public static void main(String[] args) {
    // empty tree = 0

//        5
//    1       5*
//  5*  5*  x   5* -> return 4
    System.out.println(countUnivalSubtrees(TREE_UNI_VALUE_51)); // 4
//        5*
//    5*      5*
//  5*  5*  x   5* -> return 6
    System.out.println(countUnivalSubtrees(TREE_UNI_VALUE_5)); // 6

  }

}
