"""
Structure:
       [ 50 ]
      /      \
   [ 30 ]   [ 70 ]
  /    \    /    \
[ 20 ][ 40 ][ 60 ][ 80 ]
"""

class Node:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None


class BinarySearchTreeCustom:
    """Custom Binary Search Tree (BST) Implementation."""
    def __init__(self):
        self.root = None

    def insert(self, data):
        self.root = self._insert_rec(self.root, data)

    def _insert_rec(self, root, data):
        if not root:
            return Node(data)
        if data < root.data:
            root.left = self._insert_rec(root.left, data)
        elif data > root.data:
            root.right = self._insert_rec(root.right, data)
        return root

    def search(self, data):
        return self._search_rec(self.root, data)

    def _search_rec(self, root, data):
        if not root:
            return False
        if root.data == data:
            return True
        if data < root.data:
            return self._search_rec(root.left, data)
        return self._search_rec(root.right, data)

    def delete(self, data):
        self.root = self._delete_rec(self.root, data)

    def _delete_rec(self, root, data):
        if not root:
            return root

        if data < root.data:
            root.left = self._delete_rec(root.left, data)
        elif data > root.data:
            root.right = self._delete_rec(root.right, data)
        else:
            if not root.left:
                return root.right
            elif not root.right:
                return root.left

            root.data = self._min_value(root.right)
            root.right = self._delete_rec(root.right, root.data)
        return root

    def _min_value(self, root):
        minv = root.data
        while root.left:
            minv = root.left.data
            root = root.left
        return minv

    def inorder(self):
        self._inorder_rec(self.root)
        print()

    def _inorder_rec(self, root):
        if root:
            self._inorder_rec(root.left)
            print(f"{root.data} ", end="")
            self._inorder_rec(root.right)


if __name__ == "__main__":
    bst = BinarySearchTreeCustom()
    bst.insert(50)
    bst.insert(30)
    bst.insert(20)
    bst.insert(40)
    bst.insert(70)
    bst.insert(60)
    bst.insert(80)

    print("Inorder traversal: ", end="")
    bst.inorder()

    print("Search 40:", bst.search(40))
    print("Search 90:", bst.search(90))

    print("Delete 20")
    bst.delete(20)
    bst.inorder()

    print("Delete 30")
    bst.delete(30)
    bst.inorder()

    print("Delete 50")
    bst.delete(50)
    bst.inorder()
