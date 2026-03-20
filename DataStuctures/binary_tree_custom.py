from collections import deque

class Node:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None


class BinaryTreeCustom:
    """Custom Binary Tree Implementation."""
    def inorder(self, node):
        if not node:
            return
        self.inorder(node.left)
        print(f"{node.data} ", end="")
        self.inorder(node.right)

    def preorder(self, node):
        if not node:
            return
        print(f"{node.data} ", end="")
        self.preorder(node.left)
        self.preorder(node.right)

    def postorder(self, node):
        if not node:
            return
        self.postorder(node.left)
        self.postorder(node.right)
        print(f"{node.data} ", end="")

    def level_order(self, root):
        if not root:
            return
        queue = deque([root])
        while queue:
            node = queue.popleft()
            print(f"{node.data} ", end="")
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)


if __name__ == "__main__":
    tree = BinaryTreeCustom()
    root = Node(1)
    root.left = Node(2)
    root.right = Node(3)
    root.left.left = Node(4)
    root.left.right = Node(5)

    print("Inorder: ", end="")
    tree.inorder(root)
    print()

    print("Preorder: ", end="")
    tree.preorder(root)
    print()

    print("Postorder: ", end="")
    tree.postorder(root)
    print()

    print("Level Order: ", end="")
    tree.level_order(root)
    print()
