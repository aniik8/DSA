package SlidingWIndow;

import java.util.HashMap;

public class SlidingVariable {

    public static void main(String[] args) {
//        System.out.println(longestkSubstr("aabacbebebe", 3));
        System.out.println(longestSubstring("aaabb", 3));
    }
    // Longest K unique characters substring

    /**
     * S = "aabacbebebe", K = 3  Output: 7
     */
    static int longestkSubstr(String s, int k) {
        int i = 0, j = 0, n = s.length(), max = Integer.MIN_VALUE;
        HashMap<Character, Integer> map = new HashMap<>();
        while (j < n) {
            if (map.containsKey(s.charAt(j))) {
                map.put(s.charAt(j), map.get(s.charAt(j)) + 1);
            } else {
                map.put(s.charAt(j), 1);
            }
            if (map.size() < k)
                j++;
            else if (map.size() == k) {
                {
                    max = Math.max(j - i + 1, max);
                    j++;
                }
            } else {
                while (map.size() > k) {
                    map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                    if (map.get(s.charAt(i)) == 0)
                        map.remove(s.charAt(i));
                    i++;
                }
                j++;
            }
        }
        return max;
    }

    // leetcode 395
    /*
    int i = 0, j = 0, max = 0;
        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        while(j < n){
            if(map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get(s.charAt(i))+1);
            }else{
                map.put(s.charAt(i), 1);
            }
            if()
        }
    * */
    static int longestSubstring(String s, int k) {
        return longestSubstringRe(s, k, 0, s.length() - 1);
    }

    static int longestSubstringRe(String s, int k, int start, int end) {
        // string length is lesser than the required repeating characters.
        if(end - start + 1 < k){
            return 0;
        }
        int[] frequency = new int[26];
        // count the frequency of characters
        for (int i = start; i <= end; i++) {
            char ch = s.charAt(i);
            frequency[ch - 'a']++;
        }
        // break point of the string - where the element less than k
        int mid = -1;
        for (int i = start; i <= end; i++){
            if(frequency[s.charAt(i) - 'a'] < k) {
                mid = i;
                break;
            }
        }
        //  if no break point, means every character of that string is atleast k times
        if(mid == -1)
            return end - start + 1;
        // now after diving the substring, checking which part of the string have maximum length
        int leftSubstring = longestSubstringRe(s, k, start, mid-1);
        int rightSubstring = longestSubstringRe(s, k, mid+1, end);
        return Math.max(leftSubstring, rightSubstring);
}
    // leetcode 76
    // Input: s = "ADOBECODEBANC", t = "ABC"
    //Output: "BANC" -- window size no
    // "zoomlazapzo" ---- "oza"
    // approach, as soon as the repeating element arrives, slide the window
    // main thing is, how to count the occurrences of the element
//    public String minWindow(String s, String t) {
//
//        }
}