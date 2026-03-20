
import java.util.LinkedList;
import java.util.Queue;

/**
 * Custom Binary Tree Implementation.
 * 
 * @param <T> the type of elements in this tree
 */
public class BinaryTreeCustom<T> {

    public static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        public Node(T data) {
            this.data = data;
        }
    }

    public void inorder(Node<T> node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    public void preorder(Node<T> node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public void postorder(Node<T> node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }

    public void levelOrder(Node<T> root) {
        if (root == null) return;
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> temp = queue.poll();
            System.out.print(temp.data + " ");
            if (temp.left != null) queue.add(temp.left);
            if (temp.right != null) queue.add(temp.right);
        }
    }

    public static void main(String[] args) {
        BinaryTreeCustom<Integer> tree = new BinaryTreeCustom<>();
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);

        System.out.print("Inorder: ");
        tree.inorder(root);
        System.out.println();

        System.out.print("Preorder: ");
        tree.preorder(root);
        System.out.println();

        System.out.print("Postorder: ");
        tree.postorder(root);
        System.out.println();

        System.out.print("Level Order: ");
        tree.levelOrder(root);
        System.out.println();
    }
}
