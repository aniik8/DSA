package Trees;
import java.util.*;
public class Trees {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(12);
        tree.insert(14);
        tree.insert(19);
        tree.insert(1);
        tree.insert(20);
    }

}
class TreeNode{
    TreeNode leftChild, rightChild;
    int key;
    TreeNode(int key){
        this.key = key;
        this.leftChild = null;
        this.rightChild = null;
    }
}
class BinaryTree{
    TreeNode root = null;
    BinaryTree(){
        this.root = null;
    }
    public void insert(int data){
        if(root == null){
            root = new TreeNode(data);
            return;
        }
        insertionIntree(root, data);
    }
    public void insertionIntree(TreeNode root, int data){
        if(root.leftChild == null){
            root.leftChild = new TreeNode(data);
        }
        else if(root.rightChild == null)
            root.rightChild = new TreeNode(data);
        else
            insertionIntree(root.leftChild, data);
    }
    
}