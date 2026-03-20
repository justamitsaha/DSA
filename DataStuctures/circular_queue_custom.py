class CircularQueueCustom:
    """Custom Circular Queue Implementation."""
    def __init__(self, capacity):
        self.capacity = capacity
        self.queue = [None] * capacity
        self.front = -1
        self.rear = -1
        self._size = 0

    def enqueue(self, value):
        if self.is_full():
            print("Queue is Full")
            return False
        if self.is_empty():
            self.front = 0
        self.rear = (self.rear + 1) % self.capacity
        self.queue[self.rear] = value
        self._size += 1
        return True

    def dequeue(self):
        if self.is_empty():
            print("Queue is Empty")
            return None
        value = self.queue[self.front]
        self.queue[self.front] = None
        if self.front == self.rear:
            self.front = -1
            self.rear = -1
        else:
            self.front = (self.front + 1) % self.capacity
        self._size -= 1
        return value

    def peek(self):
        if self.is_empty():
            return None
        return self.queue[self.front]

    def is_empty(self):
        return self._size == 0

    def is_full(self):
        return self._size == self.capacity

    def size(self):
        return self._size

    def display(self):
        if self.is_empty():
            print("Queue is empty")
            return
        print("Circular Queue: ", end="")
        i = self.front
        while True:
            print(f"{self.queue[i]} ", end="")
            if i == self.rear:
                break
            i = (i + 1) % self.capacity
        print()


if __name__ == "__main__":
    q = CircularQueueCustom(5)
    q.enqueue(10)
    q.enqueue(20)
    q.enqueue(30)
    q.enqueue(40)
    q.display()
    print("Dequeued:", q.dequeue())
    q.enqueue(50)
    q.enqueue(60)
    q.display()
