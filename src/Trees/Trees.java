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
        tree.insert(10);
        tree.insert(190);
//        BinaryTree.print(1);
        System.out.println(BinaryTree.heightOftree(BinaryTree.root));
//        System.out.println(BinaryTree.levelOrder(BinaryTree.root));
        System.out.println(BinaryTree.inOrder(BinaryTree.root));
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
    public static ArrayList <Integer> levelOrder(TreeNode node)
    {   int height = heightOftree(node);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= height; i++) {
            levelOrderTree(node, i, list);
        }
        return list;
    }
    public static void levelOrderTree(TreeNode node, int level, ArrayList<Integer> list){
        if(node == null)
            return;
        else if(level == 1) {
            list.add(node.key);
            return;
        }
        levelOrderTree(node.leftChild, level-1, list);
        levelOrderTree(node.rightChild, level-1, list);
    }
    static int heightOftree(TreeNode node){
        return node == null ? 0 : Math.max(heightOftree(node.leftChild), heightOftree(node.rightChild))+1;
    }
    // breadth first search
    static ArrayList<Integer> inOrder(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
      inOrderTraversal(root, list);
      return list;
    }
    static void inOrderTraversal(TreeNode root, ArrayList<Integer> list){
        if(root == null)
            return;
        inOrderTraversal(root.leftChild, list);
        list.add(root.key);
        inOrderTraversal(root.rightChild, list);
    }
    static ArrayList<Integer> preorder(TreeNode root)
    {
        ArrayList<Integer> list = new ArrayList<>();
        PreOrderTraversal(root, list);
        return list;

    }
    static void PreOrderTraversal(TreeNode root, ArrayList<Integer> list){
        if(root == null)
            return;
        list.add(root.key);
        PreOrderTraversal(root.leftChild, list);
        PreOrderTraversal(root.rightChild, list);
    }
    ArrayList<Integer> postOrder(TreeNode root)
    {
        ArrayList<Integer> list = new ArrayList<>();
        PostOrderTraversal(root, list);
        return list;
    }
    static void PostOrderTraversal(TreeNode root, ArrayList<Integer> list){
        if(root == null)
            return;

        PostOrderTraversal(root.leftChild, list);
        PostOrderTraversal(root.rightChild, list);
        list.add(root.key);
    }
}