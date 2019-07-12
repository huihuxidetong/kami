package com.card.inteface.dao;

import com.card.inteface.vo.*;
import org.springframework.stereotype.Component;

import com.card.inteface.entity.CreditCardInfo;

import java.util.List;

/**
 * @notes:信用卡信息Dao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Component
public interface CreditCardInfoDao {

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
     * @notes 查询信用卡分页
     * @return
     */
    public List<CreditCardInfoHotVo> findCreditCardInfoPage() throws Exception;

    /**
     * @notes 查询信用卡分页
     * @return
     */
    public List<CreditCardInfoHotVo> findBankCreditCardListPage(Integer bankId) throws Exception;

    /**
     * @notes 通过信用卡id查询信用卡信息
     * @param id
     * @return
     */
    public CreditCardDetailVo findCreditCardDetailVo(QueryCreditCardVo queryCreditCardVo) throws Exception;

    /**
     * @notes 查询信用卡
     * @param id
     * @return
     */
    public CreditCardInfo findCreditCardInfoById(Integer id) throws Exception;

    /**
     * @notes 更新信用卡
     * @param creditCardInfo
     */
    public Object updateCreditCardInfo(CreditCardInfo creditCardInfo) throws Exception;
}
