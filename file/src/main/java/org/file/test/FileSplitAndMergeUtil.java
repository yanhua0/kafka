package org.file.test;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileSplitAndMergeUtil {
    public static String PATH="G:\\JavaWeb\\studyJava\\kafka\\file\\src\\main\\resources\\file\\t.java";
    public static String PATH_MERGE="G:\\JavaWeb\\studyJava\\kafka\\file\\src\\main\\resources\\merge";
    public static void main(String[] args) throws Exception {
        splitFile2(PATH);//大文件分片方法2：RandomAccessFile方式
   //     mergeFile2(PATH_MERGE,PATH);//大文件合成方法2：RandomAccessFile方式
    }

    /**
     * 大文件分片方法2：RandomAccessFile方式
     * @param filePath 文件路径
     * */
    public static void splitFile2(String filePath){
        File file=new File(filePath);
        RandomAccessFile in=null;
        RandomAccessFile out =null;
        long length=file.length();//文件大小
      //  long splitSize=5*1024*1024;//单片文件大小,5M
        long splitSize=10;//单片文件大小,5M
        long count=length%splitSize==0?(length/splitSize):(length/splitSize+1);//文件分片数
        byte[] bt=new byte[1024];
        try {
            in=new RandomAccessFile(file, "r");
            for (int i = 1; i <= count; i++) {
                out = new RandomAccessFile(new File(filePath+"."+i+".part"), "rw");//定义一个可读可写且后缀名为.part的二进制分片文件
                long begin = (i-1)*splitSize;
                long end = i* splitSize;
                int len=0;
                in.seek(begin);
                while (in.getFilePointer()<end&&-1!=(len=in.read(bt))) {
                    out.write(bt, 0, len);
                }
                out.close();
            }
            System.out.println("文件分片成功！");
        } catch (Exception e) {
            System.out.println("文件分片失败！");
            e.printStackTrace();
        }finally {
            try {
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 大文件合成方法2：RandomAccessFile方式
     * @param splitDir 分片目录
     * @param newFilePath 新文件路径
     * @throws Exception
     * */
    public static void mergeFile2(String splitDir,String newFilePath){
        File dir=new File(splitDir);//目录对象
        File[] fileArr=dir.listFiles(new FilenameFilter() {//分片文件
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".part");
            }
        });
        List<File> fileList = Arrays.asList(fileArr);
        Collections.sort(fileList, new Comparator<File>() {//根据文件名称对fileList顺序排序
            @Override
            public int compare(File o1, File o2) {
                int lastIndex11=o1.getName().lastIndexOf(".");
                int lastIndex12=o1.getName().substring(0,lastIndex11).lastIndexOf(".")+1;
                int lastIndex21=o2.getName().lastIndexOf(".");
                int lastIndex22=o2.getName().substring(0,lastIndex21).lastIndexOf(".")+1;
                int num1=Integer.parseInt(o1.getName().substring(lastIndex12,lastIndex11));
                int num2=Integer.parseInt(o2.getName().substring(lastIndex22,lastIndex21));
                return num1-num2;
            }
        });
        RandomAccessFile in=null;
        RandomAccessFile out =null;
        try {
            out=new RandomAccessFile(newFilePath, "rw");
            for(File file:fileList){//按顺序合成文件
                in=new RandomAccessFile(file, "r");
                int len=0;
                byte[] bt=new byte[1024];
                while (-1!=(len=in.read(bt))) {
                    out.write(bt, 0, len);
                }
                in.close();
            }
            System.out.println("文件合成成功！");
        } catch (Exception e) {
            System.out.println("文件合成失败！");
            e.printStackTrace();
        }finally {
            try {
                if(in!=null){
                    in.close();
                }
                if(out!=null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}