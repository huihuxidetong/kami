package com.card.inteface.service;

import com.card.inteface.entity.User;
import com.card.inteface.entity.UserCreditCard;
import com.card.inteface.vo.UserBasicVo;

import java.util.List;

/**
 * @notes:用户Service接口
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
public interface UserService  {

    /**
     * @notes 通过openId查询用户信息
     * @param openId
     */
    public User findUserByOpenId(String openId) throws Exception;

    /**
     * @notes 通过userId查询用户信息
     * @param userId
     */
    public User findUserByUserId(Integer userId) throws Exception;

    /**
     * @notes 保存用户信息
     * @param user
     */
    public void saveUser(User user) throws Exception;

    /**
     * @notes 更新用户信息
     * @param user
     */
    public void updateUser(User user) throws Exception;

    /**
     * @notes 保存用户信息
     * @param userBasicVo
     */
    public void saveUserUserBasicVo(UserBasicVo userBasicVo) throws Exception;

}
