package com.card.inteface.service;

import com.card.inteface.entity.Bank;
import com.card.inteface.vo.BankVo;

import java.util.List;

/**
 * @notes:银行Service接口
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
public interface BankService  {

    /**
     * @notes 查询所有银行信息
     * @return
     */
    public List<BankVo> findBankVoList() throws Exception;
}
