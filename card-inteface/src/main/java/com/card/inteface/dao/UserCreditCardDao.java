package com.card.inteface.dao;

import com.card.inteface.vo.MyCreditCardInfoVo;
import org.springframework.stereotype.Component;

import com.card.inteface.entity.UserCreditCard;

import java.util.List;

/**
 * @notes:用户绑定信用卡Dao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Component
public interface UserCreditCardDao {

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
    public List<MyCreditCardInfoVo> findMyCreditCardList(Integer userId) throws Exception;

    /**
     * @notes 保存用户信用卡
     * @param userCreditCard
     */
    public void saveUserCreditCard(UserCreditCard userCreditCard) throws Exception;
}
