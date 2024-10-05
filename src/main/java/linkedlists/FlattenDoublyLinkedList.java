package linkedlists;

import java.util.HashMap;
import java.util.Map;

public class FlattenDoublyLinkedList {

  public final Map<Integer, Integer> valueAndFrequency = new HashMap<>();

  public Node flatten(Node node) {
    return flattenHelper(node, new Node[1]);
  }

  public Node flattenHelper(Node node, Node[] last) {
    if (node != null) {
      valueAndFrequency.put(node.val, valueAndFrequency.getOrDefault(node.val, 0) + 1);
    }

    if (node == null || node.child == null && node.next == null) {
      if (node != null) last[0] = node;
      return node;
    }
    var lastOfChildsList = node;
    Node childsList = null;
    if (node.child != null) {
      childsList = flattenHelper(node.child, last);
      lastOfChildsList = last[0];
//      while (lastOfChildsList.next != null) {
//        lastOfChildsList = lastOfChildsList.next; // ptr to last node of child's list
//
//        valueAndFrequency.put(lastOfChildsList.val, valueAndFrequency.getOrDefault(lastOfChildsList.val, 0) + 1);
//      }
    }

    var nextsList = flattenHelper(node.next, last);
    if (childsList != null) {
      node.next = childsList;
      childsList.prev = node;
    }
    lastOfChildsList.next = nextsList;
    if (nextsList != null) {
      nextsList.prev = lastOfChildsList;
    }
    node.child = null;
    return node;
  }


  public static void main(String[] args) {
    var instance = new FlattenDoublyLinkedList();
    var ex2 = instance.flatten(EX3);
  }

  private static Node EX2;

  private static Node EX3;

  static {
    EX2 = new Node(1, new Node(2), new Node(3));

    EX3 = new Node(1);
    var n2 = new Node(2);
    var n3 = new Node(3);
    var n4 = new Node(4);
    var n5 = new Node(5);
    var n6 = new Node(6);
    var n7 = new Node(7);
    var n8 = new Node(8);
    var n9 = new Node(9);
    var n10 = new Node(10);
    var n11 = new Node(11);
    var n12 = new Node(12);

    EX3.next = n2;
    n2.prev = EX3;
    n2.child = n3;
    n3.child = n4;
    n4.child = n5;
    n5.child = n6;
    n6.child = n7;
    n7.child = n8;
    n8.child = n9;
    n9.child = n10;
    n10.next = n11;
    n11.next = n12;

    n11.prev = n10;
    n12.prev = n11;
  }

  static class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node(int val, Node next, Node child) {
      this.val = val;
      this.next = next;
      this.child = child;
    }

    public Node(int val, Node next) {
      this.val = val;
      this.next = next;
    }

    public Node(int val) {
      this.val = val;
    }

    public Node() {
    }
  }

}
