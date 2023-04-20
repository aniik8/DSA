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
}
