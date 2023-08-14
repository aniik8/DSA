package RecursionNdp;

import java.util.Arrays;

// basic questions, reversal, palindrome, number conversion, sorting ---> merge, bubble, selection, quicksort,book,etc
// tomorrow ---> medium --> tough level questions (include book ones too)
// 1. base condition --> n -1 condition --> n condition
public class Recursion {
    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 9, 7};
//        System.out.println(reversal_string("aniket"));
//        System.out.println(reverse_number(12345));
//        System.out.println(palindrome(12321));
//        toBinary(10);
//        bubbleSort(arr, arr.length);
//        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(mergeS(arr)));
    }

 // -------------------------------------------------------------------------------------------------------------------

// reversal of a string using recursion
    static String reversal_string(String str){
        return helperString(str);
    }
    static String helperString(String str){
        if(str.length() == 0)
            return "";
        return helperString(str.substring(1)) + str.charAt(0) ;
    }
    // reverse a number
    // 12345 ===> 54321
// -------------------------------------------------------------------------------------------------------------------
    static int reverse_number(int number){
        // count the digit in our number
        int n = number, count = 0;
        while(n != 0){
            n /= 10;
            count++;
        }

        return help_reverse(number, count-1);
    }
// -------------------------------------------------------------------------------------------------------------------
    static boolean palindrome(int number){
        // count the digit in our number
        int n = number, count = 0;
        while(n != 0){
            n /= 10;
            count++;
        }

        return number == help_reverse(number, count-1);
    }
// -------------------------------------------------------------------------------------------------------------------
    // 12345  --- 54321 ==> 50000 + 4000 + 300 + 20 + 1
    // 12345 % 10 == 5 --> 5 x 10000 (10 , count)
    static int help_reverse(int number, int count){
        if(number == 0)
            return 0;
        int n = number % 10;
        int num = n * (int)Math.pow(10, count);
        return  num +  help_reverse(number/10, count-1) ;
    }
// -------------------------------------------------------------------------------------------------------------------
    // number conversion from decimal to binary
    static void toBinary(int N) {
        String num = convert_binary(N);
        System.out.println(num);
    }
    static String convert_binary(int N){
        if(N == 0)
            return "";
        return  convert_binary(N/2) + "" + (N%2) ;
    }
// -------------------------------------------------------------------------------------------------------------------
// sorting algorithm
// bubble sort
// {4, 1, 3, 9, 7}
    public static void bubbleSort(int arr[], int n)
    {
        bubbleSort_helper(arr, n);
        System.out.println(Arrays.toString(arr));
    }
    static void bubbleSort_helper(int[] arr, int n){
        if(n == 1)
            return;
        for (int i = 0; i < n - 1; i++) {
            if(arr[i]> arr[i+1])
            {
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        bubbleSort_helper(arr, n-1);
    }
// -------------------------------------------------------------------------------------------------------------------
    // Merge sort -
    // This function will be used to break arrays into halves so that it can send to mergeSort function
    static void merge(int arr[], int l, int m, int r)
    {
        // two pointers , i , j where i <= m and j <= r
        int[] merger = new int[arr.length];
        int i = 0, j = m + 1, k = 0;
        while(i <= m && j <= r){
            if(arr[i] <= arr[j])
            {
                merger[k++] = arr[i++];
            }else merger[k++] = arr[j++];
        }
        while(i <= m){
            merger[k++] = arr[i++];
        }
        while(j <= r)
            merger[k++] = arr[j++];
        for(int index=0, index2=l; index< merger.length; index++, index2++) {
            arr[index2]=merger[index];
        }
    }
    static void mergeSort(int arr[], int l, int r)
    {
        //code here
        if(l >= r)
            return;
        int mid = (l + r )/2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid+1, r);
        merge(arr, l, mid, r);
    }
    // merge sort second way
    static int[] mergeS(int[] arr){
        if(arr.length == 1)
            return arr;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid+1, arr.length);
        return mergeSorting(left, right);
    }
    static int[] mergeSorting(int[] arr1, int[] arr2){
        int i = 0, j = 0, k = 0;
        int[] combination = new int[arr1.length+ arr2.length];
        while(i < arr1.length && j < arr2.length){
            if(arr1[i] <= arr2[j]){
                combination[k++] = arr1[i++];
            }else{
                combination[k++] = arr2[j++];
            }
        }
        while(i < arr1.length){
            combination[k++] = arr1[i++];
        }
        while(j < arr2.length){
            combination[k++] = arr2[j++];
        }
        return combination;
    }

}
