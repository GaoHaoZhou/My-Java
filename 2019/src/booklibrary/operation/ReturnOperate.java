package booklibrary.operation;
import booklibrary.book.Book;
import booklibrary.book.BookList;
public class ReturnOperate implements IOperation {
    @Override
    public void work(BookList booklist) {
        System.out.println("退还图书");
        System.out.println("输入要退还的书名：");
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
            System.out.println("该书不存在");
            return;
        }
        book = booklist.getBooks(i);
        if(book.isBorrowed()==false){
            System.out.println("已经归还");
        }else{
            book.setBorrowed(false);
            System.out.println("归还成功");
        }
    }
}
