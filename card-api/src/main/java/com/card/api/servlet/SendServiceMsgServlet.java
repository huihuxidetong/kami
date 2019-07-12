package com.card.api.servlet;

import com.card.api.common.exception.BusinessException;
import com.card.api.service.ActivityService;
import com.card.api.utils.JedisUtil;
import com.card.api.vo.params.ActivityCreditCardListParam;
import com.card.api.vo.params.ActivityDetailParam;
import com.card.api.vo.params.ActivityListParam;
import com.card.api.vo.params.JoinActivityParam;
import com.card.api.vo.responses.ActivityCreditCardListResponse;
import com.card.api.vo.responses.ActivityDetailResponse;
import com.card.api.vo.responses.ActivityListResponse;
import com.card.api.vo.responses.JoinActivityResponse;
import com.card.api.vo.vo.ActivityIndexVo;
import com.card.inteface.entity.ActivityApply;
import com.card.inteface.entity.User;
import com.card.inteface.service.ActivityApplyService;
import com.card.inteface.service.UserService;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.ActivityCreditCardVo;
import com.card.inteface.vo.ActivityDetailVo;
import com.card.inteface.vo.ActivityVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SendServiceMsgServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost方法被调用了");
		resp.getOutputStream().write("doPost方法被调用了".getBytes());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost方法被调用了");
		resp.getOutputStream().write("doPost方法被调用了".getBytes());
	}
}
