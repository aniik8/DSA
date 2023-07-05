package Stack;

import java.util.Stack;

public class StackGfg
{
    public static void main(String[] args) {
        int M[][] = {{0,1},
                     {1,0}};
        System.out.println(celebrity(M, M.length));
    }
    static int celebrity(int M[][], int n)
    {
        Stack<pair> stack = new Stack<>();
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if(M[i][j] == 1)
                    if(!stack.isEmpty())
                    {
                        if(stack.peek().i == i || stack.peek().j == j)
                            return j;
                    }
                    stack.push(new pair(i, j));
            }
        }
        return -1;
    }
}
class pair{
    int i, j;
    pair(int i, int j){
        this.i = i;
        this.j = j;
    }

}
