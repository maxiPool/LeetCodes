package trees;

import trees.model.TreeNode;

public final class Utils {

  public static TreeNode TREE_A;
  public static TreeNode TREE_B;
  public static TreeNode TREE_SYMMETRIC;
  public static TreeNode TREE_UNI_VALUE_51;
  public static TreeNode TREE_UNI_VALUE_5;

  static {
//    1
//       2
//      3
    TREE_A = new TreeNode(1);
    var treeA2 = new TreeNode(2);
    var treeA3 = new TreeNode(3);
    TREE_A.right = treeA2;
    treeA2.left = treeA3;

//     3
//  9     20
//      15  7
    TREE_B = new TreeNode(3);
    var B1 = new TreeNode(20);
    var B2 = new TreeNode(9);
    var B3 = new TreeNode(15);
    var B4 = new TreeNode(7);
    TREE_B.left = B2;
    TREE_B.right = B1;
    B1.left = B3;
    B1.right = B4;

//      1
//   2  |  2
//  3 4 | 4 3
    TREE_SYMMETRIC = new TreeNode(1,
        new TreeNode(2,
            new TreeNode(3), new TreeNode(4)),
        new TreeNode(2,
            new TreeNode(4), new TreeNode(3)));

//        5
//    1       5*
//  5*  5*  x   5* -> return 4
    TREE_UNI_VALUE_51 = new TreeNode(5,
        new TreeNode(1,
            new TreeNode(5), new TreeNode(5)),
        new TreeNode(5,
            null, new TreeNode(5)));
//        5*
//    5*      5*
//  5*  5*  x   5* -> return 6
    TREE_UNI_VALUE_5 = new TreeNode(5,
        new TreeNode(5,
            new TreeNode(5), new TreeNode(5)),
        new TreeNode(5,
            null, new TreeNode(5)));
  }

}
