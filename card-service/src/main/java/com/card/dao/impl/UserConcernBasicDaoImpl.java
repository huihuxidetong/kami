package com.card.dao.impl;

import com.card.dao.DaoSupport;
import com.card.inteface.vo.ActivityIndexVo;
import com.card.inteface.vo.CreditCardInfoHotVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.entity.UserConcernBasic;  
import com.card.inteface.dao.UserConcernBasicDao;

import java.util.List;

/**
 * @notes:用户关注基本信息Dao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Repository
public class UserConcernBasicDaoImpl  implements UserConcernBasicDao {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * @notes 查询用户关注信用卡
     * @param userConcernBasic
     * @return
     */
    public List<CreditCardInfoHotVo> findUserConcernBasic4CreditCardPage(UserConcernBasic userConcernBasic) throws Exception {
        return (List<CreditCardInfoHotVo>) daoSupport.findForList("UserConcernBasicMapper.findUserConcernBasic4CreditCardPage",userConcernBasic);
    }

    /**
     * @notes 查询用户关注信用卡
     * @param userConcernBasic
     * @return
     */
    public List<ActivityIndexVo> findUserConcernBasic4ActivityPage(UserConcernBasic userConcernBasic) throws Exception{
        return (List<ActivityIndexVo>) daoSupport.findForList("UserConcernBasicMapper.findUserConcernBasic4ActivityPage",userConcernBasic);
    }

    /**
     * @notes 保存用户关注信息
     * @param userConcernBasic
     */
    public void saveUserConcernBasic(UserConcernBasic userConcernBasic) throws Exception {
        daoSupport.save("UserConcernBasicMapper.insertSelective",userConcernBasic);
    }

    /**
     * @notes 查询实体
     * @param userConcernBasic
     * @return
     */
    public UserConcernBasic findUserConcernBasicByEntity(UserConcernBasic userConcernBasic) throws Exception {
        return (UserConcernBasic) daoSupport.findForObject("UserConcernBasicMapper.findUserConcernBasicByEntity",userConcernBasic);
    }

    /**
     * @ntoes 更新关注
     * @param userConcernBasic
     */
    public Object updateUserConcernBasic(UserConcernBasic userConcernBasic) throws Exception {
        return daoSupport.update("UserConcernBasicMapper.updateByPrimaryKeySelective",userConcernBasic);
    }
}