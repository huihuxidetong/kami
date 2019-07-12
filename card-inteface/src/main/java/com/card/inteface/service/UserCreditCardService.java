package com.card.inteface.service;

import com.card.inteface.entity.UserCreditCard;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.MyCreditCardInfoVo;

import java.util.List;

/**
 * @notes:用户绑定信用卡Service接口
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
public interface UserCreditCardService  {

    /**
     * @ntoes 查询用户头像信息
     * @param userId
     * @return
     */
    public Integer findUserCreditCardLCount(Integer userId) throws Exception;

    /**
     * @note 查询用户绑定信用卡列表
     * @param userId
     * @return
     */
    public List<MyCreditCardInfoVo> findMyCreditCardListPage(Integer userId) throws Exception;

    /**
     * @notes 保存用户信用卡
     * @param userCreditCard
     */
    public void saveUserCreditCard(UserCreditCard userCreditCard) throws Exception;

}
