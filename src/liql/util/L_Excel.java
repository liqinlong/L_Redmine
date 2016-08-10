package liql.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import liql.redmine.RedmineRowData;

public class L_Excel {
	public static void WriteExcel_Redmine(String outfile, LinkedList<RedmineRowData> dataList) throws WriteException, IOException {
		if (dataList == null || dataList.isEmpty())
			return;
		Workbook wb = null;
		WritableWorkbook wwb = null;

		try {

			File fout = new File(outfile);
			OutputStream os = new FileOutputStream(fout);
			fout.createNewFile();
			wwb = Workbook.createWorkbook(os);

			// 单元头内容
			WritableFont wfCellTitle = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wcfFCCellTitle = new WritableCellFormat(wfCellTitle);
			wcfFCCellTitle.setAlignment(Alignment.CENTRE);
			wcfFCCellTitle.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfFCCellTitle.setBackground(Colour.BRIGHT_GREEN);
			wcfFCCellTitle.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			// 单元格内容
			WritableFont wfCellContent = new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD, false);
			WritableCellFormat wcfFCCellContentA = new WritableCellFormat(wfCellContent);
			wcfFCCellContentA.setAlignment(Alignment.LEFT);
			wcfFCCellContentA.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfFCCellContentA.setBackground(Colour.WHITE);
			wcfFCCellContentA.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			wcfFCCellContentA.setWrap(true);

			WritableSheet ws = wwb.createSheet("第一页", 0);
			ws.getSettings().setVerticalFreeze(1);
			ws.setColumnView(0, 30);
			ws.setColumnView(1, 10);
			ws.setColumnView(2, 50);
			ws.setColumnView(3, 10);
			ws.setColumnView(4, 10);
			ws.setColumnView(5, 10);
			ws.setColumnView(6, 10);
			ws.setColumnView(7, 10);
			ws.setColumnView(8, 15);
			ws.setColumnView(9, 15);
			ws.setColumnView(10, 20);
			ws.setColumnView(11, 20);
			ws.setColumnView(12, 100);
			
			
			Label label = null;

			for (int i = 0; i < 1; i++) {// st.length fix == 1
				int row = dataList.size();

				label = new Label(0, 0, "所属项目", wcfFCCellTitle);
				ws.addCell(label);
				label = new Label(1, 0, "任务ID", wcfFCCellTitle);
				ws.addCell(label);
				label = new Label(2, 0, "主题", wcfFCCellTitle);
				ws.addCell(label);
				label = new Label(3, 0, "跟踪", wcfFCCellTitle);
				ws.addCell(label);
				label = new Label(4, 0, "状态", wcfFCCellTitle);
				ws.addCell(label);
				label = new Label(5, 0, "优先级", wcfFCCellTitle);
				ws.addCell(label);
				label = new Label(6, 0, "创建人", wcfFCCellTitle);
				ws.addCell(label);
				label = new Label(7, 0, "指派人", wcfFCCellTitle);
				ws.addCell(label);
				label = new Label(8, 0, "创建日期", wcfFCCellTitle);
				ws.addCell(label);
				label = new Label(9, 0, "开始日期", wcfFCCellTitle);
				ws.addCell(label);
				label = new Label(10, 0, "最后更新时间", wcfFCCellTitle);
				ws.addCell(label);
				label = new Label(11, 0, "计划完成日期", wcfFCCellTitle);
				ws.addCell(label);
				label = new Label(12, 0, "描述", wcfFCCellTitle);
				ws.addCell(label);

				for (int j = 0; j < row; j++) {
					RedmineRowData rowData = dataList.get(j);
					label = new Label(0, j + 1, rowData.getPro_name(), wcfFCCellContentA);
					ws.addCell(label);
					label = new Label(1, j + 1, String.valueOf(rowData.getIssue_id()), wcfFCCellContentA);
					ws.addCell(label);
					label = new Label(2, j + 1, rowData.getSubject(), wcfFCCellContentA);
					ws.addCell(label);
					label = new Label(3, j + 1, rowData.getTracker_name(), wcfFCCellContentA);
					ws.addCell(label);
					label = new Label(4, j + 1, rowData.getStatus(), wcfFCCellContentA);
					ws.addCell(label);
					label = new Label(5, j + 1, rowData.getPriority(), wcfFCCellContentA);
					ws.addCell(label);
					label = new Label(6, j + 1, rowData.getAuthor(), wcfFCCellContentA);
					ws.addCell(label);
					label = new Label(7, j + 1, rowData.getAssignee(), wcfFCCellContentA);
					ws.addCell(label);
					label = new Label(8, j + 1, rowData.getCreate_time(), wcfFCCellContentA);
					ws.addCell(label);
					label = new Label(9, j + 1, rowData.getStart_time(), wcfFCCellContentA);
					ws.addCell(label);
					label = new Label(10, j + 1, rowData.getUpdate_time(), wcfFCCellContentA);
					ws.addCell(label);
					label = new Label(11, j + 1, rowData.getDue_time(), wcfFCCellContentA);
					ws.addCell(label);
					label = new Label(12, j + 1, rowData.getDesc(), wcfFCCellContentA);
					ws.addCell(label);
				}
			}
			wwb.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (wb != null)
				wb.close();
			if (wwb != null)
				wwb.close();
		}
	}
}
