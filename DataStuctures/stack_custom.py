class ArrayStack:
    """Array-based implementation of Stack."""
    def __init__(self, capacity):
        self.capacity = capacity
        self.stack = []

    def push(self, value):
        if self.is_full():
            raise OverflowError("Stack is full")
        self.stack.append(value)

    def pop(self):
        if self.is_empty():
            raise IndexError("pop from empty stack")
        return self.stack.pop()

    def peek(self):
        if self.is_empty():
            raise IndexError("peek from empty stack")
        return self.stack[-1]

    def is_empty(self):
        return len(self.stack) == 0

    def is_full(self):
        return len(self.stack) == self.capacity

    def size(self):
        return len(self.stack)

    def display(self):
        print("Array Stack:", self.stack)


class Node:
    def __init__(self, data):
        self.data = data
        self.next = None


class LinkedStack:
    """Linked List-based implementation of Stack."""
    def __init__(self):
        self.top = None
        self._size = 0

    def push(self, value):
        new_node = Node(value)
        new_node.next = self.top
        self.top = new_node
        self._size += 1

    def pop(self):
        if self.is_empty():
            raise IndexError("pop from empty stack")
        value = self.top.data
        self.top = self.top.next
        self._size -= 1
        return value

    def peek(self):
        if self.is_empty():
            raise IndexError("peek from empty stack")
        return self.top.data

    def is_empty(self):
        return self.top is None

    def size(self):
        return self._size

    def display(self):
        print("Linked Stack: ", end="")
        current = self.top
        while current:
            print(f"{current.data} -> ", end="")
            current = current.next
        print("None")


if __name__ == "__main__":
    print("--- Testing Array Stack ---")
    as_stack = ArrayStack(5)
    as_stack.push(10)
    as_stack.push(20)
    as_stack.push(30)
    as_stack.display()
    print("Popped:", as_stack.pop())
    as_stack.display()

    print("\n--- Testing Linked Stack ---")
    ls_stack = LinkedStack()
    ls_stack.push("A")
    ls_stack.push("B")
    ls_stack.push("C")
    ls_stack.display()
    print("Peek:", ls_stack.peek())
    print("Popped:", ls_stack.pop())
    ls_stack.display()
