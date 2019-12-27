package practice01;

import java.util.Arrays;
import java.util.Random;

public class Main1 {

    public static void main1(String[] args) {
        SortTest obj = new SortTest();

        int[] array = new int[10000000];
        /*for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }*/
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100000);
        }

        //System.out.println(Arrays.toString(array));
        long time1 = System.currentTimeMillis();
        obj.quickSort(array);
        long time2 = System.currentTimeMillis();
        System.out.println(time2-time1);
        //System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        SortTest obj = new SortTest();
        int[] array = {12,5,9,34,6,8,33,56,89,0,7,4,22,55,77};
        System.out.println(Arrays.toString(array));
        obj.quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
