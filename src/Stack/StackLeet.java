package Stack;
import javax.swing.*;
import java.util.*;
public class StackLeet {
    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("bcabc"));
        int[] arr = {73,74,75,71,69,72,76,73};
//        System.out.println(find132pattern(arr));
        System.out.println(Arrays.toString(dailyTemperatures(arr)));
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
    // i < j < k and nums[i] < nums[k] < nums[j]. (nearest greatest right i guess)
    // [-2,1,1,-2,1,1]
    //
    static boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int minVal = Integer.MIN_VALUE;
        int[] arrayLeft = new int[nums.length];
        int[] arrayRight = new int[nums.length];
        int a = 0, b = nums.length-1;
        for (int i  = 0; i< nums.length; i++){
            if(stack.isEmpty())
                arrayLeft[a++] = minVal;
            else if(stack.size() > 0 && stack.peek() < nums[i])
                arrayLeft[a++] = stack.peek();
            else if(stack.size() > 0 && stack.peek() >= nums[i]){
                while(stack.size() > 0 && stack.peek() >= nums[i])
                    stack.pop();
                if(stack.isEmpty()) arrayLeft[a++] = minVal;
                else arrayLeft[a++] = stack.peek();
            }
            stack.push(nums[i]);
        }
        for (int i  = nums.length-1; i >= 0; i--){
            if(stack2.isEmpty())
                arrayRight[b--] = minVal;
            else if(stack2.size() > 0 && stack2.peek() < nums[i])
                arrayRight[b--] = stack2.peek();
            else if(stack2.size() > 0 && stack2.peek() >= nums[i])
            {
                while(stack2.size() > 0 && stack2.peek() >= nums[i]) {
                    stack2.pop();
                }
                if(stack2.isEmpty()) arrayRight[b--] = minVal;
                else arrayRight[b--] = stack2.peek();
            }
            stack2.push(nums[i]);
        }
        System.out.println(Arrays.toString(arrayLeft));
        System.out.println(Arrays.toString(arrayRight));
        for (int i = 0; i < nums.length; i++) {
            if(arrayLeft[i] != minVal && arrayRight[i] != minVal)
            {   if(arrayLeft[i] < arrayRight[i])
                    return true;
            }
        }
        return false;
    }
    //  Next Greater Element I
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> st = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[nums2.length];
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums2[i] >= st.peek()) {
                st.pop();
            }
            int nextGreater;
            if (st.isEmpty()) {
                nextGreater = -1;
            } else {
                nextGreater = st.peek();
            }
            map.put(nums2[i], nextGreater);
            st.push(nums2[i]);
        }

        int[] fina = new int[nums1.length];
        for (int i = 0; i < fina.length; i++) {
            fina[i] = map.get(nums1[i]);
        }
        return fina;
    }
    // 739 daily temperature
    // [73,74,75,71,69,72,76,73]
    // [1,1,4,2,1,1,0,0]
    // return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a
    // warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
    // Approach -- next greater element right index - index of current in the array
    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] days = new int[temperatures.length];

        for (int i = temperatures.length-1; i >= 0; i--) {
            if(stack.isEmpty())
                days[i] = 0;
            else if(stack.size() > 0){
                if(temperatures[stack.peek()] > temperatures[i]){
                    days[i] = stack.peek() - i;
                }
                else{
                    while(stack.size() > 0 && temperatures[stack.peek()] <= temperatures[i]){
                        stack.pop();
                    }
                    if(stack.isEmpty()) days[i] = 0;
                    else days[i] = stack.peek() - i;
                }
            }
            stack.push(i);
        }
        return days;
    }
    // 503 Next greater element
    public int[] nextGreaterElements(int[] nums) {
        int[] answer = new int[nums.length];
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length*2; i++) {
            while(!stack.isEmpty() && stack.peek() < nums[i % n])
                answer[stack.pop()] = nums[i%n];
            if(i < n)
                stack.push(i);
        }
        return answer;
    }
}
