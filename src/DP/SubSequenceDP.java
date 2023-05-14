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
        String S1 = "a", S2 = "z";
        int a = S1.length(), b = S2.length();
//        System.out.println(longestSubsequenceDP("abcdgh", "abedfhr", "abcdgh".length(),"abedfhr".length()));
//        System.out.println(longestSubstring("abcde", "abfce", 0, 0));
//        System.out.println(longestCommonSubstr(S1, S2, a, b));
//        System.out.println(lcsPrint(S1, S2, a, b));
//        System.out.println(shortestCommonSupersequence(S1,S2,a,b));
//        System.out.println(minOperations(S1, S2));
        System.out.println(longestPalindromeSubseq("agbcba"));
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
// "abcde", "abfce
    static int longestSubstring(String s, String r, int count, int max){
        if(s.length() == 0 || r.length() == 0) {
            max = Math.max(count, max);
            return max;
        }
        else if(s.charAt(0) == r.charAt(0))
            return longestSubstring(s.substring(1), r.substring(1), count+1, max);
        else
            return longestSubstring(s.substring(1), r.substring(1), 0, max);
    }
    static int longestCommonSubstr(String S1, String S2, int n, int m){
        int[][] t = new int[n+1][m+1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if(i == 0 || j == 0)
                    t[i][j] = 0;
            }
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if(S1.charAt(i-1) == S2.charAt(j-1))
                    t[i][j] = t[i-1][j-1] + 1;
                else
                    t[i][j] = 0;
            }
        }
        int max =0;
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                max = Math.max(t[i][j], max);
            }
        }
        return max;
    }
    static String lcsPrint(String s, String r, int m, int n){
        StringBuilder str = new StringBuilder();
        int[][] t = new int[m+1][n+1];
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if(i == 0 || j == 0)
                    t[i][j] = 0;
            }
        }
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if(s.charAt(i-1) == r.charAt(j-1))
                    t[i][j] = 1 + t[i-1][j-1];
                else
                    t[i][j] = Math.max(t[i-1][j], t[i][j-1]);
            }
        }
        int i = m, j = n;
        while(i > 0 && j > 0){
            if(s.charAt(i-1) == r.charAt(j-1)){
                str.append(s.charAt(i-1));
                i--;j--;
            }
            else if(t[i-1][j] > t[i][j-1]){
                i--;
            }
            else j--;
        }
        return str.reverse().toString();
    }
    public static int shortestCommonSupersequence(String X,String Y,int m,int n)
    {
        int count = longestSubsequenceDP(X, Y, m, n);
        return m+n-count;
    }
    static int minOperations(String str1, String str2)
    {
        int count = longestSubsequenceDP(str1, str2, str1.length(), str2.length());
        int deletedElement = str1.length() - count;
        int insertedElement  = str2.length() - count;
        System.out.println(deletedElement + "  " + insertedElement);
            return deletedElement + insertedElement;

    }


    static int longestPalindromeSubseq(String s) {
        StringBuilder str = new StringBuilder();
        for (int i = s.length()-1; i >= 0; i--) {
            str.append(s.charAt(i));
        }
        return longestSubsequenceDP(s, str.toString(), s.length(), str.length());
    }

    
}
