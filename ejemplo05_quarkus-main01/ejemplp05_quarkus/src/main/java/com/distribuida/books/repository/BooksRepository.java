package com.distribuida.books.repository;

import com.distribuida.books.db.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class BooksRepository implements PanacheRepositoryBase<Book, Integer> {



}
