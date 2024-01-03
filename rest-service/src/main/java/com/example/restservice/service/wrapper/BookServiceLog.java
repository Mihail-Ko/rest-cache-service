package com.example.restservice.service.wrapper;

import com.example.restservice.model.BookModel;
import com.example.restservice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceLog {

    private final BookService bookService;

    public BookModel addBook(BookModel book) {
        log.info("add book request");
        return bookService.addBook(book);
    }

    public List<BookModel> getAll(int pageN) {
        log.info("get books page request");
        return bookService.getAll(pageN);
    }

    public BookModel getOne(long id) {
        log.info("get book request");
        return bookService.getOne(id);
    }

    public long delete(long id) {
        log.info("delete book request");
        return bookService.delete(id);
    }

    public BookModel update(BookModel book) {
        log.info("update book request");
        return bookService.update(book);
    }
}