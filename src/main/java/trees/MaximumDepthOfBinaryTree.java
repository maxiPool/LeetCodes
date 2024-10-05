package trees;

import trees.model.TreeNode;

import static trees.Utils.TREE_B;

public class MaximumDepthOfBinaryTree {

  public static int maxDepth(TreeNode root) {
    return maxDepthHelper(root, 0);
  }

  private static int maxDepthHelper(TreeNode root, int value) {
    if (root == null) {
      return value;
    }
    var leftDepth = maxDepthHelper(root.left, value + 1);
    var rightDepth = maxDepthHelper(root.right, value + 1);
    return Math.max(leftDepth, rightDepth);
  }

  public static void main(String[] args) {
    System.out.println(maxDepth(TREE_B));
  }

}
