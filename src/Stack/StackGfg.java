package Stack;

import java.util.Stack;

public class StackGfg
{
    public static void main(String[] args) {
        int M[][] = {{0,1},
                     {1,0},
                     };
        System.out.println(celebrity(M, M.length));
    }
    static int celebrity(int M[][], int n)
    {
        System.out.println(M.length + " " + M[0].length);

        Stack<pair> stack = new Stack<>();
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if(M[i][j] == 1) {
                    if (!stack.isEmpty()) {
                        System.out.println(stack.peek().j == j);
                        if (stack.peek().j == j)
                            return j;
                    }
                    stack.push(new pair(i, j));
                }
            }
        }
        return -1;
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
/ delimiter class
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