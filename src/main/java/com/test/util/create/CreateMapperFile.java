/**
 * 
 */
package com.test.util.create;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * @author Administrator
 *
 */
public class CreateMapperFile extends CreateFileBase {
	//protected final static String packageName = "";
	protected final static String typeName = "Mapper";
	protected final static String extension = ".xml";


	@Override
	public void create(EntityInfos entityInfos) {
		String table_name = entityInfos.getTable_name();
		String table_comment = entityInfos.getTable_comment();
		if(StringUtils.isEmpty(table_comment)) {
			table_comment = table_name;
		}
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
		
		String className = entityNameU + typeName;
		
		// 文件名
		String fileName = className + extension;
		String paramClassNameD = entityNameU + CreateDModelFile.flg;

		// 目录
		File directory = new File(rootDirM);
		// 新目录不存在
		if (!directory.exists()) {
			// 创建目录
			directory.mkdirs();
		}
		
		// 目录存在
		if (directory.exists()) {

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

				// 头部内容
//				String head = "";

				// resultMap字符串
				String resultMap = "";

				// selectList方法字符串
				String selectListMethodStr = "";

				// select方法字符串
				String selectMethodStr = "";

				// insert方法字符串
				String insertMethodStr = "";

				// update方法字符串
				String updateMethodStr = "";

				// update方法字符串
				String updateByValueMethodStr = "";

				// delete方法字符串
				String deleteMethodStr = "";

				resultMap += "\r\n" + "	" + "<resultMap id=\"" + entityNameU + "Map\" type=\"" + CreateDModelFile.packageName + "." + paramClassNameD + "\">";
				
				selectListMethodStr += "\r\n" + "	" + "<!-- 取得" + table_comment + "List -->";
				selectListMethodStr += "\r\n" + "	" + "<select  id=\"get" + entityNameU + "List\" parameterType=\"" + CreateDModelFile.packageName + "." + paramClassNameD + "\" resultMap=\"" + entityNameU + "Map\" >";
				
				selectMethodStr += "\r\n" + "	" + "<!-- 取得" + table_comment + " -->";
				selectMethodStr += "\r\n" + "	" + "<select  id=\"get" + entityNameU + "\" parameterType=\"" + CreateDModelFile.packageName + "." + paramClassNameD + "\" resultMap=\"" + entityNameU + "Map\" >";
				
				insertMethodStr += "\r\n" + "	" + "<!-- 添加" + table_comment + " -->";
				insertMethodStr += "\r\n" + "	" + "<insert  id=\"insert" + entityNameU + "\" parameterType=\"" + CreateDModelFile.packageName + "." + paramClassNameD + "\">";
				
				updateMethodStr += "\r\n" + "	" + "<!-- 更新" + table_comment + " -->";
				updateMethodStr += "\r\n" + "	" + "<update  id=\"update" + entityNameU + "\" parameterType=\"" + CreateDModelFile.packageName + "." + paramClassNameD + "\">";
				
				updateByValueMethodStr += "\r\n" + "	" + "<!-- 更新" + table_comment + "ByValue -->";
				updateByValueMethodStr += "\r\n" + "	" + "<update  id=\"update" + entityNameU + "ByValue\" parameterType=\"" + CreateDModelFile.packageName + "." + paramClassNameD + "\">";
				
				deleteMethodStr += "\r\n" + "	" + "<!-- 删除" + table_comment + " -->";
				deleteMethodStr += "\r\n" + "	" + "<delete  id=\"delete" + entityNameU + "\" parameterType=\"" + CreateDModelFile.packageName + "." + paramClassNameD + "\">";
				
				String selectListMethodSelectStr = "";
				String selectListMethodConditionStr = "";
				
				String selectMethodSelectStr = "";
				String selectMethodConditionStr = "";
				
				String insertMethodNameStr = "";
				String insertMethodValueStr = "";

				String updateMethodNotKeyStr = "";
				String updateMethodNotKeyStrK = "";
				String updateMethodKeyStr = "";

				String updateByValueMethodNotKeyStr = "";
				String updateByValueMethodNotKeyStrK = "";
				String updateByValueMethodKeyStr = "";

				String deleteMethodKeyStr = "";

				// 类型名称
				String jdbcType = "";
				String testStr = "";
				for (FieldInfos f : fieldInfosList) {
					// 字段名称
					String column_name = f.getColumn_name();
					// 字段描述
					String column_comment = f.getColumn_comment();
					// 数据类型
					String data_type = f.getData_type();
					//字段主键
					boolean iskey = "PRI".equals(f.getColumn_key());
					
					if ("datetime".equalsIgnoreCase(data_type)) {
						jdbcType = "TIMESTAMP";
					} else if ("int".equalsIgnoreCase(data_type)) {
						jdbcType = "INTEGER";
					} else if ("decimal".equalsIgnoreCase(data_type)) {
						jdbcType = "DECIMAL";
					} else {
						jdbcType = "VARCHAR";
					}
					
					// 字段名称复制
					String fieldNameC = column_name;
					// 字段名称首字母大写
//					String fieldNameU = fieldNameC.substring(0, 1).toUpperCase() + fieldNameC.substring(1);
					// 字段名称首字母小写
					String fieldNameL = fieldNameC.substring(0, 1).toLowerCase() + fieldNameC.substring(1);

					if(!StringUtils.isEmpty(column_comment)) {
						selectListMethodSelectStr += "\r\n" + "	" + "	" + "	" + "-- " + column_comment;
					}
					selectListMethodSelectStr += "\r\n" + "	" + "	" + "	" + "T1." + fieldNameC + ",";

					if(!StringUtils.isEmpty(column_comment)) {
						selectMethodSelectStr += "\r\n" + "	" + "	" + "	" + "-- " + column_comment;
						
					}
					selectMethodSelectStr += "\r\n" + "	" + "	" + "	" + "T1." + fieldNameC + ",";

					if (!"createdUser".equalsIgnoreCase(fieldNameL)
							&& !"createdTime".equalsIgnoreCase(fieldNameL)
							&& !"modifiedUser".equalsIgnoreCase(fieldNameL)
							&& !"modifiedTime".equalsIgnoreCase(fieldNameL)) {
						selectListMethodConditionStr += "\r\n" + "	" + "	" + "	" + "<!-- " + column_comment + " -->";

						if ("datetime".equalsIgnoreCase(data_type)) {
							testStr = fieldNameL + " != null";
						} else if ("int".equalsIgnoreCase(data_type)) {
							testStr = fieldNameL + " != null and " + fieldNameL + " > 0";
						} else if ("decimal".equalsIgnoreCase(data_type)) {
							testStr = fieldNameL + " != null";
						} else if ("bit".equalsIgnoreCase(data_type)) {
							testStr = fieldNameL + " != null ";
						}else {
							testStr = fieldNameL + " != null and " + fieldNameL + " !=''";
						}
						
						selectListMethodConditionStr += "\r\n" + "	" + "	" + "	" + "<if test=\"" + testStr + "\">";
						selectListMethodConditionStr += "\r\n" + "	" + "	" + "	" + "	" + "AND T1." + fieldNameC + " = #{" + fieldNameL + ", jdbcType=" + jdbcType + "}";
						selectListMethodConditionStr += "\r\n" + "	" + "	" + "	" + "</if>";
					}
					
					if (iskey) {
						if(!StringUtils.isEmpty(column_comment)) {
							selectMethodConditionStr += "\r\n" + "	" + "	" + "-- " + column_comment;
						}
						selectMethodConditionStr += "\r\n" + "	" + "	" + "AND T1." + fieldNameC + " = #{" + fieldNameL + ", jdbcType=" + jdbcType + "}";
						
						updateMethodNotKeyStrK += "\r\n" + "	" + "	" + "	" + "	," + fieldNameC + " = #{" + fieldNameL + ", jdbcType=" + jdbcType + "}";
						
						if(!StringUtils.isEmpty(column_comment)) {
							updateMethodKeyStr += "\r\n" + "	" + "	" + "-- " + column_comment;
						}
						updateMethodKeyStr += "\r\n" + "	" + "	" + "AND " + fieldNameC + " = #{" + fieldNameL + ", jdbcType=" + jdbcType + "}";
						
						updateByValueMethodNotKeyStrK += "\r\n" + "	" + "	" + "	" + "	," + fieldNameC + " = #{" + fieldNameL + ", jdbcType=" + jdbcType + "}";

						if(!StringUtils.isEmpty(column_comment)) {
							updateByValueMethodKeyStr += "\r\n" + "	" + "	" + "-- " + column_comment;
						}
						updateByValueMethodKeyStr += "\r\n" + "	" + "	" + "AND " + fieldNameC + " = #{" + fieldNameL + ", jdbcType=" + jdbcType + "}";

						if(!StringUtils.isEmpty(column_comment)) {
							deleteMethodKeyStr += "\r\n" + "	" + "	" + "-- " + column_comment;
						}
						deleteMethodKeyStr += "\r\n" + "	" + "	" + "AND " + fieldNameC + " = #{" + fieldNameL + ", jdbcType=" + jdbcType + "}";
					} else {
						if (!"InsertUserId".equalsIgnoreCase(fieldNameL)
								&& !"insertDate".equalsIgnoreCase(fieldNameL)) {
							if(!StringUtils.isEmpty(column_comment)) {
								updateMethodNotKeyStr += "\r\n" + "	" + "	" + "	" + "-- " + column_comment;						
							}
							
							updateMethodNotKeyStr += "\r\n" + "	" + "	" + "	" + "	," + fieldNameC + " = #{" + fieldNameL + ", jdbcType=" + jdbcType + "}";
							
							updateByValueMethodNotKeyStr += "\r\n" + "	" + "	" + "	" + "<!-- " + column_comment + " -->";

							if ("datetime".equalsIgnoreCase(data_type)) {
								testStr = fieldNameL + " != null";
							} else if ("int".equalsIgnoreCase(data_type)) {
								testStr = fieldNameL + " != null and " + fieldNameL + " > 0";
							} else if ("decimal".equalsIgnoreCase(data_type)) {
								testStr = fieldNameL + " != null";
							} else if ("bit".equalsIgnoreCase(data_type)) {
								testStr = fieldNameL + " != null ";
							} else {
								testStr = fieldNameL + " != null and " + fieldNameL + " !=''";
							}
							
							updateByValueMethodNotKeyStr += "\r\n" + "	" + "	" + "	" + "<if test=\"" + testStr + "\">";	
							
							updateByValueMethodNotKeyStr += "\r\n" + "	" + "	" + "	" + "	," + fieldNameC + " = #{" + fieldNameL + ", jdbcType=" + jdbcType + "}";							
							
							updateByValueMethodNotKeyStr += "\r\n" + "	" + "	" + "	" + "</if>";
						}
					}

					if(!StringUtils.isEmpty(column_comment)) {
						resultMap += "\r\n" + "	" + "	" + "<!-- " + column_comment + " -->";
					}
					resultMap += "\r\n" + "	" + "	" + "<result column=\"" + fieldNameC + "\" property=\"" + fieldNameL +"\" jdbcType=\"" + jdbcType + "\" />";

					if(!StringUtils.isEmpty(column_comment)) {
						insertMethodNameStr += "\r\n" + "	" + "	" + "	" + "-- " + column_comment;
					}
					insertMethodNameStr += "\r\n" + "	" + "	" + "	" + fieldNameC + ",";

					if(!StringUtils.isEmpty(column_comment)) {
						insertMethodValueStr += "\r\n" + "	" + "	" + "	" + "-- " + column_comment;
					}
					insertMethodValueStr += "\r\n" + "	" + "	" + "	" + "#{" + fieldNameL + ", jdbcType=" + jdbcType + "}" + ",";
				}
				
				selectListMethodSelectStr = selectListMethodSelectStr.substring(0, selectListMethodSelectStr.length() - 1);

				//selectListMethodConditionStr += "\r\n" + "	" + "	" + "	" + "<if test=\"pageSize != null and pageSize > 0\">";
				//selectListMethodConditionStr += "\r\n" + "	" + "	" + "	" + "	" + "OFFSET ${pageStart, jdbcType=INTEGER} ROW FETCH NEXT ${pageSize, jdbcType=INTEGER} ROWS ONLY";
				//selectListMethodConditionStr += "\r\n" + "	" + "	" + "	" + "</if>";
				
				selectListMethodStr += "\r\n" + "	" + "	" + "SELECT";
				selectListMethodStr += selectListMethodSelectStr;
				selectListMethodStr += "\r\n" + "	" + "	" + "<!-- " + table_comment + " -->";
				selectListMethodStr += "\r\n" + "	" + "	" + "FROM " + table_name + " T1";
				selectListMethodStr += "\r\n" + "	" + "	" + "WHERE 1=1";
				selectListMethodStr += selectListMethodConditionStr;
				
				selectMethodSelectStr = selectMethodSelectStr.substring(0, selectMethodSelectStr.length() - 1);

				selectMethodStr += "\r\n" + "	" + "	" + "SELECT";
				selectMethodStr += selectMethodSelectStr;
				selectMethodStr += "\r\n" + "	" + "	" + "<!-- " + table_comment + " -->";
				selectMethodStr += "\r\n" + "	" + "	" + "FROM " + table_name + " T1";
				selectMethodStr += "\r\n" + "	" + "	" + "WHERE 1=1";
				selectMethodStr += selectMethodConditionStr;

				insertMethodNameStr = insertMethodNameStr.substring(0, insertMethodNameStr.length() - 1);
				insertMethodValueStr = insertMethodValueStr.substring(0, insertMethodValueStr.length() - 1);

				insertMethodStr += "\r\n" + "	" + "	" + "<!-- " + table_comment + " -->";
				insertMethodStr += "\r\n" + "	" + "	" + "INSERT INTO " + table_name;
				insertMethodStr += "\r\n" + "	" + "	" + "(";
				insertMethodStr += insertMethodNameStr;
				insertMethodStr += "\r\n" + "	" + "	" + ") VALUES (";
				insertMethodStr += insertMethodValueStr;
				insertMethodStr += "\r\n" + "	" + "	" + ")";
				
				if(updateMethodNotKeyStrK.length() > 6) {
					updateMethodNotKeyStrK = updateMethodNotKeyStrK.substring(0,6) + updateMethodNotKeyStrK.substring(7);

				}
				
				updateMethodStr += "\r\n" + "	" + "	" + "<!-- " + table_comment + " -->";
				updateMethodStr += "\r\n" + "	" + "	" + "UPDATE " + table_name;
				updateMethodStr += "\r\n" + "	" + "	" + "<set>";
				updateMethodStr += updateMethodNotKeyStrK;
				updateMethodStr += updateMethodNotKeyStr;
				updateMethodStr += "\r\n" + "	" + "	" + "</set>";
				updateMethodStr += "\r\n" + "	" + "	" + "WHERE 1=1";
				updateMethodStr += updateMethodKeyStr;
				
				if(updateByValueMethodNotKeyStrK.length() > 6) {
					updateByValueMethodNotKeyStrK = updateByValueMethodNotKeyStrK.substring(0,6) + updateByValueMethodNotKeyStrK.substring(7);
				}
				
				updateByValueMethodStr += "\r\n" + "	" + "	" + "<!-- " + table_comment + " -->";
				updateByValueMethodStr += "\r\n" + "	" + "	" + "UPDATE " + table_name;
				updateByValueMethodStr += "\r\n" + "	" + "	" + "<trim prefix=\"set\" prefixOverrides=\",\">";
				updateByValueMethodStr += updateByValueMethodNotKeyStrK;
				updateByValueMethodStr += updateByValueMethodNotKeyStr;
				updateByValueMethodStr += "\r\n" + "	" + "	" + "</trim>";
				updateByValueMethodStr += "\r\n" + "	" + "	" + "WHERE 1=1";
				updateByValueMethodStr += updateByValueMethodKeyStr;

				deleteMethodStr += "\r\n" + "	" + "	" + "<!-- " + table_comment + " -->";
				deleteMethodStr += "\r\n" + "	" + "	" + "DELETE FROM " + table_name;
				deleteMethodStr += "\r\n" + "	" + "	" + "WHERE 1=1";
				deleteMethodStr += deleteMethodKeyStr;
				
				resultMap += "\r\n" + "	" + "</resultMap>";
				selectListMethodStr += "\r\n" + "	" + "</select>";
				selectMethodStr += "\r\n" + "	" + "</select>";
				insertMethodStr += "\r\n" + "	" + "</insert>";
				updateMethodStr += "\r\n" + "	" + "</update>";
				updateByValueMethodStr += "\r\n" + "	" + "</update>";
				deleteMethodStr += "\r\n" + "	" + "</delete>";

				content += "\r\n" + "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>  ";
				content += "\r\n" + "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >";
				content += "\r\n" + "<mapper namespace=\"" + CreateDaoFile.packageName + "." + entityNameU + CreateDaoFile.typeName + "\">";
				
				content += resultMap;
				content += "\r\n";
				content += selectListMethodStr;
				content += "\r\n";
				content += selectMethodStr;
				content += "\r\n";
				content += insertMethodStr;
				content += "\r\n";
				content += updateMethodStr;
				content += "\r\n";
				content += updateByValueMethodStr;
				content += "\r\n";
				content += deleteMethodStr;
				content += "\r\n";
				content += "\r\n" + "</mapper>";
				
				content = content.substring(2);

				write(file.getAbsolutePath(), content);

				System.out.println("CreateMapperFile success");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws SQLException {
//		List<FieldInfos> fieldInfosList = new CreateModelFile().getFieldInfosList("m_admin");
//		System.out.println(fieldInfosList.get(0));
		new CreateMapperFile().create("student");
	}

}
