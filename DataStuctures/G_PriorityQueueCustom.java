/*
Structure:
(Heap Representation)
      [ 10 ]
     /      \
  [ 7 ]    [ 8 ]
 /    \
[ 5 ] [ 2 ]
*/

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Custom Priority Queue Implementation using Min and Max Heaps.
 * 
 * @param <T> the type of elements in this priority queue
 */
public class G_PriorityQueueCustom<T extends Comparable<T>> {

    public static class Heap<T extends Comparable<T>> {
        private ArrayList<T> list;
        private boolean isMinHeap;

        public Heap(boolean isMinHeap) {
            this.list = new ArrayList<>();
            this.isMinHeap = isMinHeap;
        }

        public void insert(T value) {
            list.add(value);
            upHeap(list.size() - 1);
        }

        public T remove() {
            if (list.isEmpty()) return null;
            T root = list.get(0);
            T last = list.remove(list.size() - 1);
            if (!list.isEmpty()) {
                list.set(0, last);
                downHeap(0);
            }
            return root;
        }

        private void upHeap(int index) {
            if (index == 0) return;
            int parent = (index - 1) / 2;
            if (compare(list.get(index), list.get(parent)) < 0) {
                swap(index, parent);
                upHeap(parent);
            }
        }

        private void downHeap(int index) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < list.size() && compare(list.get(left), list.get(smallest)) < 0) {
                smallest = left;
            }
            if (right < list.size() && compare(list.get(right), list.get(smallest)) < 0) {
                smallest = right;
            }

            if (smallest != index) {
                swap(index, smallest);
                downHeap(smallest);
            }
        }

        private int compare(T a, T b) {
            if (isMinHeap) return a.compareTo(b);
            else return b.compareTo(a);
        }

        private void swap(int i, int j) {
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        public int size() {
            return list.size();
        }

        public void display() {
            System.out.println(list);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Min Heap ---");
        Heap<Integer> minHeap = new Heap<>(true);
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(20);
        minHeap.insert(2);
        minHeap.display();
        System.out.println("Removed: " + minHeap.remove());
        minHeap.display();

        System.out.println("\n--- Max Heap ---");
        Heap<Integer> maxHeap = new Heap<>(false);
        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(20);
        maxHeap.insert(2);
        maxHeap.display();
        System.out.println("Removed: " + maxHeap.remove());
        maxHeap.display();
    }
}
