package booklibrary;

import booklibrary.book.BookList;
import booklibrary.user.Admin;
import booklibrary.user.NormalUser;
import booklibrary.user.User;

import java.util.Scanner;

public class Main {

    public static User login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入姓名：");
        String name = scanner.nextLine();
        System.out.println("请输入你的身份：1-》管理员 0：普通用户");
        int who = scanner.nextInt();
        if(who == 1) {
            return new Admin(name);
        }else {
            return new NormalUser(name);
        }
    }

    public static void main(String[] args) {
        //准备书籍
        BookList bookList = new BookList();
        //登录  返回对象  Admin    NormalUser
        User user =  login(); //调用login返回用户对象
        while (true) {
            int choice = user.menu();//显示对应用户菜单，并返回选项
            //如何去对应操作  Admin    NormalUser
            user.doOperation(choice, bookList);
        }
    }
}


