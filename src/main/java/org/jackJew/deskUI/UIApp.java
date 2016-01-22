package org.jackJew.deskUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class UIApp {

	public static void main(String args[]) {
		JFrame frame = new JFrame("Java窗口程序");
		String[] titles = { "姓名", "年龄", "性别" }; // 表格列头标题
		Object[][] userInfo = { { "小强", 22, "男" }, { "小明", 23, "女" }, { "小华", 24, "男" } };
		
		JTable table = new JTable(userInfo, titles); // 建立表格
		table.setEnabled(false);
		JScrollPane scr = new JScrollPane(table);
		
		frame.add(scr); // 将JScrollPanel添加进窗口
		frame.setSize(500, 300);
		frame.setLocation(350, 150);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}