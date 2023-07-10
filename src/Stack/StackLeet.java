package Stack;
import java.util.*;
public class StackLeet {
    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("bcabc"));
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
}
