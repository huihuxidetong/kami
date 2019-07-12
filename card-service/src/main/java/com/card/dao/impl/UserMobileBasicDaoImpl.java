package com.card.dao.impl;

import com.card.dao.DaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.dao.UserMobileBasicDao;  
import com.card.inteface.entity.UserMobileBasic;  

/**
 * @notes:用户手机基本信息Dao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Repository
public class UserMobileBasicDaoImpl  implements UserMobileBasicDao {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * @保存用户手机信息
     * @param userMobileBasic
     */
    public void saveUserMobileBasic(UserMobileBasic userMobileBasic) throws Exception {
        daoSupport.save("UserMobileBasicMapper.insertSelective",userMobileBasic);
    }
}