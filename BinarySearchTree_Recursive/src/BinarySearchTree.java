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


    /*--------------------------------------------------------------DELETION-----------------------------------------------------*/
    //if root is being deleted, in order not to break the binary search tree rule:
    //1st option: smallest of right subtree should be new root
    //2nd option: largest of left subtree should be new root

    //a note to myself for studying deletion: https://www.youtube.com/watch?v=kouxiP_H5WE

    Node deleteNode(Node root, int key){
        if(root == null){ //if tree is empty
            return null;
        }

        if(root.key == key){ //element to be deleted is found
            return helper(root);
        }

        Node dummy = root;
        while(root != null){  //find element to be deleted
            if(root.key > key){ //if it's smaller than root, search it in left side
                if(root.left != null && root.left.key == key){ //if the left is key itself, take action and delete it.
                    root.left = helper(root.left);
                    break;
                }
                else{  //key is on left subtree, but not the left node yet, it's in deeper
                    root = root.left;
                }
            }
            else{ //it's larger than root, search it in right side
                if(root.right != null && root.right.key == key){ //if the right is key itself, take action and delete it.
                    root.right = helper(root.right);
                    break;
                }
                else{ //key is on left subtree, but not the left node yet, it's in deeper
                    root = root.right;
                }
            }
        }
        return dummy;  //the first node, real root on top
    }

    public Node helper(Node root){//for deleting
        if(root.left == null){  //if given part of tree doesn't have left subtree then deleted node's parent will be connected to right tree
            return root.right;
        }
        else if(root.right == null){  //if given part of tree doesn't have right subtree then deleted node's parent will be connected to left tree
            return root.left;
        }
        else{  //given part of the tree has both right and left side
            Node rightChild = root.right;
            Node lastRight = findLastRight(root.left);
            lastRight.right = rightChild;
            return root.left;
        }
    }

    public Node findLastRight(Node root){
        if(root.right == null){
            return root;
        }
        return findLastRight(root.right);
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------*/


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
        bst.insert(12,"twelve");
        bst.insert(5,"five");
        bst.insert(25, "twenty-five");
        bst.insert(18, "eighteen");
        bst.insert(10, "ten");
        bst.insert(3,"three");
        bst.insert(55, "fifty-five");
        bst.insert(6,"six");
        bst.insert(8,"eight");


        bst.inOrderTraversal(bst.root);

        System.out.println("Searching for 12: ");
        System.out.println(bst.search(bst.root, 12));

        System.out.println("Searching for 60: ");
        System.out.println(bst.search(bst.root, 60));

        System.out.println("Max value on tree:");
        System.out.println(bst.max(bst.root).key);

        System.out.println("Min value on tree:");
        System.out.println(bst.min(bst.root).key);


        bst.inOrderTraversal(bst.root);


        System.out.println();
        System.out.println("deletion of 5:");
        bst.deleteNode(bst.root, 5);
        bst.inOrderTraversal(bst.root);
    }

}
