package com.atguigu.mongodb;

import com.atguigu.mongodb.entity.User;
import com.atguigu.mongodb.repository.UserRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@SpringBootTest
class DemomogoApplicationTest1 {

    //注入mongoTemplate
    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    //添加
    @Test
    public void createUser() {
        User user = new User();
        user.setAge(20);
        user.setName("李四");
        user.setEmail("3332200@qq.com");
        User user1 = userRepository.save(user);
        System.out.println(user1);
    }

    //查询所有
    @Test
    public void findUser() {
        List<User> userList = userRepository.findAll();
        System.out.println(userList);
    }

    //id查询
    @Test
    public void getById() {
        User user = userRepository.findById("6381b16341fa0c53086a8917").get();
        System.out.println(user);
    }

    //条件查询
    @Test
    public void findUserList() {
        User user = new User();
        user.setName("李四");
        user.setAge(20);
        Example<User> userExample = Example.of(user);
        List<User> userList = userRepository.findAll(userExample);
        System.out.println(userList);
    }

    //模糊查询
    @Test
    public void findUsersLikeName() {
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写
        User user = new User();
        user.setName("四");
        Example<User> userExample = Example.of(user, matcher);
        List<User> userList = userRepository.findAll(userExample);
        System.out.println(userList);
    }

    //分页查询
    @Test
    public void findUsersPage() {
        Sort sort = Sort.by(Sort.Direction.DESC, "age");
        //0为第一页
        Pageable pageable = PageRequest.of(0, 10, sort);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写
        User user = new User();
        user.setName("四");
        Example<User> userExample = Example.of(user, matcher);
        //创建实例
        Example<User> example = Example.of(user, matcher);
        Page<User> pages = userRepository.findAll(example, pageable);
        System.out.println(pages);
    }

    //修改
    @Test
    public void updateUser() {
        User user = userRepository.findById("6381b16341fa0c53086a8917").get();
        user.setName("李四_1");
        user.setAge(25);
        user.setEmail("883220990@qq.com");
        User save = userRepository.save(user);
        System.out.println(save);
    }

    //删除
    @Test
    public void delete() {
        userRepository.deleteById("6381b16341fa0c53086a8917");
    }

}
