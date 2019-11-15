package teatArrayList;

import java.util.Arrays;

/**
 * Created with IDEA
 * 顺序表
 * User：ROCK
 * Date:2019-4-25
 * Time:15:25
 */
public class test04 {
    private int[] elem;
    private int usedSize;
    private final int CAPACITY = 5;
    public test04(){
        this.elem = new int[CAPACITY];
        this.usedSize = 0;
    }

    //方法
    public void display(){
        for(int i=0;i<this.usedSize;i++){
            System.out.println(this.elem[i]+" ");
        }
//        System.out.println();
    }
    private boolean isFull(){
        return this.usedSize == this.elem.length;
    }
    //在pos位置新增元素
    public void add(int pos,int data){
        if(isFull()){
            //扩容
            this.elem = Arrays.copyOf(
                    this.elem,this.elem.length*2);
        }
        //判断位置是否合法
        if(pos<0 || pos>this.usedSize){
            System.out.println("输入位置不合法");
            return;
        }

        for (int i=this.usedSize-1;i>=pos;i--){
            this.elem[i+1]=this.elem[i];
        }
        this.elem[pos]=data;
        this.usedSize++;
    }
    //判断是都包含某元素
    public boolean contains(int toFind){
        for(int i=0;i<=this.usedSize-1;i++){
            if(this.elem[i]==toFind){
                return true;
            }
        }
        return false;
    }
    //查找某元素对应的位置
    public int search(int toFind){
        for(int i=0;i<this.usedSize;i++){
            if(this.elem[i]==toFind){
                return i;
            }
        }
        return -1;
    }

    //获取pos位置的元素
    public int getPos(int pos){
        if(pos<0 || pos>usedSize-1){
            System.out.println("输入位置不合法");
            return -1;
        }
        return this.elem[pos];
    }

    //给pos位置的元素赋值
    public void setPos(int pos, int val){
        if(pos<0 || pos>usedSize-1){
            System.out.println("输入位置不合法");
            return;
        }
        this.elem[pos]=val;
    }

    //删除第一次出现的关键字key
    public void remove(int key){
        //返回key对应的位置
        int index = search(key);
        if(index==-1){
            System.out.println("找不到对应的元素");
            return;
        }
        for(int i=index;i<usedSize-1;i++){
            this.elem[i]=this.elem[i+1];
        }
        this.usedSize--;
    }

    //获取顺序表长度
    public int size(){
        return this.usedSize;
    }

    //清空顺序表
    public void clear(){
        this.usedSize = 0;
    }
}
