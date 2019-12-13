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
public class CreateIServiceFile extends CreateFileBase {

	protected final static String SERVICEIMPL = "service.impl";
	protected final static String packageName = CreateFileBase.packagePrefix+"."+SERVICEIMPL;
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
		
		String className =  entityNameU + typeName + "Impl" ;
		String paramClassNameV = entityNameU + CreateVModelFile.flg;
		String paramClassNameD = entityNameU + CreateDModelFile.flg;
		
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
				String mapperName = entityNameL + CreateDaoFile.typeName;
				
				// 字段定义
				//fieldStr += "\r\n" + "	" + "/**";
				//fieldStr += "\r\n" + "	" + " * " + CreateDaoFile.typeName;
				//fieldStr += "\r\n" + "	" + " */";
				fieldStr += "\r\n" + "	" + "@Resource";
				fieldStr += "\r\n" + "	" + "private " + entityNameU + CreateDaoFile.typeName + " " + mapperName + ";";
				fieldStr += "\r\n" + "";

				//getList
//				methodStr += "\r\n" + "	" + "/**";
//				methodStr += "\r\n" + "	" + " * 取得" + table_comment + "List";
//				methodStr += "\r\n" + "	" + " * ";
//				methodStr += "\r\n" + "	" + " * @return " + table_comment + "List";
//				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "@Override";
				methodStr += "\r\n" + "	" + "public List<"+paramClassNameD+"> get"+ entityNameU + "List("+ entityNameU + " " +entityNameL+") {";
				methodStr += "\r\n" ;
				methodStr += "\r\n" + "		"+ "List<"+paramClassNameD+"> " + entityNameL +"List = " + mapperName + ".get" + entityNameU + "List("+entityNameL+");";
				methodStr += "\r\n" ;
				methodStr += "\r\n" + "		" + "return " + entityNameL + "List;";
				methodStr += "\r\n" + "	" + "}";
				methodStr += "\r\n" + "";

				//getModel
//				methodStr += "\r\n" + "	" + "/**";
//				methodStr += "\r\n" + "	" + " * 取得" + table_comment;
//				methodStr += "\r\n" + "	" + " * ";
//				methodStr += "\r\n" + "	" + " * @return " + table_comment;
//				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "@Override";
				methodStr += "\r\n" + "	" + "public "+paramClassNameD +" get"+ entityNameU +" ("+ entityNameU + " " +entityNameL+") {";
				methodStr += "\r\n" ;
				methodStr += "\r\n" + "		"+paramClassNameD + " entity" +" = " + mapperName + ".get" + entityNameU + "("+entityNameL+");";
				methodStr += "\r\n" ;
				methodStr += "\r\n" + "		" + "return entity;";
				methodStr += "\r\n" + "	" + "}";
				methodStr += "\r\n" + "";

				//insertModel
//				methodStr += "\r\n" + "	" + "/**";
//				methodStr += "\r\n" + "	" + " * 添加" + table_comment;
//				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "@Override";
				methodStr += "\r\n" + "	" + "public "+"void" +" insert"+ entityNameU +" ("+ entityNameU + " " +entityNameL+") {";
				methodStr += "\r\n" + "		"  + mapperName + ".insert" + entityNameU +"("+ entityNameL+");";
				methodStr += "\r\n" + "	" + "}";
				methodStr += "\r\n" + "";

				//updateModel
//				methodStr += "\r\n" + "	" + "/**";
//				methodStr += "\r\n" + "	" + " * 更新" + table_comment;
//				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "@Override";
				methodStr += "\r\n" + "	" + "public "+"void" +" update"+ entityNameU +" ("+ entityNameU + " " +entityNameL+") {";
				methodStr += "\r\n" + "		"  + mapperName + ".update" + entityNameU +"("+ entityNameL+");";
				methodStr += "\r\n" + "	" + "}";
				methodStr += "\r\n" + "";

				//updateModelByValue
//				methodStr += "\r\n" + "	" + "/**";
//				methodStr += "\r\n" + "	" + " * 更新" + table_comment + "ByValue";
//				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "@Override";
				methodStr += "\r\n" + "	" + "public "+"void" +" update"+ entityNameU +"ByValue ("+ entityNameU + " " +entityNameL+") {";
				methodStr += "\r\n" + "		"  + mapperName + ".update" + entityNameU +"ByValue("+ entityNameL+");";
				methodStr += "\r\n" + "	" + "}";
				methodStr += "\r\n" + "";

				//deleteModel				
//				methodStr += "\r\n" + "	" + "/**";
//				methodStr += "\r\n" + "	" + " * 删除" + table_comment;
//				methodStr += "\r\n" + "	" + " */";
				methodStr += "\r\n" + "	" + "@Override";
				methodStr += "\r\n" + "	" + "public "+ "void" +" delete"+ entityNameU +" ("+ entityNameU + " " +entityNameL+") {";
				methodStr += "\r\n" + "		"  + mapperName + ".delete" + entityNameU +"("+ entityNameL+");";
				methodStr += "\r\n" + "	" + "}";
				methodStr += "\r\n" + "";

				//saveList
//				rmethodStr += "\r\n" + "	" + "/**";
//				methodStr += "\r\n" + "	" + " * 保存" + table_comment + "List";
//				methodStr += "\r\n" + "	" + " */";
//				methodStr += "\r\n" + "	" + "@Override";
//				methodStr += "\r\n" + "	" + "public " + "void" + " save" + entityNameU + "List(List<" + paramClassNameV +"> " + entityNameL + "List) {";
//				methodStr += "\r\n" + "	" + "	for (" + paramClassNameV +" " + entityNameL + " : " + entityNameL + "List) {";
//				methodStr += "\r\n" + "	" + "		if (" + entityNameL + ".getRowState() == RowState.Added) {";
//				//methodStr += "\r\n" + "	" + "	 		" + entityNameL + ".setInsertUserId(RequestContext.get().getAdminUser().getUserName());";
//				//methodStr += "\r\n" + "	" + "	 		" + entityNameL + ".setInsertDate(new Timestamp(System.currentTimeMillis()));";
//				//methodStr += "\r\n" + "	" + "	 		" + entityNameL + ".setUpdateUserId(RequestContext.get().getAdminUser().getUserName());";
//				//methodStr += "\r\n" + "	" + "	 		" + entityNameL + ".setUpdateDate(new Timestamp(System.currentTimeMillis()));";
//				methodStr += "\r\n" + "	" + "	 		" + paramClassNameD + " dataModel = BeanUtil.cloneData(" + entityNameL + ", " + paramClassNameD + ".class);";
//				methodStr += "\r\n" + "	" + "	 		" + mapperName + ".insert" + entityNameU + "(dataModel);";
//				methodStr += "\r\n" + "	" + "		} else if (" + entityNameL + ".getRowState() == RowState.Modified) {";
//				//methodStr += "\r\n" + "	" + "	 		" + entityNameL + ".setUpdateUserId(RequestContext.get().getAdminUser().getUserName());";
//				//methodStr += "\r\n" + "	" + "	 		" + entityNameL + ".setUpdateDate(new Timestamp(System.currentTimeMillis()));";
//				methodStr += "\r\n" + "	" + "	 		" + paramClassNameD + " dataModel = BeanUtil.cloneData(" + entityNameL + ", " + paramClassNameD + ".class);";
//				methodStr += "\r\n" + "	" + "	 		" + mapperName + ".update" + entityNameU + "(dataModel);";
//				methodStr += "\r\n" + "	" + "		} else if (" + entityNameL + ".getRowState() == RowState.Detele) {";
//				methodStr += "\r\n" + "	" + "	 		" + paramClassNameD + " dataModel = BeanUtil.cloneData(" + entityNameL + ", " + paramClassNameD + ".class);";
//				methodStr += "\r\n" + "	" + "	 		" + mapperName + ".delete" + entityNameU + "(dataModel);";
//				methodStr += "\r\n" + "	" + "		}";
//				methodStr += "\r\n" + "	" + "	}";
//				methodStr += "\r\n" + "	" + "}";
//				methodStr += "\r\n" + "";

				//head += "\r\n" + "import java.sql.Timestamp;";
				head += "\r\n" + "import java.util.List;";
				head += "\r\n" + "";
				head += "\r\n" + "import javax.annotation.Resource;";
				head += "\r\n" + "import org.springframework.stereotype.Service;";
				//head += "\r\n" + "import com.safeneeds.inep.util.BeanUtil;";
				head += "\r\n" + "import " + CreateServiceFile.packageName + "." + entityNameU + CreateServiceFile.typeName + ";";
				//head += "\r\n" + "import " + CreateVModelFile.packageName + "." + paramClassNameD + ";";
				head += "\r\n" + "import " + CreateDaoFile.packageName + "." + entityNameU + CreateDaoFile.typeName + ";";
				head += "\r\n" + "import " + CreateDModelFile.packageName + "." + paramClassNameD + ";";

				content += packageStr;
				content += "\r\n" + "";
				content += head;
				content += "\r\n" + "";
				content += "\r\n" + "/**";
				content += "\r\n" + " * " + table_comment + typeName;
				content += "\r\n" + " */";
				content += "\r\n" + "@Service(\"" + entityNameU + CreateServiceFile.typeName + "\")";
				content += "\r\n" + "public class " + className + " implements " + entityNameU + CreateServiceFile.typeName + " {";
				content += "\r\n" + "";
				content += fieldStr;
				content += "\r\n" + "";
				content += methodStr;
				content += "\r\n" + "}";
				content = content.substring(2);

				write(file.getAbsolutePath(), content);

				System.out.println("CreateIServiceFile success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws SQLException {
//		List<FieldInfos> fieldInfosList = new CreateModelFile().getFieldInfosList("m_admin");
//		System.out.println(fieldInfosList.get(0));
		new CreateIServiceFile().create("student");
	}

}
