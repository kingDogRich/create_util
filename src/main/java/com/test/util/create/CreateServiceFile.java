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
public class CreateServiceFile extends CreateFileBase {

	protected final static String SERVICE = "service";
	protected final static String packageName = CreateFileBase.packagePrefix+"."+SERVICE;
	protected final static String typeName = "Service";
	protected final static String extension = ".java";


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
		//String paramClassNameV = entityNameU + CreateVModelFile.flg;
		String paramClassNameV = entityNameU ;
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

				//getList
				methodStr += "\r\n" + "	" + "/**";
				methodStr += "\r\n" + "	" + " * 取得" + table_comment + "List";
				methodStr += "\r\n" + "	" + " * ";
				methodStr += "\r\n" + "	" + " * @return " + table_comment + "List";
				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "public List<" + paramClassNameV + "> get" + entityNameU + "List(" + paramClassNameV +" " + entityNameL + ");";
				methodStr += "\r\n" + "";

				//getModel
				methodStr += "\r\n" + "	" + "/**";
				methodStr += "\r\n" + "	" + " * 取得" + table_comment;
				methodStr += "\r\n" + "	" + " * ";
				methodStr += "\r\n" + "	" + " * @return " + table_comment;
				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "public " + paramClassNameV + " get" + entityNameU + "(" + paramClassNameV +" " + entityNameL + ");";
				methodStr += "\r\n" + "";

				//insertModel
				methodStr += "\r\n" + "	" + "/**";
				methodStr += "\r\n" + "	" + " * 添加" + table_comment;
				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "public " + "void" + " insert" + entityNameU + "(" + paramClassNameV +" " + entityNameL + ");";
				methodStr += "\r\n" + "";

				//updateModel
				methodStr += "\r\n" + "	" + "/**";
				methodStr += "\r\n" + "	" + " * 更新" + table_comment;
				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "public " + "void" + " update" + entityNameU + "(" + paramClassNameV +" " + entityNameL + ");";
				methodStr += "\r\n" + "";

				//updateModelByValue
				methodStr += "\r\n" + "	" + "/**";
				methodStr += "\r\n" + "	" + " * 更新" + table_comment + "ByValue";
				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "public " + "void" + " update" + entityNameU + "ByValue(" + paramClassNameV +" " + entityNameL + ");";
				methodStr += "\r\n" + "";

				//deleteModel				
				methodStr += "\r\n" + "	" + "/**";
				methodStr += "\r\n" + "	" + " * 删除" + table_comment;
				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "public " + "void" + " delete" + entityNameU + "(" + paramClassNameV +" " + entityNameL + ");";
				methodStr += "\r\n" + "";
				
				head += "\r\n" + "import java.util.List;";
				head += "\r\n" + "";
				head += "\r\n" + "import " + CreateDModelFile.packageName + "." + paramClassNameV + ";";

				//saveList
//				methodStr += "\r\n" + "	" + "/**";
//				methodStr += "\r\n" + "	" + " * 保存" + table_comment + "List";
//				methodStr += "\r\n" + "	" + " */";
//				methodStr += "\r\n" + "	" + "public " + "void" + " save" + entityNameU + "List(List<" + paramClassNameV +"> " + entityNameL + "List);";
//				methodStr += "\r\n" + "";

				content += packageStr;
				content += "\r\n" + "";
				content += head;
				content += "\r\n" + "";
				content += "\r\n" + "/**";
				content += "\r\n" + " * " + table_comment + typeName;
				content += "\r\n" + " */";
				content += "\r\n" + "public interface " + className + " {";
				content += "\r\n" + "";
				content += fieldStr;
				content += methodStr;
				content += "\r\n" + "}";
				content = content.substring(2);

				write(file.getAbsolutePath(), content);

				System.out.println("CreateServiceFile success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws SQLException {
		new CreateServiceFile().create("student");
	}

}
