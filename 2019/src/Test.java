public class Test {
    /**
     * javac -encoding UTF-8 Test.java
     * java Test hello world
     * @param args
     */
    public static void main(String[] args) {
        for (int i=0; i<args.length;i++){
            System.out.println(args[i]);//args获取命令行输入参数
        }
        int a=10;
        System.out.println("hello world");
        //System.out.println("%d\n",a);
        System.out.print("hello world");  //不换行
    }
}
