package com.card.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.card.api.common.exception.BusinessException;
import com.card.api.service.UserService;
import com.card.api.utils.JedisUtil;
import com.card.api.utils.SiteConfig;
import com.card.api.vo.params.*;
import com.card.api.vo.responses.*;
import com.card.api.vo.vo.ActivityIndexVo;
import com.card.api.vo.vo.CreditCardInfoHotVo;
import com.card.api.vo.vo.UserCustomLableVo;
import com.card.inteface.entity.ChannelData;
import com.card.inteface.entity.User;
import com.card.inteface.entity.UserConcernBasic;
import com.card.inteface.entity.UserCreditCard;
import com.card.inteface.service.*;
import com.card.inteface.utils.EmojiFilter;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.CreditCardDetailVo;
import com.card.inteface.vo.MyCreditCardInfoVo;
import com.card.inteface.vo.QueryCreditCardVo;
import com.card.inteface.vo.UserBasicVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @notes:用户信息Service实现类
 *
 * @author zzh
 *
 * 2018-08-21 14:56:12		Email:azhangzhihengi@163.com
 */
@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private com.card.inteface.service.UserService userService;

	@Autowired
	private UserCreditCardService userCreditCardService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private CreditCardInfoService creditCardInfoService;

	@Autowired
	private UserConcernBasicService userConcernBasicService;

	@Autowired
	private ChannelDataService channelDataService;

	@Autowired
	private SiteConfig siteConfig;


	@Override
	public SaveUserResponse saveUsers(SaveUserParam usersRequest) throws Exception {
		SaveUserResponse responseVo = new SaveUserResponse();
		if (usersRequest == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(usersRequest.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(usersRequest.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取信息失败");
		}
		//保存用户信息
		String openId = String.valueOf(keyMap.get("openId"));
		User user = userService.findUserByOpenId(String.valueOf(keyMap.get("openId")));
		if(null == user){
			logger.info("====================businessKey:"+usersRequest.getBusinessKey());
			String channelCode = JedisUtil.getKeyVal("channelcode"+usersRequest.getBusinessKey());
			logger.info("====================channelCode:"+channelCode);
			UserBasicVo userBasic = new UserBasicVo();
			BeanUtils.copyProperties(usersRequest,userBasic);
			userBasic.setNickName(EmojiFilter.filterEmoji(userBasic.getNickName()));
			userBasic.setOpenId(openId);
			userBasic.setCreateTime(new Date());
			userBasic.setUpdateTime(new Date());
			userBasic.setChannelCode(channelCode);
			userService.saveUserUserBasicVo(userBasic);
		}
		return responseVo;
	}

	/**
	 * @notes 查询我的信息
	 * @param myInfoParam
	 * @return
	 */
	public MyInfoResponse findMyInfo(MyInfoParam myInfoParam) throws Exception {
		MyInfoResponse myInfoResponse = new MyInfoResponse();
		if (myInfoParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(myInfoParam.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(myInfoParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取信息失败");
		}
		//用户信息
		String openId = String.valueOf(keyMap.get("openId"));
		logger.info("==================openId:"+openId);
		User user = userService.findUserByOpenId(openId);
        logger.info("===============user:"+user);
        if(null == user){
            throw new BusinessException(1, "用户对象为空");
        }
		BeanUtils.copyProperties(user,myInfoResponse);
		Integer userCreditCardLCount = userCreditCardService.findUserCreditCardLCount(user.getId());
		//查询用户是否有未读消息
		Integer userNoReadCommentCount = commentService.findUserNoReadCommentCount(user.getId());
        myInfoResponse.setUserCreditCardLCount(userCreditCardLCount);
        myInfoResponse.setIsHasNoReadComment(userNoReadCommentCount);
		myInfoResponse.setAilingkaCodeUrl(siteConfig.ailingkaCodeUrl);
		return myInfoResponse;
	}

	/**
	 * @notes 我的信用卡列表
	 * @param myCreditCardListParam
	 * @return
	 */
	public MyCreditCardListResponse findMyCreditCardList(MyCreditCardListParam myCreditCardListParam) throws Exception {
		MyCreditCardListResponse myCreditCardListResponse = new MyCreditCardListResponse();
		if (myCreditCardListParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(myCreditCardListParam.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(myCreditCardListParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取信息失败");
		}
		//用户信息
		String openId = String.valueOf(keyMap.get("openId"));
		User user = userService.findUserByOpenId(openId);
		if(null == user){
			throw new BusinessException(1, "获取用户信息失败");
		}
		List<MyCreditCardInfoVo> list = userCreditCardService.findMyCreditCardListPage(user.getId());
		List<com.card.api.vo.vo.MyCreditCardInfoVo> myCreditCardInfoVos = new ArrayList<>();
		if(null != list && list.size()>0){
			for(MyCreditCardInfoVo myCreditCardInfoVo1 : list){
				com.card.api.vo.vo.MyCreditCardInfoVo myCreditCardInfoVo2 = new com.card.api.vo.vo.MyCreditCardInfoVo();
				BeanUtils.copyProperties(myCreditCardInfoVo1,myCreditCardInfoVo2);
				myCreditCardInfoVos.add(myCreditCardInfoVo2);
			}
			myCreditCardListResponse.setCreditCardInfoHotVoPageList(myCreditCardInfoVos);
		}
		return myCreditCardListResponse;
	}

	/**
	 * @notes 保存我的信用卡范湖
	 * @param addMyCreditCardParam
	 */
	public AddMyCreditCardResponse addMyCreditCard(AddMyCreditCardParam addMyCreditCardParam) throws Exception {
		AddMyCreditCardResponse addMyCreditCardResponse = new AddMyCreditCardResponse();
		if (addMyCreditCardParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(addMyCreditCardParam.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(addMyCreditCardParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取信息失败");
		}
		//用户信息
		String openId = String.valueOf(keyMap.get("openId"));
		User user = userService.findUserByOpenId(openId);
		if(null == user){
			throw new BusinessException(1, "获取用户信息失败");
		}
		UserCreditCard userCreditCard = new UserCreditCard();

		BeanUtils.copyProperties(addMyCreditCardParam,userCreditCard);
		userCreditCard.setUserId(user.getId());
		userCreditCard.setCreateTime(new Date());
		userCreditCardService.saveUserCreditCard(userCreditCard);
		return addMyCreditCardResponse;
	}

	/**
	 * @ntoes 查询我的标签
	 * @param myLableListParam
	 * @return
	 */
	public MyLableListResponse findMyLableList(MyLableListParam myLableListParam) throws Exception {
		MyLableListResponse myLableListResponse = new MyLableListResponse();
		if (myLableListParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(myLableListParam.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(myLableListParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取信息失败");
		}
		//用户信息
		String openId = String.valueOf(keyMap.get("openId"));
		User user = userService.findUserByOpenId(openId);
		if(null == user){
			throw new BusinessException(1, "获取用户信息失败");
		}

		String userChooseLable = user.getUserChooseLable();
		List<String> userChooseLablesList = new ArrayList<>();
		if(org.apache.commons.lang.StringUtils.isNotBlank(userChooseLable)){
			String [] userChooseLables = userChooseLable.split(",");
			userChooseLablesList = Arrays.asList(userChooseLables);
		}
		String userCustomLable = user.getUserCustomLable();
		List<UserCustomLableVo> userCustomLableVoList = new ArrayList<>();
		List<UserCustomLableVo> userCustomLableVoList1 = new ArrayList<>();
		if(org.apache.commons.lang.StringUtils.isNotBlank(userCustomLable)){
			String [] userCustomLables = userCustomLable.split(",");
			List<String> userCustomLablesList = Arrays.asList(userCustomLables);
			int index =0;
			for(String customLable : userCustomLablesList){
				UserCustomLableVo userCustomLableVo = new UserCustomLableVo();
				userCustomLableVo.setIsChooseLable(0);
				userCustomLableVo.setUserCustomLableName(customLable);
				userCustomLableVo.setIndex(index);
				for(String chooseLable : userChooseLablesList){
					UserCustomLableVo userCustomLableVo1 = new UserCustomLableVo();
					if(chooseLable.equalsIgnoreCase(customLable)){
						userCustomLableVo1.setUserCustomLableName(chooseLable);
						userCustomLableVo1.setIsChooseLable(1);
						userCustomLableVo1.setIndex(index);
						userCustomLableVo.setIsChooseLable(1);
						userCustomLableVoList1.add(userCustomLableVo1);
					}
				}
				index ++;
				userCustomLableVoList.add(userCustomLableVo);
			}
		}
		myLableListResponse.setUserChooseLable(userCustomLableVoList1);
		myLableListResponse.setUserCustomLableVoList(userCustomLableVoList);
		return myLableListResponse;
	}

	/**
	 * @notes 添加自定义标签
	 * @param addCustomLableParam
	 * @return
	 */
	public AddCustomLableResponse addCustomLable(AddCustomLableParam addCustomLableParam) throws Exception {
		AddCustomLableResponse addCustomLableResponse = new AddCustomLableResponse();
		if (addCustomLableParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(addCustomLableParam.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(addCustomLableParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取信息失败");
		}
		//用户信息
		String openId = String.valueOf(keyMap.get("openId"));
		User user = userService.findUserByOpenId(openId);
		if(null == user){
			throw new BusinessException(1, "获取用户信息失败");
		}
		String userChooseLable = user.getUserChooseLable();
		String userCustomLable = user.getUserCustomLable();
		Integer isNewLable = addCustomLableParam.getIsNewLable();
		if(1==isNewLable) {
			if (org.apache.commons.lang.StringUtils.isNotBlank(userCustomLable)) {
				String[] userCustomLables = userCustomLable.split(",");
				List<String> userCustomLablesList = Arrays.asList(userCustomLables);
				if (userCustomLablesList.contains(addCustomLableParam.getCustomLable())) {
					throw new BusinessException(1, addCustomLableParam.getCustomLable() + "已存在");
				}
			}
		}
		if(StringUtils.isNotBlank(userChooseLable)){
			user.setUserChooseLable(userChooseLable + "," + addCustomLableParam.getCustomLable());
		}else{
			user.setUserChooseLable(addCustomLableParam.getCustomLable());
		}
		if(1==isNewLable) {
			user.setUserCustomLable(userCustomLable + "," + addCustomLableParam.getCustomLable());
		}
		userService.updateUser(user);
		return addCustomLableResponse;
	}

	/**
	 * @notes 删除标签
	 * @param delUserChonnseLableParam
	 * @return
	 */
	public DelUserChooseLableResponse delUserChooseLable(DelUserChonnseLableParam delUserChonnseLableParam) throws Exception{
		DelUserChooseLableResponse delUserChooseLableResponse = new DelUserChooseLableResponse();
		if (delUserChonnseLableParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(delUserChonnseLableParam.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(delUserChonnseLableParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取信息失败");
		}
		//用户信息
		String openId = String.valueOf(keyMap.get("openId"));
		User user = userService.findUserByOpenId(openId);
		if(null == user){
			throw new BusinessException(1, "获取用户信息失败");
		}
		String userChooseLableStr = "";
		String userChooseLable = user.getUserChooseLable();
		String customLable = delUserChonnseLableParam.getCustomLable();
		if(org.apache.commons.lang.StringUtils.isNotBlank(userChooseLable)){
			String [] userChooseLables = userChooseLable.split(",");
			for(String chooseLables : userChooseLables){
				System.out.println(chooseLables);
				if(customLable.equals(chooseLables)){
					userChooseLables = removeitem(userChooseLables,customLable);
				}
			}
			if(null != userChooseLables && userChooseLables.length>0) {
				for (String str : userChooseLables) {
					if (StringUtils.isNotBlank(str)) {
						userChooseLableStr += str + ",";
					}
				}
			}
			if(StringUtils.isNotBlank(userChooseLableStr)) {
				userChooseLableStr = userChooseLableStr.substring(0, userChooseLableStr.length() - 1);
			}
		}

		user.setUserChooseLable(userChooseLableStr);
		userService.updateUser(user);
		return delUserChooseLableResponse;
	}
	public static String[] removeitem(String[] arrays,String str){
		String[] tempArr = new String[arrays.length];
		int i = 0;
		for(String s:arrays){
			if(!s.equals(str)){
				tempArr[i] = s;
				i++;
			}
		}
		return tempArr;
	}

	/**
	 * @ntoes 查询用户关注信息
	 * @param userConcernBasicParam
	 * @return
	 */
	public UserConcernBasicResponse findUserConcernBasic(UserConcernBasicParam userConcernBasicParam) throws Exception {
		UserConcernBasicResponse userConcernBasicResponse = new UserConcernBasicResponse();
		if (userConcernBasicParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(userConcernBasicParam.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(userConcernBasicParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取信息失败");
		}
		//用户信息
		String openId = String.valueOf(keyMap.get("openId"));
		User user = userService.findUserByOpenId(openId);
		if(null == user){
			throw new BusinessException(1, "获取用户信息失败");
		}
		Integer concernType = userConcernBasicParam.getConcernType();
		UserConcernBasic userConcernBasic = new UserConcernBasic();
		userConcernBasic.setUserId(user.getId());
		userConcernBasic.setConcernType(concernType);
		if(1 == concernType){
			//查询信用卡
			PageBean<UserConcernBasic> pageBean = new PageBean<UserConcernBasic>();
			pageBean.setBean(userConcernBasic);
			pageBean.setPageNum(userConcernBasicParam.getPageNum());
			pageBean.setPageSize(userConcernBasicParam.getPageSize());
			PageBean<com.card.inteface.vo.CreditCardInfoHotVo> list = userConcernBasicService.findUserConcernBasic4CreditCardPage(pageBean);
			userConcernBasicResponse.setCount(list.getTotal());
			userConcernBasicResponse.setPage(list.getPageNum());
			userConcernBasicResponse.setPageSize(list.getPageSize());
			userConcernBasicResponse.setTotalPage(list.getPages());

			List<com.card.inteface.vo.CreditCardInfoHotVo> creditCardInfoHotVoList = list.getDatas();
			List<CreditCardInfoHotVo> creditCardInfoHotVos = new ArrayList<>();
			if(null != creditCardInfoHotVoList && creditCardInfoHotVoList.size()>0){
				for(com.card.inteface.vo.CreditCardInfoHotVo creditCardInfoHotVo : creditCardInfoHotVoList){
					CreditCardInfoHotVo creditCardInfoHotVo1 = new CreditCardInfoHotVo();
					BeanUtils.copyProperties(creditCardInfoHotVo,creditCardInfoHotVo1);
					creditCardInfoHotVos.add(creditCardInfoHotVo1);
				}
				userConcernBasicResponse.setCreditCardInfoHotVoList(creditCardInfoHotVos);
			}
		}else if(2 == concernType){
			//查询活动
			//查询信用卡
			PageBean<UserConcernBasic> pageBean = new PageBean<UserConcernBasic>();
			pageBean.setBean(userConcernBasic);
			pageBean.setPageNum(userConcernBasicParam.getPageNum());
			pageBean.setPageSize(userConcernBasicParam.getPageSize());
			PageBean<com.card.inteface.vo.ActivityIndexVo> list = userConcernBasicService.findUserConcernBasic4ActivityPage(pageBean);
			userConcernBasicResponse.setCount(list.getTotal());
			userConcernBasicResponse.setPage(list.getPageNum());
			userConcernBasicResponse.setPageSize(list.getPageSize());
			userConcernBasicResponse.setTotalPage(list.getPages());

			List<com.card.inteface.vo.ActivityIndexVo> activityIndexVoList = list.getDatas();
			List<ActivityIndexVo> activityIndexVoList1 = new ArrayList<>();
			if(null != activityIndexVoList && activityIndexVoList.size()>0){
				for(com.card.inteface.vo.ActivityIndexVo activityIndexVo : activityIndexVoList){
					ActivityIndexVo activityIndexVo1 = new ActivityIndexVo();
					BeanUtils.copyProperties(activityIndexVo,activityIndexVo1);
					activityIndexVoList1.add(activityIndexVo1);
				}
				userConcernBasicResponse.setActivityIndexVoList(activityIndexVoList1);
			}
		}
		return userConcernBasicResponse;
	}

	/**
	 * @notse 保存渠道点击
	 * @param saveChannelDataParam
	 * @return
	 */
	public SaveChannelDataResponse saveChannelData(SaveChannelDataParam saveChannelDataParam) throws Exception {
		SaveChannelDataResponse saveChannelDataResponse = new SaveChannelDataResponse();
		if (saveChannelDataParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(saveChannelDataParam.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(saveChannelDataParam.getChannelCode())) {
			throw new BusinessException(1, "渠道code为空");
		}

		JedisUtil.setKeyVal(("channelcode" +saveChannelDataParam.getBusinessKey()),saveChannelDataParam.getChannelCode(),60 * 60 * 24 * 30);
		ChannelData channelData = new ChannelData();
		//用户信息
		Map<String, Object> keyMap = JedisUtil.getKeyMap(saveChannelDataParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			channelData.setUserId(-999);
		}else{
			String openId = String.valueOf(keyMap.get("openId"));
			User user = userService.findUserByOpenId(openId);
			if(null == user){
				channelData.setUserId(-999);
			}else {
				channelData.setUserId(user.getId());
			}
		}

		channelData.setChannelCode(saveChannelDataParam.getChannelCode());
		channelData.setCreateTime(new Date());
		channelDataService.saveChannelData(channelData);
		return saveChannelDataResponse;
	}

	/**
	 * @notes 用户关注信息
	 * @param addUserConcernBasicParam
	 * @return
	 */
	public AddUserConcernBasicResponse addUserConcernBasic(AddUserConcernBasicParam addUserConcernBasicParam) throws Exception {
		AddUserConcernBasicResponse addUserConcernBasicResponse = new AddUserConcernBasicResponse();
		if (addUserConcernBasicParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(addUserConcernBasicParam.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(addUserConcernBasicParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取信息失败");
		}
		//用户信息
		String openId = String.valueOf(keyMap.get("openId"));
		User user = userService.findUserByOpenId(openId);
		if(null == user){
			throw new BusinessException(1, "获取用户信息失败");
		}
		UserConcernBasic userConcernBasic = new UserConcernBasic();
		userConcernBasic.setConcernType(addUserConcernBasicParam.getConcernType());
		userConcernBasic.setUserId(user.getId());
		userConcernBasic.setConcernId(addUserConcernBasicParam.getConcernId());
		userConcernBasic.setCreateTime(new Date());
		userConcernBasicService.saveUserConcernBasic(userConcernBasic);
		return addUserConcernBasicResponse;
	}


	/**
	 * @notes 用户取消关注信息
	 * @param updateUserConcernBasicParam
	 * @return
	 */
	public UpdateUserConcernBasicResponse updateUserConcernBasic(UpdateUserConcernBasicParam updateUserConcernBasicParam) throws Exception {
		UpdateUserConcernBasicResponse updateUserConcernBasicResponse = new UpdateUserConcernBasicResponse();
		if (updateUserConcernBasicParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(updateUserConcernBasicParam.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(updateUserConcernBasicParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取信息失败");
		}
		//用户信息
		String openId = String.valueOf(keyMap.get("openId"));
		User user = userService.findUserByOpenId(openId);
		if(null == user){
			throw new BusinessException(1, "获取用户信息失败");
		}
		UserConcernBasic userConcernBasic = new UserConcernBasic();
		userConcernBasic.setConcernType(updateUserConcernBasicParam.getConcernType());
		userConcernBasic.setUserId(user.getId());
		userConcernBasic.setConcernId(updateUserConcernBasicParam.getConcernId());
		userConcernBasic.setCreateTime(new Date());
		userConcernBasic = userConcernBasicService.findUserConcernBasicByEntity(userConcernBasic);
		userConcernBasicService.updateUserConcernBasic(userConcernBasic);
		return updateUserConcernBasicResponse;
	}

	/**
	 * @ntoes 获取openId
	 * @param getOpenIdParam
	 * @return
	 */
	public GetOpenIdResponse getOpenId(GetOpenIdParam getOpenIdParam) throws BusinessException {
		GetOpenIdResponse getOpenIdResponse = new GetOpenIdResponse();
		if (getOpenIdParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(getOpenIdParam.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(getOpenIdParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取信息失败");
		}
		//用户信息
		String openId = String.valueOf(keyMap.get("openId"));
		if(StringUtils.isBlank(openId)){
			throw new BusinessException(1, "获取用户信息失败");
		}
		getOpenIdResponse.setOpenId(openId);
		return getOpenIdResponse;
	}

	/**
	 * @ntoes 发送客服消息
	 * @param sendServiceMsgParam
	 * @return
	 */
	public SendServiceMsgResponse sendServiceMsg(SendServiceMsgParam sendServiceMsgParam) throws Exception {
		SendServiceMsgResponse sendServiceMsgResponse = new SendServiceMsgResponse();
		if (sendServiceMsgParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(sendServiceMsgParam.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(sendServiceMsgParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取信息失败");
		}
		//用户信息
		String openId = String.valueOf(keyMap.get("openId"));
		if(StringUtils.isBlank(openId)){
			throw new BusinessException(1, "获取用户信息失败");
		}
		User user = userService.findUserByOpenId(openId);
		if(null == user){
			throw new BusinessException(1, "获取用户信息失败");
		}
		QueryCreditCardVo queryCreditCardVo = new QueryCreditCardVo();
		queryCreditCardVo.setUserId(user.getId());
		queryCreditCardVo.setId(sendServiceMsgParam.getCreditCardId());
		//信用卡基本信息
		CreditCardDetailVo creditCardDetailVo = creditCardInfoService.findCreditCardDetailVo(queryCreditCardVo);
		RestTemplate rt = new RestTemplate();
		String token = getToken(siteConfig.appid,siteConfig.secret,siteConfig.grantType);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("touser",openId);
		jsonObject.put("msgtype","link");
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("title",creditCardDetailVo.getBankName());
		jsonObject1.put("description",creditCardDetailVo.getCardFeature());
		jsonObject1.put("url",creditCardDetailVo.getOpenCreditCardUrl());
		jsonObject1.put("thumb_url",siteConfig.fileDownLoadUrl + creditCardDetailVo.getCardLogo());
//		jsonObject1.put("url","https://www.baidu.com");
//		jsonObject1.put("thumb_url","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539348258217&di=07ace0913eb709c48f0051d05c330ad4&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F32fa828ba61ea8d3be4d248b9d0a304e241f58d5.jpg");
		jsonObject.put("link",jsonObject1);
		ResponseEntity<String> forEntity = rt.postForEntity(siteConfig.sendServiceMsgUrl+"?access_token=" + token, jsonObject,String.class);
		if (HttpStatus.OK.equals(forEntity.getStatusCode())) {
			logger.info("==========推送消息返回" +JSONObject.parseObject(forEntity.getBody()));;
		}
		return  sendServiceMsgResponse;
	}

	public String getToken(String appid, String secret, String grantType) {
		String token;
		RestTemplate rt = new RestTemplate();
		StringBuffer sb = new StringBuffer(siteConfig.wxCardTokenUrl);
		sb.append("?appid=").append(appid);
		sb.append("&secret=").append(secret);
		sb.append("&grant_type=").append(grantType);
		ResponseEntity<String> forEntity = rt.getForEntity(sb.toString(), String.class);
		logger.info("获取token，结果" + forEntity.getBody());
		if (HttpStatus.OK.equals(forEntity.getStatusCode())) {
			JSONObject json = JSONObject.parseObject(forEntity.getBody());
			if (json != null) {
				token = String.valueOf(json.get("access_token"));
			} else {
				return "";
			}
		} else {
			return "";
		}
		return token;
	}
}
