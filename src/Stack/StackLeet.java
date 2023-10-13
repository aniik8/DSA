package Stack;
import javax.swing.*;
import java.util.*;
public class StackLeet {
    public static void main(String[] args) {
//        System.out.println(removeDuplicateLetters("bcabc"));

////        System.out.println(find132pattern(arr));
//        System.out.println(Arrays.toString(dailyTemperatures(arr)));
//        System.out.println(decodeString("3[a]2[bc]"));
        String str = "((A + B) - C * ( D / E)) + F";
        System.out.println(infix_to_postFix(str));
        System.out.println(decodeStrings("2[abc]3[cd]ef"));
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
    // 394. Decode String
    // "3[a]2[bc"  ==== abcabccdcdcdef
    //                  abcabccdcdcdcdef
    public static String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        Stack<Integer> count = new Stack<>();
        int index = 0;
        String result = "";
        // 4 cases -
        while(index < s.length()){
            if(Character.isDigit(s.charAt(index)))
            {
                int num = 0;
                while(Character.isDigit(s.charAt(index))){
                    num =  10 * num + (s.charAt(index) - '0');
                }
                count.push(num);
                index += 1;
            }
            else if(s.charAt(index) == ']'){
                StringBuilder stringBuilder = new StringBuilder(stack.pop());
                int nums = count.pop();
                for (int i = 0; i < nums; i++) {
                    stringBuilder.append(result);
                }
                result = stringBuilder.toString();
                index += 1;
            }
            else if(s.charAt(index) == '['){
                stack.push(result);
                result = "";
                index += 1;
            }else{
                result += s.charAt(index);
                index += 1;
            }
            index++;
        }
        return result;
    }
    // Nearest smallest left
    // nearest smallest right.
    // area = (right - left - 1) * (weight of current)
    public static long getMaxArea(long hist[], long n)
    {   Stack<Integer> stack = new Stack<>();
        int h = hist.length;
        int[] left_arr = new int[h];
        int[] right_arr = new int[h];
        long[] area = new long[h];
        long max = Integer.MIN_VALUE;
        for (int i = 0; i < h; i++) {


            while (stack.size() > 0 && hist[stack.peek()] >= hist[i]) {
                stack.pop();
            }

            if (stack.size() == 0) left_arr[i] = -1;
            else left_arr[i] = stack.peek();
            stack.push(i);
        }
        stack = new Stack<>();
        int a = h - 1;
        for (int i = h - 1; i >= 0; i--) {

            while (stack.size() > 0 && hist[stack.peek()] >= hist[i]) {
                stack.pop();
            }


            if (stack.size() == 0) right_arr[a--] = h;
            else right_arr[a--] = stack.peek();

            stack.push(i);
        }
        for (int i = 0; i < h; i++) {
            area[i] = (right_arr[i] - (left_arr[i]) - 1) * hist[i];
            max = Math.max(max, area[i]);
        }

        return max;
    }

    static int precedence(char cha){
        switch(cha){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }
    public static String infix_to_postFix(String st){
        Stack<Character> stack = new Stack<>();
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < st.length(); i++){
            char ch = st.charAt(i);
            if(Character.isDigit(ch) || Character.isLetter(ch)){
                str.append(ch);
            }else if(ch == '('){
                stack.push(ch);
            }else if(ch == ')'){
                while(!stack.isEmpty() && stack.peek() != '('){
                    str.append(stack.pop());
                }
                if(!stack.isEmpty())
                    stack.pop();
            }
            else if(ch == ' '){
                continue;
            }
            else{
                while(!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())){
                    str.append(stack.pop());
                }
                stack.push(ch);
            }
        }
        while(!stack.isEmpty()){
            str.append(stack.pop());
        }
        System.out.println(str);
        return str.toString();
    }
    // "3[a]2[bc]"
     static String decodeStrings(String s){
        Stack<Character> charStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        StringBuilder str = new StringBuilder();
         StringBuilder string = new StringBuilder();
        int i = 0;
        while(i < s.length()){
            char ch = s.charAt(i);
            if(Character.isLetter(ch) || ch == '['){
                charStack.push(ch);
                System.out.println(ch);
            }
            else if((ch == ']')){

                while(charStack.peek() != '[')
                    string.append(charStack.pop());
                string.reverse();
                int n = numStack.pop();
                for(int j = 0; j < n; j++){
                    str.append(string);
                }
                charStack.pop();
                string.setLength(0);
            }
            else {
                int num = ch - '0';
                System.out.println(num);
                numStack.push(num);
            }
            i++;
        }
        StringBuilder sc= new StringBuilder();
        while(!charStack.isEmpty()){
            sc.append(charStack.pop());

        }

         str.append(sc.reverse());
        System.out.println(str);
        return str.toString();
    }
}
