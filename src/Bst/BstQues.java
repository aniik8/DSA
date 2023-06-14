package Bst;

import java.util.*;

public class BstQues {
    public static void main(String[] args) {

    }
    // Leetcode 96 - recursive
    public int numTrees(int n) {
        if(n <= 1)
            return 1;
        int sum = 0;
        for(int i = 1; i <=n;i++){
            int left = numTrees(i-1);
            int right = numTrees(n - i);
            sum += left *right;
        }
        return sum;
    }
    // Memoized
    static int[] t = new int[20];
    public int numTreesMemoized(int n) {
        for(int i = 0; i < 20; i++){
            t[i] = 0;

        }
        return returnNumTrees(n, t);
    }
    public int returnNumTrees(int n, int[] t){
        if(n <= 1)
            return 1;
        if(t[n] != 0)
            return t[n];

        for(int i = 1; i <=n;i++){

            t[n] += returnNumTrees(i-1, t) * returnNumTrees(n - i,t);
        }
        return t[n];
    }
    // Kth smallest element in bst using inorder traversal

    static int kthSmallest(Tree root, int k) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list.get(k);
    }
    static void inorderTraversal(Tree root, List<Integer> list){
        if(root == null)
            return;
        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }
    //
    public void recoverTree(Tree root) {
        Tree leftNode = root;
        recoverTreeRecursive(leftNode);
    }
    public void recoverTreeRecursive(Tree rootNode){
        if(rootNode == null)
            return;
        if(rootNode.left == null && rootNode.right == null)
            return;
        if(rootNode.val > rootNode.left.val)
            recoverTreeRecursive(rootNode.left);
        else{
            int temp = rootNode.val;
            rootNode.val = rootNode.left.val;
            rootNode.left.val = temp;
            return;
        }
        if(rootNode.val < rootNode.right.val)
            recoverTreeRecursive(rootNode.right);
        else{
            int temp = rootNode.val;
            rootNode.val = rootNode.right.val;
            rootNode.right.val = temp;
            return;
        }
    }
    public boolean isValidBST(Tree root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        for(int i = 0; i <= list.size()-2; i++){
            if(list.get(i) >= list.get(i+1)){
                return false;
            }
        }
        return true;
    }
}

