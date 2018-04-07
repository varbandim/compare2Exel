import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;
import java.util.Iterator;

public class Parser {

    public static String parse(String name) {

        String[] result = null;
        InputStream in = null;
        HSSFWorkbook wb = null;
        try {
            in = new FileInputStream(name);
            wb = new HSSFWorkbook(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tmp = "";
        int workCol = 0;
        Sheet sheet = wb.getSheetAt(0);//открыл книгу
        //Прочитать первую строку
        int rowFirst = sheet.getFirstRowNum();
        Row firstRowString = sheet.getRow(rowFirst);
        short minCol = firstRowString.getFirstCellNum();
        short maxCol = firstRowString.getLastCellNum();
        for (short i = minCol; i < maxCol; i++) {
            Cell cell = firstRowString.getCell(i);
            int cellType = cell.getCellType();
            if (cellType == Cell.CELL_TYPE_STRING) {
                tmp = cell.getStringCellValue();
                if ((tmp.compareToIgnoreCase("Номенклатура") == 0) /*добавить сравнение на "Товар"*/) {
                    workCol = cell.getColumnIndex();
                }
            }
        }
//        Iterator<Row> it = sheet.iterator();//начинаю обходить книгу построчно
//        while (it.hasNext()) {
//            Row row = it.next();
//            Iterator<Cell> cells = row.iterator();
//            while (cells.hasNext()) {
//                Cell cell = cells.next();
//                int cellType = cell.getCellType();
//                if (cellType == Cell.CELL_TYPE_STRING) {
//                    result = cell.getStringCellValue().split("\\s+");
//                }
//                cell.setCellValue("Пупс");//тут должно быть сравнение названия номенклатуры и сравнение цен
//                try (FileOutputStream out = new FileOutputStream(new File(name))) {
//                    wb.write(out);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        try {
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
        return "";
    }
}