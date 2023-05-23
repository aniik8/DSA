package Trees;
import java.util.*;
public class Trees {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(12);
        tree.insert(14);
        tree.insert(19);
//        tree.insert(1);
//        tree.insert(20);
//        tree.insert(10);
//        tree.insert(190);
//        BinaryTree.print(1);
        System.out.println(BinaryTree.heightOftree(BinaryTree.root));
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
    static TreeNode root = null;
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
    // Traversal of Binary tree;
    // Level order - BFS
    // inorder pre order post order - DFS
    static void PrintLevelOrder(TreeNode node, int level){
        if(node == null){
            return;
        }
        else if(level == 1){
            System.out.println(node.key);
            return;
        }
        PrintLevelOrder(node.leftChild, level-1);
        PrintLevelOrder(node.rightChild, level-1);
    }
    static void print(int level){
        while(level <= 4) {
            PrintLevelOrder(root, level);
            level++;
        }
    }

}