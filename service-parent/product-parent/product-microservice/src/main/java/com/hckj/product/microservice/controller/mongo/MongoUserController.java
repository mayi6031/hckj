package com.hckj.product.microservice.controller.mongo;

import com.hckj.common.mongo.domain.model.user.User;
import com.hckj.common.mongo.domain.user.condition.UserCondition;
import com.hckj.common.mongo.page.MPage;
import com.hckj.common.web.DataResponse;
import com.hckj.product.microservice.service.impl.MongoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MongoUserController {

    @Autowired
    private MongoUserService userService;

    @PostMapping("/selectUserByName")
    public DataResponse<User> selectUserByName(@RequestBody UserCondition userCondition) {
        User user = userService.findByName(userCondition.getName());
        if (user == null) {
            return DataResponse.ok(null);
        }
        return DataResponse.ok(user);
    }

    @PostMapping("/selectUserByLikeName")
    public DataResponse<List<User>> selectUserByLikeName(@RequestBody UserCondition userCondition) {
        List<User> user = userService.findByLikeName(userCondition.getName());
        if (user == null) {
            return DataResponse.ok(null);
        }
        return DataResponse.ok(user);
    }

    @PostMapping("/selectUserByLikeNamePage")
    public DataResponse<MPage<User>> selectUserByLikeNamePage(@RequestBody UserCondition userCondition) {
        MPage<User> MPage = userService.findByLikeNamePage(userCondition.getName(), userCondition.getPageNo(), userCondition.getPageSize());
        return DataResponse.ok(MPage);
    }

    @PostMapping("/saveUser")
    public DataResponse<?> saveUser(@RequestBody User user) {
        userService.save(user);
        return DataResponse.ok(user.getId());
    }

    @PostMapping("/editUser")
    public DataResponse<?> editUser(@RequestBody User user) {
        userService.updateById(user);
        return DataResponse.ok(user.getId());
    }

    @PostMapping("/deleteUser")
    public DataResponse<?> deleteUser(@RequestBody String userId) {
        userService.deleteById(userId);
        return DataResponse.ok("");
    }

    @PostMapping("/listUser")
    public DataResponse<?> listUser() {
        List<User> list = userService.find();
        return DataResponse.ok(list);
    }

}

