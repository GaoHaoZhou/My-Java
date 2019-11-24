package booklibrary.operation;
import booklibrary.book.BookList;
public class ExitOperate implements IOperation {
    @Override
    public void work(BookList booklist) {
        System.out.println("退出系统");
        System.exit(1);
    }
}
