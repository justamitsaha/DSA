class Node:
    def __init__(self, val):
        self.value = val
        self.next = None

class CustomFIFO:
    def __init__(self):
        self.head = None
        self.tail = None
        self._size = 0 # Renamed to _size to avoid conflict with the size() method
    
    def push(self,i):
        node = Node(i)
        if(self.head == None):
            self.head = node
            self.tail = node
        else :
            self.tail.next = node
            self.tail = node
        self._size +=1

    def pop(self):
        if(None == self.head):
            return None
        
        removedValue = self.head.value
        self.head = self.head.next
        if (self.head == None):
            self.tail = None
        self._size -=1
        return removedValue
    
    def peek(self):
        if (self.head == None):
            return None
        else :
            return self.head.value
        
    def rear(self):
        return self.tail.value if self.tail else None

    def isEmpty(self):
        return self.head == None
    
    def size(self):
        return self._size
    

    def clear(self):
        self.head = None
        self.tail = None
        self._size = 0

        
    def traverse(self):
        n = self.head
        while(n != None):
            print(n.value)
            n = n.next


queue = CustomFIFO()

queue.push(1)
queue.push(2)
queue.push(3)
queue.push(4)
queue.push(5)

print("Traverse:")
queue.traverse()

print(f"Front: {queue.peek()}")
print(f"Rear: {queue.rear()}")
print(f"Size: {queue.size()}")
print(f"Is Empty: {queue.isEmpty()}")

print(f"Dequeued: {queue.pop()}")
print(f"Dequeued: {queue.pop()}")

print("After dequeue:")
queue.traverse()

print(f"Front: {queue.peek()}")
print(f"Rear: {queue.rear()}")
print(f"Size: {queue.size()}")

queue.clear()
print(f"After clear, Is Empty: {queue.isEmpty()}")