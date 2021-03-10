package com.hckj.product.microservice.service.es;

import com.hckj.common.elasticsearch.bean.BookBean;
import com.hckj.product.microservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Optional<BookBean> findById(String id) {
        //CrudRepository中的方法
        return bookRepository.findById(id);
    }

    public Page<BookBean> findByAuthor(String author) {
        return bookRepository.findByAuthor(author, Pageable.unpaged());
    }

    public BookBean save(BookBean blog) {
        return bookRepository.save(blog);
    }

    public void delete(BookBean blog) {
        bookRepository.delete(blog);
    }

    public Optional<BookBean> findOne(String id) {
        return bookRepository.findById(id);
    }

    public List<BookBean> findAll() {
        List<BookBean> list = new ArrayList<>();
        Iterator<BookBean> it = bookRepository.findAll().iterator();
        while (it.hasNext()) {
            list.add(it.next());
        }
        return list;
    }

}

