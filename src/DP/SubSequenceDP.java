package DP;

public class SubSequenceDP {
    public static void main(String[] args) {
        System.out.println(longestSubsequence("abcdgh", "abedfhr"));
    }
    // longest common subsequence
    static int longestSubsequence(String S, String R){
        if(S.length() == 0 || R.length() == 0){
            return 0;
        }
        else if(S.charAt(0) == R.charAt(0)){
            return 1 + longestSubsequence(S.substring(1), R.substring(1));
        }
        else{
           return Math.max(longestSubsequence(S.substring(0), R.substring(1)), longestSubsequence(S.substring(1),
                    R.substring(0)));
        }
    }
}
