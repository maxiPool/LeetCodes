package linkedlists;

import linkedlists.model.Node;

import static linkedlists.Utils.*;

public class CopyRandomList {

  public Node copyRandomList(Node head) {
    if (head == null) return null;
    interweave(head);
    assignRandomCopies(head);
    return undoInterweaving(head);
  }

  private void interweave(Node head) {
    var ptr = head;
    while (ptr != null) {
      var copy = new Node(ptr.val);
      copy.next = ptr.next;
      copy.random = ptr.random;
      ptr.next = copy;
      ptr = copy.next;
    }
  }

  private void assignRandomCopies(Node head) {
    var ptr = head;
    while (ptr != null) {
      if (ptr.next.random != null) {
        ptr.next.random = ptr.next.random.next;
      }
      ptr = ptr.next.next;
    }
  }

  private Node undoInterweaving(Node originalHead) {
    var ptr = originalHead;
    var copyHead = originalHead.next;
    var copyPtr = copyHead;
    while (copyPtr != null && ptr != null) {
      var nextOriginal = copyPtr.next;
      ptr.next = nextOriginal;
      ptr = nextOriginal;
      if (nextOriginal != null) {
        var nextCopy = copyPtr.next.next;
        copyPtr.next = nextCopy;
        copyPtr = nextCopy;
      }
    }
    return copyHead;
  }

  public static void main(String[] args) {

//    [[7,null],[13,0],[11,4],[10,2],[1,0]]

    var a = new Node(7);
    var b = new Node(13);
    var c = new Node(11);
    var d = new Node(4);
    var e = new Node(1);
    a.next = b;
    b.next = c;
    b.random = a;
    c.next = d;
    c.random = e;
    d.next = e;
    d.random = c;
    e.random = a;
    printLL(a);
    var instance = new CopyRandomList();

    Node node = instance.copyRandomList(a);
    printLL(node);
  }

}
