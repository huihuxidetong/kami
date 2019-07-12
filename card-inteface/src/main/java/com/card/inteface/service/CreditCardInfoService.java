package com.card.inteface.service;

import com.card.inteface.entity.CreditCardInfo;
import com.card.inteface.entity.UserConcernBasic;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.*;

import java.util.List;

/**
 * @notes:信用卡信息Service接口
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
public interface CreditCardInfoService  {

    /**
     * @notes 查询首页信用卡信息
     * @return
     */
    public List<CreditCardInfoVo> findIndexCreditCardInfoVo() throws Exception;

    /**
     * @notes 信用看阿里列表查询银行列表
     * @return
     */
    public List<BankVo> findBankVoListForCreditCradList() throws Exception;

    /**
     * @notes 查询热门的信用卡
     * @return
     */
    public List<CreditCardInfoHotVo> findCreditCardInfoHotVoList() throws Exception;

    /**
     * @note 查询信用卡分页
     * @param pageBean
     * @return
     */
    public PageBean<CreditCardInfoHotVo> findCreditCardInfoPage(PageBean<CreditCardInfoHotVo> pageBean) throws Exception;

    /**
     * @note 查询信用卡分页
     * @param pageBean
     * @return
     */
    public PageBean<CreditCardInfoHotVo> findBankCreditCardListPage(PageBean<CreditCardInfoHotVo> pageBean) throws Exception;

    /**
     * @notes 通过信用卡id查询信用卡信息
     * @param id
     * @return
     */
    public CreditCardDetailVo findCreditCardDetailVo(QueryCreditCardVo queryCreditCardVo) throws Exception;


}
