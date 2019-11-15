
//节点类，每个节点拼起来就是一个单链表
class ListNode{
    public int data;//以整形数据举例i
    public ListNode next;  //节点对象
    //不带参数的构造方法
    public ListNode(int data){
        this.data = data;
        this.next = null;
    }
}
//单链表由节点组成，每个节点有两个域
public class SingleList {
    public ListNode head;//标志头
    //初始化head
    public SingleList(){
        this.head = null;
    }
    //头插法
    public void addFirst(int data){
        ListNode node = new ListNode(data);
        if(this.head == null){
            this.head = node;
        }else {
            node.next = this.head;
            this.head = node;
        }
    }
    //尾插法
    public void addLast(int data){
        ListNode node = new ListNode(data);
        ListNode cur = this.head;  //cur代表尾巴
        if(this.head == null){
            this.head =node;
        }else{
            //1.找尾巴
            while(cur.next != null){
                cur = cur.next;
            }
            //2.进行插入
            cur.next = node;
        }
    }

     //找到index-1位置的节点  返回当前节点的引用
    private ListNode searchIndex(int index){
        ListNode prev = this.head;
        int count = 0;
        while(count<index-1){
            prev = prev.next;
            count++;
        }
        return prev;
    }
    //获取数据长度
    public int getLength(){
        int count = 0;
        ListNode cur = this.head;
        while(cur!=null){  //单链表最后一个节点为空的
            count++;
            cur = cur.next;
        }
        return count;
    }
    //插入到index位置
    public boolean addIndex(int index,int data){
        //下标不合法 //index=length时相当于尾插法
        if(index<0 || index >getLength()){
            return false;
        }
        //头插法1.什么都没有 2.有数据但插入位置为index=0
        if(index == 0){
            addFirst(data);
            return true;
        }
        ListNode prev = searchIndex(index);//返回插入位置前的坐标
        ListNode node = new ListNode(data);
        node.next = prev.next;
        prev.next = node;
//        return false;
        return true;
    }

    //打印单链表数据
    public void display(){
        if(this.head == null){
            return;
        }
        ListNode cur = this.head;
        while (cur!=null){
            System.out.print(cur.data+" ");
            cur = cur.next;
        }
        System.out.println();
    }

    //查找是否包含关键字key在单链表中
    public boolean contains(int key){
        ListNode cur = this.head;
        while(cur!=null){
            if(cur.data==key){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public ListNode contains1(int key){
        ListNode cur = this.head;
        while(cur!=null){
            if(cur.data==key){
                return cur;  //找到数据，返回当前节点的引用
            }
            cur = cur.next;
        }
        return null;
    }
    //返回关键字对应的前一个节点的引用(前驱)
    public ListNode searchPrev(int key){
        ListNode prev = this.head;
        while (prev.next != null){
            if(prev.next.data==key){
                return prev;
            }
            prev = prev.next;
        }
        return null;
    }

    //删除第一次出现关键字为key的节点
    public void remove(int key){
        //1.删除的节点如果时头节点
        if(this.head.data == key){
            this.head = this.head.next;
            return;
        }
        //2找到所删除节点的前一个节点，如果找不到，返回null
        ListNode prev = searchPrev(key);
        if(prev == null){
            System.out.println("没有你要删除的节点");
            return;
        }
        ListNode del = prev.next;//del为要删除的节点
        //3进行删除
        prev.next = del.next;//让后一个节点覆盖要删除的节点
    }

    //删除所有值为key的节点
    public void removeAllKey(int key){
        ListNode prev = this.head;
        ListNode cur = this.head.next;
        while(cur!=null){
            if(prev.next.data==key){
                prev.next = cur.next;
                cur = cur.next;
            }else {
                prev = cur;
                cur = cur.next;
            }
        }
        //处理头节点等于key的情况
        if(this.head.data == key){
            this.head = this.head.next;//如果只有一个节点时head.next值为null
        }
    }

    //清空链表，一次次清空
    public void clear(){
        while (this.head.next !=null){
            ListNode cur = this.head.next;
            this.head.next =cur.next;
        }
        this.head = null;
    }
     //一次清空
//  public void clear(){
//        this.head = null;
//    }
}
