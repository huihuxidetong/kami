package com.card.dao.impl;

import com.card.dao.DaoSupport;
import com.card.inteface.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.entity.CreditCardInfo;  
import com.card.inteface.dao.CreditCardInfoDao;

import java.util.List;

/**
 * @notes:信用卡信息Dao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Repository
public class CreditCardInfoDaoImpl  implements CreditCardInfoDao {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * @notes 查询首页信用卡信息
     * @return
     */
    @Override
    public List<CreditCardInfoVo> findIndexCreditCardInfoVo() throws Exception {
        return (List<CreditCardInfoVo>) daoSupport.findForList("CreditCardInfoMapper.findIndexCreditCardInfoVo",null);
    }

    /**
     * @notes 信用看阿里列表查询银行列表
     * @return
     */
    public List<BankVo> findBankVoListForCreditCradList() throws Exception {
        return (List<BankVo>) daoSupport.findForList("BankMapper.findBankVoListForCreditCradList",null);
    }

    /**
     * @notes 查询热门的信用卡
     * @return
     */
    public List<CreditCardInfoHotVo> findCreditCardInfoHotVoList() throws Exception {
        return (List<CreditCardInfoHotVo>) daoSupport.findForList("CreditCardInfoMapper.findCreditCardInfoHotVoList",null);
    }

    /**
     * @notes 查询信用卡分页
     * @return
     */
    public List<CreditCardInfoHotVo> findCreditCardInfoPage() throws Exception {
        return (List<CreditCardInfoHotVo>) daoSupport.findForList("CreditCardInfoMapper.findCreditCardInfoPage",null);
    }

    /**
     * @notes 查询信用卡分页
     * @return
     */
    public List<CreditCardInfoHotVo> findBankCreditCardListPage(Integer bankId) throws Exception {
        return (List<CreditCardInfoHotVo>) daoSupport.findForList("CreditCardInfoMapper.findBankCreditCardListPage",bankId);
    }

    /**
     * @notes 通过信用卡id查询信用卡信息
     * @param id
     * @return
     */
    public CreditCardDetailVo findCreditCardDetailVo(QueryCreditCardVo queryCreditCardVo) throws Exception {
        return (CreditCardDetailVo) daoSupport.findForObject("CreditCardInfoMapper.findCreditCardDetailVo",queryCreditCardVo);
    }

    /**
     * @notes 查询信用卡
     * @param id
     * @return
     */
    public CreditCardInfo findCreditCardInfoById(Integer id) throws Exception {
        return (CreditCardInfo) daoSupport.findForObject("CreditCardInfoMapper.selectByPrimaryKey",id);
    }

    /**
     * @notes 更新信用卡
     * @param creditCardInfo
     */
    public Object updateCreditCardInfo(CreditCardInfo creditCardInfo) throws Exception {
        return daoSupport.update("CreditCardInfoMapper.updateByPrimaryKeySelective",creditCardInfo);
    }
}