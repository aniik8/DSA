package HashMap;

import java.util.HashMap;
import java.util.HashSet;

public class HashMapC {
    public static void main(String[] args) {
//        int[] arr = {2,2,1};
//        System.out.println(singleNumberr(arr));
//        System.out.println(firstUniqChar2("dddccdbba"));
        System.out.println(checkCharacter("AbDcE", "ABE"));
    }
    static int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0;i < s.length(); i++){
            if(map.containsKey(s.charAt(i)))
                map.put(s.charAt(i), map.get(s.charAt(i))+1);
            else
                map.put(s.charAt(i), 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if(map.get(s.charAt(i)) == 1)
                return s.indexOf(s.charAt(i));
        }
        return -1;
    }
    static int firstUniqChar2(String s){
        for (int i = 0; i < s.length(); i++) {
            if(s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i)))
                return i;
        }
        return -1;
    }
    public static int singleNumber(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i])+1);
            }
            else{
                map.put(nums[i], 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if(map.get(nums[i]) == 1){
                return nums[i];
            }
        }
        return nums[nums.length-1];
    }
    public int singleNumberII(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i])+1);
            }
            else{
                map.put(nums[i], 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if(map.get(nums[i]) == 1){
                return nums[i];
            }
        }
        return nums[nums.length-1];
    }
    // single number 3 leetcode
    public int[] singleNumberIII(int[] nums) {
        int n = nums.length, a = 0;
        int[] ans = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i])+1);
            }
            else{
                map.put(nums[i], 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if(map.get(nums[i]) == 1){
                ans[a++] = nums[i];
                if(a > 2){
                    return ans;
                }
                
            }
        }
        return ans;
    }
    // "AbDcE", "ABF"
    static String checkCharacter(String str1, String str2){
        HashMap<Integer, Character> map = new HashMap<>();
        for(int i = 0; i < str1.length(); i++){
            map.put(i, str1.charAt(i));
        }
        for(int i = 0; i < str2.length();i++){
            if(!(map.containsValue(str2.charAt(i))) && !(map.containsValue(Character.toLowerCase(str2.charAt(i))))){
                return "NO";
            }
        }
        return "YES";
    }
}
