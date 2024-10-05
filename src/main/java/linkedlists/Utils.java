package linkedlists;

import linkedlists.model.ListNode;
import linkedlists.model.Node;

import java.util.ArrayList;

public class Utils {


  public static void printLL(ListNode node, boolean withHashcode) {
    var a = new ArrayList<String>();
    while (node != null) {
      if (withHashcode) {
        a.add("%d %d".formatted(node.val, node.hashCode()));
      } else {
        a.add(String.valueOf(node.val));
      }
      node = node.next;
    }
    System.out.println(a);
  }

  public static void printLL(Node node) {
    var a = new ArrayList<Node>();
    while (node != null) {
      a.add(node);
      node = node.next;
    }
    System.out.println(a);
  }

  public static final ListNode LIST_A =
      new ListNode(1,
          new ListNode(2,
              new ListNode(6,
                  new ListNode(3,
                      new ListNode(4,
                          new ListNode(5,
                              new ListNode(6)))))));

}
