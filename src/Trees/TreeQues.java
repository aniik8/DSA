package Trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        path.add(root.val);
        if(root.val == target && root.left == null && root.right == null){
            list.add(path);
            return;
        }
        findPath(root.left, new ArrayList<>(path), list, target - root.val);
        findPath(root.right, new ArrayList<>(path), list, target - root.val);
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

        if(root.val == target){
            count++;
        }
        findPath(root.left, target - root.val);
        findPath(root.right, target - root.val);

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
    public TreeNode buildTreepost(int[] inorder, int[] postorder) {
        // Create a map to store the index of each element in the inorder array
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTreeHelper(inorder, postorder, map, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] inorder, int[] postorder, Map<Integer, Integer> map, int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        // The root of the current subtree is the last element in the postorder array
        int rootValue = postorder[postEnd];
        TreeNode root = new TreeNode(rootValue);

        // Find the index of the root in the inorder array
        int rootIndex = map.get(rootValue);

        // Determine the sizes of the left and right subtrees
        int leftSubtreeSize = rootIndex - inStart;
        int rightSubtreeSize = inEnd - rootIndex;

        // Recursively build the left and right subtrees
        root.left = buildTreeHelper(inorder, postorder, map, inStart, rootIndex - 1, postStart, postStart + leftSubtreeSize - 1);
        root.right = buildTreeHelper(inorder, postorder, map, rootIndex + 1, inEnd, postEnd - rightSubtreeSize, postEnd - 1);

        return root;
    }
}
