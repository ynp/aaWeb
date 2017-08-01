package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
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

public class SummaryHSSF2 {

	public static void main(String[] args) throws IOException {
		//创建Workbook对象（这一个对象代表着对应的一个Excel文件）
                     //HSSFWorkbook表示以xls为后缀名的文件
		Workbook wb = new HSSFWorkbook();
		//获得CreationHelper对象,这个应该是一个帮助类
		CreationHelper helper = wb.getCreationHelper();
		//创建Sheet并给名字(表示Excel的一个Sheet)
		Sheet sheet1 = wb.createSheet("HSSF_Sheet_1");		
		//Row表示一行Cell表示一列
		Row row = null;
		Cell cell = null;
		//表头样式
		CellStyle columnTopStyle = getColumnTopStyle(wb);
		
		// 将列头设置到sheet的单元格中
		row = sheet1.createRow(0);
		//设置表头高度
		row.setHeightInPoints(35);
		for (int n = 0; n < 25; n++) {
			cell = row.createCell(n); // 创建列头对应个数的单元格
			//设置每个sheet每一行的宽度,自动,根据需求自行确定
			sheet1.autoSizeColumn(n+1, true);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING); // 设置列头单元格的数据类型
//			HSSFRichTextString text = new HSSFRichTextString("测试 " + n);
			cell.setCellValue("测试 " + n); // 设置列头单元格的值
			cell.setCellStyle(columnTopStyle); // 设置列头单元格样式
		}
		
		for(int i=1;i<60;i++){
			//获得这个sheet的第i行
			row = sheet1.createRow(i);
			//设置行长度自动			
			row.setHeightInPoints(20);
			for(int j=0;j<25;j++){		
				cell = row.createCell(j);
				cell.setCellValue(22.23);
			}
		}
		//输出
		OutputStream os = new FileOutputStream(new File("d://SummaryHSSF.xls"));
		wb.write(os);
		os.close();		
	}
	

	/*
	 * 列头单元格样式
	 */
	public static CellStyle getColumnTopStyle(Workbook workbook) {

		// 设置字体
		Font font = workbook.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) 11);
		// 字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("Courier New");
		
		//设置边框
		CellStyle cellStyle = workbook.createCellStyle();
		/*
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		*/
		
		// 在样式用应用设置的字体;
		cellStyle.setFont(font);
		// 设置自动换行;
		cellStyle.setWrapText(false);
//		// 设置水平对齐的样式为居中对齐;
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		return cellStyle;

	}
}
