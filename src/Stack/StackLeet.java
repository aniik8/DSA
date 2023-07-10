package Stack;
import java.util.*;
public class StackLeet {
    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("bcabc"));
        int[] arr = {1,0,1,-4,-3};
        System.out.println(find132pattern(arr));
    }
    static String removeDuplicateLetters(String s) {
        String str = "";
        HashMap<Character, Integer> map = new HashMap<>();
        Stack<Character> stack = new Stack<>();
        HashSet<Character> visited = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        // Step 1 - put the characters in the map with frequency
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
        }
        // iterating through the string now
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.get(s.charAt(i))-1);
            // if our alphabet is already existed before in our string then no need to push that again
            if(visited.contains(s.charAt(i))) continue;
            // check all the condition together, if stack is not empty + if our top of the stack is greater than current
            // + our stack.peek have it's frequency > 0 only then remove the popped element from the visited set
            while(!stack.isEmpty() && stack.peek() > s.charAt(i) && map.get(stack.peek()) > 0){
                visited.remove(stack.pop());
            }
            // push the current smaller character into the stack and visited...
            stack.push(s.charAt(i));
            visited.add(s.charAt(i));
        }
        // just to reverse the string, we are using the string-builder.
        while(!stack.isEmpty()){
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.reverse().toString();
    }
    // find if nearest smaller left and nearest smaller right exist of any element.
    /// remember these two condition for bug fixing
    // i < j < k and nums[i] < nums[k] < nums[j].
    static boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int[] arrayLeft = new int[nums.length];
        int[] arrayRight = new int[nums.length];
        int a = 0, b = nums.length-1;
        for (int i  = 0; i< nums.length; i++){
            if(stack.isEmpty())
                arrayLeft[a++] = -1;
            else if(stack.size() > 0 && stack.peek() < nums[i])
                arrayLeft[a++] = stack.peek();
            else if(stack.size() > 0 && stack.peek() >= nums[i]){
                while(stack.size() > 0 && stack.peek() >= nums[i])
                    stack.pop();
                if(stack.isEmpty()) arrayLeft[a++] = -1;
                else arrayLeft[a++] = stack.peek();
            }
            stack.push(nums[i]);
        }
        for (int i  = nums.length-1; i >= 0; i--){
            if(stack2.isEmpty())
                arrayRight[b--] = -1;
            else if(stack2.size() > 0 && stack2.peek() < nums[i])
                arrayRight[b--] = stack.peek();
            else if(stack2.size() > 0 && stack2.peek() >= nums[i]){
                while(stack2.size() > 0 && stack2.peek() >= nums[i])
                    stack2.pop();
                if(stack2.isEmpty()) arrayLeft[b--] = -1;
                else arrayRight[b--] = stack2.peek();
            }
            stack2.push(nums[i]);
        }
        System.out.println(Arrays.toString(arrayLeft));
        System.out.println(Arrays.toString(arrayRight));
        for (int i = 0; i < nums.length; i++) {
            if(arrayLeft[i] != -1 && arrayRight[i] != -1)
                return true;
        }
        return false;
    }
}
