package com.card.api.service;

import com.card.api.common.exception.BusinessException;
import com.card.api.vo.params.*;
import com.card.api.vo.responses.*;

/**
 * @notes:用户信息Service接口
 *
 * @author zzh
 *
 * 2018-08-21 14:56:12		Email:azhangzhihengi@163.com
 */
public interface UserService {

    /**
     * @ntoes 保存用户信息
     * @param usersRequest
     * @return
     */
    public SaveUserResponse saveUsers(SaveUserParam usersRequest) throws Exception;

    /**
     * @notes 查询我的信息
     * @param myInfoParam
     * @return
     */
    public MyInfoResponse findMyInfo(MyInfoParam myInfoParam) throws Exception;

    /**
     * @notes 我的信用卡列表
     * @param myCreditCardListParam
     * @return
     */
    public MyCreditCardListResponse findMyCreditCardList(MyCreditCardListParam myCreditCardListParam) throws Exception;

    /**
     * @notes 保存我的信用卡范湖
     * @param addMyCreditCardParam
     */
    public AddMyCreditCardResponse addMyCreditCard(AddMyCreditCardParam addMyCreditCardParam) throws Exception;

    /**
     * @ntoes 查询我的标签
     * @param myLableListParam
     * @return
     */
    public MyLableListResponse findMyLableList(MyLableListParam myLableListParam) throws Exception;

    /**
     * @notes 添加自定义标签
     * @param addCustomLableParam
     * @return
     */
    public AddCustomLableResponse addCustomLable(AddCustomLableParam addCustomLableParam) throws Exception;

    /**
     * @notes 添加自定义标签
     * @param delUserChonnseLableParam
     * @return
     */
    public DelUserChooseLableResponse delUserChooseLable(DelUserChonnseLableParam delUserChonnseLableParam) throws Exception;

    /**
     * @ntoes 查询用户关注信息
     * @param userConcernBasicParam
     * @return
     */
    public UserConcernBasicResponse findUserConcernBasic(UserConcernBasicParam userConcernBasicParam) throws Exception;

    /**
     * @notse 保存渠道点击
     * @param saveChannelDataParam
     * @return
     */
    public SaveChannelDataResponse saveChannelData(SaveChannelDataParam saveChannelDataParam) throws Exception;

    /**
     * @notes 用户关注信息
     * @param addUserConcernBasicParam
     * @return
     */
    public AddUserConcernBasicResponse addUserConcernBasic(AddUserConcernBasicParam addUserConcernBasicParam) throws Exception;

    /**
     * @notes 用户取消关注信息
     * @param updateUserConcernBasicParam
     * @return
     */
    public UpdateUserConcernBasicResponse updateUserConcernBasic(UpdateUserConcernBasicParam updateUserConcernBasicParam) throws Exception;

    /**
     * @ntoes 获取openId
     * @param getOpenIdParam
     * @return
     */
    public GetOpenIdResponse getOpenId(GetOpenIdParam getOpenIdParam) throws BusinessException;

    /**
     * @ntoes 发送客服消息
     * @param sendServiceMsgParam
     * @return
     */
    public SendServiceMsgResponse sendServiceMsg(SendServiceMsgParam sendServiceMsgParam) throws Exception;


}
