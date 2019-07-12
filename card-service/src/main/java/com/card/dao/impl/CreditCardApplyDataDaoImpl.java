package com.card.dao.impl;

import com.card.dao.DaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.entity.CreditCardApplyData;  
import com.card.inteface.dao.CreditCardApplyDataDao;  

/**
 * @notes:信用卡数据Dao实现类
 *
 * @author zzh
 *
 * 2018-10-12 11:20:54		Email:azhangzhihengi@163.com
 */
@Repository
public class CreditCardApplyDataDaoImpl  implements CreditCardApplyDataDao {

    @Autowired
    private DaoSupport daoSupport;
    /**
     * @ntoes 信用卡申请
     * @param creditCardApplyData
     */
    public void addCreditCardApplyData(CreditCardApplyData creditCardApplyData) throws Exception {
        daoSupport.save("CreditCardApplyDataMapper.insertSelective",creditCardApplyData);
    }

    /**
     * @notes 查询
     * @param creditCardApplyData
     * @return
     */
    public CreditCardApplyData findCreditCardApplyData(CreditCardApplyData creditCardApplyData) throws Exception {
        return (CreditCardApplyData) daoSupport.findForObject("CreditCardApplyDataMapper.findCreditCardApplyData",creditCardApplyData);
    }
}