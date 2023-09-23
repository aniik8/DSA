package Trees;
import java.util.*;
import Trees.TreeNode;
public class TreeTwo {
    public static void main(String[] args) {

    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(queue.size() != 0){
            int queueSize = queue.size();
            List<Integer> currentResult = new ArrayList<>();

            for(int i = 0;i < queueSize; i++){
                TreeNode currentNode = queue.poll();
                currentResult.add(currentNode.val);
                if(currentNode.left != null)
                    queue.offer(currentNode.left);
                if(currentNode.right != null)
                    queue.offer(currentNode.right);
            }
            result.add(currentResult);
        }
        return result;
    }
// ---------------------------------------------------------------------------------------------------------------------

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double>  currentResult = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        if(root == null)
            return currentResult;
        double sum = 0;
        while(queue.size() != 0){
            int queueSize = queue.size();
            for(int i = 0;i < queueSize; i++){
                TreeNode currentNode = queue.poll();
                if(currentNode.left != null)
                {
                    queue.offer(currentNode.left);
                }
                if(currentNode.right != null)
                {
                    queue.offer(currentNode.right);
                }
                sum += currentNode.val;
            }
            currentResult.add(sum/queueSize);
            sum = 0;
        }
        return currentResult;
    }
    public List<Integer> rightSideView(TreeNode root){
        List<Integer> currentResult = new ArrayList<>();
        if(root == null)
            return currentResult;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(queue.size() != 0){
            int queueSize = queue.size();
            for(int i = 0;i < queueSize; i++){
                TreeNode currentNode = queue.poll();
                if(i == queueSize - 1)
                    currentResult.add(currentNode.val);
                if(currentNode.left != null)
                    queue.offer(currentNode.left);
                if(currentNode.right != null)
                    queue.offer(currentNode.right);
            }

        }
        return currentResult;
    }
}


