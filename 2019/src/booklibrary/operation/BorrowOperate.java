package booklibrary.operation;
import booklibrary.book.Book;
import booklibrary.book.BookList;
public class BorrowOperate implements IOperation {
    @Override
    public void work(BookList booklist) {
        System.out.println("借图书");
        System.out.println("请输入书名");
        String name = scan.next();
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
        book = booklist.getBooks(i);
        if(book.isBorrowed()==true){
            System.out.println(book);
        }else{
            book.setBorrowed(true);
            System.out.println("借阅成功");
        }

    }
}
