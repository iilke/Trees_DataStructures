public class BinarySearchTree {
    Node root;


    public void insert(int key, String value){
        root = insertRec(root, key, value);
    }

    private Node insertRec(Node root, int key, String value){
        if(root == null){
            root = new Node();
            root.key = key;
            root.value = value;
            return root;
        }

        if(key < root.key){
            root.left = insertRec(root.left, key, value);
        }
        else{
            root.right = insertRec(root.right, key, value);
        }

        return root;
    }

    boolean search(Node root, int key){
        return searchRec(root, key);
    }

    boolean searchRec(Node root, int key){
        Node current = root;
        if(root == null){
            return false;
        }

        if(key == current.key){
            return true;
        }
        else if(key < current.key){
            return searchRec(current.left, key);
        } else{
            return searchRec(current.right, key);
        }
    }


    //if root is being deleted, in order not to break the binary search tree rule:
    //1st option: smallest of right subtree should be new root
    //2nd option: largest of left subtree should be new root


    //deletion part is taken from 'geeksforgeeks'
    Node deleteNode(Node root, int key) {
        // Base case
        if (root == null)
            return root;

        // Recursive calls for ancestors of
        // node to be deleted
        if (key < root.key) {
            root.left = deleteNode(root.left, key);
            return root;
        } else if (key > root.key) {
            root.right = deleteNode(root.right, key);
            return root;
        }

        // We reach here when root is the node
        // to be deleted.

        // If one of the children is empty
        if (root.left == null) {
            Node temp = root.right;
            return temp;
        } else if (root.right == null) {
            Node temp = root.left;
            return temp;
        }

        // If both children exist
        else {

            Node succParent = root;

            // Find successor
            Node succ = root.right;
            while (succ.left != null) {
                succParent = succ;
                succ = succ.left;
            }

            // Delete successor.  Since successor
            // is always left child of its parent
            // we can safely make successor's right
            // right child as left of its parent.
            // If there is no succ, then assign
            // succ.right to succParent.right
            if (succParent != root)
                succParent.left = succ.right;
            else
                succParent.right = succ.right;

            // Copy Successor Data to root
            root.key = succ.key;

            // Delete Successor and return root
            return root;
        }
    }


    public void inOrderTraversal(Node root){
        if(root==null){
            return;
        }
        inOrderTraversal(root.left);
        System.out.println(root.key + " " + root.value);
        inOrderTraversal(root.right);

    }

    public Node max(Node root){
        if(root == null){
            return null;
        }
        Node walk = root;
        while(walk.right!=null){
            walk = walk.right;
        }
        return walk;
    }

    public Node min(Node root){
        if(root == null){
            return null;
        }
        Node walk = root;
        while(walk.left!=null){
            walk = walk.left;
        }
        return walk;
    }


    //public Node search(Node root, int key){}

    public static void main(String[] args){
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(5,"five");
        bst.insert(25, "twenty-five");
        bst.insert(18, "eighteen");
        bst.insert(10, "ten");
        bst.insert(12,"twelve");
        bst.insert(3,"three");
        bst.insert(55, "fifty-five");


        bst.inOrderTraversal(bst.root);

        System.out.println("Searching for 12: ");
        System.out.println(bst.search(bst.root, 12));

        System.out.println("Searching for 60: ");
        System.out.println(bst.search(bst.root, 60));

        System.out.println("Max value on tree:");
        System.out.println(bst.max(bst.root).key);

        System.out.println("Min value on tree:");
        System.out.println(bst.min(bst.root).key);

        System.out.println("delete 25: ");
        bst.deleteNode(bst.root,25);


        bst.inOrderTraversal(bst.root);
    }

}
