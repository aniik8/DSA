package DP;

public class SubSequenceDP {
    static int[][] t = new int[1001][1001];
    public static void main(String[] args) {
//        System.out.println(longestSubsequence("abcdgh", "abedfhr"));
        for (int i = 0; i < 1001; i++) {
            for (int j = 0; j < 1001; j++) {
                t[i][j] = -1;
            }
            //
        }
        System.out.println(longestSubsequenceDP("abcdgh", "abedfhr", "abcdgh".length(),"abedfhr".length()));
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


    static int longestSubsequencememoize(String S, String R, int m, int n){
        if(S.length() == 0 || R.length() == 0){
            return 0;
        }
        if(t[m][n] != -1)
            return t[m][n];
        else if(S.charAt(m-1) == R.charAt(n-1)){
            return t[m][n] =  1 + longestSubsequence(S.substring(0, m), R.substring(0, n));
        }
        else{
            return t[m][n] = Math.max(longestSubsequence(S.substring(0, m), R.substring(0, n-1)),
                    longestSubsequence(S.substring(0, m-1),
                    R.substring(0, n)));
        }
    }
    static int longestSubsequenceDP(String S, String R, int m, int n){
        int[][] t = new int[m+1][n+1];
        for (int i = 0; i < m+1; i++) {
            for (int j = 0; j < n+1; j++) {
                if(i == 0 || j == 0)
                    t[i][j] = 0;
            }
        }
       for(int i = 1; i < m+1; i++){
           for(int j = 1; j < n+1; j++){
               if(S.charAt(i-1) == R.charAt(j-1))
                   t[i][j] = 1 + t[i-1][j-1];
               else
                   t[i][j] = Math.max(t[i-1][j], t[i][j-1]);
           }
       }
       return t[m][n];
    }

}
