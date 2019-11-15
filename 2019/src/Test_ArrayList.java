public class Test_ArrayList {
    public static void main(String[] args){
        ArrayList ArrayList = new ArrayList();
        ArrayList.add(0,12);
        ArrayList.add(1,13);
        ArrayList.add(2,14);
        ArrayList.add(2,11);
        ArrayList.add(0,15);
        ArrayList.display();
        ArrayList.remove(12);
        ArrayList.display();
        ArrayList.remove(6);
        ArrayList.display();
        ArrayList.remove(15);
        ArrayList.display();
        System.out.println("清空");
        ArrayList.clear();
    }
}
