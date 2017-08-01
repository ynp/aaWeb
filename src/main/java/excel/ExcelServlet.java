package excel;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Servlet implementation class ExcelServlet
 */
@WebServlet(name = "ExcelServlet", urlPatterns = "/excel")
public class ExcelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = "test";
        String[] rowsName = new String[] { "序号", "货物运输批次号", "提运单号", "状态", "录入人", "录入时间" };
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        for (int i = 0; i < 5; i++) {
            objs = new Object[6];
            objs[0] = i;
            objs[1] = "xxxx " + i;
            objs[2] = "yyyy " + i;
            objs[3] = "zzzz " + i;
            objs[4] = "dddd " + i;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(new Date());
            objs[5] = date;
            dataList.add(objs);
        }
        ExportExcel ex = new ExportExcel(title, rowsName, dataList);

        HSSFWorkbook workbook = ex.export();

        if (workbook != null) {
            String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
            String headStr = "attachment; filename=\"" + fileName + "\"";
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", headStr);
            // OutputStream out = response.getOutputStream();
            // workbook.write(out);

            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            bufferedOutPut.flush();
            workbook.write(bufferedOutPut);
            bufferedOutPut.close();
        }
    }

}
