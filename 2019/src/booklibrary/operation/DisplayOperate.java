package booklibrary.operation;
import booklibrary.book.*;
public class DisplayOperate implements IOperation {
    @Override
    public void work(BookList booklist) {
        System.out.println("显示图书");
        for(int i =0;i<booklist.getUsedSize();i++){
            Book book = booklist.getBooks(i);
            System.out.println(book);
            }
        }
}
