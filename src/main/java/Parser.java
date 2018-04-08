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
        int indxTovInFst = 0;
        int indxPriceInFst = 0;
        int countOfTrue = 0;

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
                switch (tmp) {
                    case "Номенклатура":
                        indxTovInFst = cell.getColumnIndex();
                        break;
                    case "Цена":
                        indxPriceInFst = cell.getColumnIndex();
                        break;
                }
//                if ((tmp.compareToIgnoreCase("Номенклатура") == 0) /*добавить сравнение на "Товар"*/) {
//                    indxTovInFst = cell.getColumnIndex();
//                }
            }
        }

        //то же самое со вторым файлом - находим индекс колонки "Номенклатура"
        int fstInScndFile = sheet2.getFirstRowNum();
        int endInScndFile = sheet2.getLastRowNum();
        int indxTovInScnd = 0;
        int indxPriceInSecond = 0;
        Row firstRowInScnd = sheet2.getRow(fstInScndFile);
        short minColInScnd = firstRowInScnd.getFirstCellNum();
        short maxColInScnd = firstRowInScnd.getLastCellNum();
        for (int k = minColInScnd; k < maxColInScnd; k++) {
            Cell cell2 = firstRowInScnd.getCell(k);
            int cell2Type = cell2.getCellType();
            if (cell2Type == Cell.CELL_TYPE_STRING) {
                tmp = cell2.getStringCellValue();
                switch (tmp) {
                    case "Номенклатура":
                        indxTovInScnd = cell2.getColumnIndex();
                        break;
                    case "Цена":
                        indxPriceInSecond = cell2.getColumnIndex();
                        break;
                }
//                if ((tmp.compareToIgnoreCase("Номенклатура") == 0)/*добавить сравнение на "Товар"*/) {
//                    indxTovInScnd = cell2.getColumnIndex();
//                }
            }
        }

        for (int i = ++rowFirst; i < rowLast; i++) {
            Row row1 = sheet1.getRow(i);
            Cell cell1 = row1.getCell(indxTovInFst);
            stringFirstFile = cell1.getStringCellValue().split("\\s+");
            for (int j = ++fstInScndFile; j < endInScndFile; j++) {
                Row row2 = sheet2.getRow(j);
                Cell cell2 = row2.getCell(indxTovInScnd);
                stringSecondFile = cell2.getStringCellValue().split("\\s+");
                //сравнить содержимое первого строкового массива со вторым, если совпадения = длина меньшего массива -1, то строки соответствуют.
            }


        }

        //Прочитать строку из рабочей колонки и разбить на слова
        //тестовый вывод
        for (String s :
                stringFirstFile) {
            System.out.println(s);
        }


        return "";
    }
}