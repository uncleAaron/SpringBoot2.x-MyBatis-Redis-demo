package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     *
     * @Cacheable : # 方法运行之前检查缓存，可能不会执行方法
     *      cacheNames/value : 指定要缓存到的缓存组件名(是个数组，可以指定多个缓存)
     *      key : 指定缓存数据key生成策略，可用SpEL指定（默认是方法参数值）（不能用#result）
     *      keyGenerator: 指定keyGenerator（key生成策略器），需要自己实现一个
     *      condition: 指定条件下才缓存（用SpEL指定）(, condition = "#id>0")
     *      unless: 默认缓存，除非遇到了某条件，就不缓存了
     *      sync: 是否异步模式（默认false同步缓存下，方法执行完才缓存），异步模式不支持unless
     * @return
     */
    @Cacheable(cacheNames = {"user"}, key = "#id")
    public User getUser(Integer id) {
        return userMapper.findUser(id);
    }

    public List<User> getAllUser() {
        return userMapper.findAllUser();
    }

    /**
     *
     * @Cacheput : # 既调用方法，又更新缓存（方法一定会执行）
     *      先调用目标方法
     *      再将目标方法的结果缓存（# 注意要key与查询key相同才是真正更新了缓存）
     * @return
     */
    @CachePut(cacheNames = {"user"}, key = "#user.id")
    public User updateUser(User user) {
        userMapper.updateUser(user);
        return user;
    }

    @CacheEvict(cacheNames = {"user"}, key = "#id")
    public int deleteUser(Integer id) {
        userMapper.deleteUser(id);
        return id;
    }

    @CachePut(cacheNames = {"user"}, key = "#user.id")
    public User addUser(User user) {
        userMapper.addUser(user);
        return user;
    }
}
