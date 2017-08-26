package cn.xiuyu.core.dto;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import cn.xiuyu.annotation.Mapping;

/**
 * <p>
 * Title: MappingAttributeDto
 * </p>
 * <p>
 * Description:映射DTO
 * </p>
 * 
 * @author Xiuyu.Ge
 * @created 2017年8月26日 下午12:26:09
 * @modified [who date description]
 * @check [who date description]
 */
public class MappingAttributeDto {
	private String requestUrl;
	private String type;
	private Method method;
	private Map.Entry<String, Object> maps;
	private Mapping mappinganno;
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	
	public Map.Entry<String, Object> getMaps() {
		return maps;
	}
	public void setMaps(Map.Entry<String, Object> maps) {
		this.maps = maps;
	}
	
	public Mapping getMappinganno() {
		return mappinganno;
	}
	public void setMappinganno(Mapping mappinganno) {
		this.mappinganno = mappinganno;
	}
	public MappingAttributeDto() {
		super();
	}
	public MappingAttributeDto(String requestUrl, String type, Method method, Entry<String, Object> maps,
			Mapping mappinganno) {
		super();
		this.requestUrl = requestUrl;
		this.type = type;
		this.method = method;
		this.maps = maps;
		this.mappinganno = mappinganno;
	}
	
}	
