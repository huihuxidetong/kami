package com.card.inteface.entity;

import java.io.Serializable;

/**
 * @notes:信用卡费用实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class CreditCardCost implements Serializable {

	private static final long serialVersionUID = 201809261303390L;
	
    private Integer id;
    
    private Integer creditCardId;
    
    private String costType;
    
    private String costExplain;
    
    private String costMeasures;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Integer creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getCostExplain() {
        return costExplain;
    }

    public void setCostExplain(String costExplain) {
        this.costExplain = costExplain;
    }

    public String getCostMeasures() {
        return costMeasures;
    }

    public void setCostMeasures(String costMeasures) {
        this.costMeasures = costMeasures;
    }
}