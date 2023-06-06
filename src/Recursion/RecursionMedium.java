package Recursion;
import java.util.*;

public class RecursionMedium {
    public static void main(String[] args) {
//        System.out.println(NBitBinary(2));
        int[] num3 = {2,1,2};
//        System.out.println(uniquesubsets(num3));
//        System.out.println(subsequences("abc"));
//        System.out.println(AllParenthesis(3));
//        System.out.println(permutation("AB"));
        System.out.println(permutationwithCase("a1b2"));
    }
// K-th symbol grammer. 779 leetcode
    static int kthGrammar(int n, int k) {
        if(n == 1 && k == 1)
            return 0;
        int mid = ((int) Math.pow(2, n-1))/2;
        if(k <= mid){
            return kthGrammar(n-1, k);
        }else{
            return reverse(kthGrammar(n-1, k-1)) ;
        }
    }
    static int reverse(int n){
        return n == 0 ? 1 : 0;
    }


    // Tower Of Hanoi
    static long toh(int N, int from, int to, int aux) {
        if(N == 1){
            System.out.println("move disk " + N + " from rod " + from + " to rod " + to);
            return (long) (Math.pow(2, N)-1);
        }
        toh(N-1, from, aux, to);
        System.out.println("move disk " + N + "from rod " + from + " to rod " + to);
        toh(N-1, aux, to, from);
        return (long) (Math.pow(2, N)-1);
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>(); // initialize the result list
        generate(result, "", n, n); // call the recursive helper function
        return result; // return the result list
    }

    static void generate(List<String> result, String s, int leftParenthesis, int rightParenthesis){
        if(leftParenthesis == 0 && rightParenthesis == 0){ // base case: no more parentheses to add
            result.add(s); // add the generated string to the result list
            return; // exit the current recursive call
        }
        if(leftParenthesis != 0){ // if there are still leftParenthesis parentheses to add
            generate(result, s + "(", leftParenthesis - 1, rightParenthesis); // add a leftParenthesis parenthesis and recursively call the function
        }
        if(rightParenthesis > leftParenthesis){ // if there are still rightParenthesis parentheses to add and there are more leftParenthesis parentheses than rightParenthesis parentheses in the current string
            generate(result, s + ")", leftParenthesis, rightParenthesis - 1); // add a rightParenthesis parenthesis and recursively call the function
        }
    }


    // Print N-bit binary numbers having more 1s than 0s
    static ArrayList<String> NBitBinary(int N) {
        ArrayList<String> list = new ArrayList<>();
        generateOneZero(list, 0, 0, N, "");
        return list;
    }
    static void generateOneZero(ArrayList<String> list, int one, int zero, int n, String output){
        if(n == 0) {
            list.add(output);
            return;
        }
        String oneS = output + "1";
        generateOneZero(list, one+1, zero, n-1, oneS);
        if(one > zero){
            String zeroS = output + "0";
            generateOneZero(list, one, zero+1, n-1, zeroS);
        }

    }

    // 78 Subsets
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int index = 0;
        generateSubsets(nums, ans, index, output);
        return output;
    }
    static void generateSubsets(int[] nums,List<Integer> ans, int index,  List<List<Integer>> output){
        if(index == nums.length){
            output.add(new ArrayList<>(ans));
            return;
        }
        // exclude condition.
        generateSubsets(nums, new ArrayList<>(ans), index+1, output);
        // include condition.
        ans.add(nums[index]);
        generateSubsets(nums, ans,index+1, output);
    }


    // Subsequence of a String.
    static ArrayList<String> subsequences(String str){
        ArrayList<String> answer = new ArrayList<>();
        subsequenceOfstring(answer, "", str);
        return answer;
    }


    static void subsequenceOfstring(ArrayList<String> answer, String unp, String p){
        if(p.length() == 0){
            answer.add(unp);
            return;
        }
        // processed
        char ch = p.charAt(0);
        subsequenceOfstring(answer, unp + ch,p.substring(1));
        // unprocessed
        subsequenceOfstring(answer,  unp, p.substring(1));


    }

    // Unique Subsets GFG
    public static ArrayList <ArrayList <Integer>> uniquesubsets(int[] arr) {
        ArrayList <ArrayList <Integer>> output = new ArrayList<>();
        ArrayList <Integer> ans = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>>  map = new HashMap<>();
        int index = 0;
        generateuniqueSubsets(arr, ans, index, output, map, 0);
//        Collections.sort(output);
        return output;
    }
    // https://leetcode.com/problems/subsets/solutions/2034062/subset-i-4-approaches-java-solution/?languageTags=java&topicTags=recursion
    static void generateuniqueSubsets(int[] nums,ArrayList <Integer> ans, int index,  ArrayList <ArrayList <Integer>> output,
                                      HashMap<Integer, ArrayList <Integer>>  map, int i){
        if(index == nums.length){
            if(!(map.containsValue(ans))) {
                map.put(i, ans);
                output.add(new ArrayList<>(ans));
                return;
            }
            return;
        }
        // exclude condition.
        generateuniqueSubsets(nums, new ArrayList<>(ans), index+1, output, map,i+1);
        // include condition.
        ans.add(nums[index]);
        generateuniqueSubsets(nums, ans,index+1, output, map, i+1);
    }

    // All parentheses GFG
    public static List<String> AllParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generates(result, "", n, n);
        return result;
    }
    static void generates(List<String> result, String s, int open, int close){
        if(open == 0 && close == 0){
            result.add(s);
            return;
        }
        if(open!=0){
            generates(result, s + "(", open - 1, close);
        }
        if(open < close){
            generates(result, s+")", open, close-1);
        }
    }

    static ArrayList<String> permutation(String S){
        ArrayList<String> str = new ArrayList<>();
        generatePermutation(str,S.substring(1), ""+S.charAt(0), 0);
        return str;
    }
    // https://practice.geeksforgeeks.org/problems/permutation-with-spaces3627/1
    static void generatePermutation(ArrayList<String> str,String unprocessed, String processed, int n){
        if(unprocessed.isEmpty()){
            str.add(processed);
            return;
        }
        String ch =  " ";
        generatePermutation(str, unprocessed.substring(1), processed+ch+unprocessed.charAt(0), n+1);
        generatePermutation(str, unprocessed.substring(1), processed+unprocessed.charAt(0), n+1);
    }

    // permutation by changing cases - Leetcode 784
    static ArrayList<String> permutationwithCase(String s){
        ArrayList<String> str = new ArrayList<>();
        generateCasePermutation(str, s,"");
        return str;
    }
    static void generateCasePermutation(ArrayList<String> str, String unprocessed, String processed){
        if(unprocessed.isEmpty()){
            str.add(processed);
            return;
        }
        if((Character.isAlphabetic(unprocessed.charAt(0)))){
            char ch = Character.isUpperCase(unprocessed.charAt(0)) ? Character.toLowerCase(unprocessed.charAt(0)) :
                    Character.toUpperCase(unprocessed.charAt(0))
                    ;
            generateCasePermutation(str,  unprocessed.substring(1),processed+ch);

        }
        generateCasePermutation(str, unprocessed.substring(1),processed+unprocessed.charAt(0));
    }
}



