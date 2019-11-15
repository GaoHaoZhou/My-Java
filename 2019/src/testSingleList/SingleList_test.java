package testSingleList;

public class SingleList_test {
    public static void main(String[] args) {
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
        mySingleList.addLast(1);
        mySingleList.addLast(2);
        mySingleList.addLast(1);
        mySingleList.addLast(1);
        mySingleList.display();
        ListNode node3 = mySingleList.deleteDuplication();
        mySingleList.display2(node3);
    }
}
