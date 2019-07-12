package com.card.utils;

import com.alibaba.fastjson.JSONObject;
import com.card.utils.ObjTools;
import com.card.utils.ObjectTranscoder;
import com.card.utils.SerializeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.*;

public class JedisUtil {

	protected final static Logger logger = LoggerFactory.getLogger(JedisUtil.class);

	private static JedisPool pool;
	private static Integer sessionTimeOut = 604800;// 默认时间7天

	private static final String LOGIN_KEY = "login_";
	private static final String LOGIN_STAT_KEY = "login_stat_";

	static {
		try {
			logger.info("load redis service ---------------------------");
			ResourceBundle bundle = ResourceBundle.getBundle("redis");
			if (bundle == null) {
				logger.info("[redis.properties] is not found!");
				throw new IllegalArgumentException("[redis.properties] is not found!");
			}

			// 连接的db索引
			String db = bundle.getString("redis.database");
			int database = StringUtils.isBlank(db) ? 0 : Integer.valueOf(db);

			String password = bundle.getString("redis.password");// 密码
			password = StringUtils.isBlank(password) ? null : password;

			sessionTimeOut = Integer.valueOf(bundle.getString("sessionTimeOut"));
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(Integer.valueOf(bundle.getString("redis.pool.maxActive").trim()));
			config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle").trim()));
			config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWait").trim()));
			config.setTestOnBorrow(true);
			config.setTestOnReturn(true);
			logger.info("redis.ip" + bundle.getString("redis.ip").trim());
			logger.info("redis.port" + bundle.getString("redis.port").trim());
			pool = new JedisPool(config, bundle.getString("redis.ip").trim(),
					Integer.valueOf(bundle.getString("redis.port").trim()), Protocol.DEFAULT_TIMEOUT, password,
					database, "dxm-api");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	public static Jedis getResource() {
		Jedis jedis = pool.getResource();
		return jedis;
	}

	/**
	 * redis map数据存入
	 * 
	 * @param key
	 *            存入key值
	 * @param map
	 *            存入 value 值
	 * @param timeOut
	 *            超时时间 单位 秒
	 */
	public static <T> void setKeyMap(String key, Map<String,T> map, int timeOut) {

		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(key.getBytes(), ObjectTranscoder.serialize(map));
			// 设置过期时间
			jedis.expire(key, timeOut);
		} catch (Exception e) {
			logger.error("set keyVal 设值错误:" + key + "++" + map + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 获取 redis 存入的map值
	 * 
	 * @param key
	 * @return
	 */
	public static <T> Map<String,T> getKeyMap(String key) {
		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
	        byte[] in = jedis.get(key.getBytes());      
	        Map<String,T> map = (Map<String, T>) ObjectTranscoder.deserialize(in);  
			return map;
		} catch (Exception e) {
			logger.error("getKeyMap 取值错误:" + key + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}
	
	/**
	 * redis map数据存入
	 * 
	 * @param key
	 *            存入key值
	 * @param list
	 *            存入 value 值
	 * @param timeOut
	 *            超时时间 单位 秒
	 */
	public static <T> void setKeyList(String key, List<T> list, int timeOut) {

		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(key.getBytes(), ObjectTranscoder.serialize(list));
			// 设置过期时间
			jedis.expire(key, timeOut);
		} catch (Exception e) {
			logger.error("set keyVal 设值错误:" + key + "++" + list + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 获取 redis 存入的List值
	 * 
	 * @param key
	 * @return
	 */
	public static <T> List<T> getKeyList(String key) {
		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
	        byte[] in = jedis.get(key.getBytes());      
	        List<T> list = (List<T>)ObjectTranscoder.deserialize(in);
			return list;
		} catch (Exception e) {
			logger.error("getKeyMap 取值错误:" + key + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}
	/**
	 * redis 字符串数据存入
	 * 
	 * @param key
	 *            存入key值
	 * @param val
	 *            存入 value 值
	 * @param timeOut
	 *            超时时间 单位 秒
	 */
	public static void setKeyVal(String key, String val, int timeOut) {

		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(key, val);
			// 设置过期时间
			jedis.expire(key, timeOut);
		} catch (Exception e) {
			logger.error("set keyVal 设值错误:" + key + "++" + val + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 获取 redis 存入的字符串值（每次获取后重新设置过期时间）
	 * 
	 * @param key
	 * @return
	 */
	public static String getKeyVal(String key) {
		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			String val = jedis.get(key);
			jedis.expire(key, sessionTimeOut);
			return val;
		} catch (Exception e) {
			logger.error("getKeyVal 取值错误:" + key + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 获取 redis 存入的字符串值
	 * 
	 * @param key
	 * @return
	 */
	public static String getKeyValNoReset(String key) {
		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			String val = jedis.get(key);
			// jedis.expire(key, sessionTimeOut);
			return val;
		} catch (Exception e) {
			logger.error("getKeyValNoReset 取值错误:" + key + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param key
	 *            存入的key
	 * @param val
	 *            存入的 object 对象
	 * @param timeOut
	 *            key 生存周期。单位为秒
	 */
	public static void setKeyObject(String key, Object val, Integer timeOut) {
		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		int time = sessionTimeOut.intValue();
		try {
			jedis = pool.getResource();
			if (timeOut != 0) {
				time = timeOut;
			}
			jedis.set(key.getBytes(), SerializeUtil.serialize(val));
			// 设置过期时间
			jedis.expire(key, time);
		} catch (Exception e) {
			logger.error("setKeyObject 错误:" + key + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 获取key Object值（每次获取后重新设置过期时间）
	 * 
	 * @param key
	 * @return
	 */
	public static Object getKeyObject(String key) {
		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			byte[] val = jedis.get(key.getBytes());
			if (val != null) {
				jedis.expire(key, sessionTimeOut);
				return SerializeUtil.unserialize(val);
			}
			return null;
		} catch (Exception e) {
			logger.error("getKeyObject 错误:" + key + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 获取key Object值
	 * 
	 * @param key
	 * @return
	 */
	public static Object getKeyObjectNoReset(String key) {
		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			byte[] val = jedis.get(key.getBytes());
			if (val != null) {
				return SerializeUtil.unserialize(val);
			}
			return null;
		} catch (Exception e) {
			logger.error("getKeyObjectNoReset 错误:" + key + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static void setKeyObjectJSON(String key, Object val, Integer timeOut) {
		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		int time = sessionTimeOut.intValue();
		try {
			jedis = pool.getResource();
			if (timeOut != 0) {
				time = timeOut;
			}
			jedis.set(key, JSONObject.toJSONString(val));
			// 设置过期时间
			jedis.expire(key, time);
		} catch (Exception e) {
			logger.error("setKeyObject 错误:" + key + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public static JSONObject getKeyObjectJSON(String key) {
		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			String val = jedis.get(key);
			if (val != null) {
				jedis.expire(key, sessionTimeOut);
				return JSONObject.parseObject(val);
			}
			return null;
		} catch (Exception e) {
			logger.error("getKeyObject 错误:" + key + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 刷新用户登陆状态，重复登陆把之前登陆的账号信息删除。存入新的登陆账号信息 刷新登陆的信息 刷新 过期的信息 登陆过期为6， 登陆被挤掉为5
	 * 
	 * @param mobile
	 */
	public static void refreshLoginStatus(String mobile, String token) {
		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		try {
			jedis = pool.getResource();

			/**
			 * 1、先查询出是否已登陆的 token 2、如果存在则删除之前的 token 3、把已登陆的账号存入 重新登陆的 缓存集中 并删除存在的 token
			 * 
			 */
			String loginToken = jedis.get(LOGIN_KEY + mobile);
			if (loginToken != null) {
				jedis.del(LOGIN_KEY + mobile);
				jedis.del(loginToken);
				jedis.set(LOGIN_STAT_KEY + loginToken, "5");
				jedis.expire(LOGIN_STAT_KEY + loginToken, 604800);
			}

			jedis.set(LOGIN_KEY + mobile, token);
			jedis.expire(LOGIN_KEY + mobile, 604800);

		} catch (Exception e) {
			logger.error("刷新用户登陆状态 错误:" + mobile + "====" + e.getMessage(), e);
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 获得 该 token 的状态。是因为在另一台设备重新登陆了还是token 过期 6 为过期 5 为重新登陆
	 * 
	 * @param key
	 * @return
	 */
	public static int getTokenStatus(String key) {
		Jedis jedis = null;
		String val = "6";
		try {
			jedis = pool.getResource();

			val = jedis.get(LOGIN_STAT_KEY + key);

			if (val == null) {
				val = "6";
			} else {
				jedis.expire(LOGIN_STAT_KEY + key, 1800);
			}
		} catch (Exception e) {
			logger.error("刷新用户登陆状态 错误:" + key + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return Integer.parseInt(val);
	}

	public static void setTimeOut(String key) {
		// 从池中获取一个Jedis对象
		Jedis jedis = pool.getResource();
		try {
			// 设置过期时间
			jedis.expire(key, sessionTimeOut);
		} catch (Exception e) {
			logger.error("getKeyObject 错误:" + key + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 设置超时时间
	 * 
	 * @param key
	 */
	public static void setTimeOut(String key, int timeOut) {
		// 从池中获取一个Jedis对象
		Jedis jedis = pool.getResource();
		try {
			// 设置过期时间
			jedis.expire(key, timeOut);
		} catch (Exception e) {
			logger.error("getKeyObject 错误:" + key + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 删除已存在的 token
	 * 
	 * @param key
	 */
	public static void delTokenKey(String key) {
		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.del(key.getBytes());
		} catch (Exception e) {
			logger.error("delKey 错误:" + key + "====" + e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 判断对象是否存在
	 **/
	public static boolean isExists(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			boolean count = jedis.exists(key);
			if (count) {
				jedis.expire(key, sessionTimeOut);
			}
			return count;
		} catch (Exception e) {
			logger.error("isExists 错误:" + key + "====" + e.getMessage(), e);
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	/**
	 * 判断 token 是否存在
	 * 
	 * @param key
	 * @return
	 */
	public static boolean isTokenExists(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			boolean count = jedis.exists(key);
			if (count) {
				jedis.expire(key, sessionTimeOut.intValue());
			}
			return count;
		} catch (Exception e) {
			logger.error("isExists 错误:" + key + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	/**
	 * 查询出缓存对象还有多少时间销毁
	 * 
	 * @param key
	 * @return
	 */
	public static Long getKeyPttl(String key) {
		Jedis jedis = null;
		try {
			logger.info("getKeyPttl 调用:" + key + "====");
			jedis = pool.getResource();
			if (jedis.exists(key)) {
				return jedis.pttl(key);
			}
			return null;
		} catch (Exception e) {
			logger.error("isExists 错误:" + key + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/***
	 * 手动删除key
	 * 
	 * @param key
	 * @return
	 */
	public static void delKeyStr(String key) {
		Jedis jedis = null;
		try {
			logger.info("delKeyStr 调用:" + key + "====");
			jedis = pool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			logger.info("delKeyStr 错误:" + key + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public static void delSystemCache(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			Set<String> set = jedis.keys(key + "*");
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				String keyStr = it.next();
				logger.info("清除已存在缓存，key：" + keyStr + "==== key：" + jedis.get(keyStr));
				jedis.del(keyStr);
			}
		} catch (Exception e) {
			logger.error(" 启动时清除 cache 开头的缓存失败:" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * notes 设置jedis的值
	 * 
	 * @param key
	 * @param field
	 * @param val
	 */
	public static void hset(String key, String field, String val) {
		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.hset(key, field, val);
			// 设置过期时间
			jedis.expire(key, sessionTimeOut);
		} catch (Exception e) {
			logger.error("set keyVal 设值错误:" + key + "++field设值错误:" + field + "++" + val + "====" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * notes 获取jedis的值
	 * 
	 * @param key
	 * @param field
	 */
	public static String hget(String key, String field) {
		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return ObjTools.obj2String(jedis.hget(key, field));
		} catch (Exception e) {
			logger.error("获取redis值错误！key:" + key + "fileld:" + field);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * notes 删除
	 * 
	 * @param key
	 * @param field
	 */
	public static void hdel(String key, String field) {
		// 从池中获取一个Jedis对象
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.hdel(key, field);
		} catch (Exception e) {
			logger.error("删除redis值错误！key:" + key + "fileld:" + field);
		} finally {
			if (jedis != null) {
				jedis.close();
			} 
		}
	}
	
	public static void main(String[] args) {
//		JedisUtil.setKeyObject("test", "hello Jedis!", 60);
//		System.out.println(JedisUtil.getKeyObjectNoReset("test"));
//		System.out.println(JedisUtil.getKeyMap("BiEbgFQsVZy3tYCeWFly55XIF4A4SyO8PK9VO8r34JUbjlSSCVpvjwf5IsIY7LuL"));
//		System.out.println(JedisUtil.getKeyObjectJSON("hackers"));


	}
}
