package com.card.api.service;

import com.card.api.common.exception.BusinessException;
import com.card.api.vo.params.*;
import com.card.api.vo.responses.*;

/**
 * @notes:信用卡Service接口
 *
 * @author zzh
 *
 * 2018-08-21 14:56:12		Email:azhangzhihengi@163.com
 */
public interface CreditCardService {

    /**
     * @note 查询信用卡列表信息
     * @return
     */
    public CreditCardListResponse findCreditCardList(CreditCardListParam creditCardListParam) throws Exception;


    /**
     * @ntoes 查询银行下面的上架的信用卡
     * @param bankCreditCardListParam
     * @return
     */
    public BankCreditCardListResponse findBankCreditCardList(BankCreditCardListParam bankCreditCardListParam) throws Exception;

    /**
     * @ntoes 查询信用卡详情
     * @param creditCardDetailParam
     * @return
     */
    public CreditCardDetailResponse findCreditCardDetail(CreditCardDetailParam creditCardDetailParam) throws Exception;

    /**
     * @notes 查询信用卡评论
     * @param creditCardCommentParam
     * @return
     */
    public CreditCardCommentResponse findCreditCardComment(CreditCardCommentParam creditCardCommentParam) throws Exception;

    /**
     * @nots 信用卡关注
     * @param param
     * @return
     */
    public CreditCardConcernResponse findCreditCardConcernPage(CreditCardConcernParam param) throws Exception;

    /**
     * @note 查询银信息
     * @param bankParam
     * @return
     */
    public BankResponse findBankList(BankParam bankParam) throws Exception;

    /**
     * @notes 信用卡搜索
     * @param bankCreditCardListQueryParam
     * @return
     */
    public BankCreditCardQueryResponse queryCreditCardList(BankCreditCardListQueryParam bankCreditCardListQueryParam) throws Exception;

    /**
     * @notes 添加信用卡
     * @param addCreditCardApplyDataParam
     * @return
     */
    public AddCreditCardApplyDataResponse addCreditCardApplyData(AddCreditCardApplyDataParam addCreditCardApplyDataParam) throws Exception;

}
