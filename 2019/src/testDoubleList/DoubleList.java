package testDoubleList;

 class ListNode{
     public int data;
     ListNode prev;
     ListNode next;
     public ListNode(int data){
         this.data = data;
     }
}


public class DoubleList {
    public ListNode head;
    public ListNode last;
    //头插法
    public void addFirst(int data){
        ListNode node = new ListNode(data);
        if (this.head == null){
            this.head = node;
            this.last = node;
        }else{
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        }
    }

    //尾插法
    public void addLast(int data){
        ListNode node = new ListNode(data);
        if (this.head == null){
            this.head = node;
            this.last = node;
        }else{
            node.prev = this.last;
            this.last.next = node;
            this.last = node;
        }
    }

    //
    public void display(){
        ListNode cur = this.head;
        while(cur!=null){
            System.out.print(cur.data+" ");
            cur = cur.next;
        }
        System.out.println();
    }

    //任意位置插入
    public int getLength(){
        int count=0;
        ListNode cur = this.head;
        while(cur!=null){
            cur = cur.next;
            count++;
        }
        return count;
    }
    //2.找到第index个位置的前驱
    public ListNode searchIndex(int index){
        if(index<=0 || index>=getLength()){//去掉结尾插入和开头插入的情况
            System.out.println("输入有误");
            return null;
        }
        ListNode cur = this.head;
        while(index-1>0){
            index--;
            cur = cur.next;
        }
        return cur;


    }
    public void addIndex(int index,int data){
        if(index == 0){
            addFirst(data);
            return;
        }
        if(index == getLength()){
            addLast(data);
            return;
        }
        //中间插入
        ListNode node = new ListNode(data);
        ListNode cur = searchIndex(index);
        if(cur==null){
            return;
        }
        node.prev = cur;
        node.next = cur.next;
        cur.next.prev = node;
        cur.next = node;
    }
    //查找是否包含关键字key在链表中
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

    //删除第一次出现关键字为Key的节点
    public int remove(int key){
        ListNode cur = this.head;
        while(cur!=null){
            if(cur.data==key){
                int oldData = cur.data;//记录删除的值
                if(cur==this.head){
                    this.head = this.head.next;
                    this.head.prev = null;
                }else{//尾巴和中间
                    cur.prev.next = cur.next;
                    if(cur.next==null){ //删除尾巴的情况
                        this.last = cur.prev;
                    }else{
                        cur.next.prev = cur.prev;
                    }
                }
                return oldData;
            }
            cur = cur.next;
        }
        return -1;
    }

    //删除所有值为key的节点,和删除第一次出现的值方法相同，只不过去掉了return
    public void removeAll(int key){
        ListNode cur = this.head;
        while(cur!=null){
            if(cur.data==key){
                if(cur==this.head){
                    this.head = this.head.next;
                    this.head.prev = null;
                }else{//尾巴和中间
                    cur.prev.next = cur.next;
                    if(cur.next==null){ //删除尾巴的情况
                        this.last = cur.prev;
                    }else{
                        cur.next.prev = cur.prev;
                    }
                }
            }
            cur = cur.next;
        }
    }

    //清空链表
    public void clear(){
        while(this.head!=null){
            ListNode cur = this.head.next;
            this.head.next = null;
            this.head.prev = null;
            this.head = cur;
        }
        this.last = null;
    }

}
