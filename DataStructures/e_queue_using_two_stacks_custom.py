"""
Structure:
Inbound Stack:  [ 3 ]  Outbound Stack: [   ]
                [ 2 ]                  [   ]
                [ 1 ]                  [   ]
(To dequeue, pop from Inbound and push to Outbound)
"""

class QueueUsingTwoStacksCustom:
    """Custom Queue Implementation using two Stacks."""
    def __init__(self):
        self.stack1 = []
        self.stack2 = []

    def enqueue(self, data):
        self.stack1.append(data)

    def dequeue(self):
        if self.is_empty():
            return None
        if not self.stack2:
            while self.stack1:
                self.stack2.append(self.stack1.pop())
        return self.stack2.pop()

    def peek(self):
        if self.is_empty():
            return None
        if not self.stack2:
            while self.stack1:
                self.stack2.append(self.stack1.pop())
        return self.stack2[-1]

    def is_empty(self):
        return len(self.stack1) == 0 and len(self.stack2) == 0

    def size(self):
        return len(self.stack1) + len(self.stack2)


if __name__ == "__main__":
    q = QueueUsingTwoStacksCustom()
    q.enqueue(1)
    q.enqueue(2)
    q.enqueue(3)
    print("Dequeued:", q.dequeue())
    q.enqueue(4)
    print("Dequeued:", q.dequeue())
    print("Peek:", q.peek())
