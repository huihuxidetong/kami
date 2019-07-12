package com.card.api.vo.responses;

import com.card.api.vo.vo.CreditDetailActivityVo;
import com.card.inteface.entity.CreditCardCost;
import com.card.inteface.entity.CreditCardQuestion;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.CommentVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "信用卡详情信息返回", discriminator = "CreditCardDetailResponse", subTypes = {CreditCardDetailResponse.class})
public class CreditCardDetailResponse extends Response{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "信用卡id")
    private Integer id;
    @ApiModelProperty(value = "信用卡logo")
    private String cardLogo;
    @ApiModelProperty(value = "信用卡银行id")
    private Integer bankId;
    @ApiModelProperty(value = "信用卡银行名称")
    private String bankName;
    @ApiModelProperty(value = "信用卡名称")
    private String cardName;
    @ApiModelProperty(value = "信用卡特色")
    private String cardFeature;
    @ApiModelProperty(value = "信用卡标签,请按照，分隔")
    private String cardLabel;
    @ApiModelProperty(value = "卡的等级")
    private String cardLeval;
    @ApiModelProperty(value = "币种")
    private String cardCurrency;
    @ApiModelProperty(value = "发卡组织")
    private String cardHairpinOrgani;
    @ApiModelProperty(value = "免息规则")
    private String freeInterestRule;
    @ApiModelProperty(value = "积分规则")
    private String integralRule;
    @ApiModelProperty(value = "申请条件")
    private String applyCondition;
    @ApiModelProperty(value = "首刷礼（请按照；分隔）")
    private String firstBrushCeremony;
    @ApiModelProperty(value = "专享特权")
    private String cardPrivilege;
    @ApiModelProperty(value = "费用")
    private List<CreditCardCost> creditCardCost;
    @ApiModelProperty(value = "操作流程")
    private String oprProcess;
    @ApiModelProperty(value = "常见问题")
    private List<CreditCardQuestion> creditCardQuestion;
    @ApiModelProperty(value = "关注者头像")
    private List<String> creditCardConcernPortrait;
    @ApiModelProperty(value = "总关注人数")
    private Integer creditCardConcernTotal;
    @ApiModelProperty(value = "相关活动")
    private List<CreditDetailActivityVo> creditDetailActivityVoList;
    @ApiModelProperty(value = "是否关注 1=是 0=否")
    private Integer userConcernStatus;
    @ApiModelProperty(value = "开卡链接")
    private String openCreditCardUrl;
    @ApiModelProperty(value = "特性")
    private String creditCharacters;
    @ApiModelProperty(value = "免息期限")
    private String freeInterestPeriod;
    @ApiModelProperty(value = "是否审核通过 0=否 1=是")
    private String isPass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardLogo() {
        return cardLogo;
    }

    public void setCardLogo(String cardLogo) {
        this.cardLogo = cardLogo;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardFeature() {
        return cardFeature;
    }

    public void setCardFeature(String cardFeature) {
        this.cardFeature = cardFeature;
    }

    public String getCardLabel() {
        return cardLabel;
    }

    public void setCardLabel(String cardLabel) {
        this.cardLabel = cardLabel;
    }

    public String getCardCurrency() {
        return cardCurrency;
    }

    public void setCardCurrency(String cardCurrency) {
        this.cardCurrency = cardCurrency;
    }

    public String getCardHairpinOrgani() {
        return cardHairpinOrgani;
    }

    public void setCardHairpinOrgani(String cardHairpinOrgani) {
        this.cardHairpinOrgani = cardHairpinOrgani;
    }

    public String getFreeInterestRule() {
        return freeInterestRule;
    }

    public void setFreeInterestRule(String freeInterestRule) {
        this.freeInterestRule = freeInterestRule;
    }

    public String getIntegralRule() {
        return integralRule;
    }

    public void setIntegralRule(String integralRule) {
        this.integralRule = integralRule;
    }

    public String getApplyCondition() {
        return applyCondition;
    }

    public void setApplyCondition(String applyCondition) {
        this.applyCondition = applyCondition;
    }

    public String getFirstBrushCeremony() {
        return firstBrushCeremony;
    }

    public void setFirstBrushCeremony(String firstBrushCeremony) {
        this.firstBrushCeremony = firstBrushCeremony;
    }

    public String getCardPrivilege() {
        return cardPrivilege;
    }

    public void setCardPrivilege(String cardPrivilege) {
        this.cardPrivilege = cardPrivilege;
    }

    public String getCardLeval() {
        return cardLeval;
    }

    public void setCardLeval(String cardLeval) {
        this.cardLeval = cardLeval;
    }

    public List<CreditCardCost> getCreditCardCost() {
        return creditCardCost;
    }

    public void setCreditCardCost(List<CreditCardCost> creditCardCost) {
        this.creditCardCost = creditCardCost;
    }

    public String getOprProcess() {
        return oprProcess;
    }

    public void setOprProcess(String oprProcess) {
        this.oprProcess = oprProcess;
    }

    public List<CreditCardQuestion> getCreditCardQuestion() {
        return creditCardQuestion;
    }

    public void setCreditCardQuestion(List<CreditCardQuestion> creditCardQuestion) {
        this.creditCardQuestion = creditCardQuestion;
    }

    public List<String> getCreditCardConcernPortrait() {
        return creditCardConcernPortrait;
    }

    public void setCreditCardConcernPortrait(List<String> creditCardConcernPortrait) {
        this.creditCardConcernPortrait = creditCardConcernPortrait;
    }

    public Integer getCreditCardConcernTotal() {
        return creditCardConcernTotal;
    }

    public void setCreditCardConcernTotal(Integer creditCardConcernTotal) {
        this.creditCardConcernTotal = creditCardConcernTotal;
    }

    public List<CreditDetailActivityVo> getCreditDetailActivityVoList() {
        return creditDetailActivityVoList;
    }

    public void setCreditDetailActivityVoList(List<CreditDetailActivityVo> creditDetailActivityVoList) {
        this.creditDetailActivityVoList = creditDetailActivityVoList;
    }

    public Integer getUserConcernStatus() {
        return userConcernStatus;
    }

    public void setUserConcernStatus(Integer userConcernStatus) {
        this.userConcernStatus = userConcernStatus;
    }

    public String getOpenCreditCardUrl() {
        return openCreditCardUrl;
    }

    public void setOpenCreditCardUrl(String openCreditCardUrl) {
        this.openCreditCardUrl = openCreditCardUrl;
    }

    public String getCreditCharacters() {
        return creditCharacters;
    }

    public void setCreditCharacters(String creditCharacters) {
        this.creditCharacters = creditCharacters;
    }

    public String getFreeInterestPeriod() {
        return freeInterestPeriod;
    }

    public void setFreeInterestPeriod(String freeInterestPeriod) {
        this.freeInterestPeriod = freeInterestPeriod;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }
}
