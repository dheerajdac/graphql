package com.graphql.graphql.provider;

import com.graphql.graphql.model.Author;
import com.graphql.graphql.model.Book;

import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GraphQLDataFetchers {

    private static final List<Book> books;

    private static final List<Author> authors;

    static {
        books = new ArrayList<>();
        authors = new ArrayList<>();
        
        Book book1 = new Book();
        book1.setId("book-1");
        book1.setName("test book 1");
        book1.setAuthorId("author-1");

        Book book2 = new Book();
        book2.setId("book-2");
        book2.setName("test book 2");
        book2.setAuthorId("author-2");

        Book book3 = new Book();
        book3.setId("book-3");
        book3.setName("test book 3");
        book3.setAuthorId("author-3");

        books.add(book1);
        books.add(book2);
        books.add(book3);

        Author author1 = new Author();
        author1.setId("author-1");
        author1.setFirstName("firstName");
        author1.setLastName("lastName");

        authors.add(author1);

    }

    public DataFetcher<Book> getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books
                    .stream()
                    .filter(book -> book.getId().equals(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher<Author> getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Book book = dataFetchingEnvironment.getSource();
            String authorId = book.getAuthorId();
            return authors
                    .stream()
                    .filter(author -> author.getId().equals(authorId))
                    .findFirst()
                    .orElse(null);
        };
    }
}