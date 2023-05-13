package com.why.transportsecuritybackend.common.utils;

import com.why.transportsecuritybackend.entity.dto.AccidentExcelDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 文件输出工具类
 *
 * @author why
 * @date 2023/04/29 13:28
 **/
public class OutputFileUtils {
    /**
     * excel输出
     * @param list 数据
     * @param response 响应
     */
    public static void outputFile(List<AccidentExcelDTO> list, HttpServletResponse response){
        //新建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //列名数组
        String[] columnNames = {"车主","车牌号","车辆类型","驾驶员损伤情况","后排乘员损伤情况","事故时间","经度","纬度","地址","碰撞方向","安全气囊是否弹开","横向加速度","纵向加速度"};

        //新建sheet
        XSSFSheet sheet = workbook.createSheet();
        //新建字体
        Font font = workbook.createFont();
        font.setFontName("simsun");
        font.setBold(true);
        font.setColor(IndexedColors.BLACK.index);

        //新建样式
        XSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        titleStyle.setFont(font);

        //创建一行
        Row titleRow = sheet.createRow(0);
        //设置一行的单元格的值
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(titleStyle);
        }

        String number = null;
        //添加数据
        int lastRowNum = 0;
        for (AccidentExcelDTO infoExcelModel : list) {
            lastRowNum = lastRowNum + 1;
            //创建新行
            Row dataRow = sheet.createRow(lastRowNum);
            //创建单元格添加数据
            //"车主","车牌号","车辆类型","损伤情况","日期","时间","经度","纬度","地址","碰撞方向","安全气囊是否弹开","横向加速度","纵向加速度"
            dataRow.createCell(0).setCellValue(infoExcelModel.getVehicleNumber());
            number = infoExcelModel.getVehicleNumber();
            dataRow.createCell(1).setCellValue(infoExcelModel.getVehicleNumber());
            dataRow.createCell(2).setCellValue(infoExcelModel.getVehicleType());
            dataRow.createCell(3).setCellValue(infoExcelModel.getAccidentLevel());
            dataRow.createCell(4).setCellValue(infoExcelModel.getPassengerAccidentLevel());
            dataRow.createCell(5).setCellValue(infoExcelModel.getAccidentDate());
            dataRow.createCell(6).setCellValue(infoExcelModel.getLng());
            dataRow.createCell(7).setCellValue(infoExcelModel.getLat());
            dataRow.createCell(8).setCellValue(infoExcelModel.getAddress());
            dataRow.createCell(9).setCellValue(infoExcelModel.getDirection());
            dataRow.createCell(10).setCellValue(infoExcelModel.getPop());
            dataRow.createCell(11).setCellValue(infoExcelModel.getAxList().get(0));
            dataRow.createCell(12).setCellValue(infoExcelModel.getAyList().get(0));
        }
        if (!list.isEmpty()){
            AccidentExcelDTO infoExcelModel = list.get(0);
            int max = 0;
            boolean flag = true;
            if (infoExcelModel.getAxList().size() >= infoExcelModel.getAyList().size()){
                max = infoExcelModel.getAxList().size();
                flag = true;
            }else {
                max = infoExcelModel.getAyList().size();
                flag = false;
            }
            int lastRowNumR = 1;
            for (int i = 0; i < max-1; i++) {
                Row dataRow = sheet.createRow(lastRowNumR + 1);
                double ax;
                double ay;
                if (flag){
                    if (i > infoExcelModel.getAyList().size() - 2){
                        ay = 0.000;
                    }else {
                        ay = infoExcelModel.getAyList().get(i+1);
                    }
                    ax = infoExcelModel.getAxList().get(i+1);
                }else {
                    if (i > infoExcelModel.getAxList().size() -2){
                        ax = 0.000;
                    }else {
                        ax = infoExcelModel.getAxList().get(i+1);
                    }
                    ay = infoExcelModel.getAyList().get(i+1);
                }
                dataRow.createCell(11).setCellValue(ax);
                dataRow.createCell(12).setCellValue(ay);
                lastRowNumR++;
            }

        }
        try {
            response.setContentType("application/vnd.ms-excel");
            if (number != null){
                response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode(number+".xlsx", "utf-8"));
            }else {
                response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode("data.xlsx", "utf-8"));
            }
            response.setHeader("Access-Control-Expose-Headers", "content-Disposition");
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
