/**
 * 
 */
package com.test.util.create;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class CreateVModelFile extends CreateFileBase {

	protected final static String VO = "db.model.vo";
	protected final static String packageName = CreateFileBase.packagePrefix+"."+VO;
	protected final static String extension = ".java";
	protected final static String flg = "VO";

	@Override
	public void create(EntityInfos entityInfos) {
		String table_name = entityInfos.getTable_name();
		String table_comment = entityInfos.getTable_comment();
		List<FieldInfos> fieldInfosList = entityInfos.getFieldInfosList();
		
		// 字段名称复制
		String[] s = table_name.split("_");
		String entityNameC = table_name;
		if(s.length > 1){
			entityNameC = table_name.split("_")[1];
		}

		// 字段名称首字母大写
		String entityNameU = entityNameC.substring(0, 1).toUpperCase() + entityNameC.substring(1);
		// 字段名称首字母小写
//		String entityNameL = entityNameC.substring(0, 1).toLowerCase() + entityNameC.substring(1);
		
		String className = entityNameU;
		
		// 文件名
		String fileName = className + flg + extension;

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

				// 存在BigDecimal类型
				boolean hasBigDecimal = false;

				// 存在Timestamp类型
				boolean hasTimestamp = false;
				
				for (FieldInfos f : fieldInfosList) {
					// 字段名称
					String column_name = f.getColumn_name();
					// 字段描述
					String column_comment = f.getColumn_comment();
					// 位置
//					long ordinal_position = f.getOrdinal_position();
					// 数据类型
					String data_type = f.getData_type();
					// 字段类型
//					String column_type = f.getColumn_type();
					// 是否必须
					String is_nullableStr = f.getIs_nullable();
					boolean is_nullable = true;
					if("NO".equals(is_nullableStr)){
						is_nullable = false;
					}

					// 类型名称
					String typeName = "String";

					// 字段名称复制
					String fieldNameC = column_name;
					// 字段名称首字母大写
					String fieldNameU = fieldNameC.substring(0, 1).toUpperCase() + fieldNameC.substring(1);
					// 字段名称首字母小写
					String fieldNameL = fieldNameC.substring(0, 1).toLowerCase() + fieldNameC.substring(1);
					
					if ("createdUser".equals(fieldNameL)
							|| "createdTime".equals(fieldNameL)
							|| "modifiedUser".equals(fieldNameL)
							|| "modifiedTime".equals(fieldNameL)) {
						continue;
					}

					if ("nvarchar".equalsIgnoreCase(data_type)) {
						typeName = "String";
					} else if ("varchar".equalsIgnoreCase(data_type)) {
						typeName = "String";
					} else if ("smallint".equalsIgnoreCase(data_type)) {
						typeName = "Short";
					} else if ("int".equalsIgnoreCase(data_type)) {
						typeName = "Integer";
					} else if ("bigint".equalsIgnoreCase(data_type)) {
						typeName = "Long";
					} else if ("number".equalsIgnoreCase(data_type)) {
						typeName = "BigDecimal";

						hasBigDecimal = true;
					} else if ("decimal".equalsIgnoreCase(data_type)) {
						typeName = "BigDecimal";

						hasBigDecimal = true;
					} else if ("datetime".equalsIgnoreCase(data_type)) {
						typeName = "Timestamp";

						hasTimestamp = true;
					} else if ("bit".equalsIgnoreCase(data_type)) {
						typeName = "Boolean";
					} else if ("tinyint".equalsIgnoreCase(data_type)) {
						typeName = "Short";
					} else {
						typeName = "String";
					}

					// 字段定义
					fieldStr += "\r\n" + "	" + "/**";
					fieldStr += "\r\n" + "	" + " * " + column_comment;
					fieldStr += "\r\n" + "	" + " */";
					//fieldStr += "\r\n" + "	" + "@XmlElement(name = \"" + fieldNameC + "\", type = " + typeName + ".class, required = " + is_nullable + ")";
					fieldStr += "\r\n" + "	" + "private " + typeName + " " + fieldNameL + ";";
					fieldStr += "\r\n" + "";

					// get方法定义
					methodStr += "\r\n" + "	" + "/**";
					methodStr += "\r\n" + "	" + " * 取得" + column_comment;
					methodStr += "\r\n" + "	" + " * ";
					methodStr += "\r\n" + "	" + " * @return " + column_comment;
					methodStr += "\r\n" + "	" + " */";

					methodStr += "\r\n" + "	" + "public " + typeName + " get" + fieldNameU + "() {";

					methodStr += "\r\n" + "	" + "	return " + fieldNameL + ";";
					methodStr += "\r\n" + "	" + "}";
					methodStr += "\r\n" + "";

					// set方法定义
					methodStr += "\r\n" + "	" + "/**";
					methodStr += "\r\n" + "	" + " * 设定" + column_comment;
					methodStr += "\r\n" + "	" + " * ";
					methodStr += "\r\n" + "	" + " * @param " + fieldNameL;
					methodStr += "\r\n" + "	" + " *            " + column_comment;
					methodStr += "\r\n" + "	" + " */";
					methodStr += "\r\n" + "	" + "public void set" + fieldNameU + "(" + typeName + " " + fieldNameL
							+ ") {";
					methodStr += "\r\n" + "	" + "	this." + fieldNameL + " = " + fieldNameL + ";";
					methodStr += "\r\n" + "	" + "}";
					methodStr += "\r\n" + "";
				}

				if (hasBigDecimal) {
					head += "\r\n" + "import java.math.BigDecimal;";
					head += "\r\n" + "";
				}

				if (hasTimestamp) {
					head += "\r\n" + "import java.sql.Timestamp;";
					head += "\r\n" + "";
				}

				//head += "\r\n" + "import javax.xml.bind.annotation.XmlElement;";
				//head += "\r\n" + "import javax.xml.bind.annotation.XmlRootElement;";
				//head += "\r\n" + "";

				content += packageStr;
				content += "\r\n" + "";
				content += head;
				content += "\r\n" + "";
				content += "\r\n" + "/**";
				content += "\r\n" + " * " + entityNameC + "	" + table_comment + "ViewModel";
				content += "\r\n" + " */";
				//content += "\r\n" + "@XmlRootElement(name = \"" + table_name + "\")";
				//content += "\r\n" + "public class " + className + flg + " extends BaseViewModel {";
				content += "\r\n" + "public class " + className + flg + "{";
				content += "\r\n" + "";
				content += fieldStr;
				content += methodStr;
				content += "\r\n" + "}";
				content = content.substring(2);

				write(file.getAbsolutePath(), content);
				System.out.println("CreateVModelFile success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws SQLException {
		new CreateVModelFile().create("student");
	}

}
