package practice01;

import java.util.Stack;

public class SortTest {
    //*********************************************************
    // 6 冒泡排序
    /**
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 稳定性：稳定
     * @param array
     */
    public static void bubbleSort(int[] array){
        //外层for循环控制冒泡的次数，每一次冒泡的完成代表，完成一个数字有序排序
       for (int i = 0; i <array.length-1 ; i++) {
           //内层for循环控制每次冒泡进行交换的次数
           //如果在一次冒泡的过程中，一次都没有进行交换，则说明
           //该数组已有序，退出冒泡排序即可
           boolean flg = false;
            for (int j = 0; j <array.length-1-i ; j++) {
                if(array[j]>array[j+1]){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    flg = true;
                }
            }
            if(!flg){
                break;
            }
        }
    }


    //*********************************************************
    // 7 快速排序
    /**
     * 时间复杂度：O(nlog2n)    最坏情况：O(n^2) 数据有序
     * 空间复杂度：O(log2n)
     * 稳定性：不稳定
     * @param array
     */
    public void quickSort(int[] array){
        quick(array,0,array.length-1);
    }
    public void quick(int[] array,int low, int high){
        if(low>=high){
            return;
        }
        //依据递归的思想，每次都是找基准点，划分区间
        int pivot = partion(array,low,high);
        quick(array,low,pivot-1);
        //如果pivot为最后一个元素，此时pivot+1将会有low大于high的情况
        quick(array,pivot+1,high);
    }
    public int partion(int[] array,int low,int high){
        int tmp =array[low];
        while(low<high){
            //右边找小于tmp的元素，赋给array[low]
            while((low<high)&&array[high]>=tmp){
                high--;
            }
            if(low>=high){
                break;
            }else{
                array[low] = array[high];
            }
            //左边找大于tmp的元素,赋给array[high]
            while((low<high)&&array[low]<=tmp){
                low++;
            }
            if(low>=high){
                break;
            }else{
                array[high] = array[low];
            }
        }
        array[low] = tmp;
        return low;
    }

    //*********************************************************
    // 7.1 快速排序优化1——设置阈值，如果区间长度小于该阈值，则用直接插入排序
    //因为，区间越有序，直接插入排序性能越高，为O(n)

    public void quickSort1(int[] array){
        quick1(array,0,array.length-1);
    }

    public void quick1(int[] array,int low,int high){
        if(low>=high){
            return;
        }
        //区间长度小于该阈值，则用直接插入排序
        if(high-low+1<100){
            insertSort(array,low,high);
            return;
        }
        int pivot = partion(array,low ,high);
        quick1(array,low,pivot-1);
        quick1(array,pivot+1,high);
    }
    public void insertSort(int[] array,int low ,int high){
        for (int i = low+1; i <=high ; i++) {
            int tmp = array[i];
            int j =0;
            for ( j =i-1; j >=low; j--) {
                if(array[j]>tmp){
                    array[j+1]=array[j];
                }else{
                    break;
                }
            }
            array[j+1] = tmp;
        }
    }

    //*********************************************************
    // 7.2 快速排序优化2 在区间已经趋于有序的情况下，也是快排最坏的情况
    //时间复杂度为O(n^2),

    public void quickSort2(int[] array){
        quick2(array,0,array.length-1);
    }

    public void quick2(int[] array,int low,int high){
        if(low>=high){
            return;
        }
        ThreeNumOfMiddle(array,low,high);
        int pivot = partion(array,low ,high);
        quick1(array,low,pivot-1);
        quick1(array,pivot+1,high);
    }

    public static void swap(int[] array,int low,int high) {
        int tmp = array[low];
        array[low] = array[high];
        array[high] = tmp;
    }
    public static void ThreeNumOfMiddle
            (int[] array,int low,int high) {
        //构造这种关系array[mid] <= array[low] <= array[high];
        //让中间值最小
        int mid = (low+high)/2;
        //array[mid] <= array[high]
        if(array[mid] > array[high]) {
            swap(array,mid,high);
        }
        //array[mid] <= array[low]
        if(array[mid] > array[low]) {
            swap(array,mid,low);
        }
        //array[low] <= array[high]
        if(array[low] > array[high]) {
            swap(array,low,high);
        }
    }

    //**********************************************************
    // 8 快排的递归形式
    public  void quickSort3(int[] array) {
        quick3(array,0,array.length-1);
    }
    public  void quick3(int[] array,int low,int high) {
        int pivot = partion(array,low,high);
        Stack<Integer> stack = new Stack<>();
        //>low+1保证左边有两个元素以上
        if(pivot > low+1 ) {
            stack.push(low);
            stack.push(pivot-1);
        }
        //<high-1保证右边有两个元素以上
        if(pivot < high-1) {
            stack.push(pivot+1);
            stack.push(high);
        }
        while (!stack.empty()) {
            high = stack.pop();
            low = stack.pop();
            pivot = partion(array,low,high);
            if(pivot > low+1 ) {
                stack.push(low);
                stack.push(pivot-1);
            }
            if(pivot < high-1) {
                stack.push(pivot+1);
                stack.push(high);
            }
        }
    }

}
