package cn.xiuyu.core.mapping;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xiuyu.entity.MappingEntity;
import cn.xiuyu.utils.InitUtils;

/**
 * <p>
 * Title: InitMappingHandler
 * </p>
 * <p>
 * Description:初始化扫描映射
 * </p>
 * 
 * @author Xiuyu.Ge
 * @created 2017年8月26日 上午11:52:56
 * @modified [who date description]
 * @check [who date description]
 */
public class InitMappingHandler extends AbstractMapingHandler {
	
	private static final Logger  log  =  LoggerFactory.getLogger(InitUtils.class);
	
	
	public void requestTomapping(Map<String, Object> instanceMaps, Map<String, MappingEntity> getmappings,Map<String, MappingEntity> postmappings) {
		//判断是是什么请求
		
	}
}
