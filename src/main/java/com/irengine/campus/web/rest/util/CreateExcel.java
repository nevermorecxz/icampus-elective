package com.irengine.campus.web.rest.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.irengine.campus.domain.ClassHour;
import com.irengine.campus.web.rest.dto.ResultsDTOByClass;
import com.irengine.campus.web.rest.dto.ResultsDTOByCourses;
import com.irengine.campus.web.rest.dto.SyllabusInfoDTO;

public class CreateExcel {

	public static void createExcelByClass(ResultsDTOByClass result, HttpServletResponse response) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("预选结果");
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 7000);
		/* 默认单元格样式 */
		HSSFCellStyle normalStyle = workbook.createCellStyle();
		normalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		normalStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		normalStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		HSSFCellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		/* 字体 */
		HSSFFont normalfont = workbook.createFont();
		normalfont.setFontHeightInPoints((short) 10);
		normalfont.setFontName("宋体");
		normalStyle.setFont(normalfont);
		headerStyle.setFont(normalfont);
		/* 表格内容 */
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 400);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		HSSFCell ce1_1 = row.createCell(0);
		ce1_1.setCellValue(result.getTitle());
		ce1_1.setCellStyle(headerStyle);
		HSSFRow row2 = sheet.createRow(1);
		row2.setHeight((short) 400);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
		HSSFCell ce2_3 = row2.createCell(2);
		ce2_3.setCellStyle(headerStyle);
		ce2_3.setCellValue(result.getTeacherInfo());
		for (int i = 0; i < result.getStudents().size(); i++) {
			HSSFRow row_n = sheet.createRow(i + 2);
			row_n.setHeight((short) 400);
			HSSFCell ce_n_1 = row_n.createCell(0);
			ce_n_1.setCellValue(result.getStudents().get(i).getClassInfo());
			ce_n_1.setCellStyle(normalStyle);
			HSSFCell ce_n_2 = row_n.createCell(1);
			ce_n_2.setCellValue(result.getStudents().get(i).getStudentNum());
			ce_n_2.setCellStyle(normalStyle);
			HSSFCell ce_n_3 = row_n.createCell(2);
			ce_n_3.setCellValue(result.getStudents().get(i).getName());
			ce_n_3.setCellStyle(normalStyle);
			HSSFCell ce_n_4 = row_n.createCell(3);
			ce_n_4.setCellValue(result.getStudents().get(i).getSelectInfo());
			ce_n_4.setCellStyle(normalStyle);
		}
		downloadExcel(result.getTitle(), workbook, response);
	}

	public static void createExcelByCourses(ResultsDTOByCourses result, HttpServletResponse response)
			throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("预选结果");
		// for(int x=0;x<result.getData().get(0).getData().size();x++){
		// sheet.setColumnWidth(x, 4000);
		// }
		/* 默认单元格样式 */
		HSSFCellStyle normalStyle = workbook.createCellStyle();
		normalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		normalStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		normalStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		HSSFCellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		/* 字体 */
		HSSFFont normalfont = workbook.createFont();
		normalfont.setFontHeightInPoints((short) 10);
		normalfont.setFontName("宋体");
		normalStyle.setFont(normalfont);
		headerStyle.setFont(normalfont);
		/* 表格内容 */
		// 第一行内容
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 400);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, result.getData().get(0).getData().size() - 1));
		HSSFCell ce1_1 = row.createCell(0);
		ce1_1.setCellValue(result.getTitle());
		ce1_1.setCellStyle(headerStyle);
		// 主要内容
		for (int y = 0; y < result.getData().size(); y++) {
			HSSFRow row_y = sheet.createRow(y + 1);
			if (y == 0) {
				row_y.setHeight((short) 1000);
			} else {
				row_y.setHeight((short) 400);
			}
			for (int x = 0; x < result.getData().get(0).getData().size(); x++) {
				HSSFCell ce_y_x = row_y.createCell(x);
				if (y == 0) {
					String str = result.getData().get(y).getData().get(x).replaceAll(",", "\n");
					ce_y_x.setCellValue(str);
				} else {
					ce_y_x.setCellValue(result.getData().get(y).getData().get(x));
				}
				ce_y_x.setCellStyle(normalStyle);
			}
		}
		downloadExcel(result.getTitle(), workbook, response);
	}

	public static void createSyllabusExcelByTeacher(SyllabusInfoDTO syllabusInfo, HttpServletResponse response)
			throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("任课老师课表");
		sheet.setColumnWidth(0, 1000);
		sheet.setColumnWidth(1, 3800);
		sheet.setColumnWidth(2, 3800);
		sheet.setColumnWidth(3, 3800);
		sheet.setColumnWidth(4, 3800);
		sheet.setColumnWidth(5, 3800);
		sheet.setColumnWidth(6, 3800);
		sheet.setColumnWidth(7, 3800);
		/* 默认单元格样式 */
		/* 水平居中，垂直居中 */		
		HSSFCellStyle normalStyle = workbook.createCellStyle();
		// 水平对齐
		normalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 垂直对齐
		normalStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 自动换行
		normalStyle.setWrapText(true);
		// 边框
		normalStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		/* 右对齐，垂直居中 */
		HSSFCellStyle rightStyle = workbook.createCellStyle();
		rightStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		normalStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		normalStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		/* 字体 */
		HSSFFont normalFont = workbook.createFont();
		normalFont.setFontHeightInPoints((short) 10);
		normalFont.setFontName("宋体");

		normalStyle.setFont(normalFont);
		rightStyle.setFont(normalFont);	
		/* 表格内容 */
		HSSFRow row1 = sheet.createRow(0);
		row1.setHeight((short) 400);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
		HSSFCell c1_1 = row1.createCell(0);
		c1_1.setCellStyle(rightStyle);
		c1_1.setCellValue(syllabusInfo.getTitle());
		
		HSSFRow row2 = sheet.createRow(1);
		row2.setHeight((short) 500);
		HSSFCell c2_1 = row2.createCell(0);
		HSSFCell c2_2 = row2.createCell(1);
		HSSFCell c2_3 = row2.createCell(2);
		HSSFCell c2_4 = row2.createCell(3);
		HSSFCell c2_5 = row2.createCell(4);
		HSSFCell c2_6 = row2.createCell(5);
		HSSFCell c2_7 = row2.createCell(6);
		HSSFCell c2_8 = row2.createCell(7);
		c2_1.setCellStyle(normalStyle);
		c2_2.setCellStyle(normalStyle);
		c2_2.setCellValue("一");
		c2_3.setCellStyle(normalStyle);
		c2_3.setCellValue("二");
		c2_4.setCellStyle(normalStyle);
		c2_4.setCellValue("三");
		c2_5.setCellStyle(normalStyle);
		c2_5.setCellValue("四");
		c2_6.setCellStyle(normalStyle);
		c2_6.setCellValue("五");
		c2_7.setCellStyle(normalStyle);
		c2_7.setCellValue("六");
		c2_8.setCellStyle(normalStyle);
		c2_8.setCellValue("日");
		
		for (int i = 3; i < 7; i++) {
			HSSFRow row_i = sheet.createRow(i - 1);
			row_i.setHeight((short) 850);
			HSSFCell c_i_0 = row_i.createCell(0);
			c_i_0.setCellStyle(normalStyle);
			c_i_0.setCellValue(i - 2);
			for (int j = 1; j < 8; j++) {
				HSSFCell c_i_j = row_i.createCell(j);
				c_i_j.setCellStyle(normalStyle);
				for (int m = 0; m < syllabusInfo.getSyllabusInfo1s().size(); m++) {
					for (ClassHour classHour : syllabusInfo.getSyllabusInfo1s().get(m).getClassHours()) {
						if (classHour.getDay() == j && classHour.getHour() == i - 2) {
							c_i_j.setCellValue(syllabusInfo.getSyllabusInfo1s().get(m).getCourse().getCourseName());
						}
					}
				}
			}
		}
		HSSFRow row_7 = sheet.createRow(6);
		row_7.setHeight((short) 200);
		for (int i = 8; i < 11; i++) {
			HSSFRow row_i = sheet.createRow(i - 1);
			row_i.setHeight((short) 850);
			HSSFCell c_i_0 = row_i.createCell(0);
			c_i_0.setCellStyle(normalStyle);
			c_i_0.setCellValue(i - 3);
			for (int j = 1; j < 8; j++) {
				HSSFCell c_i_j = row_i.createCell(j);
				c_i_j.setCellStyle(normalStyle);
				for (int m = 0; m < syllabusInfo.getSyllabusInfo1s().size(); m++) {
					for (ClassHour classHour : syllabusInfo.getSyllabusInfo1s().get(m).getClassHours()) {
						if (classHour.getDay() == j && classHour.getHour() == i - 3) {
							c_i_j.setCellValue(syllabusInfo.getSyllabusInfo1s().get(m).getCourse().getCourseName());
						}
					}
				}
			}

		}
		HSSFRow row11 = sheet.createRow(10);
		row11.setHeight((short) 200);
		for (int i = 12; i < 14; i++) {
			HSSFRow row_i = sheet.createRow(i - 1);
			row_i.setHeight((short) 850);
			HSSFCell c_i_0 = row_i.createCell(0);
			c_i_0.setCellStyle(normalStyle);
			c_i_0.setCellValue(i - 4);
			for (int j = 1; j < 8; j++) {
				HSSFCell c_i_j = row_i.createCell(j);
				c_i_j.setCellStyle(normalStyle);

				for (int m = 0; m < syllabusInfo.getSyllabusInfo1s().size(); m++) {
					for (ClassHour classHour : syllabusInfo.getSyllabusInfo1s().get(m).getClassHours()) {
						if (classHour.getDay() == j && classHour.getHour() == i - 4) {
							c_i_j.setCellValue(syllabusInfo.getSyllabusInfo1s().get(m).getCourse().getCourseName());
						}
					}
				}

			}

		}
		downloadExcel("aaa", workbook, response);
	}

	public static void createSyllabusExcelByStudent(SyllabusInfoDTO syllabusInfo, HttpServletResponse response)
			throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("学生课表");
		sheet.setColumnWidth(0, 1000);
		sheet.setColumnWidth(1, 3800);
		sheet.setColumnWidth(2, 3800);
		sheet.setColumnWidth(3, 3800);
		sheet.setColumnWidth(4, 3800);
		sheet.setColumnWidth(5, 3800);
		sheet.setColumnWidth(6, 3800);
		sheet.setColumnWidth(7, 3800);
		/* 默认单元格样式 */
		/* 水平居中，垂直居中 */
		HSSFCellStyle normalStyle = workbook.createCellStyle();
		// 水平对齐
		normalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 垂直对齐
		normalStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 自动换行
		normalStyle.setWrapText(true);
		// 边框
		normalStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		/* 右对齐，垂直居中 */
		HSSFCellStyle rightStyle = workbook.createCellStyle();
		rightStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		normalStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		normalStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		/* 字体 */
		HSSFFont normalFont = workbook.createFont();
		normalFont.setFontHeightInPoints((short) 10);
		normalFont.setFontName("宋体");

		normalStyle.setFont(normalFont);
		rightStyle.setFont(normalFont);

		/* 表格内容 */
		HSSFRow row1 = sheet.createRow(0);
		row1.setHeight((short) 400);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
		HSSFCell c1_1 = row1.createCell(0);
		c1_1.setCellStyle(rightStyle);
		c1_1.setCellValue(syllabusInfo.getTitle());

		HSSFRow row2 = sheet.createRow(1);
		row2.setHeight((short) 500);
		HSSFCell c2_1 = row2.createCell(0);
		HSSFCell c2_2 = row2.createCell(1);
		HSSFCell c2_3 = row2.createCell(2);
		HSSFCell c2_4 = row2.createCell(3);
		HSSFCell c2_5 = row2.createCell(4);
		HSSFCell c2_6 = row2.createCell(5);
		HSSFCell c2_7 = row2.createCell(6);
		HSSFCell c2_8 = row2.createCell(7);
		c2_1.setCellStyle(normalStyle);
		c2_2.setCellStyle(normalStyle);
		c2_2.setCellValue("一");
		c2_3.setCellStyle(normalStyle);
		c2_3.setCellValue("二");
		c2_4.setCellStyle(normalStyle);
		c2_4.setCellValue("三");
		c2_5.setCellStyle(normalStyle);
		c2_5.setCellValue("四");
		c2_6.setCellStyle(normalStyle);
		c2_6.setCellValue("五");
		c2_7.setCellStyle(normalStyle);
		c2_7.setCellValue("六");
		c2_8.setCellStyle(normalStyle);
		c2_8.setCellValue("日");

		for (int i = 3; i < 7; i++) {
			HSSFRow row_i = sheet.createRow(i - 1);
			row_i.setHeight((short) 850);
			HSSFCell c_i_0 = row_i.createCell(0);
			c_i_0.setCellStyle(normalStyle);
			c_i_0.setCellValue(i - 2);
			for (int j = 1; j < 8; j++) {
				HSSFCell c_i_j = row_i.createCell(j);
				c_i_j.setCellStyle(normalStyle);
				for (int m = 0; m < syllabusInfo.getSyllabusInfo1s().size(); m++) {
					for (ClassHour classHour : syllabusInfo.getSyllabusInfo1s().get(m).getClassHours()) {
						if (classHour.getDay() == j && classHour.getHour() == i - 2) {
							c_i_j.setCellValue(syllabusInfo.getSyllabusInfo1s().get(m).getCourse().getCourseName()
									+ syllabusInfo.getSyllabusInfo1s().get(m).getTeacher().getBaseInfo().getName());
						}
					}
				}
			}
		}
		HSSFRow row_7 = sheet.createRow(6);
		row_7.setHeight((short) 200);
		for (int i = 8; i < 11; i++) {
			HSSFRow row_i = sheet.createRow(i - 1);
			row_i.setHeight((short) 850);
			HSSFCell c_i_0 = row_i.createCell(0);
			c_i_0.setCellStyle(normalStyle);
			c_i_0.setCellValue(i - 3);
			for (int j = 1; j < 8; j++) {
				HSSFCell c_i_j = row_i.createCell(j);
				c_i_j.setCellStyle(normalStyle);
				for (int m = 0; m < syllabusInfo.getSyllabusInfo1s().size(); m++) {
					for (ClassHour classHour : syllabusInfo.getSyllabusInfo1s().get(m).getClassHours()) {
						if (classHour.getDay() == j && classHour.getHour() == i - 3) {
							c_i_j.setCellValue(syllabusInfo.getSyllabusInfo1s().get(m).getCourse().getCourseName()
									+ syllabusInfo.getSyllabusInfo1s().get(m).getTeacher().getBaseInfo().getName());
						}
					}
				}
			}

		}
		HSSFRow row11 = sheet.createRow(10);
		row11.setHeight((short) 200);
		for (int i = 12; i < 14; i++) {
			HSSFRow row_i = sheet.createRow(i - 1);
			row_i.setHeight((short) 850);
			HSSFCell c_i_0 = row_i.createCell(0);
			c_i_0.setCellStyle(normalStyle);
			c_i_0.setCellValue(i - 4);
			for (int j = 1; j < 8; j++) {
				HSSFCell c_i_j = row_i.createCell(j);
				c_i_j.setCellStyle(normalStyle);

				for (int m = 0; m < syllabusInfo.getSyllabusInfo1s().size(); m++) {
					for (ClassHour classHour : syllabusInfo.getSyllabusInfo1s().get(m).getClassHours()) {
						if (classHour.getDay() == j && classHour.getHour() == i - 4) {
							c_i_j.setCellValue(syllabusInfo.getSyllabusInfo1s().get(m).getCourse().getCourseName()
									+ syllabusInfo.getSyllabusInfo1s().get(m).getTeacher().getBaseInfo().getName());
						}
					}
				}

			}

		}
		downloadExcel("aaa", workbook, response);
	}

	private static void downloadExcel(String title, HSSFWorkbook workbook, HttpServletResponse response)
			throws IOException {
		/* 生成excel */
		OutputStream out = response.getOutputStream();
		response.reset();
		response.setContentType("application/x-msdownload");
		String pName = title;
		response.setHeader("Content-Disposition",
				"attachment; filename=" + new String(pName.getBytes("gb2312"), "ISO-8859-1") + ".xls");
		try {
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	public static void createExcelByCourse(ResultsDTOByCourses result, HttpServletResponse response)
			throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("预选结果");
		sheet.setColumnWidth(0, 4000);
		/* 默认单元格样式 */
		HSSFCellStyle normalStyle = workbook.createCellStyle();
		normalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		normalStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		normalStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		normalStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		HSSFCellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		/* 字体 */
		HSSFFont normalfont = workbook.createFont();
		normalfont.setFontHeightInPoints((short) 10);
		normalfont.setFontName("宋体");
		normalStyle.setFont(normalfont);
		headerStyle.setFont(normalfont);
		/* 表格内容 */
		// 第一行内容
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 400);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, result.getData().get(0).getData().size() - 1));
		HSSFCell ce1_1 = row.createCell(0);
		ce1_1.setCellValue(result.getTitle());
		ce1_1.setCellStyle(headerStyle);
		// 主要内容
		for (int y = 0; y < result.getData().size(); y++) {
			HSSFRow row_y = sheet.createRow(y + 1);
			row_y.setHeight((short) 400);
			for (int x = 0; x < result.getData().get(0).getData().size(); x++) {
				HSSFCell ce_y_x = row_y.createCell(x);
				if (y == 0) {
					String str = result.getData().get(y).getData().get(x);
					ce_y_x.setCellValue(str);
				} else {
					ce_y_x.setCellValue(result.getData().get(y).getData().get(x));
				}
				ce_y_x.setCellStyle(normalStyle);
			}
		}
		downloadExcel(result.getTitle(), workbook, response);
	}

}
