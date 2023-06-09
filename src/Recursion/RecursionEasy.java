package Recursion;

import java.util.*;
public class RecursionEasy {
    public static void main(String[] args) {
//       permutationOfAString("", "abc");
//        System.out.println(find_permutation("abb"));
        /*System.out.println(kthGrammar(5, 3));*/
//        System.out.println(toh(2, 1, 2, 3));
        System.out.println(myPow(2.10000, 3));
    }

    // gfg practice

    static int sum_of_digit(int n)
    {
        if (n == 0)
            return 0;
        return (n % 10 + sum_of_digit(n / 10));
    }
    // First uppercase Letter In a string.
    static char returnFirstUpper(String s){
        if(s.length() == 0) {
            return '0';
        }
        else if(Character.isUpperCase(s.charAt(0)))
            return s.charAt(0);
        else return returnFirstUpper(s.substring(1));
    }


    // 231. LeetCode - Is power of two
    static public boolean isPowerOfTwo(int n)
    {
        if(n != 1 && n % 2 == 1) {
            return false;}
        else if(n == 1)
            return true;
        return isPowerOfTwo(n/2);
    }
    //
    static public boolean isPowerOfThree(int n) {
        if((n != 1)&& ( n % 3 != 0)) {
            return false;
        }
        else if(n == 1)
            return true;
        return isPowerOfThree(n/3);
    }
    // remove consecutive character GeeksForGeeks --
//    aabaa
    static public String removeConsecutiveCharacter(String S){
        return removeHelperFunc(S, "");
    }


    static public String removeHelperFunc(String unprocessed, String processed){
        if(unprocessed.length() <= 1) {
            processed = processed + (unprocessed.charAt(0));
            return processed;
        }
        if(unprocessed.charAt(0) == unprocessed.charAt(1))
            return removeHelperFunc(unprocessed.substring(1), processed);
        else return removeHelperFunc(unprocessed.substring(1), processed + unprocessed.charAt(0));
    }

    // permutation of a string -- Printing
    static public void permutationOfAString(String processed, String unprocessed) {
        if(unprocessed.length()==0){
            System.out.println(processed);
            return;
        }
        char ch = unprocessed.charAt(0);
        for (int i = 0; i <= processed.length(); i++) {
            String first = processed.substring(0, i);
            String second = processed.substring(i, processed.length());
            permutationOfAString(first+ch+second, unprocessed.substring(1));
        }
    }
// lexicographically and unique permutations.
    static public List<String> find_permutation(String S) {
        List<String> ls = new ArrayList<>();
        permutations("", S, ls);
        return ls;
    }
    static void permutations(String processed, String unprocessed, List<String> ls){

        if(unprocessed.length() ==0){
            if(!ls.contains(processed))
            {
                ls.add(processed);
            }
return;
        }
        char ch = unprocessed.charAt(0);
        for (int i = 0; i <= processed.length(); i++) {
        permutations(processed.substring(0, i) + ch+ processed.substring(i, processed.length()),
                unprocessed.substring(1), ls);
        }
        Collections.sort(ls);
    }


// Number of steps to reduce a number to zero 1342 leetcode
    public int numberOfSteps(int num) {
        return countfunc(num, 0);
    }
    int countfunc(int num, int count)
    {
        if(num == 0) return count;
        if (num % 2 == 0){
            return countfunc(num/2, count+1);
        }
        else {
            return countfunc(num-1, count+1);
        }
    }
// reversing a stack using recursion
    static void reverse(Stack<Integer> s)
    {
        // add your code here
        Stack<Integer> sc = new Stack<>();
        sc = reverseStack(s,sc);
        s.addAll(sc);
    }
    static Stack<Integer> reverseStack(Stack<Integer> s, Stack<Integer> sc){
        if(s.isEmpty()){
            return sc;
        }
        sc.add(s.pop());
        return reverseStack(s, sc);
    }



    // power(x, n)
    static double myPow(double x, int n) {
        if(n < 0){
            return 1/ negativePower(x, Math.abs(n));
        }
        else return positivePower( x,n);
    }

// 50. Pow(x, n)
    static double negativePower(double x, int n){
        if(n == 0)
            return 1;
        return x * negativePower(x, n-1);
    }
    static double positivePower(double x, int n){
        if(n == 0)
            return 1;
        return x * negativePower(x, n-1);
    }
}

