package com.card.file.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.Cipher;
import java.io.*;


/**
 * 流关闭工具类
 * 
 * @author hujp
 *
 */
public class StreamUtil {

	static Log log = LogFactory.getLog(StreamUtil.class);

	/**
	 * 流复制方法(记录进度)
	 * 
	 * @param input
	 *            输入流
	 * @param output
	 *            输出流
	 * @param indexKey
	 *            用于查询流的进度
	 * @param explain
	 *            说明，例如：文件复制进度
	 * @throws Exception
	 *             流复制失败抛异常
	 */
	public static void copyStream(InputStream input, OutputStream output,
			Integer indexKey, String explain) throws Exception {
		try {
			// 设置文件缓冲区
			byte[] buffer = new byte[1024];
			int r;
			while ((r = input.read(buffer)) > 0) {
				// 1024次循环才记录一次进度
				output.write(buffer, 0, r);
			}
		} catch (Exception e) {
			throw new Exception("流复制失败！：", e);
		} finally {
			// 加密进度完成清除进度
		}
	}

	/**
	 * 流复制方法(记录进度)
	 * 
	 * @param inFile
	 *            需要复制的文件
	 * @param outFile
	 *            复制后的文件
	 * @param indexKey
	 *            用于查询流的进度
	 * @param explain
	 *            说明，例如：文件复制进度
	 * @throws Exception
	 *             流复制失败抛异常
	 */
	public static void copyStream(File inFile, File outFile, Integer indexKey,
			String explain) throws Exception {
		FileOutputStream output = null;
		FileInputStream input = null;
		try {
			input = new FileInputStream(inFile);
			output = new FileOutputStream(outFile);
			copyStream(input, output, indexKey, explain);
		} finally {
			close(output);
			close(input);
		}
	}

	/**
	 * 流复制方法
	 * 
	 * @param input
	 *            输入流
	 * @param output
	 *            输出流
	 * @throws Exception
	 *             流复制失败抛异常
	 */
	public static void copyStream(InputStream input, OutputStream output)
			throws Exception {
		try {
			// 设置文件缓冲区
			byte[] buffer = new byte[1024 * 1024];
			int r;
			while ((r = input.read(buffer)) > 0) {
				output.write(buffer, 0, r);
			}
		} catch (Exception e) {
			throw new Exception("留复制失败！：", e);
		}
	}

	/**
	 * 流复制方法，并且更加加密或者解密对象进行处理(记录进度)
	 * 
	 * @param cipher
	 *            加密或者解密对象
	 * @param input
	 *            输入流
	 * @param output
	 *            输出流
	 * @param indexKey
	 *            用于查询流的进度
	 * @param explain
	 *            说明，例如：文件加密进度
	 * @throws Exception
	 *             流复制失败抛异常
	 */
	public static void copyStream(Cipher cipher, InputStream input,
			OutputStream output, Integer indexKey, String explain)
			throws Exception {
		try {
			// 设置文件缓冲区
			byte[] buffer = new byte[1024];
			int r;
			while ((r = input.read(buffer)) > 0) {
				output.write(cipher.doFinal(buffer, 0, r), 0, r);
			}
		} catch (Exception e) {
			throw new Exception("留复制失败！：", e);
		} finally {
		}
	}

	/**
	 * 流复制方法，并且更加加密或者解密对象进行处理
	 * 
	 * @param cipher
	 *            加密或者解密对象
	 * @param input
	 *            输入流
	 * @param output
	 *            输出流
	 * @param indexKey
	 *            用于查询流的进度
	 * @throws Exception
	 *             流复制失败抛异常
	 */
	public static void copyStream(Cipher cipher, InputStream input,
			OutputStream output) throws Exception {
		try {
			// 设置文件缓冲区
			byte[] buffer = new byte[1024];
			int r;
			while ((r = input.read(buffer)) > 0) {
				output.write(cipher.doFinal(buffer, 0, r), 0, r);
			}
		} catch (Exception e) {
			throw new Exception("留复制失败！：", e);
		}
	}

	/**
	 * 校验fpt下载的文件是否下载完全
	 * 
	 * @param ftpOutFilePath
	 *            ftp下载文件全路径
	 * @param fileSize
	 *            实际大小
	 * @return 是否效验通过
	 */
	public static boolean checkFTPDown(String ftpOutFilePath, Long fileSize) {
		File file = new File(ftpOutFilePath);
		return checkFTPDown(file, fileSize);
	}

	/**
	 * 校验fpt下载的文件是否下载完全（检查方式：判断文件大小是否与ftp上大小一致）
	 * 
	 * @param ftpOutFile
	 *            下载的文件对象
	 * @param fileSize
	 *            实际大小
	 * @return 是否效验通过
	 */
	public static boolean checkFTPDown(File ftpOutFile, Long fileSize) {
		if (ftpOutFile == null) {
			return false;
		}
		if (ftpOutFile.exists()) {
			// 如果文件大小一致返回文件一致
			if (ftpOutFile.length() >= fileSize) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 文件校验是否下载完整
	 * 
	 * @param a
	 *            预下载大小
	 * @param b
	 *            实际大小
	 * @return 是否一致
	 */
	public static boolean fileVerify(Long a, long b) {
		if (a != b) {
			return false;
		}
		return true;
	}

	/**
	 * 关闭输入流
	 * 
	 * @param inp
	 *            输入流对象
	 */
	public static void close(InputStream inp) {
		if (inp != null) {
			try {
				inp.close();
			} catch (IOException e) {
				try {
					inp.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 关闭输出流
	 * 
	 * @param out
	 *            输出流对象
	 */
	public static void close(OutputStream out) {
		if (out != null) {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 关闭流
	 * 
	 * @param close
	 *            刘关闭对象
	 */
	public static void close(Closeable close) {
		if (close != null) {
			try {
				close.close();
			} catch (IOException e) {
				try {
					close.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 关闭流
	 * 
	 * @param close
	 *            刘关闭对象
	 */
	public static void close(Writer writer) {
		if (writer != null) {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				try {
					writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
