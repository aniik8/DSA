import java.util.ArrayList;
import java.util.List;

public class RecursionMedium {
    public static void main(String[] args) {
        System.out.println(NBitBinary(2));
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
}



