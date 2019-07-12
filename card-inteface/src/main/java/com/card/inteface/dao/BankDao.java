package com.card.inteface.dao;

import com.card.inteface.vo.BankVo;
import org.springframework.stereotype.Component;

import com.card.inteface.entity.Bank;

import java.util.List;

/**
 * @notes:银行Dao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Component
public interface BankDao {

    /**
     * @notes 查询所有银行信息
     * @return
     */
    public List<BankVo> findBankVoList() throws Exception;
}
