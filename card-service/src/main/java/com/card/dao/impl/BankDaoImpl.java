package com.card.dao.impl;

import com.card.dao.DaoSupport;
import com.card.inteface.vo.BankVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.entity.Bank;  
import com.card.inteface.dao.BankDao;

import java.util.List;

/**
 * @notes:银行Dao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Repository
public class BankDaoImpl  implements BankDao {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * @notes 查询所有银行信息
     * @return
     */
    public List<BankVo> findBankVoList() throws Exception {
        return (List<BankVo>) daoSupport.findForList("BankMapper.findBankVoList",null);
    }
}