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
    TreeNode left, right;
    int data;
    TreeNode(int key){
        this.data = key;
        this.left = null;
        this.right = null;
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
        if(root.left == null){
            root.left = new TreeNode(data);
        }
        else if(root.right == null)
            root.right = new TreeNode(data);
        else
            insertionIntree(root.left, data);
    }
    // Traversal of Binary tree;
    // Level order - BFS
    // inorder pre order post order - DFS
    static void PrintLevelOrder(TreeNode node, int level){
        if(node == null){
            return;
        }
        else if(level == 1){
            System.out.println(node.data);
            return;
        }
        PrintLevelOrder(node.left, level-1);
        PrintLevelOrder(node.right, level-1);
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
            list.add(node.data);
            return;
        }
        levelOrderTree(node.left, level-1, list);
        levelOrderTree(node.right, level-1, list);
    }
    static int heightOftree(TreeNode node){
        return node == null ? 0 : Math.max(heightOftree(node.left), heightOftree(node.right))+1;
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
        inOrderTraversal(root.left, list);
        list.add(root.data);
        inOrderTraversal(root.right, list);
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
        list.add(root.data);
        PreOrderTraversal(root.left, list);
        PreOrderTraversal(root.right, list);
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

        PostOrderTraversal(root.left, list);
        PostOrderTraversal(root.right, list);
        list.add(root.data);
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
            li.add(node.data);
            list.add(li);
            return;
        }
        levelOrderTrees(node.left, level-1, list);
        levelOrderTrees(node.right, level-1, list);
    }
    // Diagonal Traversal of Binary Tree
    public ArrayList<Integer> diagonal(TreeNode root)
    {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode node = root;
        while(node != null){
            list.add(node.data);
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                node = node.right;
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
            list.add(node.data);
        }
        leftNodes(node, list);
        addleaves(node, list);
        rightNodes(node, list);

        return list;
    }
    static void leftNodes(TreeNode node, ArrayList<Integer> list){
        node = node.left;
        while(node != null){
            if(!isLeaf(node))
                list.add(node.data);
            if(node.left != null)
                node = node.left;
            else
                node = node.right;
        }
    }
    static void rightNodes(TreeNode node, ArrayList<Integer> list){
        Stack<Integer> stack = new Stack<>();
        node = node.right;
        while(node != null){
            if(!isLeaf(node))
                stack.push(node.data);
            if(node.right != null)
                node = node.right;
            else
                node = node.left;
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
                list.add(node.data);
            }
            addleaves(node.left, list);
            addleaves(node.right, list);
    }
    static boolean isLeaf(TreeNode node){
        return node.left == null && node.right == null;
    }
    // Zigzag traversal of binary tree -- just this function is changed a bit other than that everything is same
    public static void zigzagOrderTree(TreeNode node, int level, ArrayList<Integer> list,boolean leftorright) {
        if (node == null)
            return;
        else if (level == 1) {
            list.add(node.data);
            return;
        }
        if (!leftorright) {
            zigzagOrderTree(node.right, level - 1, list, leftorright);
            zigzagOrderTree(node.left, level - 1, list, leftorright);

        } else {
            zigzagOrderTree(node.left, level - 1, list, leftorright);
            zigzagOrderTree(node.right, level - 1, list, leftorright);
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
                    list.add(node.data);
                if(node.left != null)
                    queue.add(root.left);
                if(node.right != null)
                    queue.add(root.right);
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
                    list.add(node.data);
                if(node.right != null)
                    queue.add(node.right);
                if(node.left != null)
                    queue.add(node.left);
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
                topViewMap.put(horizontalDistance, node.data);
            }

            if (node.left != null) {
                queue.add(node.left);
                horizontalDistances.add(horizontalDistance - 1);
            }

            if (node.right != null) {
                queue.add(node.right);
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
            bottomViewMap.put(horizontalDistance, node.data);

            if (node.left != null) {
                queue.add(node.left);
                horizontalDistances.add(horizontalDistance - 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                horizontalDistances.add(horizontalDistance + 1);
            }
        }

        for (int value : bottomViewMap.values()) {
            list.add(value);
        }
        return list;
    }
    int diameter(TreeNode root) {
        // Your code here
        if(root == null)
            return 0;
        int left = diameter(root.left);
        int right = diameter(root.right);
        int heights = heightOftree(root.left) + heightOftree(root.right)+1;
        return Math.max(heights, Math.max(left, right));
    }

    boolean isSumTree(TreeNode root)
    {
        return root.data == sumSubtree(root, 0, 0);
    }
    int sumSubtree(TreeNode root, int leftSum, int rightSum){
         if(root == null)
             return 0;
         if(root.right == null)
             return 0;
         sumSubtree(root.left, leftSum+root.left.data, rightSum);
         sumSubtree(root.right, leftSum,rightSum+root.right.data);
         return leftSum+rightSum;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;
        if(root.data == p.data || root.data == q.data)return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left == null) return right;
        if(right == null) return left;

        return root;
    }
    public int sumofNodes(TreeNode root){
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return root.data;
        int left = sumofNodes(root.left);
        int right = sumofNodes(root.right);

        return left+right;
    }
    // inorder is giving error so using preorder (giving error for 1 N 2  2 N 1)
    boolean isIdentical(TreeNode root1, TreeNode root2)
    {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        inTree1(root1, list1);
        inTree2(root2, list2);
        if(list1.size() != list2.size())
            return false;
        return list1.equals(list2);
    }
    void inTree1(TreeNode root1,ArrayList<Integer> list1){
        if(root1 == null) {
            list1.add(0);
            return;
        }
        list1.add(root1.data);
        inTree1(root1.left,list1);
        inTree1(root1.right,list1);

    }
    void inTree2(TreeNode root2,ArrayList<Integer> list2){
        if(root2 == null) {
            list2.add(0);
            return;
        }
        list2.add(root2.data);
        inTree2(root2.left, list2);
        inTree2(root2.right, list2);


    }
    // is same tree using recursion
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null || q==null )
            return p==q;

        return (p.data ==q.data) && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
    // is symmertic
    public boolean isSymmetric(TreeNode root) {
        return isSymmetryOrNot(root.left, root.right);

    }
    boolean isSymmetryOrNot(TreeNode p, TreeNode q){
        if(p==null || q==null )
        { return p==q;}
        return (p.data ==q.data) && isSymmetryOrNot(p.left,q.right) && isSymmetryOrNot(p.right,q.left);
    }
    public boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        return checkBalance(root) != -1;
    }
    int checkBalance(TreeNode root){
        if(root == null)
            return 0;
        int left = checkBalance(root.left)+1;
        int right = checkBalance(root.right)+1;
        if(Math.abs(left - right) > 1)
            return -1;
        return Math.max(left, right);
    }
    // has path sum
    public boolean hasPathSum(TreeNode root, int targetSum) {
        traversal(root,targetSum);
        return res;
    }
    boolean res = false;
    public void traversal(TreeNode root, int targetSum) {
        if(root != null) {
            root.data = targetSum-root.data;
            if(root.left == null && root.right == null && root.data == 0) {
                res = true;
                return;
            }
            traversal(root.left, root.data);
            traversal(root.right, root.data);
        }
    }

}
