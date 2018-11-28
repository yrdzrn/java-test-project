package com.sust.farmhousemanage;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TestJTable {
	public static void main(String[] args) {
		JFrame f = new JFrame();
//		f.setLayout(null);
		
		
		TableModel tm = new DefaultTableModel(
				new String[][]{{"1","2"},{"3","4"}},
				new String[]{"col1","col2"});
		JTable table = new JTable();
		table.setModel(tm);
		JScrollPane jsp = new JScrollPane(table);
		
		f.add(jsp);
		
		f.setBounds(10,10,400,300);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
