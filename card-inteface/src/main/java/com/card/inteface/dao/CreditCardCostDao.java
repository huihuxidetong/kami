package com.card.inteface.dao;

import org.springframework.stereotype.Component;

import com.card.inteface.entity.CreditCardCost;

import java.util.List;

/**
 * @notes:信用卡费用Dao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Component
public interface CreditCardCostDao {
    /**
     * @notes 通过信用卡id查询费用信息
     * @param creditCardId
     * @return
     */
    public List<CreditCardCost> findCreditCardCostByCreditCardId(Integer creditCardId) throws Exception;
}
