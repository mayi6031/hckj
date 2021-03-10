package com.hckj.product.microservice.repository;//package com.hckj.product.microservice.service.es;

import com.hckj.common.elasticsearch.bean.BookBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 接口关系：
 * ElasticsearchRepository --> ElasticsearchCrudRepository --> PagingAndSortingRepository --> CrudRepository
 */
public interface BookRepository extends ElasticsearchRepository<BookBean, String> {

    Page<BookBean> findByAuthor(String author, Pageable pageable);

}
