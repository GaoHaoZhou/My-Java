package practice01;

import java.util.Arrays;

public class TestHeap {
    public int[] elem;
    public int usedSize;
    public TestHeap(){
        this.elem = new int[10];
        this.usedSize=0;
    }
    //**********************************************************************
    //1 向下调整，建立一个大堆
    public void createHeap(int[] array){
        for (int i = 0; i <array.length ; i++) {
            this.elem[i] = array[i];
            this.usedSize++;
        }
        //this.usedSize-1为最后一个节点，
        // (最后一个节点-1)/2为最后一个子树的父节点
        for(int i=(this.usedSize-1-1)/2;i>=0;i--){
            //是每一棵树都按照向下调整的方式进行调整
            AdjustDown(i,this.usedSize);
        }
    }

    public void AdjustDown(int root,int len){
        int parent = root;
        int child = 2*parent+1;
        //child<len说明该父节点有孩子
        while(child<len){
            //child+1<len说明有右孩子，然后比较左右孩子大小，让child指向大值
            if(child+1<len&&this.elem[child]<this.elem[child+1]){
                child = child+1;
            }
            //child是左右孩子的最大值下标
            if(this.elem[child]>this.elem[parent]){
                //交换
                int tmp = this.elem[child];
                this.elem[child]=this.elem[parent];
                this.elem[parent]=tmp;
                //向下调整被打乱的子树顺序
                parent = child;
                child = 2*parent+1;
            }else{
                break;
            }
        }
    }

    //**********************************************************************
    //2 遍历堆
    public void show(){
        for (int i = 0; i <this.usedSize; i++) {
            System.out.print(this.elem[i]+" ");
        }
        System.out.println();
    }

    //**********************************************************************
    // 3 入堆,前提是已经是一个大根堆
    public boolean isFull(){
        return this.usedSize==this.elem.length;
    }

    public void pushHeap(int val){
        if(isFull()){
            //二倍扩容
            this.elem = Arrays.copyOf(this.elem,this.elem.length*2);
        }
        //先放到最后位置
        this.elem[this.usedSize] = val;
        this.usedSize++;
        //开始向上调整，this.usedSize-1指向最后一个位置
        AdjustUp(this.usedSize-1);
    }
    public void AdjustUp(int child) {
        int parent = (child-1)/2;
        while (child > 0) {
            if(this.elem[child] > this.elem[parent]) {
                int tmp = this.elem[child];
                this.elem[child] = this.elem[parent];
                this.elem[parent] = tmp;
                // 向上调整 child 指向 parent
                child = parent;
                parent = (child-1)/2;
            }else {
                break;
            }
        }
    }

    //**********************************************************************
    // 4 出堆
    public boolean isEmpty(){
        return this.usedSize==0;
    }
    public void popHeap(){
        if(isEmpty()){
            return;
        }
        //第一个元素和最后一个元素互换,用this.usedSize--剔除最后一个元素
        int tmp = this.elem[0];
        this.elem[0] = this.elem[this.usedSize-1];
        this.elem[this.usedSize-1]=tmp;
        this.usedSize--;
        //向下调整
        AdjustDown(0,this.usedSize);
    }

    //**********************************************************************
    // 4 获得堆顶元素
    public int getHeapTop(){
        if(isEmpty()){
            return -1;
        }
        return this.elem[0];
    }
    //**********************************************************************
    // 5  堆排序,从小到大，本例中的都是大根堆
    //时间复杂度n*log2n以2为底
    //空间复杂度o(1)
    //一次调整的时间复杂度log2n以2为底
    public void heapSort() {
        int end = this.usedSize-1;
        while (end > 0) {
            int tmp = this.elem[end];
            this.elem[end] = this.elem[0];
            this.elem[0] = tmp;
            //不断的将大元素往后排，最后完成从小到大的排序
            AdjustDown(0,end);
            end--;
        }
    }


}
