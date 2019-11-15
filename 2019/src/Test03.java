import java.util.Scanner;
import java.util.Random;
public class Test03 {
    //作业2：1
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入你的年龄:");
//        int age = sc.nextInt();
//        if (age>0 && age<=18){
//            System.out.println("少年");
//        }else if(age>=19 && age<=28){
//            System.out.println("青年");
//        }else if(age>=29 && age<=55){
//            System.out.println("中年");
//        }else {
//            System.out.println("老年");
//        }
//
//    }
//作业2:2 输入一个数判断素数
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入一个整数:");
//        int num = sc.nextInt();
//        int count=0;
//        for (int i=2;i<num;i++){
//            if(num%i==0){
//               count++;
//            }
//        }
//        if(count==0){
//            System.out.println(num+"是素数");
//        }else{
//            System.out.println(num+"不是素数");
//        }
//    }


    //作业2：3输出1-100间的所有素数:素数：质数定义为在大于1的自然数中，除了1和它本身以外不再有其他因数。
//    public static void main(String[] args){
//        for(int i=2;i<=100;i++){
//            isPrime(i);
//        }
//
//    }
//
//        public static void isPrime(int num){
//
//            int count=0;
//            for (int i=2;i<num;i++){
//                if(num%i==0){
//                count++;
//                }
//            }
//            if(count==0){
//             System.out.println(num);
//         }
//        }
    //作业2：4输出1000-2000之间的所有闰年
//    public static void main(String[] args){
//        for (int i=1000;i<=2000;i++){
//            isLeap(i);
//        }
//
//    }
//    public static void isLeap(int year){
//        if ((year%4==0 && year%100!=0)||(year%400==0)){
//            System.out.println(year);
//        }
//    }
    //作业2：5输出乘法口诀表
//    public static void main(String[] args){
//        for (int i=1;i<=9;i++){
//            for (int j=1;j<=i;j++){
//                System.out.print(j+"*"+i+"="+(i*j)+"\t");
//                if(i==j){
//                    System.out.println();
//                }
//            }
//        }
//
//    }
    //作业2: 6 求两个正整数的最大公约数
//    public static void main(String[] args){
//        System.out.println("请输入两个正整数:");
//        Scanner sc = new Scanner(System.in);
//        int num1 = sc.nextInt();
//        int num2 = sc.nextInt();
//        int temp;
//        int max=0;
//        //将最大的赋予num1
//        if (num1<num2){
//            temp = num1;
//            num1 = num2;
//            num2 = temp;
//        }
//        for(int i=num2;i>=1;i--){
//            if(num1%i==0 && num2%i==0){
//                max=i;
//                break;
//            }
//
//        }
//        System.out.println(num1+"和"+num2+"的最大公约数为:"+max);
//    }
    //作业2：7 计算1/1-1/2+1/3-....+1/99-1/100的值
//    public static void main(String[] args){
//        double sum=0;
//        for(int i=1;i<=100;i++){
//            if(i%2==0){
//                sum = sum-1.0/i;
//            }else{
//                sum = sum+1.0/i;
//            }
//        }
//        System.out.println("最终结果为："+sum);
//    }
    //作业2：8 1-100所有整数出现9的次数
//    public static void main(String[] args){
//        int count =0;
//        for (int i=1;i<=100;i++){
//            //个位是9
//            if(i%10==9){
//                count++;
//            }else if(i/10==9){
//                count++;
//            }
//        }
//        System.out.println("出现9的次数为:"+count);
//    }
    //作业2：9求取0`999之间的水仙花数并输出；
//    public static void main(String[] args){
//        for(int i=100;i<=999;i++){
//            int units = i%10;
//            int hun = i/100;
//            int tens = i/10%10;
//            double sum = Math.pow(units,3)+Math.pow(tens,3)+Math.pow(hun,3);
//            if (sum==i){
//                System.out.println(i);
//            }
//        }
//    }
    //作业2：10 模拟三次密码输入
//    public static void main(String[] arg){
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入密码:");
//        String passwad = "123456";
//        for(int i=0;i<=3;i++){
//            if (i==3){
//                System.out.println("exit");
//                break;
//            }
//            String num = sc.nextLine();
//            if (num==passwad){
//                System.out.println("登录成功");
//                break;
//            }else{
//                System.out.println("重新输入");
//            }
//
//        }
//}
    //作业2：11返回参数二进制中1的个数
//    public static void main(String[] arg) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入一个整数");
//        int num = sc.nextInt();
//        int result = renum(num);
//        System.out.println("二进制中1的个数:"+result);
//    }
//
//    public static int renum(int n) {
//        int count=0;
//        for (int i=0;i<32;i++){
//            if((n&1)==1){
//                count++;
//            }
//            n >>=1;
//        }
//        return count;
//    }
//作业2：14获取一个二进制中的所有偶数位和奇数位，分别输出二进制序列  *****

        public static void main(String[] args) {
            //首位为奇数位
            int num = 14;
            int check = 0;
            odd(num);
            System.out.println();
            even(num);
        }
        public static void odd(int num) {
            for (int i = 30; i >= 0; i = i - 2) {
                if ((num & (1 << i)) != 0) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
        }
        public static void even(int num) {
            for (int i = 31; i >= 0; i = i - 2) {
                if ((num & (1 << i)) != 0) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
        }
    }

    //作业2：13输出一个整数的每一位
//    public static void main(String[] arg) {
//        System.out.println("请输入一个整数:");
//        Scanner sc = new Scanner(System.in);
//        int num = sc.nextInt();
//        while(true){
//            int last = num%10;
//            num = num/10;
//            System.out.println(last);
//            if(num==0){
//                break;
//            }
//        }
//
//    }
//}
    //作业2：14完成猜字游戏
//    public static void main(String[] arg){
//        Random random = new Random();
//        Scanner sc = new Scanner(System.in);
//        int toGuss = random.nextInt(100);
//        while(true){
//            System.out.println("请输入数字整数1-100:");
//            int num = sc.nextInt();
//            if(num==toGuss){
//                System.out.println("猜对了");
//                break;
//            }else if(num<toGuss){
//                System.out.println("低了");
//            }else if(num>toGuss){
//                System.out.println("高了");
//            }
//        }
//    }
//}