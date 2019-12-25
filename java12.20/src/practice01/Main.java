package practice01;

public class Main {
    public static void main(String[] args) {
        TestHeap obj = new TestHeap();
        int[] a={1,24,5,75,45,756,3,2};
        obj.createHeap(a);
        obj.show();
        obj.pushHeap(999);
        obj.show();
        obj.heapSort();
        obj.show();
    }
}
