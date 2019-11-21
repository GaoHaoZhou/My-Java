package testDoubleList;

public class DoubleList_test {

    public static void main(String[] args){

        DoubleList myDoubleList = new DoubleList();
        myDoubleList.addLast(1);
        myDoubleList.addLast(2);
        myDoubleList.addLast(3);
        myDoubleList.addLast(5);
        myDoubleList.addLast(4);
        myDoubleList.addLast(5);
        myDoubleList.addLast(5);
        myDoubleList.addLast(5);
//        myDoubleList.addIndex(2,6);
        myDoubleList.removeAll(5);
        myDoubleList.clear();
        myDoubleList.display();
    }
}
