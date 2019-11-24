package booklibrary.operation;
import booklibrary.book.BookList;
import java.util.Scanner;
public interface IOperation {
    Scanner scan = new Scanner(System.in);
    void work(BookList booklist);
}
