package choi.choice.framework.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
@SuppressWarnings({"rawtypes", "unchecked"})
class MiscUtilz {
    public static boolean hasDuplicates(Vector v) {
   		int i = 0;
   		int j = 0;
   		boolean duplicates = false;

   		for (i = 0; i < v.size() - 1; i++) {
   			for (j = (i + 1); j < v.size(); j++) {
   				if (v.elementAt(i).toString().equalsIgnoreCase(v.elementAt(j).toString())) {
   					duplicates = true;
   				}

   			}

   		}

   		return duplicates;
   	}

   	public static Vector removeDuplicates(Vector s) {
   		int i = 0;
   		int j = 0;
   		boolean duplicates = false;

   		Vector v = new Vector();

   		for (i = 0; i < s.size(); i++) {
   			duplicates = false;
   			for (j = (i + 1); j < s.size(); j++) {
   				if (s.elementAt(i).toString().equalsIgnoreCase(s.elementAt(j).toString())) {
   					duplicates = true;
   				}

   			}
   			if (duplicates == false) {
   				v.addElement(s.elementAt(i).toString().trim());
   			}

   		}

   		return v;
   	}

   	public static Vector removeDuplicateDomains(Vector s) {
   		int i = 0;
   		int j = 0;
   		boolean duplicates = false;
   		String str1 = "";
   		String str2 = "";

   		Vector v = new Vector();

   		for (i = 0; i < s.size(); i++) {
   			duplicates = false;
   			for (j = (i + 1); j < s.size(); j++) {
   				str1 = "";
   				str2 = "";
   				str1 = s.elementAt(i).toString().trim();
   				str2 = s.elementAt(j).toString().trim();
   				if (str1.indexOf('@') > -1) {
   					str1 = str1.substring(str1.indexOf('@'));
   				}
   				if (str2.indexOf('@') > -1) {
   					str2 = str2.substring(str2.indexOf('@'));
   				}

   				if (str1.equalsIgnoreCase(str2)) {
   					duplicates = true;
   				}

   			}
   			if (duplicates == false) {
   				v.addElement(s.elementAt(i).toString().trim());
   			}

   		}

   		return v;
   	}

   	public static boolean areVectorsEqual(Vector a, Vector b) {
   		if (a.size() != b.size()) {
   			return false;
   		}

   		int i = 0;
   		int vectorSize = a.size();
   		boolean identical = true;

   		for (i = 0; i < vectorSize; i++) {
   			if (!(a.elementAt(i).toString().equalsIgnoreCase(b.elementAt(i).toString()))) {
   				identical = false;
   			}
   		}

   		return identical;
   	}

   	public static Vector removeDuplicates(Vector a, Vector b) {

   		int i = 0;
   		int j = 0;
   		boolean present = true;
   		Vector v = new Vector();

   		for (i = 0; i < a.size(); i++) {
   			present = false;
   			for (j = 0; j < b.size(); j++) {
   				if (a.elementAt(i).toString().equalsIgnoreCase(b.elementAt(j).toString())) {
   					present = true;
   				}
   			}
   			if (!(present)) {
   				v.addElement(a.elementAt(i));
   			}
   		}

   		return v;
   	}

   	// String readZip="C:\\xxx.zip"; //압축 풀 zip
   	// String unZip="C:\\"; //unzip dir경로
   	// unZip(readZip, unZip);//호출
   	public static void unZip(String readZip, String unZipDir) throws Exception {
   		try {
   			ZipInputStream in = new ZipInputStream(new FileInputStream(readZip));
   			ZipEntry ze;
   			while ((ze = in.getNextEntry()) != null) {
   				/**
   				 * fortify - 경로조작 및 자원삽입
   				 */
   				SecureCode.fileNameCheck(ze.getName());
   				// Open the output file
   				OutputStream out = new FileOutputStream(unZipDir + "" + ze.getName());

   				// Transfer bytes from the ZIP file to the output file
   				byte[] buf = new byte[1024];
   				int len;
   				while ((len = in.read(buf)) > 0) {
   					out.write(buf, 0, len);
   				}
   				// Close the streams
   				out.close();
   			}
   			in.close();
   		} catch (Exception e) {
   			log.error("", e);
   		}
   	}

   	// 디렉토리 하부 까지 모두 삭제
   	public static void DeleteDirectory(File file) {
   		if (file.exists()) {
   			File[] files = file.listFiles();
   			if (files == null || files.length == 0) {
   				return;
   			}
   			for (int i = 0; i < files.length; i++) {
   				if (files[i].isDirectory()) {
   					DeleteDirectory(files[i]);
   				} else {
   					files[i].delete();
   				}
   			}

   			file.delete();
   		} else {
   			return;
   		}
   	}
}
