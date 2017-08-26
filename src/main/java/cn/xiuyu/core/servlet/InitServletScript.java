package cn.xiuyu.core.servlet;

import java.io.File;
import java.util.List;
import java.util.Map;

import cn.xiuyu.annotation.Action;

/**
 * @author gexyuzz
 * @Time：2017年8月26日 下午12:09:27
 */
public class InitServletScript {
	//扫描所有包
		public void scanfPackage(String basePackage,List<String> lists){
			String rootpath = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.", "/")).getFile();
			File[] Files = new File(rootpath).listFiles();
			if(Files.length == 0){
				return;
			}
			for(File f:Files){
			
				if(f.isDirectory()){
					scanfPackage(basePackage + "." + f.getName(), lists);
				}else{
					lists.add(basePackage + "." + f.getName().substring(0, f.getName().lastIndexOf(".class")));
				}
			}
		}
		
		//扫描所有action
		public void scanfAction(List<String> packages,Map<String, Object> instanceMaps){
			if(packages.size()==0){
				return;
			}
			try {
				for(String clazz:packages){
					Class<?> clzzname  = Class.forName(clazz);				
					if(clzzname.isAnnotationPresent(Action.class)){
						Action actionanno = clzzname.getAnnotation(Action.class);
						instanceMaps.put(actionanno.value(), clzzname.newInstance());
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}	
		}
		
}
