package com.card.dao.impl;

import com.card.dao.DaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.entity.CreditCardCost;  
import com.card.inteface.dao.CreditCardCostDao;

import java.util.List;

/**
 * @notes:信用卡费用Dao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Repository
public class CreditCardCostDaoImpl  implements CreditCardCostDao {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * @notes 通过信用卡id查询费用信息
     * @param creditCardId
     * @return
     */
    public List<CreditCardCost> findCreditCardCostByCreditCardId(Integer creditCardId) throws Exception {
        return (List<CreditCardCost>) daoSupport.findForList("CreditCardCostMapper.findCreditCardCostByCreditCardId",creditCardId);
    }
}