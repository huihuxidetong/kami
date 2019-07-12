package com.fh.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * 说明：
 * 创建人：FH Q313596790
 * 修改时间：2015年11月24日
 * @version
 */
public class Constants {
	public static String PICTURE_VISIT_FILE_PATH = "";//图片访问的路径
	public static String PICTURE_SAVE_FILE_PATH = "";//图片存放的路径
	public static String getPICTURE_VISIT_FILE_PATH() {
		return PICTURE_VISIT_FILE_PATH;
	}

	public static void setPICTURE_VISIT_FILE_PATH(String pICTURE_VISIT_FILE_PATH) {
		PICTURE_VISIT_FILE_PATH = pICTURE_VISIT_FILE_PATH;
	}

	public static String getPICTURE_SAVE_FILE_PATH() {
		return PICTURE_SAVE_FILE_PATH;
	}

	public static void setPICTURE_SAVE_FILE_PATH(String pICTURE_SAVE_FILE_PATH) {
		PICTURE_SAVE_FILE_PATH = pICTURE_SAVE_FILE_PATH;
	}
	public static void main(String[] args) {
		Constants.setPICTURE_SAVE_FILE_PATH("D:/Tomcat 6.0/webapps/FH/topic/");
		Constants.setPICTURE_VISIT_FILE_PATH("http://192.168.1.225:8888/FH/topic/");
	}

	/**
	 * 所属银行
	 */
	public static final Map<Integer,String> BANK_BELONG = new LinkedHashMap<Integer, String>(0);
	public static final Integer TJ_BANK = new Integer(1);
	public static final Integer MS_BANK = new Integer(2);
	public static final Integer JS_BANK = new Integer(3);
	public static final Integer ZS_BANK = new Integer(4);
	static {
		BANK_BELONG.put(1, "交通银行");
		BANK_BELONG.put(2, "民生银行");
		BANK_BELONG.put(3, "建设银行");
		BANK_BELONG.put(4, "招商银行");
	}

	/**
	 * 信用卡名称
	 */
	public static final Map<Integer,String> CREDIT_NAME = new LinkedHashMap<Integer, String>(0);
	public static final Integer BZ_CARD = new Integer(1);
	public static final Integer IN_CARD = new Integer(2);
	public static final Integer QQ_CARD = new Integer(3);
	static {
		CREDIT_NAME.put(1, "标准卡");
		CREDIT_NAME.put(2, "in卡");
		CREDIT_NAME.put(3, "QQ联名卡");
	}

	/**
	 * 等级
	 */
	public static final Map<Integer,String> CARD_LEVAL = new LinkedHashMap<Integer, String>(0);
	public static final Integer gold_card = new Integer(1);
	public static final Integer sliver_card = new Integer(2);
	public static final Integer black_card = new Integer(3);
	public static final Integer zuanshi = new Integer(4);
	public static final Integer puka = new Integer(5);
	public static final Integer bzbaijinka = new Integer(6);
	public static final Integer jybaijinka = new Integer(7);
	public static final Integer hhbaijinka = new Integer(8);
	public static final Integer bfzbaijinka = new Integer(9);
	public static final Integer zushika = new Integer(10);
	public static final Integer wuxiaka = new Integer(11);
	public static final Integer shijieka = new Integer(12);
	public static final Integer yuxika = new Integer(13);
	public static final Integer taijinka = new Integer(14);
	static {
		CARD_LEVAL.put(1, "金卡");
		CARD_LEVAL.put(2, "银卡");
		CARD_LEVAL.put(3, "黑卡");
		CARD_LEVAL.put(4, "钻石");
		CARD_LEVAL.put(5, "普卡");
		CARD_LEVAL.put(6, "标准白金卡");
		CARD_LEVAL.put(7, "精英白金卡");
		CARD_LEVAL.put(8, "豪华白金卡");
		CARD_LEVAL.put(9, "百夫长白金卡");
		CARD_LEVAL.put(10, "钻石卡");
		CARD_LEVAL.put(11, "无限卡");
		CARD_LEVAL.put(12, "世界卡");
		CARD_LEVAL.put(13, "御玺卡");
		CARD_LEVAL.put(14, "钛金卡");
	}

	/**
	 *发卡组织
	 */
	public static final Map<Integer,String> CARD_HAIRPIN_ORGANI = new LinkedHashMap<Integer, String>(0);
	public static final Integer yin_lian = new Integer(1);
	public static final Integer visas = new Integer(2);
	public static final Integer masters = new Integer(3);
	public static final Integer jbc = new Integer(4);
	public static final Integer ae = new Integer(5);
	public static final Integer yinlian_visas = new Integer(6);
	public static final Integer yinlia_wanshida = new Integer(7);
	public static final Integer meiguoyuntong = new Integer(8);
	static {
		CARD_HAIRPIN_ORGANI.put(1, "银联");
		CARD_HAIRPIN_ORGANI.put(2, "Visa");
		CARD_HAIRPIN_ORGANI.put(3, "万事达");
		CARD_HAIRPIN_ORGANI.put(4, "JCB");
		CARD_HAIRPIN_ORGANI.put(5, "AE");
		CARD_HAIRPIN_ORGANI.put(6, "银联+Visa");
		CARD_HAIRPIN_ORGANI.put(7, "银联+万事达");
		CARD_HAIRPIN_ORGANI.put(8, "美国运通");
	}

	/**
	 * 币种
	 */
	public static final Map<Integer,String> CARD_CURRENCY = new LinkedHashMap<Integer, String>(0);
	public static final Integer rmb = new Integer(1);
	public static final Integer sbz = new Integer(2);
	public static final Integer qbz = new Integer(3);
	public static final Integer my = new Integer(4);
	static {
		CARD_CURRENCY.put(1, "人民币");
		CARD_CURRENCY.put(2, "双币种");
		CARD_CURRENCY.put(3, "全币种");
		CARD_CURRENCY.put(4, "美元");
	}

	/**
	 *特性
	 */
	public static final Map<Integer,String> CREDIT_CHARACTERS = new LinkedHashMap<Integer, String>(0);
	public static final Integer user_abroad = new Integer(1);
	public static final Integer free_yearCoast = new Integer(2);
	public static final Integer high_limit = new Integer(3);
	public static final Integer quickly_check = new Integer(4);
	public static final Integer jingwaika = new Integer(5);
	public static final Integer xiaofeifanxian = new Integer(6);
	public static final Integer duobeijifen = new Integer(7);
	public static final Integer gaoduanka = new Integer(8);
	public static final Integer shenka = new Integer(9);
	public static final Integer quxianyouhui = new Integer(10);
	static {
		CREDIT_CHARACTERS.put(1, "免年费");
		CREDIT_CHARACTERS.put(2, "国外可使用");
		CREDIT_CHARACTERS.put(3, "额度高");
		CREDIT_CHARACTERS.put(4, "审核快");
		CREDIT_CHARACTERS.put(5, "境外卡");
		CREDIT_CHARACTERS.put(6, "消费返现");
		CREDIT_CHARACTERS.put(7, "多倍积分");
		CREDIT_CHARACTERS.put(8, "高端卡");
		CREDIT_CHARACTERS.put(9, "申卡");
		CREDIT_CHARACTERS.put(10, "取现优惠");
	}

	/**
	 *活动类型
	 */
	public static final Map<Integer,String> ACTIVITY_TYPE = new LinkedHashMap<Integer, String>(0);
	public static final Integer PING_TAI = new Integer(1);
	public static final Integer CREDIT_ACTIVITY = new Integer(2);
	static {
		ACTIVITY_TYPE.put(1, "平台活动");
		ACTIVITY_TYPE.put(2, "银行活动");
	}

	/**
	 *信用卡标签
	 */
	public static final List<String> CARD_LABEL = new ArrayList<>();
	static {
		CARD_LABEL.add("旅行达人");
		CARD_LABEL.add("出差党");
		CARD_LABEL.add("游戏狂");
		CARD_LABEL.add("网购剁手党⼈");
		CARD_LABEL.add("刷剧达人");
		CARD_LABEL.add("投资理财");
		CARD_LABEL.add("投资理财");
		CARD_LABEL.add("海淘族");
		CARD_LABEL.add("运动一族");
		CARD_LABEL.add("电影娱乐");
		CARD_LABEL.add("单身狗");
		CARD_LABEL.add("首卡申请");
		CARD_LABEL.add("卡面收藏");
		CARD_LABEL.add("车主");
		CARD_LABEL.add("居家生活");
	}

	/**
	 *活动自定义标签
	 */
	public static final List<String> ACTIVITY_CUSTOM_LABLE = new ArrayList<>();
	static {
		ACTIVITY_CUSTOM_LABLE.add("旅行达人");
		ACTIVITY_CUSTOM_LABLE.add("出差党");
		ACTIVITY_CUSTOM_LABLE.add("游戏狂");
		ACTIVITY_CUSTOM_LABLE.add("网购剁手党⼈");
		ACTIVITY_CUSTOM_LABLE.add("刷剧达人");
		ACTIVITY_CUSTOM_LABLE.add("投资理财");
		ACTIVITY_CUSTOM_LABLE.add("投资理财");
		ACTIVITY_CUSTOM_LABLE.add("海淘族");
		ACTIVITY_CUSTOM_LABLE.add("运动一族");
		ACTIVITY_CUSTOM_LABLE.add("电影娱乐");
		ACTIVITY_CUSTOM_LABLE.add("单身狗");
		ACTIVITY_CUSTOM_LABLE.add("首卡申请");
		ACTIVITY_CUSTOM_LABLE.add("卡面收藏");
		ACTIVITY_CUSTOM_LABLE.add("车主");
		ACTIVITY_CUSTOM_LABLE.add("居家生活");
	}
}
