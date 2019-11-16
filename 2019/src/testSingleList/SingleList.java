package testSingleList;

import java.util.List;
//定义节点类
class ListNode{
    public int data;
    public ListNode next;  //类比于int a = 10; 用于存储ListNode类型的数据
    public ListNode(int data){
        this.data = data;
        this.next = null;
    }
}

public class SingleList {

    public ListNode head;
    public SingleList(){
        this.head = null;
    }

    //头插法
    public void addFirst(int data){
        ListNode node = new ListNode(data); //创建一个新节点 并用node保存当前节点的地址
        if(this.head==null){
            this.head=node;
            return;
        }
        node.next = this.head;
        this.head = node;
    }
    //尾插法
    public void addLast(int data){
        ListNode node = new ListNode(data);
        ListNode cur = this.head;
        if(this.head==null){
            this.head=node;
            return;
        }
        //找尾巴
        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = node;

    }
    //打印单链表数据
    public void display(){
        System.out.println("输出为:");
        if(this.head==null){
            return;
        }
        ListNode cur = this.head;
        while (cur != null){
            System.out.print(cur.data+" "); //不换行
            cur = cur.next;
        }
        System.out.println();
    }

    //找到任意位置插入元素，重要的是要找到前驱
    //1.重要的是找前驱
    private ListNode searchPrev(int index){
        ListNode prev = this.head;
        for(int i=0;i<index-1;i++){
            prev = prev.next;
        }
        return prev;
    }
    public int getLength(){
        ListNode cur  = this.head;
        int count=0;
        while(cur!=null){
            count++;
            cur = cur.next;
        }
        return count;
    }
    public boolean addIndex(int index, int data){
        if(index<0 || index >getLength()){  //==getLength时相当于在结尾插入
            System.out.println("索引超出有效范围");
            return false;
        }
        //在第一个节点插入，没有前驱的情况下
        if(index==0){
            addFirst(data); //相当于头插法
            return true;
        }
        ListNode prev = searchPrev(index);
        ListNode node = new ListNode(data);
        node.next = prev.next;
        prev.next = node;
        return true;
    }

    //查看是否包含关键字Key是否在单链表中
    public boolean contains1(int key){
        ListNode cur = this.head;
        while(cur!=null){
            if (cur.data==key){
                return  true;
            }
            cur = cur.next;
        }
        return false;
    }
    //查看是否包含关键字key，返回当前节点
    public ListNode contains2(int key){
        ListNode cur = this.head;
        while(cur!=null){
            if(cur.data == key){
                return cur;
            }
        }
        return null;
    }

    //删除第一次出现的关键字key的节点，重要的是先找到前驱
    private ListNode searchPrev1(int key){
        ListNode prev = this.head;
        while(prev.next!=null){
            if(prev.next.data==key){
                return prev;
            }
            prev = prev.next;
        }
        return null;
    }
    public void remove(int key){
        //1.删除的节点是头节点
        if(this.head.data==key){
            this.head=this.head.next;
            return;
        }
        //2.找到前驱
        ListNode prev = searchPrev1(key);
        if(prev == null){
            System.out.println("没有你要删除的节点");
            return;
        }
        //3.进行删除
        ListNode del = prev.next;
        prev.next = del.next;
    }

    //删除所有值为key的点
    public void removeallkey(int key){
        ListNode prev = this.head;
        ListNode cur = this.head.next;
        while(cur!=null){
            if(cur.data==key){
                prev.next = cur.next;
                cur = cur.next;
            }else{
                prev = cur;
                cur = cur.next;
            }
        }
        if(this.head.data==key){
            this.head = this.head.next;
        }
    }
    //清空链表
    public void clear(){
        //this.head = null;  //暴力直接清除
        while (this.head.next != null){
            ListNode cur = this.head.next;
            this.head.next = cur.next;
        }
        this.head = null;
    }

    //反转单链表,返回新的头节点
    public ListNode reverseList(){
        ListNode prev = null;
        ListNode cur = this.head;
        ListNode newhead = null;
        while(cur!=null){
            ListNode curNext = cur.next;
            if(curNext==null){
                newhead = cur;
            }
            cur.next=prev;
            prev = cur;
            cur = curNext;
        }
        return newhead;
    }
    //打印反转之后的单链表
    public void display2(ListNode newhead){
        if(newhead==null){
            return;
        }
        ListNode cur = newhead;
        while (cur!=null){
            System.out.print(cur.data+" ");
            cur = cur.next;
        }
        System.out.println();
    }

    //返回中间节点，如果中间有两个则返回右边的一个
    public ListNode middleNode(){
        //遍历了两边
//        int len  = getLength()/2;
//        ListNode cur = this.head;
//        for(int i=0;i<len;i++){
//            cur = cur.next;
//        }
//        return cur;
        //只遍历一遍完成中间节点的求取。
        ListNode fast = this.head;
        ListNode low = this.head;
        //两种情况：偶数个节点，奇数个节点
        while(fast != null && fast.next != null ){ //注意短路效应，如果将fast.next放到前面将会出现，null.next的情况，所以会报错
            fast = fast.next.next;
            low = low.next;
        }
        return low;
    }
    //返回倒数第K个节点
    public ListNode findKth(int k){
        if(k<=0 || k>getLength()){  //用了getlength相当于又遍历了一遍链表
            return null;
        }
        ListNode fast = this.head;
        ListNode low = this.head;
        //先让fast走k-1步
        while(k-1>0){
            fast = fast.next;
            k--;
        }
        //在让fast和low一起走
        while(fast.next!=null){
            fast = fast.next;
            low = low.next;
        }
        return low;
    }

    //返回倒数第K个节点，只需遍历一遍链表
    public ListNode findKth2(int k){
        if (k<=0){
            return null;
        }
        ListNode fast = this.head;
        ListNode low = this.head;
        //fast先走k-1步
        while(k-1>0){
            if(fast.next != null){
                fast = fast.next;
                k--;
            }else{
                System.out.println("没有这个节点");
                return null;
            }
        }
        while(fast.next != null){
            fast = fast.next;
            low = low .next;
        }
        return low;
    }

    //以x为基准分割链表
    public ListNode partition(int x){
        ListNode bs = null;
        ListNode be = null;
        ListNode as = null;
        ListNode ae = null;
        ListNode cur = this.head;
        while (cur!=null){
            if(cur.data<=x){
                //判断是否是第一次插入
                if(bs == null){
                    bs = cur;
                    be = bs;
                }else{
                    be.next = cur;
                    be = be.next;
                }
            }else{
                //判断是否为第一次插入
                if(as == null){
                    as = cur;
                    ae = as;
                }else{
                    ae.next = cur;
                    ae = ae.next;
                }
            }
           cur = cur.next;
        }
        //第一个区间没有数据
        if(bs==null){
            return as;
        }
        be.next = as;
        //as 不为空时，需将最后一个节点置为空
        if(as != null){
            ae.next = null;
        }
        return bs;
    }

    //删除重复节点,删除相邻的重复节点
    public ListNode deleteDuplication(){
        if(this.head == null){
            return null;
        }
        ListNode cur = this.head;
        ListNode newhead = new ListNode(666);
        ListNode tmp = newhead;
        while(cur != null){
            if(cur.next!=null && cur.data == cur.next.data){
                while(cur.next!=null && cur.data==cur.next.data){
                    cur = cur.next;
                }
                cur = cur.next; //跳出重复的节点
            }else{
                tmp.next = cur;
                tmp = tmp.next;
                cur = cur.next;
            }
        }
        tmp.next = null;
        return newhead.next; //返回头节点
    }

    //8 判断是否为回文结构
    public boolean chkPalindrome(){
        //判断是否为回文，需要先改变链表结构
        if(this.head == null){
            return false;
        }
        if(this.head.next == null){
            return false;
        }
        //找到中间节点
        ListNode fast = this.head;
        ListNode slow = this.head;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        //循环完之后的slow即为中间节点

        //反转中间节点后的链表
        ListNode cur = slow.next;
        while(cur!=null){
            ListNode curNext = cur.next;//要先将原来的cur.next节点保存下来，要不然将会找不到下一个节点
            cur.next = slow;
            slow = cur;
            cur = curNext;
        }

        //判断回文结构slow向前走，head向后走
        while(slow!=this.head){
            if(slow.data!=this.head.data){
                return false;
            }
            if(this.head.next == slow){
                return true;
            }
            slow = slow.next;
            this.head = this.head.next;
        }
        //遍历完成都没有发现错误
        return true;
    }

    //10判断一个链表是否为环
    //创造一个有环的链表
    public void creteLoop(){
        ListNode cur = this.head;
        //让cur指向最后一个节点
        while (cur.next != null){
            cur = cur.next;
        }
        //让最后一个节点的null指向一个节点
        cur.next = this.head.next.next;
    }
    public boolean hasCycle(){
        ListNode fast = this.head;
        ListNode slow = this.head;
        while(fast != null && fast.next !=null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                break;
            }
        }
        if(fast == null || fast.next ==null){
            return false;
        }
        return true;
    }

    //返回入环节点
    public ListNode detectCycle(){
        ListNode fast = this.head;
        ListNode slow = this.head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                break;
            }
        }
        if(fast == null || fast.next == null){
            return null;
        }
        slow = this.head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
