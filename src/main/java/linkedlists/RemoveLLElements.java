package linkedlists;

import linkedlists.model.ListNode;

import static linkedlists.Utils.printLL;

public class RemoveLLElements {

  public ListNode removeElements(ListNode head, int value) {
    var ptr = head;
    ListNode previous = null;

    while (ptr != null) {
      if (ptr == head && ptr.val == value) {
        head = ptr.next;
      } else if (ptr.val == value) {
        do {
          previous.next = ptr.next;
          ptr = ptr.next;
        }
        while (ptr != null && ptr.val == value);
      }
      previous = ptr;
      ptr = ptr == null ? null : ptr.next;
    }
    return head;
  }

  public static ListNode LIST_B = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));

  public static void main(String[] args) {
    var a = new RemoveLLElements();

    printLL(a.removeElements(LIST_B, 2), false);
  }

}
