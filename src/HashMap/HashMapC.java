package HashMap;

import java.util.HashMap;

public class HashMapC {
    public static void main(String[] args) {
        int[] arr = {1,3,1,-1,3};
        System.out.println(singleNumber(arr));
    }
    public static int singleNumber(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i])+1);
            }
            else{
                map.put(nums[i], 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if(map.get(nums[i]) == 1){
                return nums[i];
            }
        }
        return nums[nums.length-1];
    }
    public int singleNumberII(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i])+1);
            }
            else{
                map.put(nums[i], 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if(map.get(nums[i]) == 1){
                return nums[i];
            }
        }
        return nums[nums.length-1];
    }
    // single number 3 leetcode
    public int[] singleNumberIII(int[] nums) {
        int n = nums.length, a = 0;
        int[] ans = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i])+1);
            }
            else{
                map.put(nums[i], 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if(map.get(nums[i]) == 1){
                ans[a++] = nums[i];
                if(a > 2){
                    return ans;
                }
            }
        }
        return ans;
    }
}
