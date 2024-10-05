package trees;

import trees.model.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static trees.Utils.TREE_A;

/**
 * Pre order = root, left, right
 * In order = left root right
 * Post order = left right root
 */
public class BinaryTreeAllOrdersDFS {

  public static List<Integer> preorderTraversal(TreeNode root) {
    var result = new ArrayList<Integer>();
    preOrderHelper(root, result);
    return result;
  }

  private static void preOrderHelper(TreeNode root, List<Integer> outResult) {
    if (root == null) {
      return;
    }
    outResult.add(root.val);
    if (root.left != null) {
      preOrderHelper(root.left, outResult);
    }
    if (root.right != null) {
      preOrderHelper(root.right, outResult);
    }
  }

  /**
   * When a problem is easy to solve recursively using the call stack,
   * then it's easy to do an iterative version by using a Stack data structure.
   * This is what we show down here:
   */
  public static List<Integer> preorderTraversalIterative(TreeNode root) {
    if (root == null) {
      return Collections.emptyList();
    }

    var result = new ArrayList<Integer>();
    var stack = new Stack<TreeNode>();
    stack.push(root);

    while (!stack.isEmpty()) {
      var current = stack.pop();
      result.add(current.val);
      // right is pushed 1st so that left is processed 1st
      if (current.right != null) {
        // when you push this guy on the stack, it's kin to a recursive call
        // because when its turn happens, it will also call everything in this loop.
        stack.push(current.right);
      }
      if (current.left != null) stack.push(current.left);
    }

    return result;
  }

  /**
   * LEFT ROOT RIGHT
   */
  public static List<Integer> inorderTraversal(TreeNode root) {
    if (root == null) return Collections.emptyList();

    var result = new ArrayList<Integer>();

    var stack = new Stack<TreeNode>();
    var curr = root;

    while (curr != null || !stack.isEmpty()) {
      while (curr != null) {
        // save a pointer to the current node on the stack before traversing the left subtree
        // this has the same effect the callstack has when doing recursion:
        // it saves the values we want to pass to our recursive function on the stack
        // and pops them back when we come back to this call.
        stack.push(curr); // pushes each parent on the stack to keep a ref to it to come back for it later.
        curr = curr.left;
      }
      // left subtree done
      curr = stack.pop();
      result.add(curr.val); // save the parent's value
      // root done
      // now onto the right subtree to continue the loop.
      curr = curr.right;
    }
    return result;
  }

  public static List<Integer> inorderTraversalRec(TreeNode root) {
    var result = new ArrayList<Integer>();
    inorderTraversalRecHelper(root, result);
    return result;
  }

  private static void inorderTraversalRecHelper(TreeNode root, List<Integer> result) {
    if (root == null) return;
    if (root.left != null) {
      inorderTraversalRecHelper(root.left, result);
    }
    result.add(root.val);
    if (root.right != null) {
      inorderTraversalRecHelper(root.right, result);
    }
  }

  /**
   * Right, Root, Left
   */
  public static List<Integer> postorderTraversal(TreeNode root) {
    if (root == null) {
      return Collections.emptyList();
    }

    var result = new ArrayList<Integer>();
    var stack = new Stack<TreeNode>();

    TreeNode previous = null;
    var curr = root;
    while (previous != curr) {
      previous = curr;
      while (curr != null) {
        stack.push(curr);
        curr = curr.right;
      }
      curr = stack.peek();
      if (curr.left != null) {
        curr = curr.left;
      }
    }

    while(!stack.isEmpty()) {
      result.add(stack.pop().val);
    }
    return result;
  }

  public static List<Integer> postorderTraversalRec(TreeNode root) {
    var result = new ArrayList<Integer>();
    postorderTraversalRecHelper(root, result);
    return result;
  }

  private static void postorderTraversalRecHelper(TreeNode root, List<Integer> result) {
    if (root == null) return;
    if (root.left != null) {
      postorderTraversalRecHelper(root.left, result);
    }
    if (root.right != null) {
      postorderTraversalRecHelper(root.right, result);
    }
    result.add(root.val);
  }

  public static void main(String[] args) {
//    System.out.println("PRE order: " + preorderTraversal(TREE_A));
//    System.out.println("IN order: " + inorderTraversal(TREE_A));
    System.out.println("POST order: " + postorderTraversalRec(TREE_A));
    System.out.println("POST order: " + postorderTraversalRec(new TreeNode(1, null, new TreeNode(2))));
  }

}
