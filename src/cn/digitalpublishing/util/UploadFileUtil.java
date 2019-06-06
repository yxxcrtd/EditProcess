package cn.digitalpublishing.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadFileUtil {
	/**
	 * 文件上传
	 * 
	 * @param filePath 文件目录
	 * @param fileName 文件名称
	 * @param b 输出流文件
	 * @throws Exception 
	 */
	public static void writeFile(String filePath, String fileName, byte[] b) throws Exception {
		//文件上传
		FileOutputStream outputStream = null;
		try {
			File file = new File(filePath);
			if(!file.exists()){
				file.mkdirs();
			}
			outputStream = new FileOutputStream(filePath+fileName);
			outputStream.write(b);
		} catch (Exception e) {
			File file = new File(filePath+fileName);
			if(file.exists()){
				file.delete();
			}
			throw e;
		}finally{
			if(outputStream!=null){
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//解析文件完毕后，将服务器上的文件删除
	public static void deleteFile(String webRoot, String filePath) {
			File file = new File(PathUtil.bulidFullPath(webRoot, filePath));
			if(file.exists()){
				file.delete();
		}
	}
	
	/**
	 * 使用UUID创建上传文件的名字
	 * @param imageFormat
	 * @return
	 */
	public static String createUploadFileName(String imageFormat) {
		String uuid = java.util.UUID.randomUUID().toString();
		StringBuffer uploadFileName = new StringBuffer();
		uploadFileName.append(uuid.replaceAll(PathUtil.JUNCTION, ""));
		uploadFileName.append(PathUtil.DOT);
		uploadFileName.append(imageFormat);
		return uploadFileName.toString();
	}
	
	public static void main(String[] args) {
		//文件上传
		FileOutputStream outputStream = null;
		byte[] b = null;
		try {
			outputStream = new FileOutputStream(new File("D:/ljbd.txt"));
			outputStream.write(b);
			outputStream.close();
		} catch (Exception e) {
			File file = new File("D:/ljbd.txt");
			if(file.exists()){
				file.delete();
			}
		}
	}
}
