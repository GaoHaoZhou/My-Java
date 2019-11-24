package booklibrary.operation;
import booklibrary.book.Book;
import booklibrary.book.BookList;
public class DelOperate implements IOperation {
    @Override
    public void work(BookList booklist) {
        System.out.println("删除图书");
        System.out.println("输入要删除的书籍");
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
        for(int j=i;j<booklist.getUsedSize()-1;j++){
            book = booklist.getBooks(j+1);
            booklist.setBooks(j,book);
        }
        int curSize = booklist.getUsedSize();
        booklist.setUsedSize(curSize-1);
        System.out.println("删除成功");
    }
}
