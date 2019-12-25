package practice;

public class SortDemo {
    //*********************************************************
   //1 向下调整，创建一个大堆
   public void createHeap(int[] array){
       for(int i=(array.length-1-1)/2;i>=0;i--){
           adjustDown(array,i,array.length);
       }
   }
   public void adjustDown(int[] array,int root,int len){
       //时间复杂度log2n
       int parent = root;
       int child = 2*parent+1;
       while(child<len){
           if(child+1<len&&array[child]<array[child+1]){
               child++;
           }
           //child保存的是最大值的下标
           if(array[child]>array[parent]){
               int tmp = array[child];
               array[child] = array[parent];
               array[parent] = tmp;
               parent = child;
               child=2*parent+1;
           }else{
               break;
           }
       }
   }
    //*********************************************************
    //2 堆排序(默认为大堆)，从小到大，第一个和最后一个交换
    /*
     * 时间复杂度为：n*log2n  都是
     * 空间复杂度：O(1)
     * 建堆的时间复杂度：n*log2n
     * 一次调整的时间复杂度：log2n
     * 稳定性：不稳定的排序算法
     */
    public  void heapSort(int[] array){
        createHeap(array);//传入一个数组，建立一个大堆
        int end = array.length-1;
        while(end>0){
            int tmp = array[end];
            array[end] = array[0];
            array[0] = tmp;
            //end=array.length-1;
            adjustDown(array,0,end);
            end--;
        }
    }
    //*********************************************************
    // 3 插入排序
    /**
     * 时间复杂度：
     * 最好：O(n)   -->有序的时候   当数据越有序 越快
     * 最坏：O(n^2)
     * 空间复杂度：O(1)
     * 稳定性：稳定的排序
     * @param array
     */
    public void insertSort(int[] array){
        for (int i = 1; i <array.length ; i++) {
            int tmp = array[i];
            //tmp浮空，j依次和tmp比较向后移动
            int j=0;
            for( j=i-1;j>=0;j--){
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
    // 4 希尔排序
    /**
     * 时间复杂度：O(n^1.3-1.5)
     * 空间复杂度：O(1)
     * 稳定性：不稳定的排序
     * @param array
     */
    public void shellSort(int[] array) {
        //增量排序，第一次分5组进行选择排序，第二次分3组，最后对总的array数组
        //进行排序
        int[] drr = {5,3,1};
        for (int i = 0; i < drr.length; i++) {
            shell(array,drr[i]);
        }
    }

    public  void shell(int[] array,int gap){
        for(int i=gap;i<array.length;i++){
            int tmp =array[i];
            int j=0;
            for(j=i-gap;j>=0;j-=gap){
                if(array[j]>tmp){
                    array[j+gap]=array[j];
                }else{
                    break;
                }
            }
            array[j+gap]=tmp;
        }
    }

    //*********************************************************
    // 5 选择排序
    //依次找最小值，放入数组前
    public void selectSort(int[] array){
        for (int i = 0; i < array.length ; i++) {
            for(int j=i+1;j<array.length;j++){
                if(array[j] < array[i]) {
                    int tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                }
            }
        }
    }
}
