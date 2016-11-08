/* 
 * EE422C Quiz 9 submission by
 * Brent Atchison
 * 16445
 * bma862
 */

package quiz9.bst;

/**
 * <p>
 * In this quiz, you are asked to implement Binary Search Tree using iteration.
 * Recursion is not allowed for any methods in this quiz.
 * </p>
 * <p>
 * Replacing TODO with your implementation for add and find methods. Don't
 * change any other things, including package, class, or method declaration.
 * Your submission should not have any generic warnings.
 * </p>
 * <p>
 * remove() is not required, yet if you can finish remove() methods, we give you
 * 3 extra points.
 * </p>
 * <p>
 * Include your name in file header. Zip your quiz9 folder and name it as quiz9_
 * <EID>.zip.
 * </p>
 */
public class BSearchTree<E extends Comparable<E>> {

	private BSTNode<E> root;

	public BSearchTree() {
		this.root = null;
	}

	/**
	 * BST insert method
	 * 
	 * @param root
	 *            BST root node
	 * @param value
	 *            the inserted value, allow duplicate values.
	 * @return BST root node
	 */
	public BSTNode<E> add(E value) {
		BSTNode<E> newNode = new BSTNode<E>();
		newNode.setData(value);
		
		/* Root doesn't exist */
        if (root == null) {
            root = newNode;
            return root;
        }
        
        /* Root exists, need to place properly */
        BSTNode<E> parent = null;
        BSTNode<E> newerNode = root;
        while (newerNode != null) {
            parent = newerNode;
            int ret = value.compareTo(newerNode.data);
            if (ret < 0) { newerNode = newerNode.left; }
            else if (ret > 0) { newerNode = newerNode.right; }
            else {
            	newerNode.data = value;
                return newerNode;
            }
        }
        int ret = value.compareTo(parent.data);
        if (ret < 0) { parent.left = newNode; }
        else { parent.right = newNode; }
        return newNode;
	}

	/**
	 * Find method in BST
	 * 
	 * @param root
	 *            BST root
	 * @param value
	 *            searched value
	 * @return true if the value is found in the BST
	 */
	public boolean find(E value) {
		BSTNode<E> newRoot = root;
        while (newRoot != null) {
            int ret = value.compareTo(newRoot.data);
            if (ret < 0) { newRoot = newRoot.left; }
            else if (ret > 0) { newRoot = newRoot.right; }
            else { return true; }
        }
		return false;
	}

	/**
	 * BST remove method
	 * 
	 * @param root
	 *            BST root node
	 * @param value
	 * @return tree root
	 */
	public BSTNode<E> remove(BSTNode<E> root, E value) {
		// TODO You don't need to implement this method. Yet you will get 3
		// extra points if you successfully implement it in iteration.
		return root;
	}

	public BSTNode<E> getRoot() {
		return root;
	}
}
