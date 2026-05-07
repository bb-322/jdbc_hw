package hw4.ex_003_hibernate_get_and_insert;

import hw4.ex_003_hibernate_get_and_insert.entity.Author;
import hw4.ex_003_hibernate_get_and_insert.entity.Book;

import java.util.List;

public class Main {

    static void main(String[] args) {
        AuthorHelper ah = new AuthorHelper();
//        String[] names = {"Pushkin", "Lermontov", "Shevchenko"};

//        for (String name : names) {
//            Author author = new Author();
//            author.setName(name);
//            ah.addAuthor(author);
//        }

        Author author = new Author();
        author.setName("testUser4");
        ah.addAuthor(author);


        List<Author> authorList = ah.getAuthorList();

        for (Author a : authorList) {
            System.out.println(a.getId() + " " + a.getName());
        }

        Author a = ah.getAuthorById(1);
        System.out.println(a.getName());

        // ------------------------------------------------------------------------

        BookHelper bh = new BookHelper();

        Book book = new Book();
        book.setName("testBook1");
        book.setAuthor_id(1);
        bh.addBook(book);


        List<Book> bookList = bh.getBookList();

        for (Book b : bookList) {
            System.out.println(b.getId() + " " + b.getName());
        }

        Book b = bh.getBookById(1);
        System.out.println(b.getName() + " " + b.getAuthor_id());

    }
}
