package com.pageobjectExtractor.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class UIDesign {

    public static void button_ui_style_1(JButton btn, String btnName){
//        btn.setBackground(new Color(0, 122, 181));
        btn.setText(btnName);
        btn.setFocusPainted(false);
    }

    public static void table_ui_style_blue(JTable jTable){
        TableColumnModel columnModel= jTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80);
        columnModel.getColumn(1).setPreferredWidth(250);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(500);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable.setDefaultRenderer(String.class, centerRenderer);

        jTable.setAutoCreateRowSorter(true);
        jTable.setFont(new Font("Tahoma", 0, 16));
        jTable.setFocusable(false);
        jTable.setIntercellSpacing(new Dimension(0, 0));
        jTable.setRowHeight(35);
        jTable.setShowVerticalLines(true);
        jTable.getTableHeader().setFont(new Font("Tahoma", 0, 16));
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.getTableHeader().setOpaque(false);
        jTable.getTableHeader().setBackground(new Color(32,136,203));
        jTable.getTableHeader().setForeground(new Color(255,255,255));
    }
}
