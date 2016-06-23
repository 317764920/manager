package com.lltech.manager.util;

/**
 * @ClassName(类名) : FileType
 * @Description(描述) : 文件类型
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年03月29日 15:32
 */
public class FileType {
    public static final String JPG = "jpg";
    public static final String PNG = "png";
    public static final String JPEG = "jpeg";
    public static final String GIF = "gif";
    public static final String BMP = "bmp";
    public static final String ICO = "ico";
    public static final String PDF = "pdf";
    public static final String TXT = "txt";
    public static final String DOC = "doc";
    public static final String DOCX = "docx";
    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";


    public static boolean isImg(String filename) {
        String suffix = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
        if (GIF.equalsIgnoreCase(suffix) || JPG.equalsIgnoreCase(suffix) || JPEG.equalsIgnoreCase(suffix) || PNG.equalsIgnoreCase(suffix)
                || BMP.equalsIgnoreCase(suffix) || ICO.equalsIgnoreCase(suffix)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPdf(String filename) {
        String suffix = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
        if (PDF.equalsIgnoreCase(suffix)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isWord(String filename) {
        String suffix = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
        if (DOC.equalsIgnoreCase(suffix) || DOCX.equalsIgnoreCase(suffix)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isExcel(String filename) {
        String suffix = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
        if (XLS.equalsIgnoreCase(suffix) || XLSX.equalsIgnoreCase(suffix)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOfficeDoc(String filename) {
        String suffix = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
        if (DOC.equalsIgnoreCase(suffix) || DOCX.equalsIgnoreCase(suffix) || XLS.equalsIgnoreCase(suffix) || XLSX.equalsIgnoreCase(suffix)) {
            return true;
        } else {
            return false;
        }
    }
}
