package cn.xiuyu.entity;

import java.lang.reflect.Method;

import cn.xiuyu.annotation.Mapping.Type;

/**
 * 
 * @author gexyuzz
 * @version date：2017年6月19日 下午2:01:31
 */
public class MappingEntity {
	// URL 对应的类
	private Class<?> clazz;
	// URL 对应的方法
	private Method method;
	// 类型
	private Type type;

	//// URL
	private String mapper;
	private boolean json;

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getMapper() {
		return mapper;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void setMapper(String mapper) {
		this.mapper = mapper;
	}

	public boolean isJson() {
		return json;
	}

	public void setJson(boolean json) {
		this.json = json;
	}

	public MappingEntity(Class<?> clazz, Method method, Type type, String mapper, boolean json) {
		super();
		this.clazz = clazz;
		this.method = method;
		this.type = type;
		this.mapper = mapper;
		this.json = json;
	}

	public MappingEntity() {
		super();
	}

	@Override
	public String toString() {
		return "MappingEntity [method=" + method + ", type=" + type + ", mapper=" + mapper + "]";
	}

}
