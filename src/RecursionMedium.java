import java.util.ArrayList;

public class RecursionMedium {
    public static void main(String[] args) {
        System.out.println(NBitBinary(2));
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



