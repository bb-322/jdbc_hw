package hw6.task35;

import hw6.task35.entity.Book;

import java.util.List;

public class Main {

    static void main(String[] args) {
        BookHelper bh = new BookHelper();
        List<Book> bookList = bh.getBookList();
        System.out.println(bookList);

        List<Book> bookList2 = bh.searchBooks("tBo");
        System.out.println(bookList2);
    }
}
