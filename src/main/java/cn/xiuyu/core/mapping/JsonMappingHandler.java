package cn.xiuyu.core.mapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xiuyu.core.dto.MappingAttributeDto;
import cn.xiuyu.entity.MappingEntity;

/**
 * @author gexyuzz
 * @Time：2017年8月26日 下午12:04:46
 */
public class JsonMappingHandler extends AbstractMapingHandler{
	private static final Logger log = LoggerFactory.getLogger(JsonMappingHandler.class);
	public void jsonMappingMethod(MappingAttributeDto dto){
		MappingEntity en = new MappingEntity();
		en.setClazz(dto.getMaps().getValue().getClass());
		en.setMapper(dto.getMaps().getKey() +dto.getRequestUrl());
		en.setType(dto.getMappinganno().REQUEST_TYPE());
		en.setMethod(dto.getMethod());
		en.setJson(true);
		if(dto.getType().equals("get")){
			getMappings.put(en.getMapper(), en);
			log.info("add get-json request  {}  success ",en.getMapper());
			return;
		}
		postMappings.put(en.getMapper(), en);
		log.info("add post-json request  {}  success ",en.getMapper());
		return;
	};
}
