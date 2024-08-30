package bst;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<E> {

    BinaryNode<E> root;  // Används också i BSTVisaulizer
    int size;            // Används också i BSTVisaulizer
    private Comparator<E> ccomparator;

    public static void main(String[] args) {
		BinarySearchTree<Integer> treeA = new BinarySearchTree<Integer>();
		BinarySearchTree<Integer> treeB = new BinarySearchTree<Integer>();
		BSTVisualizer v = new BSTVisualizer("Bin Tree", 500, 500);
		
		treeA.add(50);
		treeA.add(40);
		treeA.add(60);
		treeA.add(55);
		treeA.add(70);
		treeA.add(39);
		treeA.add(46);
		treeA.printTree();
		v.drawTree(treeA);
		
        /*
		treeB.add(10);
		treeB.add(20);
		treeB.add(30);
		treeB.add(40);
		treeB.add(50);
		treeB.add(1);
		treeB.add(2);
		treeB.printTree();
		v.drawTree(treeB);
		treeB.rebuild();
		v.drawTree(treeB); */
	}

    /**
     * Constructs an empty binary search tree.
     */
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
        this.ccomparator = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * Constructs an empty binary search tree, sorted according to the specified
     * comparator.
     */
    public BinarySearchTree(Comparator<E> comparator) {
        this.root = null;
        this.size = 0;
        this.ccomparator = comparator;
    }

    /**
     * Inserts the specified element in the tree if no duplicate exists.
     *
     * @param x element to be inserted
     * @return true if the the element was inserted
     */
    public boolean add(E x) {
        if (root == null) {
            root = new BinaryNode<>(x);
            size++;
            return true;
        }
        return add(root, x);
    }

    private boolean add(BinaryNode<E> parent, E x) {
        int compareResult = ccomparator.compare(x, parent.element);

        if (compareResult == 0) {
            return false; // dupe found >:o
        }

        if (compareResult > 0) {
            if (parent.right != null) {
                return add(parent.right, x);
            }
            parent.right = new BinaryNode<>(x);
            size++;
            return true;
        }

        if (parent.left != null) {
            return add(parent.left, x);
        }
        parent.left = new BinaryNode<>(x);
        size++;
        return true;
    }

    /**
     * Computes the height of tree.
     *
     * @return the height of the tree
     */
    public int height() {
        return height(root);
    }

    private int height(BinaryNode<E> n) {
        if (n == null) {
            return 0;
        }

        int leftHeight = height(n.left);
        int rightHeight = height(n.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Returns the number of elements in this tree.
     *
     * @return the number of elements in this tree
     */
    public int size() {
        return size;
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Print tree contents in inorder.
     */
    public void printTree() {
        printTree(root);

    }

    private void printTree(BinaryNode<E> n) {
        if (n != null) {
            printTree(n.left);
            System.out.print(n.element);
            printTree(n.right);
        }
    }

    /**
     * Builds a complete tree from the elements in the tree.
     */
    public void rebuild() {
        ArrayList<E> sorted = new ArrayList<E>();
        toArray(root, sorted);
        root = buildTree(sorted, 0, sorted.size() - 1);
    }

    /*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
     */
    private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
        if (n != null) {
            toArray(n.left, sorted);
            sorted.add(n.element);
            toArray(n.right, sorted);
        }
    }

    /*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
     */
    private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
        if (first > last) {
            return null;
        }
        int mid = (first + last) / 2;
        BinaryNode<E> node = new BinaryNode<E>(sorted.get(mid));
        node.left = buildTree(sorted, first, mid - 1);
        node.right = buildTree(sorted, mid + 1, last);
        return node;
    }

    static class BinaryNode<E> {

        E element;
        BinaryNode<E> left;
        BinaryNode<E> right;

        private BinaryNode(E element) {
            this.element = element;
        }
    }

}
