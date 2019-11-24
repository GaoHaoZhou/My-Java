package booklibrary.user;
import booklibrary.operation.*;
import booklibrary.book.*;
public abstract class User {
    String name;
    IOperation operation[];
    public abstract int menu();
    public abstract void doOperation(int choice,BookList bookList);
}
