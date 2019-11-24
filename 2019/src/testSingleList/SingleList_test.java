package testSingleList;

public class SingleList_test {

    //将两个有序单链表合并成为一个新的有序单链表
    public static ListNode  mergeTwoLists(ListNode headA, ListNode headB){
        ListNode newHead = new ListNode(-1);
        ListNode tmp = newHead;
        while (headA!=null && headB!=null){
            if(headA.data<headB.data){
                tmp.next = headA;
                headA = headA.next;
                tmp = tmp.next;
            }else{
                tmp.next = headB;
                headB = headB.next;
                tmp = tmp.next;
            }
        }
        if(headA == null){
            tmp.next = headB;
        }
        if(headB == null){
            tmp.next = headA;
        }
        return newHead.next;
    }

    //判断一个链表是否有交点,并返回交点
    public static ListNode getIntersectionNode
    (ListNode headA, ListNode headB) {
        if(headA==null || headB==null){
            return null;
        }
        ListNode pL = headA;//让pL指向长的链表
        ListNode pS = headB;
        int lenA =0;
        int lenB =0;
        while(pL!=null){
            lenA++;
            pL = pL.next;
        }
        while(pS!=null){
            lenB++;
            pS = pS.next;
        }
        pL = headA;
        pS = headB;
        int len = lenA-lenB;
        if(len<0){
            pL = headB;
            pS = headA;
            len = lenB - lenA;
        }
        //让pL先走n步
        while(len>0){
            pL = pL.next;
            len--;
        }
        //开始一起走，如果最后pL为null,即认为没有交点
        while(pL!=pS){
            pL = pL.next;
            pS = pS.next;
        }
        //如果没有交点，while循环则是因为pL pS皆为null才退出的
        if(pL==null){
            return null;
        }
        return pL;

    }

    //创建一个有交点的，单链表
    public static void createCut
    (ListNode headA, ListNode headB) {
        headA.next = headB.next.next;
    }

    //重要！！复制带随机指针的链表   在OJ上做，OJ提供的ListNode有三个域
    /**
    public ListNode copyRandomList(ListNode head){
        if(head == null){
            return  null;
        }
        //老新进行交替连接
        ListNode cur = head;
        while (cur != null){
            ListNode node  = new ListNode(cur.val, cur.next, null);
            ListNode tmp = cur.next;
            cur.next = node;
            cur = tmp;
        }
        //2. 修改random
        cur = head;
        while(cur!=null){
            if(cur.random != null){
                cur.next.random = cur.random.next;
                cur = cur.next.next;
            }else{
                cur = cur.next.next;
            }
        }
        //3.将新老节点打开
        cur = head;
        ListNode  newhead = cur.next;
        while(cur.next != null){
            ListNode tmp = cur.next;
            cur.next = tmp.next;
            cur = tmp;
        }
        return newHead;
    }
    */
    public static void main(String[] args) {
        /**
        SingleList mySingleList = new SingleList();
        mySingleList.addFirst(1);
        mySingleList.addFirst(2);
        mySingleList.addFirst(3);
        mySingleList.display(); //3 2 1
        mySingleList.addLast(4);
        mySingleList.addLast(5);
        mySingleList.display();//3 2 1 4 5
       //插入元素 超范围
//        boolean a = mySingleList.addIndex(8,10);
//        System.out.println(a);
        //插入元素
        boolean b = mySingleList.addIndex(0,10);
        System.out.println(b);
        mySingleList.display();//10 3 2 1 4 5
        //查看关键字是否存在
        boolean c=mySingleList.contains1(10);
        System.out.println(c);
        //删除第一次出现的关键字key
        mySingleList.remove(4);
        mySingleList.display();//10 3 2 1 5

        mySingleList.addIndex(5,5);
        mySingleList.display(); //10 3 2 1 5 5

        //删除所有关键字key=5的节点
        mySingleList.removeallkey(5);
        mySingleList.display(); //10 3 2 1

        //清空链表
//        mySingleList.clear();
//        mySingleList.display();

        //反转单链表并打印
//        ListNode newhead = mySingleList.reverseList();
//        mySingleList.display2(newhead); // 1 2 3 10

        //返回中间节点
        ListNode middle = mySingleList.middleNode(); //此函数不能用反转之后的头来求中间节点
        System.out.println(middle.data);

        //返回倒数第几个数
        ListNode node  =  mySingleList.findKth(3);
        System.out.println(node.data);

        //返回倒数第几个数，只遍历一遍链表 10 3 2 1
        ListNode node1  =  mySingleList.findKth2(4);
        System.out.println(node1.data);

        //以2为基准分割链表，
//        ListNode node2 = mySingleList.partition(2);
//        mySingleList.display2(node2); //有一个新头需要调用display

        //删除重复节点
//        mySingleList.addLast(1);
//        mySingleList.addLast(2);
//        mySingleList.addLast(1);
//        mySingleList.addLast(1);
//        mySingleList.display();
//        ListNode node3 = mySingleList.deleteDuplication();
//        mySingleList.display2(node3); // 10 3 2 2

        mySingleList.clear();
        mySingleList.display();
        mySingleList.addLast(1);
        mySingleList.addLast(2);
        mySingleList.addLast(2);
        mySingleList.addLast(1);
        //判断是否为回文结构
//        boolean flg = mySingleList.chkPalindrome();
//        System.out.println(flg);

        //判断是否有环
        mySingleList.creteLoop();
        boolean flg = mySingleList.hasCycle();
        System.out.println(flg);

        //返回入环节点
        ListNode node3 = mySingleList.detectCycle();
        System.out.println(node3.data);  //2

        //清空
        mySingleList.clear();
        mySingleList.display();
        */
        //连接两个有序链表，成为一个新的有序链表
        SingleList mySingleList1 = new SingleList();
        mySingleList1.addLast(1);
        mySingleList1.addLast(2);
        mySingleList1.addLast(3);
        mySingleList1.addLast(7);
        mySingleList1.addLast(8);
        mySingleList1.addLast(9);
        mySingleList1.display();

        SingleList mySingleList2 = new SingleList();
        mySingleList2.addLast(4);
        mySingleList2.addLast(5);
        mySingleList2.addLast(6);
        mySingleList2.addLast(11);
        mySingleList2.addLast(12);
        mySingleList2.addLast(13);
        mySingleList2.display();
        ListNode newhead1 = mergeTwoLists(mySingleList1.head,mySingleList2.head);
        mySingleList2.display2(newhead1);

        //判断连个链表是否相交
        //创建一个有交点的单链表
        createCut(mySingleList1.head,
                mySingleList2.head);

        ListNode node = getIntersectionNode(mySingleList1.head,
                mySingleList2.head);
        System.out.println(node.data);//6
    }
}
