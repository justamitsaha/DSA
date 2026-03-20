class Node:
    def __init__(self, data):
        self.data = data
        self.prev = None
        self.next = None


class DoublyLinkedListCustom:
    """Custom Doubly Linked List Implementation."""
    def __init__(self):
        self.head = None
        self.tail = None
        self._size = 0

    def add_first(self, data):
        new_node = Node(data)
        if self.is_empty():
            self.head = self.tail = new_node
        else:
            new_node.next = self.head
            self.head.prev = new_node
            self.head = new_node
        self._size += 1

    def add_last(self, data):
        new_node = Node(data)
        if self.is_empty():
            self.head = self.tail = new_node
        else:
            self.tail.next = new_node
            new_node.prev = self.tail
            self.tail = new_node
        self._size += 1

    def remove_first(self):
        if self.is_empty():
            return None
        data = self.head.data
        if self.head == self.tail:
            self.head = self.tail = None
        else:
            self.head = self.head.next
            self.head.prev = None
        self._size -= 1
        return data

    def remove_last(self):
        if self.is_empty():
            return None
        data = self.tail.data
        if self.head == self.tail:
            self.head = self.tail = None
        else:
            self.tail = self.tail.prev
            self.tail.next = None
        self._size -= 1
        return data

    def is_empty(self):
        return self._size == 0

    def size(self):
        return self._size

    def display_forward(self):
        print("Forward: ", end="")
        current = self.head
        while current:
            print(f"{current.data} <-> ", end="")
            current = current.next
        print("None")

    def display_backward(self):
        print("Backward: ", end="")
        current = self.tail
        while current:
            print(f"{current.data} <-> ", end="")
            current = current.prev
        print("None")


if __name__ == "__main__":
    dll = DoublyLinkedListCustom()
    dll.add_last(10)
    dll.add_last(20)
    dll.add_first(5)
    dll.display_forward()
    dll.display_backward()
    print("Removed Last:", dll.remove_last())
    dll.display_forward()
