public class RecursionEasy {
    public static void main(String[] args) {

    }

    // gfg practice
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
}
