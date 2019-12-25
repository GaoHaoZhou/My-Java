package practice;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        SortDemo obj = new SortDemo();
        int[] a = {3,4,54,1,100,66,35,24,2};
        obj.createHeap(a);


        int[] array = new int[10000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10000);
        }
        System.out.println(Arrays.toString(array));
        long time1 = System.currentTimeMillis();
//        obj.insertSort(array);
//        obj. shellSort(array);
//        obj.selectSort(array);
        //heapSort先创建了一个大堆，再堆排序
        obj.heapSort(array);
        long time2 = System.currentTimeMillis();
        System.out.println(time2-time1);
        System.out.println(Arrays.toString(array));
    }
}
