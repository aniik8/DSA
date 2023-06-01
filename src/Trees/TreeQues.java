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
        path.add(root.key);
        if(root.key == target && root.leftChild == null && root.rightChild == null){
            list.add(path);
            return;
        }
        findPath(root.leftChild, new ArrayList<>(path), list, target - root.key);
        findPath(root.rightChild, new ArrayList<>(path), list, target - root.key);
    }
}
