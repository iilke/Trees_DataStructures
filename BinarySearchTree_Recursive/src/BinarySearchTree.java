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



    public void inOrderTraversal(Node root){
        if(root==null){
            return;
        }
        inOrderTraversal(root.left);
        System.out.println(root.key + " ");
        inOrderTraversal(root.right);

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

    }



}
