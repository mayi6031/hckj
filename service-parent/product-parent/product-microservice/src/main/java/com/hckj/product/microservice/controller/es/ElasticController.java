package com.hckj.product.microservice.controller.es;

import com.hckj.common.elasticsearch.bean.BookBean;
import com.hckj.product.microservice.service.es.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ElasticController {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private BookService bookService;

    @GetMapping("/book/{id}")
    public BookBean getBookById(@PathVariable String id) {
        Optional<BookBean> opt = bookService.findById(id);
        BookBean book = opt.get();
        System.out.println(book);
        return book;
    }

    @GetMapping("/book/all")
    public List<BookBean> getAllBook() {
        List<BookBean> opt = bookService.findAll();
        System.out.println(opt);
        return opt;
    }

    @GetMapping("/book/author/{author}")
    public List<BookBean> getBookByAuthor(@PathVariable String author) {
        Page<BookBean> opt = bookService.findByAuthor(author);
        List<BookBean> book = opt.getContent();
        System.out.println(book);
        return book;
    }

    @PostMapping("/save")
    public void save(String id, String name, String author) {
        BookBean book = new BookBean(id, name, author, simpleDateFormat.format(new Date()));
        System.out.println(book);
        bookService.save(book);
    }


    @PostMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        BookBean book = new BookBean();
        book.setId(id);
        System.out.println(book);
        bookService.delete(book);
    }


}

