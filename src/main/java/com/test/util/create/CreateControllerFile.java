/**
 * 
 */
package com.test.util.create;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Administrator
 *
 */
public class CreateControllerFile extends CreateFileBase {

	protected final static String CONTROLLER = "controller";
	protected final static String packageName = CreateFileBase.packagePrefix+"."+CONTROLLER;
	protected final static String typeName = "Controller";
	protected final static String extension = ".java";
	protected final static String flg = "";

	@Override
	public void create(EntityInfos entityInfos) {
		String table_name = entityInfos.getTable_name();
		String table_comment = entityInfos.getTable_comment();
//		List<FieldInfos> fieldInfosList = entityInfos.getFieldInfosList();

		// 字段名称复制
		String[] s = table_name.split("_");
		String entityNameC = table_name;
		if(s.length > 1){
			entityNameC = table_name.split("_")[1];
		}
		// 字段名称首字母大写
		String entityNameU = entityNameC.substring(0, 1).toUpperCase() + entityNameC.substring(1);
		// 字段名称首字母小写
		String entityNameL = entityNameC.substring(0, 1).toLowerCase() + entityNameC.substring(1);
		
		String className = entityNameU + typeName;

		//加后缀
		String entityNameS = entityNameU + flg;


		// 文件名
		String fileName = className + extension;

		// 目录
		File directory = new File(rootDirJ);
		// 目录存在
		if (directory.exists()) {
			for (String dir:packageName.split("\\.")) {
				// 新目录
				directory = new File(directory, dir);
			}
			// 新目录不存在
			if (!directory.exists()) {
				// 创建目录
				directory.mkdirs();
			}

			try {
				// 文件
				File file = new File(directory, fileName);
				// 文件不存在
				if (!file.exists()) {
					// 创建文件
					file.createNewFile();
				}

				// 文件内容
				String content = "";

				// 包内容
				String packageStr = "";
				packageStr += "\r\n" + "package " + packageName + ";";

				// 头部内容
				String head = "";

				// 字段字符串
				String fieldStr = "";

				// 方法字符串
				String methodStr = "";

				//mapperName
				String serviceName = entityNameL + CreateServiceFile.typeName;
				
				//命名空间
				//String workspace = "edu";
				String workspace = entityNameC;
				
				// 字段定义
//				fieldStr += "\r\n" + "	" + "/**";
//				fieldStr += "\r\n" + "	" + " * " + CreateServiceFile.typeName;
//				fieldStr += "\r\n" + "	" + " */";
				fieldStr += "\r\n" ;
				fieldStr += "\r\n" + "	" + "@Autowired";
				fieldStr += "\r\n" + "	" + "private " + entityNameU + CreateServiceFile.typeName + " " + serviceName + ";";
				fieldStr += "\r\n" + "";

				//getList
				methodStr += "\r\n" + "	" + "/**";
				methodStr += "\r\n" + "	" + " * 取得" + table_comment + "List";
				methodStr += "\r\n" + "	" + " * ";
				methodStr += "\r\n" + "	" + " * @return " + table_comment + "List";
				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "@RequestMapping(value = \"/"  + "get" + entityNameU + "List\")";
				//methodStr += "\r\n" + "	" + "@ResponseBody";
				methodStr += "\r\n" + "	" + "public " + "List<" + entityNameS + "> get" + entityNameU + "List(" + entityNameS + " " + entityNameL + ") {";
				methodStr += "\r\n" + "	" + "	return " + serviceName + ".get" + entityNameU + "List(" + entityNameL + ");";
				methodStr += "\r\n" + "	" + "}";
				methodStr += "\r\n" + "";

				//getModel
				methodStr += "\r\n" + "	" + "/**";
				methodStr += "\r\n" + "	" + " * 取得" + table_comment;
				methodStr += "\r\n" + "	" + " * ";
				methodStr += "\r\n" + "	" + " * @return " + table_comment;
				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "@RequestMapping(value = \"/" + "get" + entityNameU + "\")";
				//methodStr += "\r\n" + "	" + "@ResponseBody";
				methodStr += "\r\n" + "	" + "public "  + entityNameS + " get" + entityNameU + "(" + entityNameS + " " + entityNameL + ") {";
				methodStr += "\r\n" + "	" + "	return " + serviceName + ".get" + entityNameU + "(" + entityNameL + ");";
				methodStr += "\r\n" + "	" + "}";
				methodStr += "\r\n" + "";

				//saveList				
//				methodStr += "\r\n" + "	" + "/**";
//				methodStr += "\r\n" + "	" + " * 保存" + table_comment + "List";
//				methodStr += "\r\n" + "	" + " */";
//				methodStr += "\r\n" + "	" + "@RequestMapping(value = \"/"  + "save" + entityNameU + "List\")";
//				methodStr += "\r\n" + "	" + "@ResponseBody";
//				methodStr += "\r\n" + "	" + "public " + "void" + " save" + entityNameU + "List(@RequestBody List<" + entityNameS + "> " + entityNameL + "List) {";
//				methodStr += "\r\n" + "	" + "	" + serviceName + ".save" + entityNameU + "List(" + entityNameL + "List);";
//				methodStr += "\r\n" + "	" + "}";
//				methodStr += "\r\n" + "";

				//saveModel
				methodStr += "\r\n" + "	" + "/**";
				methodStr += "\r\n" + "	" + " * 保存" + table_comment;
				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "@RequestMapping(value = \"/" + "save" + entityNameU + "\")";
				//methodStr += "\r\n" + "	" + "@ResponseBody";
				methodStr += "\r\n" + "	" + "public " + "void" + " save" + entityNameU + "(@RequestBody " + entityNameS + " " + entityNameL + ") {";
				methodStr += "\r\n" + "		"  + serviceName + ".insert" + entityNameU +"("+ entityNameL+");";
				methodStr += "\r\n" + "	" + "}";
				methodStr += "\r\n" + "";

				//deleteModel
				methodStr += "\r\n" + "	" + "/**";
				methodStr += "\r\n" + "	" + " * 删除" + table_comment;
				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "@RequestMapping(value = \"/" + "delete" + entityNameU + "\")";
				//methodStr += "\r\n" + "	" + "@ResponseBody";
				methodStr += "\r\n" + "	" + "public " + "void" + " delete" + entityNameU + "(@RequestBody " + entityNameS + " " + entityNameL + ") {";
				methodStr += "\r\n" + "		"  + serviceName + ".delete" + entityNameU +"("+ entityNameL+");";
				methodStr += "\r\n" + "	" + "}";
				methodStr += "\r\n" + "";

				head += "\r\n" + "import java.util.List;";
				head += "\r\n" + "import java.util.ArrayList;";
				head += "\r\n" + "";
				//head += "\r\n" + "import org.springframework.stereotype.Controller;";
				head += "\r\n" + "import org.springframework.web.bind.annotation.RestController;";
				head += "\r\n" + "import org.springframework.beans.factory.annotation.Autowired;";
				head += "\r\n" + "import org.springframework.web.bind.annotation.RequestBody;";
				head += "\r\n" + "import org.springframework.web.bind.annotation.RequestMapping;";
				//head += "\r\n" + "import org.springframework.web.bind.annotation.ResponseBody;";
				head += "\r\n" + "";
				head += "\r\n" + "import " + CreateServiceFile.packageName + "." + entityNameS + CreateServiceFile.typeName + ";";
				head += "\r\n" + "import " + CreateDModelFile.packageName + "." + entityNameS + ";";

				content += packageStr;
				content += "\r\n" + "";
				content += head;
				content += "\r\n" + "";
				content += "\r\n" + "/**";
				content += "\r\n" + " * " + table_comment + typeName;
				content += "\r\n" + " */";
				content += "\r\n" + "@RestController";
				content += "\r\n" + "@RequestMapping(\"" + workspace +"\")";
				content += "\r\n" + "public class " + className + " {";
				content += fieldStr;
				content += methodStr;
				content += "\r\n" + "}";
				content = content.substring(2);

				write(file.getAbsolutePath(), content);

				System.out.println("CreateControllerFile success");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws SQLException {
//		List<FieldInfos> fieldInfosList = new CreateModelFile().getFieldInfosList("m_admin");
//		System.out.println(fieldInfosList.get(0));
		new CreateControllerFile().create("student");
	}

}
