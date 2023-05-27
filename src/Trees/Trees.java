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
    public List<List<Integer>> levelOrderr(TreeNode node) {
        int height = heightOftree(node);
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 1; i <= height; i++) {
            levelOrderTrees(node, i, list);
        }
        return list;
    }
    public static void levelOrderTrees(TreeNode node, int level, List<List<Integer>> list){
        if(node == null)
            return;
        else if(level == 1) {
            List<Integer>li=new ArrayList<>();
            li.add(node.key);
            list.add(li);
            return;
        }
        levelOrderTrees(node.leftChild, level-1, list);
        levelOrderTrees(node.rightChild, level-1, list);
    }
    // Diagonal Traversal of Binary Tree
    public ArrayList<Integer> diagonal(TreeNode root)
    {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode node = root;
        while(node != null){
            list.add(node.key);
            if(node.leftChild != null)
                queue.add(node.leftChild);
            if(node.rightChild != null)
                node = node.rightChild;
            else{
                if(!queue.isEmpty()) {
                    node = queue.peek();
                    queue.remove();
                }else{
                    node = null;
                }

            }
        }
        return list;
    }

    // Boundary Traversal of binary tree
    public ArrayList <Integer> boundary(TreeNode node)
    {   ArrayList<Integer> list = new ArrayList<>();
        if(!isLeaf(node)){
            list.add(node.key);
        }
        leftNodes(node, list);
        addleaves(node, list);
        rightNodes(node, list);

        return list;
    }
    static void leftNodes(TreeNode node, ArrayList<Integer> list){
        node = node.leftChild;
        while(node != null){
            if(!isLeaf(node))
                list.add(node.key);
            if(node.leftChild != null)
                node = node.leftChild;
            else
                node = node.rightChild;
        }
    }
    static void rightNodes(TreeNode node, ArrayList<Integer> list){
        Stack<Integer> stack = new Stack<>();
        node = node.rightChild;
        while(node != null){
            if(!isLeaf(node))
                stack.push(node.key);
            if(node.rightChild != null)
                node = node.rightChild;
            else
                node = node.leftChild;
        }
        // anticlockwise operation.
        while(!stack.isEmpty()){
            list.add(stack.peek());
            stack.pop();
        }
    }
    static void addleaves(TreeNode node, ArrayList<Integer> list){
            if(node == null)
                return;
            if(isLeaf(node)){
                list.add(node.key);
            }
            addleaves(node.leftChild, list);
            addleaves(node.rightChild, list);
    }
    static boolean isLeaf(TreeNode node){
        return node.leftChild == null && node.rightChild == null;
    }
    // Zigzag traversal of binary tree -- just this function is changed a bit other than that everything is same
    public static void zigzagOrderTree(TreeNode node, int level, ArrayList<Integer> list,boolean leftorright) {
        if (node == null)
            return;
        else if (level == 1) {
            list.add(node.key);
            return;
        }
        if (!leftorright) {
            zigzagOrderTree(node.rightChild, level - 1, list, leftorright);
            zigzagOrderTree(node.leftChild, level - 1, list, leftorright);

        } else {
            zigzagOrderTree(node.leftChild, level - 1, list, leftorright);
            zigzagOrderTree(node.rightChild, level - 1, list, leftorright);
        }
    }
    // left view of an array list
    ArrayList<Integer> leftView(TreeNode root)
    {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int n = queue.size();
            for(int i = 0; i < n; i++){
                TreeNode node= queue.poll();
                if(i == 0)
                    list.add(node.key);
                if(node.leftChild != null)
                    queue.add(root.leftChild);
                if(node.rightChild != null)
                    queue.add(root.rightChild);
            }
        }
        // Your code here
        return list;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null)return list;

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while(queue.isEmpty()==false){
            int n = queue.size();
            for(int i = 1; i <= n; i++){
                TreeNode node = queue.poll();
                if(i == 1)
                    list.add(node.key);
                if(node.rightChild != null)
                    queue.add(node.rightChild);
                if(node.leftChild != null)
                    queue.add(node.leftChild);
            }

        }
        return list;
    }
    static ArrayList<Integer> topView(TreeNode root)
    {
        ArrayList<Integer> list = new ArrayList<>();
        Map<Integer, Integer> topViewMap = new TreeMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> horizontalDistances = new LinkedList<>();

        queue.add(root);
        horizontalDistances.add(0);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int horizontalDistance = horizontalDistances.poll();

            if (!topViewMap.containsKey(horizontalDistance)) {
                topViewMap.put(horizontalDistance, node.key);
            }

            if (node.leftChild != null) {
                queue.add(node.leftChild);
                horizontalDistances.add(horizontalDistance - 1);
            }

            if (node.rightChild != null) {
                queue.add(node.rightChild);
                horizontalDistances.add(horizontalDistance + 1);
            }
        }

        for (int value : topViewMap.values()) {
            list.add(value);
        }
        return list;
    }
    public ArrayList <Integer> bottomView(TreeNode root)
    {
        ArrayList<Integer> list = new ArrayList<>();
        Map<Integer, Integer> bottomViewMap = new TreeMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> horizontalDistances = new LinkedList<>();

        queue.add(root);
        horizontalDistances.add(0);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int horizontalDistance = horizontalDistances.poll();
            bottomViewMap.put(horizontalDistance, node.key);

            if (node.leftChild != null) {
                queue.add(node.leftChild);
                horizontalDistances.add(horizontalDistance - 1);
            }
            if (node.rightChild != null) {
                queue.add(node.rightChild);
                horizontalDistances.add(horizontalDistance + 1);
            }
        }

        for (int value : bottomViewMap.values()) {
            list.add(value);
        }
        return list;
    }
}