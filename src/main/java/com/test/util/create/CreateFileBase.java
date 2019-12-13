package com.test.util.create;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.test.util.DataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 *
 */
@Component
public abstract class CreateFileBase implements ICreateFile {

	//包名
	protected final static String packagePrefix = "com.test";
	protected final static String javaDir = System.getProperty("user.dir");
	protected final static String rootDirJ = javaDir + "/src/main/java";
	protected final static String rootDirM = javaDir + "/src/main/resources/mybatis/mapper";
	protected final static String getAllTableSql = "SELECT T1.name TABLE_NAME, CAST (T2.value AS nvarchar) TABLE_COMMENT FROM sys.tables T1 LEFT JOIN sys.extended_properties T2 ON T1.object_id = T2.major_id and T2.minor_id = 0 ;";
	protected final static String getTableSql = "SELECT T1.name TABLE_NAME, CAST (T2.value AS nvarchar) TABLE_COMMENT FROM sys.tables T1 LEFT JOIN sys.extended_properties T2 ON T1.object_id = T2.major_id and T2.minor_id = 0 where T1.name = ?;";
	protected final static String getTableSql2 = "select TABLE_NAME,TABLE_COMMENT  from information_schema.tables where table_name = ?;";
	protected final static String getColumnsSql = "SELECT obj.name TABLE_NAME, col.name COLUMN_NAME, col.max_length DataLength, col.is_nullable IS_NULLABLE, t.name DATA_TYPE, CASE WHEN EXISTS(SELECT TOP 1 ind.is_primary_key FROM sys.index_columns ic LEFT JOIN sys.indexes ind ON ic.object_id = ind.object_id AND ic.index_id = ind.index_id AND ind.name LIKE 'PK_%' WHERE ic.object_id = obj.object_id AND ic.column_id = col.column_id) THEN 'PRI' ELSE '' END COLUMN_KEY, CAST (ep.value AS nvarchar) COLUMN_COMMENT FROM sys.objects obj INNER JOIN sys.columns col ON obj.object_id = col.object_id LEFT JOIN sys.types t ON t.user_type_id = col.user_type_id LEFT JOIN sys.extended_properties ep ON ep.major_id = obj.object_id AND ep.minor_id = col.column_id AND ep.name = 'MS_Description' WHERE obj.name = ? ORDER BY col.column_id ASC";
	protected final static String getColumnsSql2 = "SELECT TABLE_NAME,COLUMN_NAME,CHARACTER_MAXIMUM_LENGTH DataLengt,IS_NULLABLE,DATA_TYPE,COLUMN_KEY,COLUMN_COMMENT FROM information_schema.COLUMNS WHERE table_name = ?; ";

	public static EntityInfos getEntityInfos(String tableName) {
		List<EntityInfos> entityInfosList = new ArrayList<EntityInfos>();
		EntityInfos entityInfos = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			con = DataSourceFactory.getConnection(0);
			st = con.prepareStatement(getTableSql2);
			st.setObject(1,tableName);
			rs = st.executeQuery();

			entityInfosList = toModelList(rs, EntityInfos.class);
			if (entityInfosList != null && entityInfosList.size() > 0) {
				entityInfos = entityInfosList.get(0);

				st = con.prepareStatement(getColumnsSql2);
				st.setObject(1, tableName);
				rs = st.executeQuery();

				List<FieldInfos> fieldInfosList = toModelList(rs, FieldInfos.class);
				entityInfos.setFieldInfosList(fieldInfosList);
			}

			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();

			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return entityInfos;
	}

	public List<FieldInfos> getFieldInfosList(String tableName) {
		List<FieldInfos> fieldInfosList = new ArrayList<FieldInfos>();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			con = DataSourceFactory.getConnection(0);
			st = con.prepareStatement(getColumnsSql);
			st.setObject(1, tableName);
			rs = st.executeQuery();

			fieldInfosList = toModelList(rs, FieldInfos.class);

			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();

			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return fieldInfosList;
	}

	private static <T> List<T> toModelList(ResultSet rs, Class<T> c) throws SecurityException, SQLException {
		List<T> fieldInfosList = new ArrayList<T>();
		while (rs.next()) {
			T fieldInfos = null;
			try {
				fieldInfos = c.newInstance();
				Field[] fields = c.getDeclaredFields();
				for (Field field : fields) {
					String fieldName = field.getName();
					Type type = field.getGenericType();
					if (field.isAnnotationPresent(XmlElement.class)) {
						XmlElement xmlElement = field.getAnnotation(XmlElement.class);
						String name = xmlElement.name();

						Object value = null;
						if (type == String.class) {
							value = rs.getString(name);
						} else if (type == int.class) {
							value = rs.getInt(name);
						} else if (type == Integer.class) {
							value = rs.getInt(name);
						} else if (type == long.class) {
							value = rs.getInt(name);
						} else if (type == Long.class) {
							value = rs.getInt(name);
						} else if (type == short.class) {
							value = rs.getShort(name);
						} else if (type == Short.class) {
							value = rs.getShort(name);
						} else if (type == BigDecimal.class) {
							value = rs.getBigDecimal(name);
						} else if (type == boolean.class) {
							value = rs.getBoolean(name);
						} else if (type == Boolean.class) {
							value = rs.getBoolean(name);
						} else if (type == Date.class) {
							value = rs.getDate(name);
						} else if (type == Timestamp.class) {
							value = rs.getTimestamp(name);
						} else if (type.getTypeName().toLowerCase().contains("enum")) {
							value = rs.getInt(name);
						} else {
							System.out.println("the Type of '" + type.getTypeName() + "' can not be Setable.");
						}

						try {
							PropertyDescriptor pdO = new PropertyDescriptor(fieldName, c);
							Method rM = pdO.getWriteMethod();
							rM.invoke(fieldInfos, value);
						} catch (IllegalAccessException e1) {
							// e1.printStackTrace();
							System.err.println(e1 + ":没有权限访问变量【" + fieldName + "】");
						} catch (IllegalArgumentException e1) {
							// e1.printStackTrace();
							System.err.println(e1 + ":变量【" + fieldName + "】非法的参数");
						} catch (InvocationTargetException e1) {
							// e1.printStackTrace();
							System.err.println(e1 + ":变量【" + fieldName + "】反射异常");
						} catch (IntrospectionException e1) {
							// e1.printStackTrace();
							System.err.println(e1 + ":变量【" + fieldName + "】内省异常");
						}
					}
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			fieldInfosList.add(fieldInfos);
		}

		return fieldInfosList;
	}

	protected static boolean write(String absolutePath, String content) {
		boolean ret = true;

		try {
			FileWriter fw = new FileWriter(absolutePath);
			fw.write(content);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public void create(String tableName) {
		EntityInfos entityInfos = getEntityInfos(tableName);
		create(entityInfos);
	}

	@Override
	public void create(List<EntityInfos> entityInfosList) {
		for (EntityInfos entityInfos : entityInfosList) {
			create(entityInfos);
		}
	}

	public static void createAll(String tableName) {
		EntityInfos entityInfos = getEntityInfos(tableName);
		new CreateVModelFile().create(entityInfos);
		new CreateDModelFile().create(entityInfos);
		new CreateControllerFile().create(entityInfos);
		new CreateServiceFile().create(entityInfos);
		new CreateIServiceFile().create(entityInfos);
		new CreateDaoFile().create(entityInfos);
		new CreateMapperFile().create(entityInfos);
	}

	public static void createAll() {
		List<EntityInfos> entityInfosList = new ArrayList<EntityInfos>();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			Connection con = DataSourceFactory.getConnection(0);
			st = con.prepareStatement(getAllTableSql);
			rs = st.executeQuery();

			entityInfosList = toModelList(rs, EntityInfos.class);
			int index = 1;
			if (entityInfosList != null && entityInfosList.size() > 0) {
				for (EntityInfos info : entityInfosList) {
					createAll(info.getTable_name());
					System.out.println(index + "/" + entityInfosList.size() + ":" + info.getTable_name());
					index++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
//		 List<FieldInfos> fieldInfosList = new CreateDModelFile().getFieldInfosList("T_OrderDetailItem");
//		 System.out.println(fieldInfosList.get(0));

		// createAll("Y_Menu");
		// createAll("Y_MenuGroup");
		// createAll("Y_MenuGroupDetail");
		// createAll("Y_Role");
		// createAll("Y_RoleMenu");
		// createAll("Y_RoleMenuAuth");
		// createAll("Y_UserRole");
		// createAll("M_SyatemUserLineCategory");//生成全部
		createAll("student");

	}
}
