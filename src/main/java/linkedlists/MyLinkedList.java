package linkedlists;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class MyLinkedList {

  class Node {
    public int val;
    public Node next;
    public Node previous;

    public Node(int val) {
      this.val = val;
    }
  }

  public Node head;
  public int length;

  public MyLinkedList() {
  }

  public int get(int index) {
    return getNodeAt(index)
        .map(n -> n.val)
        .orElse(-1);
  }

  private Optional<Node> getNodeAt(int index) {
    if (index >= length || index < 0) {
      return empty();
    }
    var ptr = head;
    if (index < length / 2)
      for (var i = 0; i < index; i++) ptr = ptr.next;
    else
      for (var i = 0; i < length - index; i++) ptr = ptr.previous;
    return of(ptr);
  }

  public void addAtHead(int val) {
    helper(newHead -> {
      head.previous.next = newHead;
      newHead.next = head;
      newHead.previous = head.previous;
      head.previous = newHead;
      head = newHead;
    }, val);
  }

  public void addAtTail(int val) {
    helper(newTail -> {
      var oldTail = head.previous;
      head.previous = newTail;
      oldTail.next = newTail;
      newTail.next = head;
      newTail.previous = oldTail;
    }, val);
  }

  private void helper(Consumer<Node> fun, int val) {
    var node = new Node(val);
    if (length == 0) {
      head = node;
      head.next = node;
      head.previous = node;
    } else {
      fun.accept(node);
    }
    length++;
  }

  public void addAtIndex(int index, int val) {
    if (index == 0) {
      addAtHead(val);
    } else if (index == length) {
      addAtTail(val);
    } else {
      getNodeAt(index)
          .map(nthNode -> {
            var newNode = new Node(val);
            newNode.previous = nthNode.previous;
            newNode.next = nthNode;
            nthNode.previous.next = newNode;
            nthNode.previous = newNode;
            ++length;
            return null;
          });
    }
  }

  public void deleteAtIndex(int index) {
    getNodeAt(index)
        .map(nthNode -> {
          var prev = nthNode.previous;
          var next = nthNode.next;
          if (head == nthNode) {
            head = next;
          }
          prev.next = next;
          next.previous = prev;
          --length;
          return null;
        });
  }


  public static void main(String[] args) {

    var a = new MyLinkedList();
    a.addAtHead(2); // 2
    printLL(a.head);
    a.deleteAtIndex(1); // 2
    printLL(a.head);
    a.addAtHead(2); // 2 2
    printLL(a.head);
    a.addAtHead(7); // 7 2 2
    printLL(a.head);
    a.addAtHead(3); // 3 7 2 2
    printLL(a.head);
    a.addAtHead(2); // 2 3 7 2 2
    printLL(a.head);
    a.addAtHead(5); // 5 2 3 7 2 2
    printLL(a.head);
    a.addAtTail(5); // 5 2 3 7 2 2 5
    printLL(a.head);
    a.get(5); // output 5 expected 2; 2
    a.deleteAtIndex(6); // 5 2 3 7 2 2
    a.deleteAtIndex(4); // 5 2 3 7 2
  }

  public static void printLL(Node head) {
    if (head == null) {
      System.out.println("null head");
      return;
    }
    var a = new ArrayList<Integer>();
    var ptr = head;
    do {
      a.add(ptr.val);
      ptr = ptr.next;
    } while (ptr != head);
    System.out.println(a);
  }

}
