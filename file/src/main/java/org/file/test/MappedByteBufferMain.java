package org.file.test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferMain {
    public static String PATH="G:\\JavaWeb\\studyJava\\kafka\\file\\src\\main\\resources\\file\\t.java";
    public static String OUT_PATH="G:\\JavaWeb\\studyJava\\kafka\\file\\src\\main\\resources\\file\\t1.txt";
    public static void main(String[] args) throws Exception {
        RandomAccessFile in=new RandomAccessFile(PATH, "rw");
        System.out.println(in.getChannel().size());
        FileChannel fc = in.getChannel();
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0,10);
        byte[] b=new byte[2];
        ByteBuffer byteBuffer=mbb.get(b);


        RandomAccessFile out=new RandomAccessFile(OUT_PATH, "rw");
        FileChannel fcOut = out.getChannel();
        MappedByteBuffer fcOutMapp = fcOut.map(FileChannel.MapMode.READ_WRITE, 0,byteBuffer.getInt());
        fcOutMapp.put(byteBuffer);
        fcOutMapp.flip();
    }
}
