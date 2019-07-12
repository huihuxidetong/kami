package com.card.dao.impl;

import com.card.dao.DaoSupport;
import com.card.inteface.entity.UserCreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.dao.UserDao;  
import com.card.inteface.entity.User;  

/**
 * @notes:用户Dao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Repository
public class UserDaoImpl  implements UserDao {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * @notes 通过openId查询用户信息
     * @param openId
     */
    public User findUserByOpenId(String openId) throws Exception {
        return (User) daoSupport.findForObject("UserMapper.findUserByOpenId",openId);
    }

    /**
     * @notes 保存用户信息
     * @param user
     */
    public void saveUser(User user) throws Exception {
        daoSupport.save("UserMapper.insertSelective",user);
    }

    /**
     * @notes 通过userId查询用户信息
     * @param userId
     */
    public User findUserByUserId(Integer userId) throws Exception{
        return (User) daoSupport.findForObject("UserMapper.findUserByUserId",userId);
    }

    /**
     * @notes 更新用户信息
     * @param user
     */
    public void updateUser(User user) throws Exception {
        daoSupport.update("UserMapper.updateByPrimaryKeySelective",user);
    }

}