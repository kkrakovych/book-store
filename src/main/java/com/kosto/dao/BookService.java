package com.kosto.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository repository;

    @PersistenceContext
    protected EntityManager entityManager;

    protected Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<BookEntity> getAllBooks() {
        return repository.findAll();
    }

    @SuppressWarnings("unchecked")
    public List<BookEntity> getBookByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(BookEntity.class);
        criteria.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public BookEntity getBookByExactName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(BookEntity.class);
        criteria.add(Restrictions.eq("name", name));
        List<BookEntity> entities = criteria.list();
        if (entities != null && !entities.isEmpty()) {
            return entities.get(0);
        }
        return null;
    }

    public BookEntity getBookById(Long id) {
        Optional<BookEntity> book = repository.findById(id);
        return book.orElse(null);
    }

    public BookEntity createOrUpdateBook(BookEntity entity) {
        BookEntity book = getBookByExactName(entity.getName());

        if (book != null) {
            BookEntity newEntity = book;
            newEntity.setName(entity.getName());
            newEntity.setAuthor(entity.getAuthor());
            newEntity.setQuantity(newEntity.getQuantity() + entity.getQuantity());
            newEntity = repository.save(newEntity);
            return newEntity;
        } else {
            entity = repository.save(entity);
            return entity;
        }
    }

    public BookEntity sellBook(Long id) {
        BookEntity book = getBookById(id);
        entityManager.lock(book, LockModeType.OPTIMISTIC);
        if (book != null) {
            book.setQuantity(-1);
            createOrUpdateBook(book);
        }
        return book;
    }
}