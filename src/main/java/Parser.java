import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;
import java.util.Iterator;

public class Parser {

    public static String parse(String name1, String name2) {

        String[] stringFirstFile = null;
        String[] stringSecondFile = null;
        InputStream in1 = null;
        InputStream in2 = null;
        HSSFWorkbook wbFirstFile = null;
        HSSFWorkbook wbSecondFileFile = null;
        int workCol = 0;

        try {
            in1 = new FileInputStream(name1);
            in2 = new FileInputStream(name2);
            wbFirstFile = new HSSFWorkbook(in1);
            wbSecondFileFile = new HSSFWorkbook(in2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tmp = "";
        Sheet sheet1 = wbFirstFile.getSheetAt(0);//открыл файл 1
        Sheet sheet2 = wbSecondFileFile.getSheetAt(0);//открыл файл 2
        //Прочитать первую строку и определить рабочую колнку
        int rowFirst = sheet1.getFirstRowNum();
        int rowLast = sheet1.getLastRowNum();
        Row firstRow = sheet1.getRow(rowFirst);
        short minCol = firstRow.getFirstCellNum();
        short maxCol = firstRow.getLastCellNum();
        for (short i = minCol; i < maxCol; i++) {
            Cell cell = firstRow.getCell(i);
            int cellType = cell.getCellType();
            if (cellType == Cell.CELL_TYPE_STRING) {
                tmp = cell.getStringCellValue();
                if ((tmp.compareToIgnoreCase("Номенклатура") == 0) /*добавить сравнение на "Товар"*/) {
                    workCol = cell.getColumnIndex();
                }
            }
        }
        for (int i = ++rowFirst; i < rowLast; i++) {
            Row row = sheet1.getRow(i);
            Cell cell = row.getCell(workCol);
            stringFirstFile = cell.getStringCellValue().split("\\s+");
            //открываем второй файл и читаем строки, ищем столбец "Номенклатура"
        }

        //Прочитать строку из рабочей колонки и разбить на слова
            //тестовый вывод
            for (String s :
                    stringFirstFile) {
                System.out.println(s);
            }
        }




        return "";
    }
}