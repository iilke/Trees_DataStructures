public class BinarySearchTree {
    private Node root;

    public void insert(Node node){
        root = insertHelper(root, node);
    }

    private Node insertHelper(Node root, Node node){
        if(root == null){ //it's an empty tree
            root = node;
            return root;
        }

        else if(root.data<node.data){ //newcomer is bigger than root, look at right
            root.right = insertHelper(root.right, node);
        } else if (root.data>node.data) {//newcomer is smaller than root,look left
            root.left = insertHelper(root.left, node);
        }

        return root;

    }

    public void display(){
        displayHelper(root);
    }
    private void displayHelper(Node root){//inorder: left, root, right
        if(root != null) {
            displayHelper(root.left);
            System.out.println(root.data);
            displayHelper(root.right);
        }
    }

    public boolean search(int data) {
        return searchHelper(root, data);
    }
    private boolean searchHelper(Node root, int data) {
        if(root == null) {
            return false;
        }
        else if(root.data == data){
            return true;
        }
        else if(root.data<data){ //look right
            return searchHelper(root.right,data);
        }
        else if(root.data>data){
            return searchHelper(root.left,data);
        }

        return false;
    }

    public void remove(int data){
        if(search(data)) {
            removeHelper(root, data);
        }
        else {
            System.out.println(data + " could not be found");
        }
    }


    private Node removeHelper(Node root, int data){
        if(root == null) {
            return root;
        }
        if(data<root.data){//if it's smaller than current root, look left
            root.left = removeHelper(root.left,data);
        }
        else if(data>root.data){//if it's bigger than current root, look right
            root.right = removeHelper(root.right,data);
        }
        else if (data == root.data){ //it's found
            if(root.right==null && root.left==null){//it doesn't have children, it's a leaf node
                    root = null;
            }
            else if(root.right!=null){//it has a right child, the leftmost node at right subtree will take the deleted node's place
                Node leftMost = root;
                while(leftMost.left != null){
                    leftMost = leftMost.left;
                }//leftMost is the leftmost node of right subtree now

                root.data = leftMost.data;
                root.right = removeHelper(root.right, root.data);
            }
            else if(root.left!=null){//it has a left child, the rightmost node at left subtree will take the deleted node's place
                Node rightMost = root;
                while(rightMost.right != null){
                    rightMost = rightMost.right;
                }//rightMost is the rightmost node of left tree

                root.data = rightMost.data;
                root.left = removeHelper(root.left, root.data);
            }
        }
        return root;
    }

    public int getHeight() {
        return 0;
    }
}
