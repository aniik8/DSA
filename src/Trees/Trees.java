package Trees;
import java.util.*;
public class Trees {
    public static void main(String[] args) {

    }
    static TreeNode insertionInTree(){
        TreeNode root = null;
        Scanner sc = new Scanner(System.in);
        int key = sc.nextInt();
        root = new TreeNode(key);
        // Now to insert left child and right child, we'll call this function again for left as well as right child;
        System.out.println("Enter the left Child");
        root.leftChild = insertionInTree();
        System.out.println("Enter the right Child");
        root.rightChild = insertionInTree();
        return root;
    }
}
class TreeNode{
    TreeNode leftChild, rightChild;
    int key;
    TreeNode(int key){
        this.key = key;
    }
}