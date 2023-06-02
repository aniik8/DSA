package Trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeQues {
    public static void main(String[] args) {

    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> list = new ArrayList<>();
        findPath(root, new ArrayList<>(), list, targetSum);

        return list;
    }
    public void findPath(TreeNode root, ArrayList<Integer> path,List<List<Integer>> list, int target ){
        if(root == null)
            return;
        path.add(root.data);
        if(root.data == target && root.left == null && root.right == null){
            list.add(path);
            return;
        }
        findPath(root.left, new ArrayList<>(path), list, target - root.data);
        findPath(root.right, new ArrayList<>(path), list, target - root.data);
    }
    // 2. Path Sum III (Bugs inside it)
    int count = 0;
    public int pathSumIII(TreeNode root, int targetSum) {
        if(root == null)
            return 0;
        findPath(root, targetSum);
        pathSumIII(root.left, targetSum);
        pathSumIII(root.right, targetSum);
        return count;
    }
    public void findPath(TreeNode root, int target){
        if(root == null)
            return;

        if(root.data == target){
            count++;
        }
        findPath(root.left, target - root.data);
        findPath(root.right, target - root.data);

    }
    // here we are using preorder array just to add our element in tree and inorder array to carry out who gonna be the
    // on the left subtree and who gonna be on the right subtree.
    private int preIndex; // Global variable to keep track of current index in preorder traversal
    private HashMap<Integer, Integer> indexMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        preIndex = 0; // Initialize the preIndex to 0
        return buildTreeHelper(preorder, inorder, 0, inorder.length - 1);
    }
    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null; // Base case: No more nodes to construct
        }

        int rootValue = preorder[preIndex]; // The current value in preorder traversal is the root of the current subtree
        TreeNode root = new TreeNode(rootValue);

        int inIndex = indexMap.get(rootValue); // Find the index of the root in the inorder traversal

        preIndex++; // Move to the next element in preorder traversal

        // Recursively construct the left and right subtrees
        root.left = buildTreeHelper(preorder, inorder, inStart, inIndex - 1);
        root.right = buildTreeHelper(preorder, inorder, inIndex + 1, inEnd);

        return root;
    }
}
