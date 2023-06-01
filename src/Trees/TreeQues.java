package Trees;

import java.util.ArrayList;
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

        TreeNode temp1 = root;
        TreeNode temp2 = root;
        findPath(root, targetSum);
        findPath(temp1.left, targetSum);
        findPath(temp2.right, targetSum);

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
}
