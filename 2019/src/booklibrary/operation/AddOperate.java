package booklibrary.operation;
import booklibrary.book.*;
public class AddOperate implements IOperation{
    @Override
    public void work(BookList booklist) {
        System.out.println("添加图书");
        System.out.println("输入图书的名称：");
        String title = scan.next();
        System.out.println("输入图书的作者：");
        String name = scan.next();
        System.out.println("输入图书的价格：");
        int price = scan.nextInt();
        System.out.println("输入图书的类型：");
        String type = scan.next();
        Book book = new Book(title,name,price,type);
        int pos = booklist.getUsedSize();
        booklist.setBooks(pos,book);
        booklist.setUsedSize(pos+1);
    }
}
