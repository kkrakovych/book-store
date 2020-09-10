package com.kosto.model;

import com.kosto.dao.BookEntity;

public class BookEntityAdapter {

    private BookDTO bookDTO;

    public BookEntityAdapter(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    public BookEntity getBookEntity() {
        BookEntity entity = new BookEntity();
        entity.setName(bookDTO.getName());
        entity.setAuthor(bookDTO.getAuthor());
        entity.setQuantity(bookDTO.getQuantity());
        return entity;
    }
}
