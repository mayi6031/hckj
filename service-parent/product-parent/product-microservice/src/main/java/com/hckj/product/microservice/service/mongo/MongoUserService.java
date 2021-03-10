package com.hckj.product.microservice.service.mongo;

import com.hckj.common.mongo.base.BaseMongoImpl;
import com.hckj.common.mongo.domain.model.user.User;
import com.hckj.common.mongo.page.MPage;
import com.hckj.common.mongo.utils.MongoUtil;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现类
 *
 * @author ：yuhui
 * @date ：Created in 2020/9/28 16:39
 */
@Service
public class MongoUserService extends BaseMongoImpl<User> {

    public User findByName(String name) {
        User user = uniqueByCondition("name", name);
        return user;
    }

    public List<User> findByLikeName(String name) {
        Query query = new Query();
        query.addCriteria(MongoUtil.getLikeCriteria("name", name));
        List<User> user = findByCondition(query);
        return user;
    }

    public MPage<User> findByLikeNamePage(String name, int pageNo, int pageSize) {
        Query query = new Query();
        query.addCriteria(MongoUtil.getLikeCriteria("name", name));
        MPage<User> page = findByPage(query, pageNo, pageSize);
        return page;
    }
}
