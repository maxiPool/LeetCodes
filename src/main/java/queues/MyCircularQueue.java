package queues;

public class MyCircularQueue {

  private Integer[] internalQ;
  private Integer headIndex;
  private Integer tailIndex;
  private int size = 0;

  public MyCircularQueue(int k) {
    internalQ = new Integer[k];
    tailIndex = headIndex = null;
  }

  public boolean enQueue(int value) {
    if (size >= internalQ.length) {
      return false;
    }
    if (tailIndex == null) {
      tailIndex = -1;
    }
    if (headIndex == null) {
      headIndex = 0;
    }
    tailIndex = (tailIndex + 1) % internalQ.length;
    internalQ[tailIndex] = value;
    ++size;
    return true;
  }

  public boolean deQueue() {
    if (size <= 0 || headIndex == null) {
      return false;
    }
    internalQ[headIndex] = null;
    headIndex = (headIndex + 1) % internalQ.length;
    --size;
    if (size == 0) {
      headIndex = tailIndex = null;
    }
    return true;
  }

  /**
   * Front item of the queue; the next one that will be dequeued.
   */
  public int Front() {
    return headIndex == null ? -1 : internalQ[headIndex];
  }

  public int Rear() {
    return tailIndex == null ? -1 : internalQ[tailIndex];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == internalQ.length;
  }

  public static void main(String[] args) {
    MyCircularQueue myCircularQueue = new MyCircularQueue(3);
    System.out.println(myCircularQueue.enQueue(1)); // return True
    System.out.println(myCircularQueue.enQueue(2)); // return True
    System.out.println(myCircularQueue.enQueue(3)); // return True
    System.out.println(myCircularQueue.enQueue(4)); // return False
    System.out.println(myCircularQueue.Rear());     // return 3
    System.out.println(myCircularQueue.isFull());   // return True
    System.out.println(myCircularQueue.deQueue());  // return True
    System.out.println(myCircularQueue.enQueue(4)); // return True
    System.out.println(myCircularQueue.Rear());     // return 4
  }

}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 * <p>
 * Constraints:
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * At most 3000 calls will be made to enQueue, deQueue, Front, Rear, isEmpty, and isFull.
 */
