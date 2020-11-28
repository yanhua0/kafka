package org.file.test.utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.*;

public class MappedByteBufferUtils {
    public static String PATH = "G:\\JavaWeb\\studyJava\\kafka\\file\\src\\main\\resources\\file\\bg1.jpg";
    public static String OUT_PART_PATH = "G:\\JavaWeb\\studyJava\\kafka\\file\\src\\main\\resources\\part_file\\";
    public static String OUT_PATH = "G:\\JavaWeb\\studyJava\\kafka\\file\\src\\main\\resources\\merge\\bg1.jpg";

    public static void splitFile(Integer splitSize) throws IOException {


        RandomAccessFile in = new RandomAccessFile(PATH, "rw");
        //最多映射Integer.MAX_VALUE
        MappedByteBuffer mbb = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, in.getChannel().size());
        long intSize = in.getChannel().size();
        long loop = intSize % splitSize == 0 ? in.getChannel().size() / splitSize : (in.getChannel().size() / splitSize) + 1;
        // int start=0;
        for (int i = 0; i < loop; i++) {
            byte[] b;
            if (i == loop - 1) {
                long remain = intSize - splitSize * (loop - 1);
                b = new byte[(int) remain];
                mbb.get(b);
                //  start=(i-1)*splitSize+(int)remain;
            } else {
                b = new byte[splitSize];
                mbb.get(b);
                // start=i*splitSize;
            }

            RandomAccessFile out = new RandomAccessFile(OUT_PART_PATH + i + ".png", "rw");
            MappedByteBuffer outMapp = out.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, splitSize);
            outMapp.put(b);
            outMapp.flip();
            DecimalFormat decimalFormat = new DecimalFormat("##.00%");
            String s = decimalFormat.format((i + 1) * splitSize * 1.0 / in.getChannel().size());

            System.out.println("-----------> 进度:" + s);

        }
        mbb.flip();
    }

    public static void mergeWrite(Integer splitSize) throws IOException {
        File dir = new File(OUT_PART_PATH);//目录对象
        //分片文件
        int fileLen0 = 0;
        File[] fileArr = dir.listFiles();
        for (File file : fileArr) {
            fileLen0 += file.length();
        }
        List<File> fileList = Arrays.asList(fileArr);
        //根据文件名称对fileList顺序排序
        Collections.sort(fileList, (o1, o2) -> {
            int lastIndex1 = o1.getName().lastIndexOf(".");
            int lastIndex2 = o2.getName().lastIndexOf(".");
            int num1 = Integer.parseInt(o1.getName().substring(lastIndex1 - 1, lastIndex1));
            int num2 = Integer.parseInt(o2.getName().substring(lastIndex2 - 1, lastIndex2));
            return num1 - num2;
        });
        RandomAccessFile outFile = new RandomAccessFile(OUT_PATH, "rw");
        long intSize = fileLen0;
        long loop = intSize % splitSize == 0 ? intSize / splitSize : (intSize / splitSize) + 1;
        Map<Integer, MappedByteBuffer> hash = new HashMap<>();
        long start;
     //   ZipOutputStream zipOutputStream=;
        //防止文件过大超过最大限制。splitSize与分片splitSize相等
        for (int i = 0; i < loop; i++) {
            if (i == loop - 1) {
                start = intSize -(i-1)*splitSize;
                MappedByteBuffer outMapp = outFile.getChannel().map(FileChannel.MapMode.READ_WRITE, start, intSize%splitSize);
                hash.put(i, outMapp);
            } else {
                start = i * splitSize;
                MappedByteBuffer outMapp = outFile.getChannel().map(FileChannel.MapMode.READ_WRITE, start, splitSize);
                hash.put(i, outMapp);
            }

        }
        for (int i = 0; i <fileList.size() ; i++) {
            File file=fileList.get(i);
            RandomAccessFile inFile = new RandomAccessFile(file, "r");
            MappedByteBuffer inMapp = inFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, inFile.getChannel().size());
            if(i==fileList.size()-1){
                byte[] bt = new byte[(int)intSize % splitSize];
                inMapp.get(bt);
                hash.get(i).put(bt);
            }else{
                byte[] bt = new byte[splitSize];
                inMapp.get(bt);
                hash.get(i).put(bt);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        //splitFile(512000);
        mergeWrite(512000);
        //ZipOutputStream zipOutputStream=new ZipOutputStream();
        //zipOutputStream.

    }
}
