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
}
