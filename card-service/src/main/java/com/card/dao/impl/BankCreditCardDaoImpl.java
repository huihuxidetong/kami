package com.card.dao.impl;

import com.card.dao.DaoSupport;
import com.card.inteface.vo.CreditCardInfoQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.dao.BankCreditCardDao;  
import com.card.inteface.entity.BankCreditCard;

import java.util.List;

/**
 * @notes:银行关联信用卡Dao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Repository
public class BankCreditCardDaoImpl  implements BankCreditCardDao {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * @ntoes 搜索信用卡
     * @param bankCreditCard
     * @return
     */
    public List<CreditCardInfoQueryVo> queryCreditCardList(BankCreditCard bankCreditCard) throws Exception {
        return (List<CreditCardInfoQueryVo>) daoSupport.findForList("BankCreditCardMapper.queryCreditCardList",bankCreditCard);
    }

}