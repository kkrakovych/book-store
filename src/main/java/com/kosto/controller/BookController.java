package com.kosto.controller;

import com.kosto.dao.BookEntity;
import com.kosto.dao.BookService;
import com.kosto.model.BookDTO;
import com.kosto.model.BookEntityAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/addBook")
    @ResponseStatus(HttpStatus.OK)
    public void addBook(@RequestBody BookDTO bookDTO) {
        System.out.println(bookDTO);
        BookEntityAdapter bookEntityAdapter = new BookEntityAdapter(bookDTO);
        bookService.createOrUpdateBook(bookEntityAdapter.getBookEntity());
    }

    @PostMapping(value = "findBook")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> findBook(@RequestBody String bookName) {
        List<BookEntity> entities = bookService.getBookByName(bookName);
        return entities.stream()
                .map(v -> new BookDTO(v.getName(), v.getAuthor(), v.getQuantity()))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "buyBookById")
    @ResponseStatus(HttpStatus.OK)
    public boolean buyBook(@RequestBody Long bookId) {
        // TODO: add the operation
        return false;
    }

}
