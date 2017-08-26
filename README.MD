### github 地址：[传送门](https://github.com/GeXyu/mvc2)
2	前两天写了几个注解和处理器的Dmoe,然后发现可以写一个小框架。感觉Springmvc比较合适，之前也看过部分源码，然后就动手搞了一个。
3	代码没有抽取，设计也是一塌糊涂(比较菜，不会设计)，代码不优雅。  
4	实现了几个核心功能：注解式声明控制类，简单的数据绑定，返回jsosn，支持使用model传递数据，Resultf风格的跳转和重定向（准备添加文件上传功能）。  
5	如果有志之士可以fork一份 添加功能,修复bug。这里谢过了！  
6	### 原理 
7	1，首先初始化的时候扫描指定的包，然后扫描类上的注解，生成一个map。把注解上的值为key,Java类的全定限名为value。  
8	2，扫描方法上面的映射，维护映射map,把mapping注解的值为key,执行的方法为value。初始化完成！  
9	3，然后根据核心servlet去分发请求，然后解析返回字符串，进行跳转或者是重定向。  
10	具体的思路就是这样  
11	如果有志之士可以fork一份 添加功能,修复bug.  
12	留个邮箱,用于交流 [gexyuzz@gmail.com](gexyuzz@gmail.com) !不是广告！！！  
2 months ago @GeXyu Update README.md	
13	#### 打包好了   [Download](http://images.zzcode.cn/mvc2.jar)可以直接使用啦～
14	用到的jar包:[bcel-6.0.jar](http://mvnrepository.com/artifact/org.apache.bcel/bcel) [commons-beanutils.jar](http://mvnrepository.com/artifact/commons-beanutils/commons-beanutils/1.9.3)  [json-20170516.jar](http://mvnrepository.com/artifact/org.json/json/20160810)
15	日志组件: [logback](http://mvnrepository.com/artifact/ch.qos.logback/logback-core/1.1.7) 
2 months ago @GeXyu Update README.md	
16	### 那么怎么使用呢？
17	
2 months ago @GeXyu readme	
18	首先在action类添加@Action注解,在方法添加@Mapping注解.
19	```java
20	@Action("/index")
21	public class Controller {
22		@Mapping(value="/hello",RequestType=Type.get)
23		public String sayHello(Model model){
24			model.setParameter("root", "Hello");
25			return "welcome";
26		}
27	}
28	
29	```
30	它会跳转到/WEB-INF/jsp下的welcome.jsp(这里我写死的,前缀/WEB-INF/jsp.后缀.jsp)
31	```jsp
32	<%@ page language="java" contentType="text/html; charset=UTF-8"
33	    pageEncoding="UTF-8"%>
34	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
35	<html>
36	<head>
37	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
38	<title>Insert title here</title>
39	</head>
40	<body>
41	${root }
42	</body>
43	</html>
44	```
2 months ago @GeXyu 03	
45	然后在项目的web.xml文件添加核心servlet
46	```xml
47	<?xml version="1.0" encoding="UTF-8"?>
48	<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
49		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
50		xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
51		http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">
52		
53		<servlet>
54			<servlet-name>main</servlet-name>
55			<servlet-class>cn.xiuyu.servlet.MainServlet</servlet-class>
56			<!-- 设置最先启动保证初始化 -->
57			<load-on-startup>1</load-on-startup>
58			<init-param>
59				<!-- 自定扫描的包 -->
60				<param-name>package</param-name>
61				<param-value>cn.xiuyu</param-value>
62			</init-param>
63		</servlet>
64		<servlet-mapping>
65			<servlet-name>main</servlet-name>
66			<url-pattern>/</url-pattern>
67		</servlet-mapping>
68	</web-app>
69	
70	```
2 months ago @GeXyu Update README.md	
71	然后访问地址：http:localhost:8080/${项目名}/index/hello  
72	#### 其实使用上springmvc差不多，就这样。 有什么bug可以告诉我阿！ 告诉我阿 ！ 我阿！ 阿！！！
2 months ago @GeXyu readme	
73	
74	### 重定向和转发
75	```java
76		//post请求
77		@Mapping(value="/post",RequestType=Type.post)
78		public String post(){
79			System.out.println("---进入到了post请求 --");
80			return "redirect:/index/get";
81		}
82		//get请求value  不备注默认为get
83		@Mapping(value="/get",RequestType=Type.get)
84		public String get(Model model){
85			//model 只支持传值 取值
86			model.setParameter("key", "value");
87			System.out.println("---进入到了get请求 --");
88			return "welcome";
89		}
90	```
91	### 接受单字段 可以传入model对象（仅能传输对象）
92	```java
93		//接受单字段
94		@Mapping(value="/one",RequestType=Type.post)
95		public String one(String name,Model model){
96			//model 只支持传值 取值
97			model.setParameter("key", name);
98			return "welcome";
99		}
100	```
101	### 封装实体
102	```java
103		//封装实体
104		@Mapping(value="/object",RequestType=Type.post)
105		public String object(User u,Model model){
106			//model 只支持传值 取值
107			model.setParameter("key", u.getName() + "--" + u.getPassword());
108			return "welcome";
109		}
110	```
111	### 返回json  需要返回json对象并且在方法上添加@Json注解
112	```java
113	//返回json
114		@Mapping(value="/json",RequestType=Type.get)
115		@Json
116		public User json(String name,String password,Model model){
117			//model 只支持传值 取值
118			User u = new User();
119			u.setName(name);
120			u.setPassword(password);
121			System.out.println(u);
122			return u;
123		}
124	```
125	### 上传文件（目前仅能单文件上传），需要把表单设置为enctype="multipart/form-data"，然后在action方法添加FileEntity实体接受文件，上传到/WEB-INF/upload/*/*文件夹下(*为算法生成)
126	```html
127	<form action="${pageContext.request.contextPath }/index/uoload" method="post" enctype="multipart/form-data">
128		<input type="file" name="testFile">
129		<input type="submit" value="file">
130	</form>
131	```
132	action方法
133	```java
134	//上传文件
135		@Mapping(value="/uoload",RequestType=Type.post)
136		public String uoload(FileEntity entity,Model model){
137			//model 只支持传值 取值
138			model.setParameter("key", entity.getPath() + " " +entity.getNewName());
139			return "welcome";
140		}
141	```
142	可以在文件上传过程接收文件上传状态，返回一个json对象(封装文件大小`pContentLength`和已上传文件`pBytesRead`)