package com.atguigu.mongodb.repository;

import com.atguigu.mongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *
 * </p>
 *
 * @Author chengankang
 * @Date 2022/11/26 14:20
 * @Description
 */
@Repository
public interface UserRepository extends MongoRepository<User,String> {
}
