package hw5.task6;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static void main(String[] args) {
        AuthorHelper ah = new AuthorHelper();
        List<Author> authorList = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            Author a = new Author();
            a.setName("testAuthor " + (i + 1));
            authorList.add(a);
        }

        ah.addManyAuthors(authorList);

    }
}
