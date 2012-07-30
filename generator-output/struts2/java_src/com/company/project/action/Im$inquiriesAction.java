/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package com.company.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.web.scope.Flash;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.company.project.model.*;
import com.company.project.dao.*;
import com.company.project.service.*;
import com.company.project.vo.query.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class Im$inquiriesAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/Im$inquiries/query.jsp";
	protected static final String LIST_JSP= "/pages/Im$inquiries/list.jsp";
	protected static final String CREATE_JSP = "/pages/Im$inquiries/create.jsp";
	protected static final String EDIT_JSP = "/pages/Im$inquiries/edit.jsp";
	protected static final String SHOW_JSP = "/pages/Im$inquiries/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/Im$inquiries/list.do";
	
	private Im$inquiriesManager im$inquiriesManager;
	
	private Im$inquiries im$inquiries;
	java.lang.Long id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			im$inquiries = new Im$inquiries();
		} else {
			im$inquiries = (Im$inquiries)im$inquiriesManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setIm$inquiriesManager(Im$inquiriesManager manager) {
		this.im$inquiriesManager = manager;
	}	
	
	public Object getModel() {
		return im$inquiries;
	}
	
	public void setInquiryId(java.lang.Long val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		Im$inquiriesQuery query = newQuery(Im$inquiriesQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = im$inquiriesManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		im$inquiriesManager.save(im$inquiries);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		im$inquiriesManager.update(this.im$inquiries);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Long id = new java.lang.Long((String)params.get("inquiryId"));
			im$inquiriesManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

}
