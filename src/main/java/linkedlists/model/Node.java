package linkedlists.model;

public class Node {
  public int val;
  public Node next;
  public Node random;

  public Node(int val) {
    this.val = val;
    this.next = null;
    this.random = null;
  }

  public String toString() {
    return "<%d, %s>".formatted(val, random == null ? "null" : random.hashCode());
  }

}