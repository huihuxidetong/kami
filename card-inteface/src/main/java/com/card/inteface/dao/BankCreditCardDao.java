package com.card.inteface.dao;

import com.card.inteface.vo.CreditCardInfoQueryVo;
import org.springframework.stereotype.Component;

import com.card.inteface.entity.BankCreditCard;

import java.util.List;

/**
 * @notes:银行关联信用卡Dao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Component
public interface BankCreditCardDao {

    /**
     * @ntoes 搜索信用卡
     * @param bankCreditCard
     * @return
     */
    public List<CreditCardInfoQueryVo> queryCreditCardList(BankCreditCard bankCreditCard) throws Exception;
}
