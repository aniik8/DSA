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
            if(visited.contains(s.charAt(i))) continue;
            while(!stack.isEmpty() && stack.peek() > s.charAt(i) && map.get(stack.peek()) > 0){
                visited.remove(stack.pop());
            }
            stack.push(s.charAt(i));
            visited.add(s.charAt(i));
        }
        while(!stack.isEmpty()){
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.reverse().toString();
    }
}
