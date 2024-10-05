package trees;

import trees.model.TreeNode;

import static trees.Utils.TREE_A;
import static trees.Utils.TREE_SYMMETRIC;

public class SymmetricTree {

  public static boolean isSymmetric(TreeNode root) {
    return helper(root.left, root.right);
  }

  private static boolean helper(TreeNode left, TreeNode right) {
    if (left == null && right != null
        || left != null && right == null) {
      return false;
    }
    if (left == null) {
      return true;
    }
    if (left.val != right.val) {
      return false;
    }
    return helper(left.left, right.right) && helper(left.right, right.left);
  }

  public static void main(String[] args) {
    System.out.println(isSymmetric(TREE_A)); // false

//      1
//   2  |  2
//  3 4 | 4 3 // true
    System.out.println(isSymmetric(TREE_SYMMETRIC));

//      1
//    2 | 2
//     3|  3 // false
    System.out.println(isSymmetric(
        new TreeNode(1,
            new TreeNode(2,
                null, new TreeNode(3)),
            new TreeNode(2,
                null, new TreeNode(3)))
    ));
  }

}
