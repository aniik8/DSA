package Recursion;
import java.util.*;

public class Recursion2 {
    public static void main(String[] args) {
//        System.out.println(skipAchar("aniketsharmabanduni", 'a'));
//        subsequence("abc", "");
        int[] arra = {6,7,5,3,5,6,2,9,1,2,7,0,9};
//        System.out.println(subsequenceList("abc", ""));
//        System.out.println(subsets(arra));
//        System.out.println(Arrays.toString(recoverArray(3, arra)));

//        System.out.println(subsetsWithDup(arra));
//        find_permutations("", "abc");
//        System.out.println(find_permutation("abb"));
//        System.out.println(permute(arra));
//        System.out.println(letterCombinations("9"));
        nextPermutation(arra);
    }

    static String skipAchar(String str, char ch)
    {   String string = "";
        return skip(str, ch, string);
    }

    static String skip(String str, char ch, String string){
        if(str.length() == 0)
            return string;
        if(str.charAt(0) == ch)
            return skip(str.substring(1), ch, string);
        return skip(str.substring(1),ch, string + str.charAt(0));
    }
// -------------------------------------------------------------------------------------------------------------------
    static void subsequence(String str, String up){
        if(str.isEmpty()) {
            System.out.println(up);
            return;
        }
        subsequence(str.substring(1), up+str.charAt(0));
        subsequence(str.substring(1), up);
    }
// -------------------------------------------------------------------------------------------------------------------
    static ArrayList<String> subsequenceList(String str, String up){
        if(str.isEmpty()){
            ArrayList<String> list = new ArrayList<>();
            list.add(up);
            return list;
        }
        ArrayList<String> leftList = subsequenceList(str.substring(1), up + str.charAt(0));
        ArrayList<String> rightlist = subsequenceList(str.substring(1), up);
        leftList.addAll(rightlist);
        return leftList;
    }
// subset of an array questions
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> answer = new ArrayList<>();
        subsetArray(nums, 0, output, answer);
        return output;
    }
    static void subsetArray(int[] nums, int index, List<List<Integer>> output, List<Integer> answer){
        if(index == nums.length) {
            output.add(new ArrayList<>(answer));
            return;
        }
        answer.add(nums[index]);
        subsetArray(nums, index+1, output, answer);
    //excluding condition
        answer.remove(answer.size() - 1);
        subsetArray(nums, index+1, output, new ArrayList<>(answer));

    }
    //
    static public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> answer = new ArrayList<>();
        subsetsWithDup2(nums, 0, output, answer);
        return output;
    }
    static void subsetsWithDup2(int[] nums, int index, List<List<Integer>> output, List<Integer> answer){
            output.add(new ArrayList<>(answer));

        for (int i = index; i < nums.length; i++) {
            if(i > index && nums[i] == nums[i-1]) {
                continue;
            }
            answer.add(nums[i]);
            subsetsWithDup2(nums, index+1, output, answer);
            answer.remove(answer.size() - 1);
        }
    }
// Permutation -- here number if function calls are variable like for each processed there are processed+1 calls
    // p == "" , up = "abc"
     public static void find_permutations(String p, String up){
        if(up.length() == 0) {
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String left = p.substring(0, i);
            String right = p.substring(i, p.length());
            find_permutations(left+ch+right, up.substring(1));
        }
    }
    //
    public static List<String> find_permutation(String S) {
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        permutations(list, "", S);
        Collections.sort(list);
        // just for repeating elements otherwise leave it for non repeating..
        for (int i = 0; i < list.size(); i++) {
            if(!list2.contains(list.get(i)))
                list2.add(list.get(i));
        }
        return list2;
    }

    static void permutations(List<String> list, String p, String up){
        if(up.length() == 0) {
            list.add(p);
            return;
        }
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String left = p.substring(0, i);
            String right = p.substring(i, p.length());
            permutations(list, left + ch + right, up.substring(1));
        }
    }
    // permutations using arrays ===>
    // first + array[i] + third

    public static List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        permute_array(nums, output, list);
        return output;
    }
    static void permute_array(int[] nums,  List<List<Integer>> output, List<Integer> list) {
        if(list.size() == nums.length){
            output.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(list.contains(nums[i])) continue;
            list.add(nums[i]);
            permute_array(nums, output, list);
            list.remove(list.size()-1);
        }
    }
    // phone pad question here
    //
    static public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
       combinationhelper(digits, "", list);
       return list;
    }
    static void combinationhelper(String unprocessed, String processed, List<String> list){
        if(unprocessed.length() == 0)
        {
            list.add(processed);
            return;
        }
        int digit = unprocessed.charAt(0) - '0';
        int i=(digit - 2) * 3;
        if( digit > 7) i+=1;
        int len=i + 3;
        if( digit == 7 || digit == 9) len+=1;
        for (; i < len; i++) {
            combinationhelper( unprocessed.substring(1),processed + (char)('a'+ i), list);
        }
}
    // 31 leetcode
    public static void nextPermutation(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        Arrays.sort(nums);
        int index=0;
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> answer = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        uniquePermutations(nums, output, answer, used);
        for (int i = 0; i < output.size(); i++) {
            if(output.get(i).equals(list))
                index = i;
        }
        int x = (index + 1) % output.size();
        for (int i = 0; i < list.size(); i++) {
            nums[i] = output.get(x).get(i);
        }
        System.out.println(Arrays.toString(nums));
    }

    static void uniquePermutations(int[] nums, List<List<Integer>> output, List<Integer> answer, boolean[] used){
        if(answer.size() == nums.length)
        {
            output.add(new ArrayList<>(answer));
            return;
        }
        for(int i = 0; i < nums.length; i++)
        {
            if(used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i-1]))
            {
                continue;
            }
            used[i] = true;
            answer.add(nums[i]);
            uniquePermutations(nums, output, answer, used);
            used[i] = false;
            answer.remove(answer.size() - 1);
        }
    }
}
