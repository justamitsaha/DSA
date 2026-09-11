"""
Structure:
Queue 1: [ 1 | 2 | 3 ]
Queue 2: [           ]
(To push, add to non-empty queue. To pop, move all but last to other queue)
"""

from collections import deque

class StackUsingTwoQueuesCustom:
    """Custom Stack Implementation using two Queues."""
    def __init__(self):
        self.q1 = deque()
        self.q2 = deque()

    def push(self, data):
        self.q2.append(data)
        while self.q1:
            self.q2.append(self.q1.popleft())
        self.q1, self.q2 = self.q2, self.q1

    def pop(self):
        if self.is_empty():
            return None
        return self.q1.popleft()

    def peek(self):
        if self.is_empty():
            return None
        return self.q1[0]

    def is_empty(self):
        return len(self.q1) == 0

    def size(self):
        return len(self.q1)


if __name__ == "__main__":
    s = StackUsingTwoQueuesCustom()
    s.push(1)
    s.push(2)
    s.push(3)
    print("Popped:", s.pop())
    print("Peek:", s.peek())
    s.push(4)
    print("Popped:", s.pop())
