package cn.digitalpublishing.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tools.zip.ZipOutputStream;


public class CreateZipFile {

	public static void getZipFile(String path,String docNames,String zipName) throws Exception{
		String[] names = docNames.split(",");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		path = path + "/";
		String zipPath = path  + zipName + date + ".zip";
		FileUtil.newFolder(path +zipName+date);
		for(String docName : names){
			FileUtil.copy(new File(path+docName+".doc"), new File(path + zipName+date+"/" +docName + ".doc"));
		}
		ZipOutputStream zosm = new ZipOutputStream(new FileOutputStream(zipPath));
		FileUtil.compressionFiles2(zosm, new File(path+zipName+date), "");
		if(zosm != null){
			zosm.close();
		}
		
		FileUtil.delFolder(path +zipName+date);
	}

}
