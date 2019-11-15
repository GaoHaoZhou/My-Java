package teatArrayList;

public class test04_test {
    public static void main(String[] args){
        test04 arrayList = new test04();
        arrayList.add(0,1);
        arrayList.add(1,1);
        arrayList.add(1,2);
        arrayList.add(1,3);
        arrayList.display();
        //判断是否存在
        boolean contain=arrayList.contains(3);
        System.out.println(contain);
        //查找某元素对应的位置
        int position = arrayList.search(3);
        System.out.println(position);
        //获取pos位置的元素
        int posval = arrayList.getPos(1);
        System.out.println(posval);
        System.out.println();
        //给pos位置的元素赋值val
        arrayList.setPos(1,5);
        arrayList.display();
        System.out.println();
        //删除第一次出现的关键字
        arrayList.remove(1);
        arrayList.display();
        System.out.println();
        //获取数据表长度
        System.out.println(arrayList.size());
        System.out.println();
        //清空数据表
        arrayList.clear();
        arrayList.display();

    }
}
