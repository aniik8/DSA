package Bst;

public class BinarySearchTree {
    public static void main(String[] args) {
        Tree root = new Tree();
        root.left = new Tree();
        root.right = new Tree();
        root.left.left = new Tree();
        root.right.right = new Tree();
        root.val = 10;
        root.left.val =9;
        root.right.val = 18;
        root.left.left.val = 3;
        root.right.right.val = 99;

        System.out.println(minimumInBst(root));
    }
    static int maximumInBst(Tree root){

        if(root == null)
            return -1;
        int maxi = root.val;;

        while(root != null)
        {
            maxi = root.val;
            root = root.right;

        }
        return maxi;
    }
    static int minimumInBst(Tree root){
        if(root == null)
            return -1;
        int min = root.val;
        while(root != null)
        {
            min = root.val;
            root = root.left;
        }
        return min;
    }
}

class Tree{
    Tree left;
    Tree right;
    int val;
    Tree(){
        this.left = this.right = null;
    }
    Tree(int val){
        this.val = val;
    }
}
