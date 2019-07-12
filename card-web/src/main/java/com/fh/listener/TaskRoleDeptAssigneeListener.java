///*
// * 文件名：TaskRoleDeptAssigneeListener.java
// * 版权：Copyright by www.gujinsuo.com.cn
// * 描述：
// * 修改人：wcj
// * 修改时间：2017年11月1日
// * 跟踪单号：
// * 修改单号：
// * 修改内容：
// */
//
//package com.fh.listener;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.activiti.engine.delegate.DelegateTask;
//import org.activiti.engine.delegate.Expression;
//import org.activiti.engine.delegate.TaskListener;
//
//public class TaskRoleDeptAssigneeListener implements TaskListener, Serializable  {
//
//	private static final long serialVersionUID = -5617803636697981502L;
//
//	//private Expression deptId;
//    
//    private Expression roleId;
//	
//    
//	public void notify(DelegateTask delegateTask) {
//		//String empDeptId = deptId.getValue(delegateTask).toString() ;//根据部门找审批人
//		String roleKey =  roleId.getValue(delegateTask).toString() ;//根据角色找审批人
//		System.out.println("===============roleId="+roleKey+"===============");
//		//WorkFlowService workFlowService =(WorkFlowService)SpringContextUtil.getBean("workFlowService", WorkFlowService.class);
//		
//		List<Map<String, String>> assigneeList = new ArrayList<Map<String, String>>();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("1", "1");
//		assigneeList.add(map);
//		
//		//找到人
//		if(assigneeList!=null&&assigneeList.size()>0){
//			if(assigneeList.size()==1){//找到1个人
//				//String assigneeUserId=assignees.get(0).get("EMP_ID");
//				delegateTask.setAssignee("李四");
//				
//			}else{//找到多个人
//				/**
//				 * 添加候选人
//				 */
//				for (Map<String, String> assignee : assigneeList) {
//					String assigneeUserId=assignee.get("EMP_ID");
//					delegateTask.addCandidateUser(assigneeUserId);
//				}
//				
//			}
//		}else{//找不到人,赋给管理员
//			delegateTask.setAssignee("admin");
//			
//		}
//		
//		
//	}
//
//}
//
