package org.excel.utils;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.UUID;

public class ExcelUtils {
    public static void export() throws IOException {
        File f = new File("C:\\Users\\Administrator\\Desktop\\1.xlsx");
        File img = new File("C:\\Users\\Administrator\\Desktop\\737391.png");
        InputStream inputStream = new FileInputStream(f);
        InputStream inputStreamImg = new FileInputStream(img);
        byte[] bytes = new byte[inputStreamImg.available()];
        inputStreamImg.read(bytes);
        SXSSFWorkbook workbook = new SXSSFWorkbook(new XSSFWorkbook(inputStream));
        Sheet s = workbook.getSheetAt(0);
        Drawing patriarch = s.createDrawingPatriarch();
        User[] u = new User[]{new User("22", "33"), new User("2", "5")};
        int i = 0;
        for (User user : u) {

            Row row = s.createRow(i);
            Cell cell1 = row.createCell(0);

            cell1.setCellType(HSSFCell.CELL_TYPE_STRING);

            Cell cell2 = row.createCell(1);
            cell2.setCellType(HSSFCell.CELL_TYPE_STRING);

            cell1.setCellValue(user.getName());
            cell2.setCellValue(user.getPassword());


            //设置每张图片插入位置
            final XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, 2,
                    i, 3, i + 1);//参数为图片插入在表格的坐标，可以自行查看api研究参数
            anchor.setAnchorType(0);
            // 插入图片
            Picture picture = patriarch.createPicture(anchor, workbook.addPicture(bytes, HSSFWorkbook.PICTURE_TYPE_PNG));
            i++;
        }
        String uu = UUID.randomUUID().toString();
        String ss = String.format("C:\\Users\\Administrator\\Desktop\\%s.xlsx", uu);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(ss));
        workbook.write(fileOutputStream);
    }
}

