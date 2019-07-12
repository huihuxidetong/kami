package com.card.dao.impl;

import com.card.dao.DaoSupport;
import com.card.inteface.vo.MyCreditCardInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.dao.UserCreditCardDao;  
import com.card.inteface.entity.UserCreditCard;

import java.util.List;

/**
 * @notes:用户绑定信用卡Dao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Repository
public class UserCreditCardDaoImpl  implements UserCreditCardDao {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * @ntoes 查询用户头像信息
     * @param userId
     * @return
     */
    public Integer findUserCreditCardLCount(Integer userId) throws Exception {
        return (Integer) daoSupport.findForObject("UserCreditCardMapper.findUserCreditCardLCount",userId);
    }

    /**
     * @note 查询用户绑定信用卡列表
     * @param userId
     * @return
     */
    public List<MyCreditCardInfoVo> findMyCreditCardList(Integer userId) throws Exception {
        return (List<MyCreditCardInfoVo>) daoSupport.findForList("UserCreditCardMapper.findMyCreditCardList",userId);
    }

    /**
     * @notes 保存用户信用卡
     * @param userCreditCard
     */
    public void saveUserCreditCard(UserCreditCard userCreditCard) throws Exception {
        daoSupport.save("UserCreditCardMapper.insertSelective",userCreditCard);
    }
}