package com.card.inteface.dao;

import com.card.inteface.entity.UserCreditCard;
import org.springframework.stereotype.Component;

import com.card.inteface.entity.User;  

/**
 * @notes:用户Dao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Component
public interface UserDao {

    /**
     * @notes 通过openId查询用户信息
     * @param openId
     */
    public User findUserByOpenId(String openId) throws Exception;

    /**
     * @notes 保存用户信息
     * @param user
     */
    public void saveUser(User user) throws Exception;

    /**
     * @notes 通过userId查询用户信息
     * @param userId
     */
    public User findUserByUserId(Integer userId) throws Exception;

    /**
     * @notes 更新用户信息
     * @param user
     */
    public void updateUser(User user) throws Exception;

}
