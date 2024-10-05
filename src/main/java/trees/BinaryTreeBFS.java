package trees;

import trees.model.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static trees.Utils.TREE_B;

public class BinaryTreeBFS {

  public static List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) return Collections.emptyList();
    var result = new ArrayList<List<Integer>>();
    levelOrderHelper(List.of(root), result);
    return result;
  }

  private static void levelOrderHelper(List<TreeNode> queue, List<List<Integer>> outResult) {
    if (queue.isEmpty()) return;
    outResult.add(queue.stream().map(e -> e.val).toList());

    var newQueue = new ArrayList<TreeNode>();
    queue.forEach(e -> {
      if (e.left != null) newQueue.add(e.left);
      if (e.right != null) newQueue.add(e.right);
    });

    levelOrderHelper(newQueue, outResult);
  }

  private static void levelOrderHelper2(List<TreeNode> queue, List<List<Integer>> outResult) {
    if (queue.isEmpty()) return;
    outResult.add(queue.stream().map(e -> e.val).toList());

    levelOrderHelper2(
        queue.stream()
            .flatMap(e -> Stream.of(e.left, e.right))
            .filter(Objects::nonNull)
            .toList(),
        outResult);
  }

  public static void main(String[] args) {
    System.out.println(levelOrder(TREE_B));
  }

}
