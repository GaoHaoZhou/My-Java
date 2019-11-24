package booklibrary.user;

import booklibrary.book.BookList;
import booklibrary.operation.*;
//import booklibrary.operation.ExitOperate;
//import booklibrary.operation.IOperation;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: GAOBO
 * Date: 2019-11-24
 * Time: 11:20
 */
public class Admin extends User{

    public Admin(String name) {
        super.name = name;
        super.operation = new IOperation[]{
                new ExitOperate(),
                new FindOperate(),
                new AddOperate(),
                new DelOperate(),
                new DisplayOperate()
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
        System.out.println("2.新增图书");
        System.out.println("3.删除图书");
        System.out.println("4.显示所有图书");
        System.out.println("0.退出系统");
        System.out.println("=========================");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return choice;
    }
}