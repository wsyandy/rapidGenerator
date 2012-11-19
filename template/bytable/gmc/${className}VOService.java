package com.gm.gmportal.mygm.${mvoPackage}.service;

<#assign className= table.className>    
<#assign classNameFirstLower= table.classNameFirstLower>   

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gm.gmcore.annotation.GmAutowired;
import com.gm.soa.common.util.DateUtil;
import com.gm.soa.common.vo.PaginationVO;
import com.gm.soa.vo.buyinglead.PaginationCondition;
import com.gm.gmportal.mygm.common.constants.CommonConstants;
import com.gm.soa.vo.${soaCorePackage}.${className}VO;
import com.gm.soa.remote.${soaCorePackage}.${className}ServiceRemote;


@Service
@GmAutowired
public class ${className}VOService{
	
	private static Logger logger = LoggerFactory.getLogger(${className}VOService.class); 

    private ${className}ServiceRemote ${classNameFirstLower}ServiceClient;
    
    <#if gh.fromTable>
    /**
     * 新增记录
     */
    public Map<String, Object> add${className}(<#list table.notPkColumns as column>${column.javaType} ${column.columnNameLower}<#if column_has_next>,</#if></#list>,Long currentCompId,Long currentContactId,Long currentUserId)throws Exception{
    	
    	logger.info("In function : add${className}: <#list table.notPkColumns as column>${column.columnNameLower}={}<#if column_has_next>,</#if></#list>,currentCompId={},currentContactId={},currentUserId={}", new Object[]{<#list table.notPkColumns as column>${column.columnNameLower}<#if column_has_next>,</#if></#list>,currentCompId,currentContactId,currentUserId});
    	Map<String, Object> result = new HashMap<String, Object>();
    	
    	${className}VO ${classNameFirstLower}VO=new ${className}VO();
    	
    	<#list table.notPkColumns as column>
    	${classNameFirstLower}VO.set${column.columnName}(${column.columnNameLower});
    	</#list>
    	Long id=${classNameFirstLower}ServiceClient.add${className}(${classNameFirstLower}VO,currentCompId,currentContactId,currentUserId);
    	
    	if(id==null){
    		result.put(CommonConstants.STATUS,CommonConstants.FAIL);
    	}else{
    		result.put(CommonConstants.STATUS,CommonConstants.SUCCESS);
    		result.put(CommonConstants.DATA,id);
    	}
    	logger.info("End function : add${className}");
    	return result;
    }
    
    /**
     * 删除记录
     */
    public Map<String, Object> delete${className}ById(long id,Long currentCompId,Long currentContactId,Long currentUserId)throws Exception{
    	logger.info("In function : delete${className}ById: id={}, currentCompId={},currentContactId={},currentUserId={}", new Object[]{id,currentCompId,currentContactId,currentUserId});
    	
    	Map<String, Object> result = new HashMap<String, Object>();
    	boolean success=${classNameFirstLower}ServiceClient.delete${className}ById(id,currentCompId,currentContactId,currentUserId);
    	if(!success){
    		result.put(CommonConstants.STATUS,CommonConstants.FAIL);
    	}else{
    		result.put(CommonConstants.STATUS,CommonConstants.SUCCESS);
    	}
    	logger.info("End function : delete${className}ById");
    	return result;
    }
    
    /**
     * 更新记录
     */
    public Map<String, Object> update${className}ById(<#list table.pkColumns as column>${column.javaType} ${column.columnNameLower}</#list>,<#list table.updateColumns as column>${column.javaType} ${column.columnNameLower}<#if column_has_next>,</#if></#list>,Long currentCompId,Long currentContactId,Long currentUserId)throws Exception{
    	logger.info("In function : update${className}ById: <#list table.pkColumns as column>${column.columnNameLower}={}</#list>,<#list table.updateColumns as column>${column.columnNameLower}={},<#if column_has_next>,</#if></#list>,currentCompId={},currentContactId={},currentUserId={}", new Object[]{<#list table.pkColumns as column>${column.columnNameLower}</#list>,<#list table.updateColumns as column>${column.columnNameLower}<#if column_has_next>,</#if></#list>,currentCompId,currentContactId,currentUserId});
    	Map<String, Object> result = new HashMap<String, Object>();
    	boolean success=${classNameFirstLower}ServiceClient.update${className}ById(<#list table.pkColumns as column>${column.columnNameLower}</#list>,<#list table.updateColumns as column>${column.columnNameLower}<#if column_has_next>,</#if></#list>,currentCompId,currentContactId,currentUserId);
    	
    	if(!success){
    		result.put(CommonConstants.STATUS,CommonConstants.FAIL);
    	}else{
    		result.put(CommonConstants.STATUS,CommonConstants.SUCCESS);
    	}
    	logger.info("End function : update${className}ById");
    	return result;
    }
    </#if>
    
    /**
     * 根据id查询记录
     */
    public ${className}VO get${className}ById(long id,Long currentCompId,Long currentContactId,Long currentUserId)throws Exception{
    	logger.info("In function : get${className}ById: id={}, currentCompId={},currentContactId={},currentUserId={}", new Object[]{id,currentCompId,currentContactId,currentUserId});
    	${className}VO vo=${classNameFirstLower}ServiceClient.get${className}ById(id,currentCompId,currentContactId,currentUserId);
    	logger.info("End function : get${className}ById");
    	return vo;
    }
    
    /**
     * 查询记录
     */
    public Map<String, Object> getPagination${className}ByParam(<#list table.queryColumns as column>${column.javaType} ${column.columnNameLower},</#list>PaginationCondition pc,Long currentCompId,Long currentContactId,Long currentUserId)throws Exception{
    	logger.info("In function : getPagination${className}ByParam: <#list table.queryColumns as column>${column.columnNameLower}={},</#list>pc={}, currentCompId={},currentContactId={},currentUserId={}", new Object[]{<#list table.queryColumns as column>${column.columnNameLower},</#list>pc,currentCompId,currentContactId,currentUserId});
    	Map<String, Object> result = new HashMap<String, Object>();
    	PaginationVO<${className}VO> vos=${classNameFirstLower}ServiceClient.getPagination${className}ByParam(<#list table.queryColumns as column>${column.columnNameLower},</#list>pc,currentCompId,currentContactId,currentUserId);
       
		result.put(CommonConstants.STATUS,CommonConstants.SUCCESS);
    	
    	if(vos.getTotal()==0){
    		result.put(CommonConstants.TOTAL,0);
    	}else{
    		Map<String, Object> adata=null;
    		List<Map<String, Object>> list =new ArrayList<Map<String, Object>>();
    		if(vos.getItems()==null){
    			result.put(CommonConstants.DATA,null);
    		}else{
    		    for(${className}VO vo:vos.getItems()){
    		    	adata = new HashMap<String, Object>();
    		    	  
    		    	<#list table.columns as column>
    		    	<#if column.javaType=="Date">
    		    	adata.put("${column.columnNameLower}",DateUtil.formatDate(vo.get${column.columnName}(), DateUtil.SIMPLE_DATE_FORMAT));
    		    	<#else>
    		    	adata.put("${column.columnNameLower}",vo.get${column.columnName}());
    		    	</#if>
    		    	</#list>
    		    	
    		    	list.add(adata);
    		    }
    		    result.put(CommonConstants.DATA,list);
    		}
    	}
    	logger.info("End function : getPagination${className}ByParam");
    	return result;
    }
    /**
     * 查询总数
     */
    public Map<String, Object> getTotal${className}ByParam(<#list table.queryColumns as column>${column.javaType} ${column.columnNameLower},</#list>Long currentCompId,Long currentContactId,Long currentUserId)throws Exception{
    	logger.info("In function : getTotal${className}ByParam: <#list table.queryColumns as column>${column.columnNameLower}={},</#list>currentCompId={},currentContactId={},currentUserId={}", new Object[]{<#list table.queryColumns as column>${column.columnNameLower},</#list>currentCompId,currentContactId,currentUserId});
    	Map<String, Object> result = new HashMap<String, Object>();
    	int total=${classNameFirstLower}ServiceClient.getTotal${className}ByParam(<#list table.queryColumns as column>${column.columnNameLower},</#list>currentCompId,currentContactId,currentUserId);
    	result.put(CommonConstants.STATUS,CommonConstants.SUCCESS);
    	result.put(CommonConstants.DATA,total);	
    	logger.info("End function : getTotal${className}ByParam");
    	return result;
    }
}