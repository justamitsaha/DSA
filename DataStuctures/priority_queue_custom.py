class Heap:
    """Custom Heap Implementation for Priority Queue."""
    def __init__(self, is_min_heap=True):
        self.heap = []
        self.is_min_heap = is_min_heap

    def insert(self, value):
        self.heap.append(value)
        self._up_heap(len(self.heap) - 1)

    def remove(self):
        if not self.heap:
            return None
        if len(self.heap) == 1:
            return self.heap.pop()
        
        root = self.heap[0]
        self.heap[0] = self.heap.pop()
        self._down_heap(0)
        return root

    def _up_heap(self, index):
        if index == 0:
            return
        parent = (index - 1) // 2
        if self._compare(self.heap[index], self.heap[parent]):
            self._swap(index, parent)
            self._up_heap(parent)

    def _down_heap(self, index):
        left = 2 * index + 1
        right = 2 * index + 2
        smallest_or_largest = index

        if left < len(self.heap) and self._compare(self.heap[left], self.heap[smallest_or_largest]):
            smallest_or_largest = left
        if right < len(self.heap) and self._compare(self.heap[right], self.heap[smallest_or_largest]):
            smallest_or_largest = right

        if smallest_or_largest != index:
            self._swap(index, smallest_or_largest)
            self._down_heap(smallest_or_largest)

    def _compare(self, val1, val2):
        if self.is_min_heap:
            return val1 < val2
        else:
            return val1 > val2

    def _swap(self, i, j):
        self.heap[i], self.heap[j] = self.heap[j], self.heap[i]

    def is_empty(self):
        return len(self.heap) == 0

    def size(self):
        return len(self.heap)

    def display(self):
        print(self.heap)


if __name__ == "__main__":
    print("--- Min Heap ---")
    min_h = Heap(is_min_heap=True)
    min_h.insert(10)
    min_h.insert(5)
    min_h.insert(20)
    min_h.insert(2)
    min_h.display()
    print("Removed:", min_h.remove())
    min_h.display()

    print("\n--- Max Heap ---")
    max_h = Heap(is_min_heap=False)
    max_h.insert(10)
    max_h.insert(5)
    max_h.insert(20)
    max_h.insert(2)
    max_h.display()
    print("Removed:", max_h.remove())
    max_h.display()
