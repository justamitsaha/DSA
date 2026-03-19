class Node:
    def __init__(self, val):
        self.value = val
        self.next = None

class CustomFIFO:
    def __init__(self):
        self.head = None
        self.tail = None
    
    def addItem(self,i):
        node = Node(i)
        if(self.head == None):
            self.head = node
            self.tail = node
        else :
            self.tail.next = node
            self.tail = node
        
    def getItems(self):
        n = self.head
        while(n != None):
            print(n.value)
            n = n.next


c = CustomFIFO()
c.addItem(1)
c.addItem(2)
c.addItem(3)
c.addItem(4)
c.addItem(5)

c.getItems()

    

        