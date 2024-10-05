package trees;

import trees.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static trees.Utils.TREE_A;

/**
 * Given the root of a binary tree and an integer targetSum,
 * return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 */
public class PathSum {

  public static boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) return false;
    return helper(root, targetSum, new ArrayList<>());
  }

  private static boolean helper(TreeNode root, int targetSum, List<Integer> outSum) {
    // everytime we reach a leaf, then we can check the sum
    if (root.left == null && root.right == null) {
      outSum.add(root.val);
      return targetSum == outSum.stream().reduce(0, Integer::sum);
    }
    outSum.add(root.val);

    var bool = false;
    if (root.left != null) {
      bool = helper(root.left, targetSum, outSum);
      if (!bool && !outSum.isEmpty()) {
        outSum.remove(outSum.size() - 1);
      }
    }
    if (bool) return true;
    if (root.right != null) {
      bool = helper(root.right, targetSum, outSum);
      if (!bool && !outSum.isEmpty()) {
        outSum.remove(outSum.size() - 1);
      }
    }
    return bool;
  }

  public static void main(String[] args) {
    System.out.println(hasPathSum(TREE_A, 6)); // true
    System.out.println(hasPathSum(TREE_A, 5)); // false

    System.out.println(hasPathSum(
        new TreeNode(1,
            new TreeNode(2), new TreeNode(3))
        , 5
    ));  // false

//            5*
//         4*     8
//      11*     13  4
//    7    2*         1
    var t = new TreeNode(5,
        new TreeNode(4,
            new TreeNode(11,
                new TreeNode(7), new TreeNode(2)),
            null),
        new TreeNode(8,
            new TreeNode(13), new TreeNode(4, null, new TreeNode(1))));
    System.out.println(hasPathSum(t, 22)); // trueL 5+4+11+2 = 22

    var t2 = new TreeNode(1, new TreeNode(2), null);
    System.out.println(hasPathSum(t2, 1)); // false
  }

}
