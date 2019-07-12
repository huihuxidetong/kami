package com.card.dao.impl;

import com.card.dao.DaoSupport;
import com.card.inteface.vo.CreditCardConcernVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.entity.CreditCardConcern;  
import com.card.inteface.dao.CreditCardConcernDao;

import java.util.List;

/**
 * @notes:信用卡关注Dao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Repository
public class CreditCardConcernDaoImpl  implements CreditCardConcernDao {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * @notes 查询关注者头像
     * @param creditCardId
     * @return
     */
    public List<String> findCreditCardConcernPortraitLimit(Integer creditCardId) throws Exception {
        return (List<String>) daoSupport.findForList("UserConcernBasicMapper.findCreditCardConcernPortraitLimit",creditCardId);
    }

    /**
     * @notes 查询关注者统计
     * @param creditCardId
     * @return
     */
    public Integer findCreditCardConcernCount(Integer creditCardId) throws Exception {
        return (Integer) daoSupport.findForObject("UserConcernBasicMapper.findCreditCardConcernCount",creditCardId);
    }
    /**
     * @ntoes 查询关注者列表
     * @param creditCardId
     * @return
     */
    public List<CreditCardConcernVo> findCreditCardConcernPage(Integer creditCardId) throws Exception {
        return (List<CreditCardConcernVo>) daoSupport.findForList("UserConcernBasicMapper.findCreditCardConcernPage",creditCardId);
    }
}