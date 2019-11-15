import java.util.Arrays;
//顺序表：采用数组的形式存储
public class ArrayList {
    private int[] elem;
    private int usedSize;
    private final int CAPACITY = 5;
    //构造方法,初始化
    public ArrayList(){
        this.elem = new int[CAPACITY];
        this.usedSize = 0;
    }

    //打印顺序表
    public void display(){
        for (int i=0;i<this.usedSize;i++){
            System.out.println(this.elem[i]+ " ");
        }
        System.out.println();
    }
    //判断顺序表是否满了，满了需要进行扩容
    //private外部对象不可直接调用
    private boolean isFull(){
        return this.usedSize==this.elem.length;
    }
    //在pos位置新增元素
    public void add(int pos,int data){
        if(isFull()){
            //二倍扩容
            this.elem = Arrays.copyOf(
                    this.elem,this.elem.length*2);
        }
        //判断插入位置是否合法//pos=usedSize时相当于在尾部插入
        if(pos<0 || pos>this.usedSize){
            System.out.println("该位置不合法");
            return;
        }
        //将pos位置及其后的元素全部向后移位
        for (int i=this.usedSize-1;i>=pos;i--){
            this.elem[i+1]=this.elem[i];
        }
        //插入数据
        this.elem[pos]=data;
        this.usedSize++;
    }
    //判断是否包含某个元素
    public boolean contains(int toFind){
        for (int i=0;i<this.usedSize;i++){
            if(toFind==this.elem[i]){
                return true;
            }
        }
        return false;
    }
    //查找某个元素对应的位置
    public int search(int toFind){
        for (int i=0;i<this.usedSize;i++){
            if(toFind==this.elem[i]){
                return i;
            }
        }
        return -1;//没找到
    }
    //获取pos位置的元素
    public int getPos(int pos){
        //先判断所输入位置是否合法
        if(pos<0 || pos>=this.usedSize){
            System.out.println("输入位置不合法");
            return -1;//-1代表没有pos位置
        }
        return this.elem[pos];
    }

    //给pos位置的元素赋值
    public void setPos(int pos,int value){
        //先判断所输入位置是否合法
        if(pos<0 || pos>=this.usedSize){
            System.out.println("输入位置不合法");
            return ;//-1代表没有pos位置
        }
        this.elem[pos]=value;
    }
    //删除第一次出现的关键字key
    public void remove(int toRemove){
        int index = search(toRemove);//获取该元素的位置
        if(index==-1){
            System.out.println("找不到该元素");
            return;
        }
        //index后面的元素前移覆盖要删除的元素
        for(int i=index;i<this.usedSize-1;i++){
            this.elem[i]=this.elem[i+1];  //最后一个i+1为usedSize-1处对应的值
       }
        this.usedSize--;

    }

    //获取顺序表的长度
    public int size(){
        return this.usedSize;
    }
    //清空数据表
    public void clear(){
        this.usedSize=0;
    }
}
