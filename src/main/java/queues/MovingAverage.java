package queues;

import java.util.LinkedList;
import java.util.Queue;

public class MovingAverage {

  private final Queue<Integer> queue;
  private final int size;
  private int sum = 0;

  public MovingAverage(int size) {
    queue = new LinkedList<>();
    this.size = size;
  }

  public double next(int val) {
    queue.offer(val);
    sum += val;
    if (size > 0 && queue.size() > size) {
      sum -= queue.poll();
    }
    return sum / (double) queue.size();
  }

  public static void main(String[] args) {
    var a = new MovingAverage(3);

    System.out.println(a.next(1)); // 1.0
    System.out.println(a.next(10)); // 5.5
    System.out.println(a.next(3)); // 4.66667
    System.out.println(a.next(5)); // 6.0
  }

}
