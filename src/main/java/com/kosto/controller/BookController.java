package com.kosto.controller;

import com.kosto.dao.BookEntity;
import com.kosto.dao.BookService;
import com.kosto.model.BookDTO;
import com.kosto.model.BookEntityAdapter;
import com.kosto.model.ResponseBookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "findBook")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseBookDTO> findBook(@RequestParam String bookName) {
        List<BookEntity> entities = bookService.getBookByName(bookName);
        return entities.stream()
                .map(v -> new ResponseBookDTO(v.getId(), v.getName(), v.getAuthor(), v.getQuantity()))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "buyBookById")
    @ResponseStatus(HttpStatus.OK)
    public boolean buyBookBuId(@RequestParam Long bookId) {
        BookEntity entity = bookService.sellBook(bookId);
        return entity != null;
    }

}
