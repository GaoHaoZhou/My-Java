package booklibrary.user;

//import booklibrary.operation.ExitOperate;
//import booklibrary.operation.IOperation;
import booklibrary.book.BookList;
import booklibrary.operation.*;
import java.util.Scanner;

public class NormalUser extends User {
    //构造方法
    public NormalUser(String name) {
        super.name = name;
        super.operation = new IOperation[]{
                new ExitOperate(),
                new FindOperate(),
                new BorrowOperate(),
                new ReturnOperate(),
        };
    }
    @Override
    public void doOperation(int choice, BookList bookList) {
        this.operation[choice].work(bookList);
    }
    @Override
    public int menu() {
        System.out.println("=========================");
        System.out.println("hello"+this.name+"欢迎使用图书管理系统");
        System.out.println("1.查找图书");
        System.out.println("2.借阅图书");
        System.out.println("3.归还图书");
        System.out.println("0.退出系统");
        System.out.println("=========================");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return choice;
    }
}
