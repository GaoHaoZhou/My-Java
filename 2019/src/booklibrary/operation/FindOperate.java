package booklibrary.operation;
import booklibrary.book.*;
public class FindOperate implements IOperation {
    @Override
    public void work(BookList booklist) {
        System.out.println("查找图书");
        System.out.println("输入要查找的书名：");
        String name = scan.nextLine();
        Book book = null;
        int i =0;
        for(;i<booklist.getUsedSize();i++){
            book = booklist.getBooks(i);
            if(book.getName().equals(name)){
                break;
            }

        }
        if(i == booklist.getUsedSize()){
            System.out.println("没有找到书");
            return;
        }
        System.out.println("找到书");
        System.out.println(book);
    }
}
