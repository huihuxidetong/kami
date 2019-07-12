package com.fh.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * 说明：用于备份、还原数据库、在线编辑SQL
 * 创建人：FH Q313596790
 * 修改时间：2016年3月29日
 * @version
 */
public class DbFH{
	private static Log logger = LogFactory.getLog(DbFH.class);
	public static Map<String, String> backUpTableList = new ConcurrentHashMap<String, String>();
	public static Map<String, String> recoverTableList = new ConcurrentHashMap<String, String>();
	private static DbFH dbFH = new DbFH();

	public static DbFH getDbFH(){
		return dbFH;
	}

	/**获取本数据库的所有表名(通过PageData)
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static Object[] getTables(PageData pd) throws ClassNotFoundException, SQLException{
		String dbtype = pd.getString("dbtype");				//数据库类型
		String username = pd.getString("username");			//用户名
		String password = pd.getString("password");			//密码
		String address = pd.getString("dbAddress");			//数据库连接地址
		String dbport = pd.getString("dbport");				//端口
		String databaseName = pd.getString("databaseName");	//数据库名
		Connection conn = DbFH.getCon(dbtype,username,password,address+":"+dbport,databaseName);
		if("oracle".equals(dbtype)){databaseName = username;}
		Object[] arrOb = {databaseName,DbFH.getTablesByCon(conn, "sqlserver".equals(dbtype)?null:databaseName),dbtype};
		return arrOb;
	}
	/**
	 * @return 获取conn对象(通过PageData)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getFHCon(PageData pd) throws ClassNotFoundException, SQLException{
		String dbtype = pd.getString("dbtype");				//数据库类型
		String username = pd.getString("username");			//用户名
		String password = pd.getString("password");			//密码
		String address = pd.getString("dbAddress");			//数据库连接地址
		String dbport = pd.getString("dbport");				//端口
		String databaseName = pd.getString("databaseName");	//数据库名
		return DbFH.getCon(dbtype,username,password,address+":"+dbport,databaseName);
	}
	
	/**
	 * @param dbtype	数据库类型
	 * @param username	用户名
	 * @param password	密码
	 * @param dburl		数据库连接地址:端口
	 * @param databaseName 数据库名
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getCon(String dbtype,String username,String password,String dburl,String databaseName) throws SQLException, ClassNotFoundException{
		if("mysql".equals(dbtype)){
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://"+dburl+"/"+databaseName+"?user="+username+"&password="+password);
		}else if("oracle".equals(dbtype)){
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			return DriverManager.getConnection("jdbc:oracle:thin:@"+dburl+":"+databaseName, username, password);
		}else if("sqlserver".equals(dbtype)){
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			return DriverManager.getConnection("jdbc:sqlserver://"+dburl+"; DatabaseName="+databaseName, username, password);
		}else{
			return null;
		}
	}
	
	/**获取某个conn下的所有表
	 * @param conn 数据库连接对象
	 * @param schema mysql:数据库名; oracle:用户名;sqlserver:null
	 * @return
	 */
	public static List<String> getTablesByCon(Connection conn, String schema) {
		try {
			List<String> listTb = new ArrayList<String>();
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet rs = meta.getTables(null, schema, null, new String[] { "TABLE" });
			while (rs.next()) {
				listTb.add(rs.getString(3));
			}
			return listTb;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**数据库备份命令字符串
	 * @param dbtype 数据库类型
	 * @param address 数据库连接地址
	 * @param username 用户名
	 * @param password 密码
	 * @param sqlpath 存储路径
	 * @param tableName 表名
	 * @param databaseName 数据库名
	 * @param ffilename 日期当路径和保存文件名的后半部分
	 * @return 完整的命令字符串
	 */
	public static String getExecStr(String dbtype,String dbpath,String address,String username,String password,String sqlpath,String tableName,String databaseName,String ffilename){
		StringBuffer sb = new StringBuffer();
		if("mysql".equals(dbtype)){
			address = "localhost";
			sb.append(dbpath);
			sb.append("mysqldump ");
			sb.append("--opt ");
			sb.append("-h ");
			sb.append(address);
			sb.append(" ");
			sb.append("--user=");
			sb.append(username);
			sb.append(" ");
			sb.append("--password=");
			sb.append(password);
			sb.append(" ");
			sb.append("--lock-all-tables=true ");
			sb.append("--result-file=");
			sb.append(sqlpath);
			sb.append(("".equals(tableName)?databaseName+"_"+ffilename:tableName+"_"+ffilename)+".sql");
			sb.append(" ");
			sb.append("--default-character-set=utf8 ");
			sb.append(databaseName);
			sb.append(" ");
			sb.append(tableName);//当tableName为“”时，备份整库
		}else if("oracle".equals(dbtype)){
			if("".equals(tableName)){//备份整库
				sb.append("EXP "+username+"/"+password+" BUFFER=880000 FILE="+sqlpath+username+"_"+ffilename+".DMP LOG="+sqlpath+username+"_"+ffilename+".LOG OWNER="+username);
			}else{//备份单表
				sb.append("EXP "+username+"/"+password+" BUFFER=880000 FILE="+sqlpath+tableName+"_"+ffilename+".DMP LOG="+sqlpath+tableName+"_"+ffilename+".LOG TABLES=("+username+"."+tableName+")");
			}
		}
		return sb.toString();
	}


	/**字段名列表
	 * @param conn
	 * @param table
	 * @return
	 * @throws SQLException
	 */
	public static List<String> getFieldLsit(Connection conn, String table) throws SQLException{
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(table);
		pstmt.execute();  									//这点特别要注意:如果是Oracle而对于mysql可以不用加.
		List<String> columnList = new ArrayList<String>();	//存放字段
		ResultSetMetaData rsmd = (ResultSetMetaData) pstmt.getMetaData();
		 for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
			 columnList.add(rsmd.getColumnName(i));			//把字段名放list里
          }
		return columnList;
	}
	
	/**(字段名、类型、长度)列表
	 * @param conn
	 * @param table
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String,String>> getFieldParameterLsit(Connection conn, String table) throws SQLException{
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("select * from " + table);
		pstmt.execute();  															//这点特别要注意:如果是Oracle而对于mysql可以不用加.
		List<Map<String,String>> columnList = new ArrayList<Map<String,String>>();	//存放字段
		ResultSetMetaData rsmd = (ResultSetMetaData) pstmt.getMetaData();
		 for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
			 Map<String,String> fmap = new HashMap<String,String>();
			 fmap.put("fieldNanme", rsmd.getColumnName(i));							//字段名称
			 fmap.put("fieldType", rsmd.getColumnTypeName(i));						//字段类型名称
			 fmap.put("fieldLength", String.valueOf(rsmd.getColumnDisplaySize(i)));	//长度
			 fmap.put("fieldSccle", String.valueOf(rsmd.getScale(i)));				//小数点右边的位数
			 columnList.add(fmap);													//把字段名放list里
          }
		return columnList;
	}


}
