package cn.xiuyu.core.mapping;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.xiuyu.entity.MappingEntity;

/**
 * <p>
 * Title: MappingHandler
 * </p>
 * <p>
 * Description:映射接口
 * </p>
 * 
 * @author Xiuyu.Ge
 * @created 2017年8月26日 上午11:51:25
 * @modified [who date description]
 * @check [who date description]
 */
public interface MappingHandler {
	void requestToMapping(Map<String, Object> instanceMaps);

	MappingEntity requestToMethod(HttpServletRequest request,String type);

}
