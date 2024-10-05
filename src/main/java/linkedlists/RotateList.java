package linkedlists;

import linkedlists.model.ListNode;

import static linkedlists.Utils.LIST_A;
import static linkedlists.Utils.printLL;

public class RotateList {

  public ListNode rotateRight(ListNode head, int k) {
    if (k == 0) return head;
    var size = getSize(head);
    if (size == 0) return head;
    var remainder = k % size;
    if (remainder == 0) return head;
    var newStart = size - remainder;

    var newHead = unlinkAndGetNewHead(head, newStart);
    moveToEndAndLinkTheOldHead(head, newHead);

    return newHead;
  }

  private static ListNode unlinkAndGetNewHead(ListNode head, int newStart) {
    var ptr = head;
    for (int i = 0; i < newStart - 1; i++) ptr = ptr.next;
    var newHead = ptr.next;
    ptr.next = null;
    return newHead;
  }

  private static void moveToEndAndLinkTheOldHead(ListNode head, ListNode newHead) {
    var ptr = newHead;
    while (ptr.next != null) ptr = ptr.next;
    ptr.next = head;
  }

  private static int getSize(ListNode head) {
    var ptr = head;
    var size = 0;
    for (; ptr != null; size++) ptr = ptr.next;
    return size;
  }

  /**
   * O(n) time & space
   * Returns the size and a "copy" holding the references to the original list nodes.
   */
  private static CopyAndSize getCopyAndSize(ListNode head) {
    if (head == null) {
      return new CopyAndSize(0, null);
    }
    var size = 1;
    var headCopy = new ListNode(head.val, head.next);
    var ptrCopy = headCopy;
    var ptr = head.next;
    for (; ptr != null; size++) {
      // ptrCopy.next = new ListNode(ptr.val, ptr.next); // actual clone
      ptrCopy.next = ptr; // reference the original list
      ptrCopy = ptrCopy.next;
      ptr = ptr.next;
    }
    return new CopyAndSize(size, headCopy);
  }

  private record CopyAndSize(int size, ListNode copy) {
  }

  public static void main(String[] args) {

    var instance = new RotateList();

    printLL(LIST_A, false);
    printLL(instance.rotateRight(LIST_A, 4), false);
  }

}
