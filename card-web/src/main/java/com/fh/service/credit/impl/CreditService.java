package com.fh.service.credit.impl;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import com.fh.util.Constants;
import com.fh.util.PropertiesUtil;
import com.fh.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.service.credit.CreditManager;
import org.springframework.transaction.annotation.Transactional;

/** 
 * 说明： 信用卡管理
 * 创建人：FH Q313596790
 * 创建时间：2018-09-26
 * @version
 */
@Transactional(rollbackFor = Exception.class)
@Service("creditService")
public class CreditService implements CreditManager{
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Autowired
	private PropertiesUtil propertiesUtil;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		//保存信用卡信息
		pd.put("card_logo",pd.get("sourceFilePath"));
		pd.put("bank_credit_card_id",pd.get("credit_card"));
		pd.put("status","2");
		pd.put("card_hairpin_organi",pd.get("card_hairpin_organi_ids"));
		pd.put("credit_characters",pd.get("credit_characters_ids"));
		pd.put("opr_process",pd.get("opr_process_str"));
		dao.save("CreditMapper.save",pd);
		//保存银行卡费用信息
		PageData pageDataCost=new PageData();
		String cost_str = pd.getString("cost_str");
		JSONArray jsonArray = JSONArray.fromObject(cost_str);
		if(null != jsonArray && jsonArray.size()>0) {
			pageDataCost.put("credit_card_id", pd.get("credit_card"));
			List<PageData> pageDataList = new ArrayList<>();
			for (Object jsonObject : jsonArray) {
				JSONObject jsonObject1 = JSONObject.fromObject(jsonObject);
				PageData pageData = new PageData();
				pageData.put("credit_card_id", pd.get("credit_card"));
				pageData.put("cost_type", jsonObject1.get("cost_type"));
				pageData.put("cost_explain", jsonObject1.get("cost_explain"));
				pageData.put("cost_measures", jsonObject1.get("cost_measures"));
				pageDataList.add(pageData);
			}
			pageDataCost.put("pageDataList", pageDataList);
			dao.save("CreditCardCostMapper.batchAdd", pageDataCost);
		}
		//保存银行卡问题信息
		PageData pageDataQuestion=new PageData();
		String question_str = pd.getString("question_str");
		JSONArray jsonArray1 = JSONArray.fromObject(question_str);
		if(null != jsonArray1 && jsonArray1.size()>0) {
			pageDataCost.put("credit_card_id", pd.get("credit_card"));
			List<PageData> pageDataList1 = new ArrayList<>();
			for (Object jsonObject : jsonArray1) {
				JSONObject jsonObject1 = JSONObject.fromObject(jsonObject);
				PageData pageData = new PageData();
				pageData.put("credit_card_id", pd.get("credit_card"));
				pageData.put("question", jsonObject1.get("question"));
				pageData.put("answer", jsonObject1.get("answer"));
				pageDataList1.add(pageData);
			}
			pageDataQuestion.put("pageDataList", pageDataList1);
			dao.save("CreditCardQuestionMapper.batchAdd", pageDataQuestion);
		}
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("CreditMapper.delete", pd);

	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void edit(PageData pd)throws Exception{
	    Integer credit_card_id=Integer.valueOf(pd.get("credit_card").toString());
		//编辑信用卡信息
		pd.put("card_logo",pd.get("sourceFilePath"));
		pd.put("bank_credit_card_id",pd.get("credit_card"));
		pd.put("status","2");
		pd.put("card_hairpin_organi",pd.get("card_hairpin_organi_ids"));
		pd.put("credit_characters",pd.get("credit_characters_ids"));
		pd.put("opr_process",pd.get("opr_process_str"));
		dao.update("CreditMapper.edit",pd);
		//保存银行卡费用信息
		//删除银行卡费用信息
		PageData pageDataCost=new PageData();
		String cost_str = pd.getString("cost_str");
		JSONArray jsonArray = JSONArray.fromObject(cost_str);
		if(null != jsonArray && jsonArray.size()>0) {
			dao.delete("CreditCardCostMapper.deleteCreditCardCostByCardId",Integer.valueOf(String.valueOf(pd.get("credit_card"))));
			pageDataCost.put("credit_card_id", credit_card_id);
			List<PageData> pageDataList = new ArrayList<>();
			for (Object jsonObject : jsonArray) {
				JSONObject jsonObject1 = JSONObject.fromObject(jsonObject);
				PageData pageData = new PageData();
				pageData.put("credit_card_id", credit_card_id);
				pageData.put("cost_type", jsonObject1.get("cost_type"));
				pageData.put("cost_explain", jsonObject1.get("cost_explain"));
				pageData.put("cost_measures", jsonObject1.get("cost_measures"));
				pageDataList.add(pageData);
			}
			pageDataCost.put("pageDataList", pageDataList);
			dao.save("CreditCardCostMapper.batchAdd", pageDataCost);
		}
		//保存银行卡问题信息
		PageData pageDataQuestion=new PageData();
		String question_str = pd.getString("question_str");
		JSONArray jsonArray1 = JSONArray.fromObject(question_str);
		if(null != jsonArray1 && jsonArray1.size()>0) {
			dao.delete("CreditCardQuestionMapper.deleteCreditCardQuestionByCardId",Integer.valueOf(String.valueOf(pd.get("credit_card"))));
			pageDataCost.put("credit_card_id", credit_card_id);
			List<PageData> pageDataList1 = new ArrayList<>();
			for (Object jsonObject : jsonArray1) {
				JSONObject jsonObject1 = JSONObject.fromObject(jsonObject);
				PageData pageData = new PageData();
				pageData.put("credit_card_id", credit_card_id);
				pageData.put("question", jsonObject1.get("question"));
				pageData.put("answer", jsonObject1.get("answer"));
				pageDataList1.add(pageData);
			}
			pageDataQuestion.put("pageDataList", pageDataList1);
			dao.save("CreditCardQuestionMapper.batchAdd", pageDataQuestion);
		}
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		List<PageData> pageDataList=(List<PageData>)dao.findForList("CreditMapper.datalistPage", page);
		if(null != pageDataList && pageDataList.size()>0){
			for(PageData pageData : pageDataList){
				String card_leval = String.valueOf(pageData.get("card_leval"));
				String card_hairpin_organi = String.valueOf(pageData.get("card_hairpin_organi"));
				if(StringUtils.isNotBlank(card_leval)) {
					pageData.put("cardLeval", Constants.CARD_LEVAL.get(Integer.valueOf(card_leval)));
				}
				String card_hairpin_organi_name = "";
				if(StringUtils.isNotBlank(card_hairpin_organi) && !"null".equals(card_hairpin_organi)) {
					String [] card_hairpin_organis = card_hairpin_organi.split(",");
					for(String key : card_hairpin_organis) {
						card_hairpin_organi_name += Constants.CARD_HAIRPIN_ORGANI.get(Integer.valueOf(key)) + ",";
					}
					if(StringUtils.isNotBlank(card_hairpin_organi_name)){
						pageData.put("card_hairpin_organi",card_hairpin_organi_name.substring(0,card_hairpin_organi_name.length()-1));
					}
				}
			}
		}
		return  pageDataList;
	}
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("CreditMapper.listAll",pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		PageData pageData=(PageData)dao.findForObject("CreditMapper.findById", pd);
		if(null != pageData){
			String card_leval = String.valueOf(pageData.get("card_leval"));
			String card_hairpin_organi = String.valueOf(pageData.get("card_hairpin_organi"));
			if(StringUtils.isNotBlank(card_leval)) {
				pageData.put("card_leval", Constants.CARD_LEVAL.get(Integer.valueOf(card_leval)));
			}
			String card_hairpin_organi_name = "";
			if(StringUtils.isNotBlank(card_hairpin_organi) && !"null".equals(card_hairpin_organi)) {
				String [] card_hairpin_organis = card_hairpin_organi.split(",");
				for(String key : card_hairpin_organis) {
					card_hairpin_organi_name += Constants.CARD_HAIRPIN_ORGANI.get(Integer.valueOf(key)) + ",";
				}
				if(StringUtils.isNotBlank(card_hairpin_organi_name)){
					pageData.put("card_hairpin_organi",card_hairpin_organi_name.substring(0,card_hairpin_organi_name.length()-1));
				}
			}
		/*	pageData.put("card_leval",Constants.CARD_LEVAL.get(Integer.valueOf(pageData.get("card_leval").toString())));
			pageData.put("card_hairpin_organi",Constants.CARD_HAIRPIN_ORGANI.get(Integer.valueOf(pageData.get("card_hairpin_organi").toString())));*/
			String card_logo = pageData.getString("card_logo");
			if(StringUtils.isNotBlank(card_logo)){
				pageData.put("downloand_source_file_url", propertiesUtil.getFileDownloadUrl() + pageData.get("card_logo"));
			}else{
				pageData.put("sourceFilePath",propertiesUtil.getDefault_img_url());
				pageData.put("downloand_source_file_url", propertiesUtil.getFileDownloadUrl() + File.separator + propertiesUtil.getDefault_img_url());
			}
			String card_label = pageData.getString("card_label");
			if(StringUtils.isNotBlank(card_label)){
				pageData.put("CARD_LABEL",Arrays.asList(card_label.split(",")));
			}
			String opr_process = pageData.getString("opr_process");
			if(StringUtils.isNotBlank(opr_process)){
				String [] opr_processList = opr_process.split(",");
				pageData.put("opr_processList",Arrays.asList(opr_processList));
			}
			//查询信用卡费用和问题
            PageData pageData1=new PageData();
			pageData1.put("credit_card_id",pageData.get("bank_credit_card_id"));
			List<PageData> creditCost=(List<PageData>)dao.findForList("CreditCardCostMapper.findCost",pageData1);
			List<PageData> creditQue=(List<PageData>) dao.findForList("CreditCardQuestionMapper.findQuestion",pageData1);
            pageData.put("creditCost",creditCost);
            pageData.put("creditQues",creditQue);

		}
		return pageData;
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("CreditMapper.deleteAll", ArrayDATA_IDS);
	}

	/**
	 * 更新信用卡的状态
	 * @param pageData
	 * @throws Exception
	 */
	public void updateCreditState(PageData pageData)throws Exception{
		dao.update("CreditMapper.updateCreditState",pageData);
	}

	/**
	 * @notes 根据银行查询Id查询所有的信用卡
	 * @param pageData
	 * @return
	 */
	public List<PageData> findAllCreditCardByBankId(PageData pageData) throws Exception {
		return (List<PageData>) dao.findForList("CreditMapper.findAllCreditCardByBankId",pageData);
	}
	
}

