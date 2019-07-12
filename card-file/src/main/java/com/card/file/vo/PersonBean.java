package com.card.file.vo;

/**
 * createUser：lizq
 * createDate：2016年4月14日
 * version：1.0
 * note：
 *
 **/

public class PersonBean implements java.io.Serializable {

    /**
     * <code>name</code>属性(注意大小写)
     */
    private String name = null;

    private boolean deceased = false;

    /** 无参构造器(没有参数) */
    public PersonBean() {
    }

    /**
     * <code>name</code>属性的Getter方法
     */
    public String getName() {
        return name;
    }

    /**
     * <code>name</code>属性的Setter方法
     * @param value
     */
    public void setName(final String value) {
        name = value;
    }

    /**
     * "deceased"属性的Getter方法
     * 布尔型属性的Getter方法的不同形式(这里使用了is而非get)
     */
    public boolean isDeceased() {
        return deceased;
    }

    /**
     * <code>deceased</code>属性的Setter方法
     * @param value
     */
    public void setDeceased(final boolean value) {
        deceased = value;
    }
}