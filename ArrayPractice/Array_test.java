package ArrayPractice;

import java.util.Arrays;



public class Array_test {

    public static void main(String[] args) {
        int[] arr = {1,5,6,7,8,121};
        transform(arr);
        System.out.println(toString(arr));
    }
    //偶数放在前面，奇数放到后面
    public static  void transform(int[] arr){
        int left =0;
        int right = arr.length-1;
        while (left<right) {
            //是奇数保持原位置不动，等待交换位置，是偶数则跳过判断下一个数
            while (left < right && arr[left] % 2 == 0) {
                left++;
            }
            //是偶数保持原位置不同,是奇数则跳过判断下一个数
            while (left < right && arr[right] % 2 != 0) {
                right--;
            }
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
        }
    }
    public static void main5(String[] args) {
        int[] arr = {1,5,6,7,8,121};
        reverse(arr);
        System.out.println(toString(arr));
    }
    //逆序排列
    public static void reverse(int[] arr){
        int left = 0;
        int right = arr.length-1;
        while(left<right){
            int tmp = arr[right];
            arr[right] = arr[left];
            arr[left] = tmp;
            left++;
            right--;
        }
    }

    public static void main4(String[] args) {
        int[] arr = {1,5,64,7,72,8,6,7,121};
        bubbleSort(arr);
        System.out.println(toString(arr));
    }
    //冒泡排序(升序)
    public static void bubbleSort(int[] arr){
        int len = arr.length;
        for(int i=0;i<len;i++){
            for(int cur = len-1;cur>i;cur--){
                if(arr[cur]<arr[cur-1]){
                    int tmp = arr[cur-1];
                    arr[cur-1] = arr[cur];
                    arr[cur] = tmp;
                }
            }
        }

    }
    public static void main3(String[] args) {
        //二分查找法
        int[] arr = {1,5,6,7,7,8,6,7,121};
        int index = binarySearch(arr,121);
        System.out.println(index);
    }
    //升序数组的二分查找
    public static int binarySearch(int[] arr,int num){
        int left = 0;
        int right = arr.length-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(num>arr[mid]){
                left = mid+1;
            }else if(num<arr[mid]){
                right = mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }
    public static void main2(String[] args){
        int[] arr = {1,5,6,7};
        int[] newarr = Arrays.copyOf(arr,arr.length*2);
        System.out.println(Arrays.toString(newarr));
    }
    public static void main1(String[] args){
        int[] arr = {1,5,6,7};
        System.out.println(arr);//打印地址
//        System.out.println(Arrays.toString(arr));
        System.out.println(toString(arr));


    }



    public static String toString(int[] arr){
        String ret = "[";
        for (int i=0;i<arr.length;i++){
            ret+=arr[i];
            if(i!=arr.length-1){
                ret+="*";
            }
        }
        ret+="]";
        return ret;
    }

}
