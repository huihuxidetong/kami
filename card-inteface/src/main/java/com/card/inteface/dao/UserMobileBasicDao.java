package com.card.inteface.dao;

import org.springframework.stereotype.Component;

import com.card.inteface.entity.UserMobileBasic;  

/**
 * @notes:用户手机基本信息Dao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Component
public interface UserMobileBasicDao {

    /**
     * @保存用户手机信息
     * @param userMobileBasic
     */
    public void saveUserMobileBasic(UserMobileBasic userMobileBasic) throws Exception;
}
