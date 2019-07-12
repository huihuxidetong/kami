package com.card.service.impl;

import com.card.inteface.entity.UserConcernBasic;
import com.card.inteface.utils.Constants;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.*;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.entity.CreditCardInfo;  
import com.card.inteface.service.CreditCardInfoService;  
import com.card.inteface.dao.CreditCardInfoDao;

import java.util.List;

/**
 * @notes:信用卡信息Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class CreditCardInfoServiceImpl implements CreditCardInfoService {

	@Autowired
	private CreditCardInfoDao creditCardInfoDao;

	/**
	 * @notes 查询首页信用卡信息
	 * @return
	 */
	@Override
	public List<CreditCardInfoVo> findIndexCreditCardInfoVo() throws Exception {
		return creditCardInfoDao.findIndexCreditCardInfoVo();
	}

	/**
	 * @notes 信用看阿里列表查询银行列表
	 * @return
	 */
	public List<BankVo> findBankVoListForCreditCradList() throws Exception {
		return creditCardInfoDao.findBankVoListForCreditCradList();
	}

	/**
	 * @notes 查询热门的信用卡
	 * @return
	 */
	public List<CreditCardInfoHotVo> findCreditCardInfoHotVoList() throws Exception {
		return creditCardInfoDao.findCreditCardInfoHotVoList();
	}

	/**
	 * @note 查询信用卡分页
	 * @param pageBean
	 * @return
	 */
	public PageBean<CreditCardInfoHotVo> findCreditCardInfoPage(PageBean<CreditCardInfoHotVo> pageBean) throws Exception{
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<CreditCardInfoHotVo> list = creditCardInfoDao.findCreditCardInfoPage();
		return new PageBean<CreditCardInfoHotVo>(list);
	}

	/**
	 * @note 查询信用卡分页
	 * @param pageBean
	 * @return
	 */
	public PageBean<CreditCardInfoHotVo> findBankCreditCardListPage(PageBean<CreditCardInfoHotVo> pageBean) throws Exception{
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<CreditCardInfoHotVo> list = creditCardInfoDao.findBankCreditCardListPage(pageBean.getBean().getBankId());
		return new PageBean<CreditCardInfoHotVo>(list);
	}

	/**
	 * @notes 通过信用卡id查询信用卡信息
	 * @param queryCreditCardVo
	 * @return
	 */
	public CreditCardDetailVo findCreditCardDetailVo(QueryCreditCardVo queryCreditCardVo) throws Exception {
		//更新查看次数
		CreditCardInfo creditCardInfo = creditCardInfoDao.findCreditCardInfoById(queryCreditCardVo.getId());
		if (null != creditCardInfo) {
			creditCardInfo.setCardLookNumber(creditCardInfo.getCardLookNumber() + 1);
			creditCardInfoDao.updateCreditCardInfo(creditCardInfo);
		}
		CreditCardDetailVo creditCardDetailVo = creditCardInfoDao.findCreditCardDetailVo(queryCreditCardVo);
		String creditCharacters = creditCardDetailVo.getCreditCharacters();
		String cardLeval = creditCardDetailVo.getCardLeval();
		String cardCurrency = creditCardDetailVo.getCardCurrency();
		String cardHairpinOrgani = creditCardDetailVo.getCardHairpinOrgani();
		String creditCharacterssName = "";
		String cardLevalName = "";
		String cardCurrencyName = "";
		String cardHairpinOrganiName = "";
		if(StringUtils.isNotBlank(creditCharacters)){
			String [] creditCharacterss = creditCharacters.split(",");
			for(String key : creditCharacterss){
				creditCharacterssName += Constants.CREDIT_CHARACTERS.get(Integer.valueOf(key)) + ",";
			}
			if(StringUtils.isNotBlank(creditCharacterssName)){
				creditCharacterssName = creditCharacterssName.substring(0,creditCharacterssName.length()-1);
			}
			creditCardDetailVo.setCreditCharacters(creditCharacterssName);
		}
		if(StringUtils.isNotBlank(cardLeval)){
			String [] cardLevals = cardLeval.split(",");
			for(String key : cardLevals){
				cardLevalName += Constants.CARD_LEVAL.get(Integer.valueOf(key)) + ",";
			}
			if(StringUtils.isNotBlank(cardLevalName)){
				cardLevalName = cardLevalName.substring(0,cardLevalName.length()-1);
			}
			creditCardDetailVo.setCardLeval(cardLevalName);
		}
		if(StringUtils.isNotBlank(cardCurrency)){
			String [] cardCurrencys = cardCurrency.split(",");
			for(String key : cardCurrencys){
				cardCurrencyName += Constants.CARD_CURRENCY.get(Integer.valueOf(key)) + ",";
			}
			if(StringUtils.isNotBlank(cardCurrencyName)){
				cardCurrencyName = cardCurrencyName.substring(0,cardCurrencyName.length()-1);
			}
			creditCardDetailVo.setCardCurrency(cardCurrencyName);
		}
		if(StringUtils.isNotBlank(cardHairpinOrgani)){
			String [] cardHairpinOrganis = cardHairpinOrgani.split(",");
			for(String key : cardHairpinOrganis){
				cardHairpinOrganiName += Constants.CARD_HAIRPIN_ORGANI.get(Integer.valueOf(key)) + ",";
			}
			if(StringUtils.isNotBlank(cardHairpinOrganiName)){
				cardHairpinOrganiName = cardHairpinOrganiName.substring(0,cardHairpinOrganiName.length()-1);
			}
			creditCardDetailVo.setCardHairpinOrgani(cardHairpinOrganiName);
		}
		return creditCardDetailVo;
	}
}
