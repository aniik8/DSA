package Bst;

import java.util.*;

public class BstGfG {
    public static void main(String[] args) {

    }
    boolean isBST(Tree root)
    {
        List<Integer> list = new ArrayList<>();
        checkIsBst(root, list);
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) >= list.get(i+1))
                return false;
        }
        return true;
    }
    void checkIsBst(Tree root, List<Integer> list){
        if(root == null)
            return;
        checkIsBst(root.left, list);
        list.add(root.val);
        checkIsBst(root.right, list);
    }
    // min value in bst - microsoft
    int minValue(Tree node) {
        if(node == null) return -1;
        int val = node.val;
        while(node != null){
            val = node.val;
            node = node.left;
        }
        return val;
    }
    boolean isBalanced(Tree root)
    {
        int left = height(root.left);
        int right = height(root.right);
        int k =Math.abs(left-right);
        if(k > 1)
            return false;
        else return true;
    }
    int height(Tree root)
    {
        if (root == null)
            return 0;
        return Math.max(height(root.left), height(root.right))+1;
    }
    // Kth largest
    public int kthLargest(Tree root,int K)
    {
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list,K);
        return list.get(list.size()-K-1);
    }
    void inorderTraversal(Tree root,  List<Integer> list, int k){
        if(root == null)
            return;
        inorderTraversal(root.left, list, k);
        list.add(root.val);
        if(list.size() == k)
            return;
        inorderTraversal(root.right, list, k);
    }
}
