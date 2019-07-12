package com.card.api.service.impl;

import com.card.api.common.exception.BusinessException;
import com.card.api.service.CreditCardService;
import com.card.api.utils.JedisUtil;
import com.card.api.utils.SiteConfig;
import com.card.api.vo.params.*;
import com.card.api.vo.responses.*;
import com.card.api.vo.vo.BankVo;
import com.card.api.vo.vo.CommentVo;
import com.card.api.vo.vo.CreditCardInfoQueryVo;
import com.card.inteface.entity.*;
import com.card.inteface.service.*;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @notes:信用卡Service实现类
 *
 * @author zzh
 *
 * 2018-08-21 14:56:12		Email:azhangzhihengi@163.com
 */
@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	private com.card.inteface.service.CreditCardInfoService creditCardInfoService;

	@Autowired
    private CreditCardCostService creditCardCostService;

	@Autowired
    private CreditCardQuestionService creditCardQuestionService;

	@Autowired
    private CreditCardConcernService creditCardConcernService;

	@Autowired
    private ActivityService activityService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private BankService bankService;

	@Autowired
	private BankCreditCardService bankCreditCardService;

	@Autowired
	private UserService userService;

	@Autowired
	private CreditCardApplyDataService creditCardApplyDataService;

	@Autowired
	private SiteConfig siteConfig;
	/**
	 * @note 查询信用卡列表信息
	 * @return
	 */
	public CreditCardListResponse findCreditCardList(CreditCardListParam creditCardListParam) throws Exception {
		CreditCardListResponse creditCardListResponse = new CreditCardListResponse();
		List<com.card.inteface.vo.BankVo> bankVoList = creditCardInfoService.findBankVoListForCreditCradList();
		List<BankVo> bankVoList1 = new ArrayList<>();
		if(null !=bankVoList && bankVoList.size()>0){
			for(com.card.inteface.vo.BankVo bankVo : bankVoList){
				BankVo bankVo1 = new BankVo();
				BeanUtils.copyProperties(bankVo,bankVo1);
				bankVoList1.add(bankVo1);
			}
			creditCardListResponse.setBankVoLiist(bankVoList1);
		}

		List<CreditCardInfoHotVo> creditCardInfoHotVoList = creditCardInfoService.findCreditCardInfoHotVoList();
		List<com.card.api.vo.vo.CreditCardInfoHotVo> creditCardInfoHotVoList1 = new ArrayList<>();
		if(null != creditCardInfoHotVoList && creditCardInfoHotVoList.size()>0){
			for(CreditCardInfoHotVo creditCardInfoHotVo : creditCardInfoHotVoList){
				com.card.api.vo.vo.CreditCardInfoHotVo creditCardInfoHotVo1 = new com.card.api.vo.vo.CreditCardInfoHotVo();
				BeanUtils.copyProperties(creditCardInfoHotVo,creditCardInfoHotVo1);
				creditCardInfoHotVoList1.add(creditCardInfoHotVo1);
			}
			creditCardListResponse.setCreditCardInfoHotVoList(creditCardInfoHotVoList1);
		}

		CreditCardInfoHotVo creditCardInfoHotVo = new CreditCardInfoHotVo();
		PageBean<CreditCardInfoHotVo> pageBean = new PageBean<CreditCardInfoHotVo>();
		pageBean.setBean(creditCardInfoHotVo);
		pageBean.setPageNum(creditCardListParam.getPageNum());
		pageBean.setPageSize(creditCardListParam.getPageSize());
		PageBean<CreditCardInfoHotVo> list = creditCardInfoService.findCreditCardInfoPage(pageBean);
		creditCardListResponse.setCount(list.getTotal());
		creditCardListResponse.setPage(list.getPageNum());
		creditCardListResponse.setPageSize(list.getPageSize());
		creditCardListResponse.setTotalPage(list.getPages());
		List<CreditCardInfoHotVo> creditCardInfoHotVoList2 = list.getDatas();
		List<com.card.api.vo.vo.CreditCardInfoHotVo> creditCardInfoHotVoPageList = new ArrayList<>();
		if(null != creditCardInfoHotVoList2 && creditCardInfoHotVoList2.size()>0){
			for(CreditCardInfoHotVo creditCardInfoHotVo1 : creditCardInfoHotVoList2){
				com.card.api.vo.vo.CreditCardInfoHotVo creditCardInfoHotVo2 = new com.card.api.vo.vo.CreditCardInfoHotVo();
				BeanUtils.copyProperties(creditCardInfoHotVo1,creditCardInfoHotVo2);
				creditCardInfoHotVoPageList.add(creditCardInfoHotVo2);
			}
			creditCardListResponse.setCreditCardInfoHotVoPageList(creditCardInfoHotVoPageList);
		}
		creditCardListResponse.setIsCanClickDetail(siteConfig.isCanClickDetail);
		return creditCardListResponse;
	}

	/**
	 * @ntoes 查询银行下面的上架的信用卡
	 * @param bankCreditCardListParam
	 * @return
	 */
	public BankCreditCardListResponse findBankCreditCardList(BankCreditCardListParam bankCreditCardListParam) throws Exception {
		BankCreditCardListResponse bankCreditCardListResponse = new BankCreditCardListResponse();
		CreditCardInfoHotVo creditCardInfoHotVo = new CreditCardInfoHotVo();
		PageBean<CreditCardInfoHotVo> pageBean = new PageBean<CreditCardInfoHotVo>();
		creditCardInfoHotVo.setBankId(bankCreditCardListParam.getId());
		pageBean.setBean(creditCardInfoHotVo);
		pageBean.setPageNum(bankCreditCardListParam.getPageNum());
		pageBean.setPageSize(bankCreditCardListParam.getPageSize());
		PageBean<CreditCardInfoHotVo> list = creditCardInfoService.findBankCreditCardListPage(pageBean);
		bankCreditCardListResponse.setCount(list.getTotal());
		bankCreditCardListResponse.setPage(list.getPageNum());
		bankCreditCardListResponse.setPageSize(list.getPageSize());
		bankCreditCardListResponse.setTotalPage(list.getPages());
		List<CreditCardInfoHotVo> creditCardInfoHotVoList2 = list.getDatas();
		List<com.card.api.vo.vo.CreditCardInfoHotVo> creditCardInfoHotVoPageList = new ArrayList<>();
		if(null != creditCardInfoHotVoList2 && creditCardInfoHotVoList2.size()>0){
			bankCreditCardListResponse.setId(creditCardInfoHotVoList2.get(0).getBankId());
			bankCreditCardListResponse.setBankName(creditCardInfoHotVoList2.get(0).getBankName());
			bankCreditCardListResponse.setBankLogo(creditCardInfoHotVoList2.get(0).getBankLogo());
			for(CreditCardInfoHotVo creditCardInfoHotVo1 : creditCardInfoHotVoList2){
				com.card.api.vo.vo.CreditCardInfoHotVo creditCardInfoHotVo2 = new com.card.api.vo.vo.CreditCardInfoHotVo();
				BeanUtils.copyProperties(creditCardInfoHotVo1,creditCardInfoHotVo2);
				creditCardInfoHotVoPageList.add(creditCardInfoHotVo2);
			}
			bankCreditCardListResponse.setCreditCardInfoHotVoPageList(creditCardInfoHotVoPageList);
		}
		return bankCreditCardListResponse;
	}

	/**
	 * @ntoes 查询信用卡详情
	 * @param creditCardDetailParam
	 * @return
	 */
	public CreditCardDetailResponse findCreditCardDetail(CreditCardDetailParam creditCardDetailParam) throws Exception {
		if (creditCardDetailParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (org.apache.commons.lang3.StringUtils.isBlank(creditCardDetailParam.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(creditCardDetailParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取信息失败");
		}
		//用户信息
		String openId = String.valueOf(keyMap.get("openId"));
		User user = userService.findUserByOpenId(openId);
		QueryCreditCardVo queryCreditCardVo = new QueryCreditCardVo();
		if(null == user){
			queryCreditCardVo.setUserId(-999);
		}else{
			queryCreditCardVo.setUserId(user.getId());
		}
		queryCreditCardVo.setId(creditCardDetailParam.getId());
		CreditCardDetailResponse creditCardDetailResponse =  new CreditCardDetailResponse();
		//信用卡基本信息
		CreditCardDetailVo creditCardDetailVo = creditCardInfoService.findCreditCardDetailVo(queryCreditCardVo);
		//查询费用信息
        List<CreditCardCost> creditCardCost = creditCardCostService.findCreditCardCostByCreditCardId(creditCardDetailParam.getId());
        //查询常见问题
		List<CreditCardQuestion> creditCardQuestion = creditCardQuestionService.findcreditCardQuestionByCreditCardId(creditCardDetailParam.getId());
        //关注者头像
        List<String> creditCardConcernPortrait = creditCardConcernService.findCreditCardConcernPortraitLimit(creditCardDetailParam.getId());
        //关注数量
        Integer CreditCardConcernCount = creditCardConcernService.findCreditCardConcernCount(creditCardDetailParam.getId());
        //相关活动
        List<CreditDetailActivityVo> creditDetailActivityVoList = activityService.findActivityVoListByCreditCardId(creditCardDetailParam.getId());
		String oprProcess = creditCardDetailVo.getOprProcess();
		if(StringUtils.isNotBlank(oprProcess)){
			oprProcess =  oprProcess.replaceAll(",",",1,");
			creditCardDetailVo.setOprProcess(oprProcess);
		}

        BeanUtils.copyProperties(creditCardDetailVo,creditCardDetailResponse);
        creditCardDetailResponse.setCreditCardCost(creditCardCost);
        creditCardDetailResponse.setCreditCardQuestion(creditCardQuestion);
        creditCardDetailResponse.setCreditCardConcernPortrait(creditCardConcernPortrait);
        creditCardDetailResponse.setCreditCardConcernTotal(CreditCardConcernCount);
        List<com.card.api.vo.vo.CreditDetailActivityVo> creditDetailActivityVoList1 = new ArrayList<>();
        if(null != creditDetailActivityVoList){
            for(CreditDetailActivityVo creditDetailActivityVo : creditDetailActivityVoList) {
                com.card.api.vo.vo.CreditDetailActivityVo creditDetailActivityVo1 = new com.card.api.vo.vo.CreditDetailActivityVo();
                BeanUtils.copyProperties(creditDetailActivityVo,creditDetailActivityVo1);
                creditDetailActivityVoList1.add(creditDetailActivityVo1);
            }
        }
        creditCardDetailResponse.setCreditDetailActivityVoList(creditDetailActivityVoList1);
		creditCardDetailResponse.setIsPass(siteConfig.isPass);
		return creditCardDetailResponse;
	}

	/**
	 * @notes 查询信用卡评论
	 * @param creditCardCommentParam
	 * @return
	 */
	public CreditCardCommentResponse findCreditCardComment(CreditCardCommentParam creditCardCommentParam) throws Exception {
		CreditCardCommentResponse creditCardCommentResponse = new CreditCardCommentResponse();
		//相关评论
		com.card.inteface.vo.CommentVo commentVo = new com.card.inteface.vo.CommentVo();
		commentVo.setObjectId(creditCardCommentParam.getId());
		PageBean<com.card.inteface.vo.CommentVo> pageBean = new PageBean<com.card.inteface.vo.CommentVo>();
		pageBean.setBean(commentVo);
		pageBean.setPageNum(creditCardCommentParam.getPageNum());
		pageBean.setPageSize(creditCardCommentParam.getPageSize());
		PageBean<com.card.inteface.vo.CommentVo> list = commentService.findCommentVoByCreditCardId(pageBean);
		creditCardCommentResponse.setCount(list.getTotal());
		creditCardCommentResponse.setPage(list.getPageNum());
		creditCardCommentResponse.setPageSize(list.getPageSize());
		creditCardCommentResponse.setTotalPage(list.getPages());
		List<CommentVo> commentVos = new ArrayList<>();
		List<com.card.inteface.vo.CommentVo> list1 = list.getDatas();
		if(null != list1 && list1.size()>0){
			for(com.card.inteface.vo.CommentVo commentVo1 : list1){
				CommentVo commentVo2 = new CommentVo();
				BeanUtils.copyProperties(commentVo1,commentVo2);
				List<CommentReplyVo> commentReplyVoList = commentVo1.getCommentReplyVo();
				List<com.card.api.vo.vo.CommentReplyVo> commentReplyVos = new ArrayList<>();
				if(null !=commentReplyVoList && commentReplyVoList.size()>0 ){
					for(CommentReplyVo commentReplyVo : commentReplyVoList){
						com.card.api.vo.vo.CommentReplyVo commentReplyVo1 = new com.card.api.vo.vo.CommentReplyVo();
						BeanUtils.copyProperties(commentReplyVo,commentReplyVo1);
						commentReplyVos.add(commentReplyVo1);
					}
					commentVo2.setCommentReplyVo(commentReplyVos);
				}
				commentVos.add(commentVo2);
			}
		}
		creditCardCommentResponse.setCommentVos(commentVos);
		return creditCardCommentResponse;
	}

	@Override
	public CreditCardConcernResponse findCreditCardConcernPage(CreditCardConcernParam param) throws Exception {
		CreditCardConcernResponse creditCardConcemResponse = new CreditCardConcernResponse();
		CreditCardConcernVo creditCardConcemVo = new CreditCardConcernVo();
		creditCardConcemVo.setCreditCardId(param.getId());
		PageBean<CreditCardConcernVo> pageBean = new PageBean<CreditCardConcernVo>();
		pageBean.setBean(creditCardConcemVo);
		pageBean.setPageNum(param.getPageNum());
		pageBean.setPageSize(param.getPageSize());
		PageBean<CreditCardConcernVo> list = creditCardConcernService.findCreditCardConcernPage(pageBean);
		creditCardConcemResponse.setCount(list.getTotal());
		creditCardConcemResponse.setPage(list.getPageNum());
		creditCardConcemResponse.setPageSize(list.getPageSize());
		creditCardConcemResponse.setTotalPage(list.getPages());
		List<CreditCardConcernVo> list1 = list.getDatas();
		List<com.card.api.vo.vo.CreditCardConcernVo> list2 = new ArrayList<>();
		if(null != list1 && list1.size()>0){
			for(CreditCardConcernVo creditCardConcernVo :list1 ){
				com.card.api.vo.vo.CreditCardConcernVo creditCardConcernVo1 = new com.card.api.vo.vo.CreditCardConcernVo();
				BeanUtils.copyProperties(creditCardConcernVo,creditCardConcernVo1);
				list2.add(creditCardConcernVo1);
			}
			creditCardConcemResponse.setCreditCardConcemVoList(list2);
		}
		return creditCardConcemResponse;
	}

	/**
	 * @note 查询银信息
	 * @param bankParam
	 * @return
	 */
	public BankResponse findBankList(BankParam bankParam) throws Exception {
		BankResponse bankResponse = new BankResponse();
		List<com.card.inteface.vo.BankVo> bankVoList = bankService.findBankVoList();
		List<BankVo> bankVoList1 = new ArrayList<>();
		if(null != bankVoList && bankVoList.size()>0){
			for(com.card.inteface.vo.BankVo bankVo : bankVoList){
				BankVo bankVo1 = new BankVo();
				BeanUtils.copyProperties(bankVo,bankVo1);
				bankVoList1.add(bankVo1);
			}
			bankResponse.setBankVoList(bankVoList1);
		}
		return bankResponse;
	}

	/**
	 * @notes 信用卡搜索
	 * @param bankCreditCardListQueryParam
	 * @return
	 */
	public BankCreditCardQueryResponse queryCreditCardList(BankCreditCardListQueryParam bankCreditCardListQueryParam) throws Exception {
		BankCreditCardQueryResponse bankCreditCardQueryResponse = new BankCreditCardQueryResponse();
		BankCreditCard bankCreditCard = new BankCreditCard();
		bankCreditCard.setBankId(bankCreditCardListQueryParam.getBankId());
		bankCreditCard.setCardName(bankCreditCardListQueryParam.getCreditCardName());
		List<com.card.inteface.vo.CreditCardInfoQueryVo> creditCardInfoQueryVoList = bankCreditCardService.queryCreditCardList(bankCreditCard);
		List<CreditCardInfoQueryVo> creditCardInfoQueryVoList1 = new ArrayList<>();
		if(null != creditCardInfoQueryVoList && creditCardInfoQueryVoList.size()>0){
			for(com.card.inteface.vo.CreditCardInfoQueryVo creditCardInfoQueryVo : creditCardInfoQueryVoList){
				CreditCardInfoQueryVo creditCardInfoQueryVo1 = new CreditCardInfoQueryVo();
				BeanUtils.copyProperties(creditCardInfoQueryVo,creditCardInfoQueryVo1);
				creditCardInfoQueryVoList1.add(creditCardInfoQueryVo1);
			}
			bankCreditCardQueryResponse.setCreditCardInfoQueryVos(creditCardInfoQueryVoList1);
		}
		return bankCreditCardQueryResponse;
	}

	public static void main(String[] args) {
		String a = "代丹丹,的答复,品牌,的地位";
		System.out.println(a.replaceAll(",",",1,"));
	}

	/**
	 * @notes 添加信用卡
	 * @param addCreditCardApplyDataParam
	 * @return
	 */
	public AddCreditCardApplyDataResponse addCreditCardApplyData(AddCreditCardApplyDataParam addCreditCardApplyDataParam) throws Exception {
		AddCreditCardApplyDataResponse addCreditCardApplyDataResponse = new AddCreditCardApplyDataResponse();
		if (addCreditCardApplyDataParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(addCreditCardApplyDataParam.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(addCreditCardApplyDataParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取信息失败");
		}
		//保存用户信息
		String openId = String.valueOf(keyMap.get("openId"));
		User user = userService.findUserByOpenId(String.valueOf(keyMap.get("openId")));
		if(null == user){
			throw new BusinessException(1, "用户对象为空");
		}

		CreditCardApplyData creditCardApplyData = new CreditCardApplyData();
		creditCardApplyData.setCreditCardId(addCreditCardApplyDataParam.getCreditCardId());
		creditCardApplyData.setUserId(user.getId());
		creditCardApplyDataService.addCreditCardApplyData(creditCardApplyData);
		return addCreditCardApplyDataResponse;
	}
}
