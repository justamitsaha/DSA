class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

class SinglyLinkedList:
    def __init__(self):
        self.head = None

    # O(1) Operation
    def add_at_front(self, data):
        new_node = Node(data)
        new_node.next = self.head
        self.head = new_node

    def display(self):
        current = self.head
        while current:
            print(f"{current.data} -> ", end="")
            current = current.next
        print("None")

# Usage
ll = SinglyLinkedList()
ll.add_at_front(10)
ll.add_at_front(20)
ll.add_at_front(30)
ll.display() # Output: 30 -> 20 -> 10 -> None