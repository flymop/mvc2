package cn.xiuyu.core.mapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xiuyu.annotation.Json;
import cn.xiuyu.annotation.Mapping;
import cn.xiuyu.core.dto.MappingAttributeDto;
import cn.xiuyu.entity.MappingEntity;
import cn.xiuyu.utils.InitUtils;

/**
 * <p>
 * Title: AbstractMapingHandler
 * </p>
 * <p>
 * Description:处理映射
 * </p>
 * 
 * @author Xiuyu.Ge
 * @created 2017年8月26日 上午11:51:16
 * @modified [who date description]
 * @check [who date description]
 */
public class AbstractMapingHandler implements MappingHandler {
	private static final Logger log = LoggerFactory.getLogger(InitUtils.class);

	// 存储所有get请求映射
	protected Map<String, MappingEntity> getMappings = new HashMap<String, MappingEntity>();

	// 存储所有post请求映射
	protected Map<String, MappingEntity> postMappings = new HashMap<String, MappingEntity>();

	@Override
	public void requestToMapping(Map<String, Object> instanceMaps) {
		if (instanceMaps.size() == 0) {
			return;
		}

		for (Map.Entry<String, Object> maps : instanceMaps.entrySet()) {
			Method[] methods = maps.getValue().getClass().getMethods();
			for (Method m : methods) {
				if (m.isAnnotationPresent(Mapping.class)) {
					Mapping mappinganno = m.getAnnotation(Mapping.class);
					String MethodType = mappinganno.REQUEST_TYPE().name();
					String requestUrl = mappinganno.value();

					if (MethodType != null) {
						String type = MethodType.toLowerCase();

						// 如果带有Json标签
						if (m.isAnnotationPresent(Json.class)) {
							JsonMappingHandler jsonMapping = new JsonMappingHandler();
							MappingAttributeDto dto = new MappingAttributeDto(requestUrl, type, m, maps, mappinganno);
							jsonMapping.jsonMappingMethod(dto);
						}
						//普通请求
						MappingAttributeDto dto = new MappingAttributeDto(requestUrl, type, m, maps, mappinganno);
						requestMappingHandler(dto);

					} else {
						throw new RuntimeException("No value in @Mapping...");
					}
				}
			}
		}
	}

	@Override
	public MappingEntity requestToMethod(HttpServletRequest request,String type) {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestURI.replaceAll(contextPath, "");
		
		Map<String, MappingEntity> mappings = null;
		if(type.equals("POST")){
			mappings = postMappings;
		}else{
			mappings = getMappings;
		}
		
		if(mappings.containsKey(url)){
			try{
				return mappings.get(url);
			}catch(Exception e){
				System.out.println("No Mapping ...");
			}
				
		}	
		return null;
	}

	
	/**
	 * 处理映射
	 */
	private void requestMappingHandler(MappingAttributeDto dto) {
		MappingEntity en = new MappingEntity();
		en.setClazz(dto.getMaps().getValue().getClass());
		en.setMapper(dto.getMaps().getKey() + dto.getRequestUrl());
		en.setType(dto.getMappinganno().REQUEST_TYPE());
		en.setMethod(dto.getMethod());
		en.setJson(false);
		if (dto.getType().equals("get")) {
			getMappings.put(en.getMapper(), en);
			log.info("add get request  {}  success ",en.getMapper());
			return;
		}
		postMappings.put(en.getMapper(), en);
		log.info("add post request  {}  success ",en.getMapper());
	}
}
