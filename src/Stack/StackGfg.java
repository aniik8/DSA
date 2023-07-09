package Stack;

import java.util.Stack;

public class StackGfg
{
    // 0 1 1 0 0 1 1 0 1 1 0 1 1 1 1 0
    public static void main(String[] args) {
        int M[][] = {{0,1,1,0},
                     {0,1,1,0},
                     {1,1,0,1},
                     {1,1,1,0}
                     };
        System.out.println(celebrity(M, M.length));
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