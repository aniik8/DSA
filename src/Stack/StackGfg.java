package Stack;
import java.util.*;
import java.util.Stack;

import static Stack.twoStacks.trappingWater;

public class StackGfg
{
    // 0 1 1 0 0 1 1 0 1 1 0 1 1 1 1 0
    public static void main(String[] args) {
        int M[][] = {{0,1,1,0},
                     {0,1,1,0},
                     {1,1,0,1},
                     {1,1,1,0}
                     };
        int[] arr = {4,2,0,3,2,5};
//        System.out.println(celebrity(M, M.length));
//        System.out.println(reverseWords("pqr.mno"));
//        System.out.println(findMaxLen("))()(()"));
        System.out.println(solve(10, 20, "lrrfrrprgprpppppmurr"));
        System.out.println(trappingWater(arr, arr.length));

        // 6 8
        //orzprqrd
    }
    static int celebrity(int M[][], int n)
    {
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<n;i++){
            stack.push(i);
        }
        while(stack.size()>1){
            int a = stack.peek();
            stack.pop();
            int b = stack.peek();
            stack.pop();
            if(M[a][b]==1){
                stack.push(b);
            }
            else{
                stack.push(a);
            }
        }
        int ans = stack.peek();
        stack.pop();
        for (int i=0;i<n;i++){
            if(i!=ans){
                if(M[ans][i]==1 || M[i][ans]==0){
                    return -1;
                }
            }
        }
        return ans;
    }


// "i.like.this.program.very.much"
    static String reverseWords(String S)
    {
     StringBuilder str = new StringBuilder();
     Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if(S.charAt(i) == '.')
            {
                while(stack.size() > 0)
                {
                    str.append(stack.pop());
                }
                str.append('.');
            }
            else{
                stack.push(S.charAt(i));
            }
        }
        while(stack.size() > 0)
        {
            str.append(stack.pop());
        }
        return str.toString();
    }
    // Max length valid parenthesis
    static int findMaxLen(String S) {
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        int max = 0;
        stack.push(-1);
        for (int i = 0; i < S.length(); i++) {
            if(S.charAt(i) == '(')
                stack.push(i);
            else{
                stack.pop();
                if(stack.isEmpty()) {
                    stack.push(i);
                }
                else{
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
    // string rp or pr
    // "abppprrr"
    // my approach - non p and r should not be pushed in the stack
    // if stack top is p - check or push p until we'll find r
    // if stack top is r - check or push r until we'll find p
    // "prpptppr"

    static long solve(int X,int Y, String S)
    {
        Stack<Character> stack = new Stack<>();
        int rp = 0;
        int pr = 0;
        for(int i = 0; i < S.length(); i++){
          if(stack.isEmpty())
              stack.push(S.charAt(i));
          else if(S.charAt(i) == 'p' && stack.peek() == 'r')
                    {
                        rp++; stack.pop();
                    }
          else if(S.charAt(i) == 'r' && stack.peek() == 'p'){
                        pr++; stack.pop();
                    }
          else{
              stack.push(S.charAt(i));
            }
        }
        return (pr * X + rp * Y);
    }
    // celebrity o(n) solution
    static int celebrityII(int M[][], int n)
    {
        //initializing two pointers for two corners.
        int a = 0;
        int b = n-1;
        //we keep moving till the a<b.
        while(a<b)
        {
            if(M[a][b] == 1)
                a++;
            else
                b--;
        }
        //checking if a is actually a celebrity or not.
        for(int i=0; i<n; i++)
        {
            //if any person doesn't know a or a knows any person, we return -1.
            if((i != a) && (M[a][i]==1 || M[i][a] == 0))
                return -1;
        }
        //if we reach here a is celebrity so we return a.
        return a;
    }
    // is valid parenthesis
    public boolean isValid(String s) {
        boolean flag = true;
        if(s.length() <= 1) return false; // for empty or string len=1;
        Stack<Character> parenthesis = new Stack<>();
        for(int i=0; i < s.length(); i++){
            char ch = s.charAt(i);
            switch (ch)
            {
                case '(':
                case '{':
                case '[':
                    parenthesis.push(ch);
                    break;
                case ')':
                case '}':
                case ']':
                    if(!parenthesis.isEmpty())
                    {
                        char chPop = parenthesis.pop();
                        if ((ch == '}' && chPop != '{')
                                || (ch == ')' && chPop != '(')
                                || (ch == ']' && chPop != '['))
                        {
                            return false;
                        }
                    }
                    else return false;  // prematurely empty
            }
        }
        if(!parenthesis.isEmpty()) return false;
        else return true;
    }
}
class pair{
    int i, j;
    pair(int i, int j){
        this.i = i;
        this.j = j;
    }

}
// delimiter class
class delimiterChecker{
    private int maxLength;
    private int topList;
    private char[] delimiterList;

    public delimiterChecker(int n){
        maxLength = n;
        topList = -1;
        delimiterList = new char[n];
    }
    public void pushStack(char ch){

        delimiterList[++topList] = ch;
    }
    public char popStack(){
        return delimiterList[topList--];
    }
    public boolean isEmpty()
    {
        return topList == -1;
    }
    public boolean isFull()
    {
        return topList == maxLength;
    }}

class GfG
{
    int minEle;
    Stack<Integer> stack;

    // Constructor
    GfG()
    {
        stack = new Stack<>();
        minEle = Integer.MAX_VALUE;
    }

    /*returns min element from stack*/
    int getMin()
    {if (stack.isEmpty()) {
        return -1;
    }
        return minEle;
    }

    /*returns poped element from stack*/
    int pop()
    {
        if (stack.isEmpty()) {
            return -1;
        }
        int poppedElement = stack.pop();
        if (poppedElement == minEle) {
            minEle = stack.pop();
        }
        return poppedElement;
    }

    /*push element x into the stack*/
    void push(int x)
    { if (x <= minEle) {
        stack.push(minEle);
        minEle = x;
    }
        stack.push(x);
    }
}
//  special stack
class GfG2{
    public void push(int a,Stack<Integer> s)
    {
        s.push(a);
    }
    public int pop(Stack<Integer> s)
    {
        if(isEmpty(s))
            return -1;
        int poppedElement = s.pop();
        return poppedElement;
    }
    public int min(Stack<Integer> s)
    {
        if(isEmpty(s))
            return -1;
        int minEle = s.pop();
        while(s.size() > 0){
            if(s.peek() < minEle){
                minEle = s.peek();
            }
            s.pop();
        }
        return minEle;
    }
    public boolean isFull(Stack<Integer>s, int n)
    {
        return s.size() == n;
    }
    public boolean isEmpty(Stack<Integer>s)
    {
        return s.size() == 0;
    }
}
// two stack using single array

class twoStacks {
    int top1;
    int top2;
    int[] arr;

    //Function to push an integer into the stack1.
    twoStacks() {
        arr = new int[100];
        top1 = -1;
        top2 = (arr.length / 2) - 1;
    }

    void push1(int x) {
        if (top1 >= (arr.length / 2))
            return;

        arr[++top1] = x;
    }

    //Function to push an integer into the stack2.
    void push2(int x) {
        arr[++top2] = x;
    }

    //Function to remove an element from top of the stack1.
    int pop1() {
        if (top1 < 0)
            return -1;
        return arr[top1--];
    }

    //Function to remove an element from top of the stack2.
    int pop2() {
        if (top2 <= (arr.length / 2) - 1)
            return -1;
        return arr[top2--];
    }

    // gfg trapping rain water
    // maxLeft, max right
    // min(maxLeft , maxRIght) - length_of_that and sum
    static long trappingWater(int arr[], int n) {
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];
        long sum = 0;
        maxLeft[0] = arr[0];
        maxRight[n - 1] = arr[n - 1];
        int maxleft = maxLeft[0], maxright = arr[n - 1];
        // calculate the maxLeft for each element
        for (int i = 1; i < n; i++) {
            maxleft = Math.max(maxleft, arr[i]);
            maxLeft[i] = maxleft;
        }
        for (int i = n - 2; i >= 0; i--) {
            maxright = Math.max(maxright, arr[i]);
            maxRight[i] = maxright;
        }
        for (int i = 0; i < n; i++) {
            sum += Math.min(maxLeft[i], maxRight[i]) - arr[i];
        }
        return sum;
    }
}
