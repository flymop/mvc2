package cn.xiuyu.core.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xiuyu.core.mapping.AbstractMapingHandler;
import cn.xiuyu.core.mapping.InitMappingHandler;
import cn.xiuyu.core.mapping.MappingHandler;
import cn.xiuyu.entity.MappingEntity;

/**
 * <p>
 * Title: HttpCoreServlet
 * </p>
 * <p>
 * Description:核心servlet
 * </p>
 * 
 * @author Xiuyu.Ge
 * @created 2017年8月26日 上午11:43:29
 * @modified [who date description]
 * @check [who date description]
 */
public class HttpCoreServlet extends HttpServlet {

	private static final long serialVersionUID = -8232536059596665840L;
	
	// 所有action实例
	Map<String, Object> instanceMaps = new HashMap<String, Object>();
	// 扫描的所有包
	List<String> packages = new ArrayList<String>();
	private MappingHandler mappingHandler = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		String basePackage = config.getInitParameter("package");

		//扫描所有包
		InitServletScript scrpit = new InitServletScript();
		scrpit.scanfPackage(basePackage, packages);
		scrpit.scanfAction(packages, instanceMaps);
		
		mappingHandler = new InitMappingHandler();
		mappingHandler.requestToMapping(instanceMaps);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MappingEntity mappringEntity = null;
		// 根据请求url去相应的map找运行的方法
		String requesttype = request.getMethod().toLowerCase();
		mappingHandler = new AbstractMapingHandler();
		if (requesttype.equals("get")) {
			mappringEntity = mappingHandler.requestToMethod(request, "GET");
		} else {
			mappringEntity = mappingHandler.requestToMethod(request, "POST");
		}
		// 如果没有任何的注解
		if (mappringEntity == null) {
			System.out.println("no mapping ...");
			return;
		}
		// 处理文件上传
		try {
			//fileUtils.FileUpload(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 执行返回
		//doreturn(mappringEntity, request, response);

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
