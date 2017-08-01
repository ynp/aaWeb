package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class SummaryHSSF {

	public static void main(String[] args) throws IOException {
		//创建Workbook对象（这一个对象代表着对应的一个Excel文件）
                     //HSSFWorkbook表示以xls为后缀名的文件
		Workbook wb = new HSSFWorkbook();
		//获得CreationHelper对象,这个应该是一个帮助类
		CreationHelper helper = wb.getCreationHelper();
		//创建Sheet并给名字(表示Excel的一个Sheet)
		Sheet sheet1 = wb.createSheet("HSSF_Sheet_1");		
		Sheet sheet2 = wb.createSheet("HSSF_Sheet_2");
		//Row表示一行Cell表示一列
		Row row = null;
		Cell cell = null;
		for(int i=0;i<60;i=i+2){
			//获得这个sheet的第i行
			row = sheet1.createRow(i);
			//设置行长度自动			
			//row.setHeight((short)500);
			row.setHeightInPoints(20);
			//row.setZeroHeight(true);
			for(int j=0;j<25;j++){		
				//设置每个sheet每一行的宽度,自动,根据需求自行确定
				sheet1.autoSizeColumn(j+1, true);
				//创建一个基本的样式
				CellStyle cellStyle = SummaryHSSF.createStyleCell(wb);
				//获得这一行的每j列
				cell = row.createCell(j);
				if(j==0){
					//设置文字在单元格里面的位置
					cellStyle = SummaryHSSF.setCellStyleAlignment(cellStyle, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
					//先创建字体样式,并把这个样式加到单元格的字体里面
					cellStyle.setFont(createFonts(wb));
					//把这个样式加到单元格里面
					cell.setCellStyle(cellStyle);					
					//给单元格设值
					cell.setCellValue(true);
				}else if(j==1){
					//设置文字在单元格里面的位置
					cellStyle = SummaryHSSF.setCellStyleAlignment(cellStyle, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
					//设置这个样式的格式(Format)
					cellStyle = SummaryHSSF.setCellFormat(helper,cellStyle, "#,##0.0000");					
					//先创建字体样式,并把这个样式加到单元格的字体里面
					cellStyle.setFont(createFonts(wb));
					//把这个样式加到单元格里面
					cell.setCellStyle(cellStyle);
					//给单元格设值
					cell.setCellValue(new Double(2008.2008));
				}else if(j==2){
					cellStyle = SummaryHSSF.setCellStyleAlignment(cellStyle, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);					
					cellStyle.setFont(createFonts(wb));
					cell.setCellStyle(cellStyle);
					cell.setCellValue(helper.createRichTextString("RichString"+i+j));					
				}else if(j==3){
					cellStyle = SummaryHSSF.setCellStyleAlignment(cellStyle, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
					cellStyle = SummaryHSSF.setCellFormat(helper,cellStyle, "MM-yyyy-dd");
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new Date());
				}else if(j==24){
					cellStyle = SummaryHSSF.setCellStyleAlignment(cellStyle, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
					cellStyle.setFont(createFonts(wb));
					//设置公式
					cell.setCellFormula("SUM(E"+(i+1)+":X"+(i+1)+")");					
				}else{					
					cellStyle = SummaryHSSF.setCellStyleAlignment(cellStyle, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
					cellStyle = SummaryHSSF.setFillBackgroundColors(cellStyle,IndexedColors.ORANGE.getIndex(),IndexedColors.ORANGE.getIndex(),CellStyle.SOLID_FOREGROUND);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(1);
				}
			}
		}
		//输出
		OutputStream os = new FileOutputStream(new File("d://SummaryHSSF.xls"));
		wb.write(os);
		os.close();		
	}
	/**
	 * 边框
	 * @param wb
	 * @return
	 */
	public static CellStyle createStyleCell(Workbook wb){
		CellStyle cellStyle = wb.createCellStyle();
		//设置一个单元格边框颜色
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		//设置一个单元格边框颜色
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());		
		return cellStyle;
	}
	/**
	 * 设置文字在单元格里面的位置
	 * CellStyle.ALIGN_CENTER
	 * CellStyle.VERTICAL_CENTER
	 * @param cellStyle
	 * @param halign
	 * @param valign
	 * @return
	 */
	public static CellStyle setCellStyleAlignment(CellStyle cellStyle,short halign,short valign){
		//设置上下
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		//设置左右
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		return cellStyle;
	}
	/**
	 * 格式化单元格
	 * 如#,##0.00,m/d/yy去HSSFDataFormat或XSSFDataFormat里面找
	 * @param cellStyle
	 * @param fmt
	 * @return
	 */
	public static CellStyle setCellFormat(CreationHelper helper,CellStyle cellStyle,String fmt){
		//还可以用其它方法创建format
		cellStyle.setDataFormat(helper.createDataFormat().getFormat(fmt));
		return cellStyle;
	}
	/**
	 * 前景和背景填充的着色
	 * @param cellStyle
	 * @param bg IndexedColors.ORANGE.getIndex();
	 * @param fg IndexedColors.ORANGE.getIndex();
	 * @param fp CellStyle.SOLID_FOREGROUND
	 * @return
	 */
	public static CellStyle setFillBackgroundColors(CellStyle cellStyle,short bg,short fg,short fp){
		//cellStyle.setFillBackgroundColor(bg);
		cellStyle.setFillForegroundColor(fg);
		cellStyle.setFillPattern(fp);
		return cellStyle;
	}
	/**
	 * 设置字体
	 * @param wb
	 * @return
	 */
	public static Font createFonts(Workbook wb){
		//创建Font对象
		Font font = wb.createFont();
		//设置字体
		font.setFontName("黑体");
		//着色
		font.setColor(HSSFColor.BLUE.index);
		//斜体
		font.setItalic(true);
		//字体大小
		font.setFontHeight((short)300);
		return font;
	}
}
