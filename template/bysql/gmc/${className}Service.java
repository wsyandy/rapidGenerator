package com.gm.soa.service.${soaCorePackage};

<#assign className= table.className>    
<#assign classNameFirstLower= table.classNameFirstLower>   

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gm.soa.common.SoaException;
import com.gm.soa.common.vo.PaginationVO;
import com.gm.soa.constants.ErrorCode;
import com.gm.soa.vo.buyinglead.PaginationCondition;
import com.gm.soa.vo.${soaCorePackage}.${className}VO;
import com.gm.soa.dao.${soaSrcPackage}.${className}DAO;
import com.gm.soa.remote.${soaCorePackage}.${className}ServiceRemote;


@Service
public class ${className}Service implements ${className}ServiceRemote{
	
	private static Logger logger = LoggerFactory.getLogger(${className}Service.class); 
	
    @Autowired
    private ${className}DAO ${classNameFirstLower}DAO;
    
  
    /**
     * 根据id查询记录
     */
    @Override
    public ${className}VO get${className}ById(long id,Long currentCompId,Long currentContactId,Long currentUserId)throws SoaException{
    	logger.info("In function : get${className}ById: id={}, currentCompId={},currentContactId={},currentUserId={}", new Object[]{id,currentCompId,currentContactId,currentUserId});
    	try{
	        ${className}VO result=${classNameFirstLower}DAO.get${className}ById(id);
	        logger.info("End function : get${className}ById");
	    	return result;
    	}catch (Exception e) {
    		logger.info(" ckpt1 exception:{}", new Object[] { e });
			if(e instanceof SoaException){
				throw (SoaException)e;
			}else{
				throw new SoaException( ErrorCode.UNKNOWN, e.getMessage());
			}
		} 
    }
    
    /**
     * 查询记录
     */
    @Override
    public PaginationVO<${className}VO> getPagination${className}ByParam(<#list table.queryColumns as column>${column.javaType} ${column.columnNameLower},</#list>PaginationCondition pc,Long currentCompId,Long currentContactId,Long currentUserId)throws SoaException{
    	logger.info("In function : getPagination${className}ByParam: <#list table.queryColumns as column>${column.columnNameLower}={},</#list>pc={}, currentCompId={},currentContactId={},currentUserId={}", new Object[]{<#list table.queryColumns as column>${column.columnNameLower},</#list>pc,currentCompId,currentContactId,currentUserId});
    	${className}VO ${classNameFirstLower}VO=new ${className}VO();
    	<#list table.queryColumns as column>
    	${classNameFirstLower}VO.set${column.columnName}(${column.columnNameLower});
    	</#list>
    	try{
	    	PaginationVO<${className}VO> result=new PaginationVO<${className}VO>();
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	        result.setPageNum(pc.getPageNum());
	        result.setPageSize(pc.getPageSize());
	        
	        int total = getTotal${className}ByParam(<#list table.queryColumns as column>${column.columnNameLower}<#if column_has_next>,</#if></#list>,currentCompId,currentContactId,currentUserId);
	        result.setTotal(total);
	
	        result.setMaxPage(total/pc.getPageSize()+(total%pc.getPageSize())>0?1:0);
	        if(total!=0){
	        	List<${className}VO> list=${classNameFirstLower}DAO.get${className}ListByParam(${classNameFirstLower}VO,pc);
	        	result.setItems(list);
	        }
	        logger.info("End function : getPagination${className}ByParam");
	    	return result;
    	}catch (Exception e) {
    		logger.info(" ckpt1 exception:{}", new Object[] { e });
			if(e instanceof SoaException){
				throw (SoaException)e;
			}else{
				throw new SoaException( ErrorCode.UNKNOWN, e.getMessage());
			}
		} 
    }
    /**
     * 查询总数
     */
    @Override
    public int getTotal${className}ByParam(<#list table.queryColumns as column>${column.javaType} ${column.columnNameLower},</#list>Long currentCompId,Long currentContactId,Long currentUserId)throws SoaException{
    	logger.info("In function : getTotal${className}ByParam: <#list table.queryColumns as column>${column.columnNameLower}={},</#list>currentCompId={},currentContactId={},currentUserId={}", new Object[]{<#list table.queryColumns as column>${column.columnNameLower},</#list>currentCompId,currentContactId,currentUserId});
    	${className}VO ${classNameFirstLower}VO=new ${className}VO();
    	<#list table.queryColumns as column>
    	${classNameFirstLower}VO.set${column.columnName}(${column.columnNameLower});
    	</#list>
    	try{
	    	int result=${classNameFirstLower}DAO.getTotal${className}ByParam(${classNameFirstLower}VO);
	    	logger.info("End function : getTotal${className}ByParam");
	    	return result;
    	}catch (Exception e) {
    		logger.info(" ckpt1 exception:{}", new Object[] { e });
			if(e instanceof SoaException){
				throw (SoaException)e;
			}else{
				throw new SoaException( ErrorCode.UNKNOWN, e.getMessage());
			}
		} 
    }
    
}