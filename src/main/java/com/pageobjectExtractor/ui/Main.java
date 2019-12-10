package com.pageobjectExtractor.ui;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pageobjectExtractor.utils.Generic;
import com.pageobjectExtractor.utils.GlobalConstants;
import com.pageobjectExtractor.utils.UIDesign;
import javafx.application.Platform;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Bharath Kumar Reddy
 */
public class Main extends javax.swing.JFrame {

    public static DefaultTableModel tblRecordmodel = new DefaultTableModel();

    JSONArray objects = new JSONArray();
    boolean recordFlag = false;
    public static WebBrowserRecorder webBrowser;
    public static PageElements pageElements;

    public static String selected_table_row_objName = null;
    public static String selected_table_row_objType = null;
    public static String selected_table_row_objPath = null;
    public static int selected_row = 0;

    public Main() {
        initComponents();
    }

    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblMinimize = new javax.swing.JLabel();
        lblMaximize = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        txtEnterUrl = new javax.swing.JLabel();
        inpuUrl = new javax.swing.JTextField();
        txtPageName = new javax.swing.JLabel();
        inputPageName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPageObjects = new javax.swing.JTable();
        btnFind = new javax.swing.JButton();
        inputSearch = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        imgCignitiLogo = new javax.swing.JLabel();
        btnRecord = new javax.swing.JButton();
        btnCapture = new javax.swing.JButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2,new Color(0,122,181) ));

        pnlHeader.setBackground(new java.awt.Color(0, 122, 181));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Page Object Extractor");

        lblMinimize.setForeground(new java.awt.Color(255, 255, 255));
        lblMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus_18px.png")));
        lblMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblMinimizeMousePressed(evt);
            }
        });

        lblMaximize.setForeground(new java.awt.Color(255, 255, 255));
        lblMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/rectangle_stroked_18px.png")));
        lblMaximize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblMaximizeMousePressed(evt);
            }
        });

        lblClose.setForeground(new java.awt.Color(255, 255, 255));
        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/multiply_18px.png")));
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblCloseMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
                pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlHeaderLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblMaximize, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
                pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblMaximize, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel1))
                                .addGap(25, 25, 25))
        );

        txtEnterUrl.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtEnterUrl.setText("Enter Url: ");

        inpuUrl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)));

        txtPageName.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtPageName.setText("Page Name:");

        inputPageName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)));

//        tblPageObjects.setModel(new javax.swing.table.DefaultTableModel(
//                new Object [][] {
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null}
//                },
//                new String [] {
//                        "Title 1", "Title 2", "Title 3", "Title 4"
//                }
//        ));

        tblRecordmodel.addColumn("Sno");
        tblRecordmodel.addColumn("Object Name");
        tblRecordmodel.addColumn("Object Type");
        tblRecordmodel.addColumn("xpath");
        tblPageObjects.setModel(tblRecordmodel);

        jScrollPane1.setViewportView(tblPageObjects);

        UIDesign.table_ui_style_blue(tblPageObjects);
        tblPageObjects.getSelectionModel().addListSelectionListener(new SelectionListener());


        btnFind.setFont(new java.awt.Font("Calibri", 1, 15));
//        btnFind.setForeground(new java.awt.Color(0, 153, 102));
        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/detective_26px.png")));
        btnFind.setFocusPainted(false);
        btnFind.setBackground(new Color(240, 240, 240));
        btnFind.setText("Find");
//        btnFind.setToolTipText("");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        inputSearch.setText("jTextField1");
        inputSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 153, 0)));

        inputSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputSearchKeyReleased(evt);
            }
        });
        inputSearch.setFont(new java.awt.Font("Tahoma", 0, 16));
        inputSearch.setForeground(new java.awt.Color(127 , 127, 127));
        inputSearch.setText("  Search with Object Name");
        inputSearch.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                inputSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                if(inputSearch.getText().trim().contains("Search with Object Name")){
                    inputSearch.setText("");
                }
            }
            public void focusLost(FocusEvent e) {
                if(StringUtils.isEmpty(inputSearch.getText())){
                    inputSearch.setText("  Search with Object Name");
                }
            }

        });


        btnDelete.setFont(new java.awt.Font("Calibri", 1, 15));
//        btnDelete.setForeground(new java.awt.Color(0, 153, 102));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete_bin_26px.png")));
        btnDelete.setFocusPainted(false);
        btnDelete.setBackground(new Color(240, 240, 240));
        btnDelete.setText("New");
        btnDelete.setText("Delete");
//        btnDelete.setToolTipText("");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });


        btnSave.setFont(new java.awt.Font("Calibri", 1, 15));
//        btnSave.setForeground(new java.awt.Color(0, 153, 102));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save_close_26px.png")));
        btnSave.setFocusPainted(false);
        btnSave.setBackground(new Color(240, 240, 240));
        btnSave.setText("Save");
//        btnSave.setToolTipText("");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        imgCignitiLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Cigniti_logo.png")));


        btnRecord.setFont(new java.awt.Font("Calibri", 1, 15));
        btnRecord.setForeground(new java.awt.Color(237, 27, 37));
        btnRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/record_28.png")));
        btnRecord.setFocusPainted(false);
        btnRecord.setBackground(new Color(240, 240, 240));
        btnRecord.setText("Record");
     //   btnRecord.setToolTipText("");
        btnRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecordActionPerformed(evt);
            }
        });


        btnCapture.setFont(new java.awt.Font("Calibri", 1, 15));
        btnCapture.setForeground(new java.awt.Color(63, 72, 204));
        btnCapture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/screenshot_26px.png")));
        btnCapture.setFocusPainted(false);
        btnCapture.setBackground(new Color(240, 240, 240));
        btnCapture.setText("Capture");
//        btnCapture.setToolTipText("");
        btnCapture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaptureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(txtPageName)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(inputPageName, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(329, 329, 329))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(txtEnterUrl)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(btnRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(49, 49, 49)
                                                                                .addComponent(btnCapture, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(inpuUrl)
                                                                                .addGap(20, 20, 20))))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(98, 98, 98)
                                                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(57, 57, 57)
                                                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(imgCignitiLogo)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtEnterUrl)
                                        .addComponent(inpuUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCapture, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtPageName)
                                        .addComponent(inputPageName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(36, 36, 36))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(imgCignitiLogo)
                                                .addGap(22, 22, 22))))
        );

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void lblMinimizeMousePressed(java.awt.event.MouseEvent evt) {
        this.setState(Frame.ICONIFIED);
    }

    private void lblMaximizeMousePressed(java.awt.event.MouseEvent evt) {
        if (this.getExtendedState() == MAXIMIZED_BOTH) {
            this.setExtendedState(JFrame.NORMAL);
        } else {
            this.setExtendedState(MAXIMIZED_BOTH);
        }
    }

    private void lblCloseMousePressed(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }

    private void btnRecordActionPerformed(java.awt.event.ActionEvent evt) {
        GlobalConstants.BASE_URL = inpuUrl.getText();


        if(verifyURL(GlobalConstants.BASE_URL)) {
            if (recordFlag) {
                recordFlag = false;
                btnRecord.setForeground(new java.awt.Color(222, 31, 38));
                btnRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/record_28.png")));
                btnRecord.setFocusPainted(false);
                btnRecord.setBackground(new Color(240, 240, 240));
                btnRecord.setText("Record");

                try {
                    Platform.exit();
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                Thread t = new Thread() {
                    public void run() {
                        webBrowser = new WebBrowserRecorder();
                        webBrowser.browserLaunch(GlobalConstants.BASE_URL);

                    }
                };
                t.start();
                recordFlag = true;
                btnRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/record_Stop_28.png")));
                btnRecord.setForeground(new java.awt.Color(34, 177, 76));
                btnRecord.setBackground(new Color(214, 248, 224));
                btnRecord.setText("Stop");
                // flashColor(btnRecord,new Color(34, 177, 76));


            }
        } else{
            JOptionPane.showMessageDialog(null, "Please enter correct url...", "Warning" , JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnCaptureActionPerformed(java.awt.event.ActionEvent evt) {
        GlobalConstants.BASE_URL = inpuUrl.getText();

        if(verifyURL(GlobalConstants.BASE_URL)) {
            btnCapture.setEnabled(false);
            pageElements=new PageElements();
            pageElements.loadInit();
            pageElements.setVisible(true);

        } else{
            JOptionPane.showMessageDialog(null, "Please enter correct url...", "Warning" , JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {
        webBrowser.hilightElement(selected_table_row_objPath);
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        tblRecordmodel.removeRow(selected_row);
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        String fileFolderPath = Generic.choosefolderPath();
        objects.clear();
        int rowCount = tblRecordmodel.getRowCount();
        for(int k=0;k<rowCount;k++){
            objects.add(getObjectInfo(tblRecordmodel.getValueAt(k,1).toString(),tblRecordmodel.getValueAt(k,2).toString(),tblRecordmodel.getValueAt(k,3).toString()));
        }
        try (FileWriter file = new FileWriter(fileFolderPath+ File.separator+Generic.removeSpecialChars(inputPageName.getText())+"_"+Generic.getDate()+"_"+Generic.getTime()+".json")) {
            file.write(objects.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Successfully Saved , File Path::"+fileFolderPath+File.separator+Generic.removeSpecialChars(inputPageName.getText())+"_"+Generic.getDate()+"_"+Generic.getTime()+".json", "SmartSpy" , JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);

    }

    private void inputSearchKeyReleased(java.awt.event.KeyEvent evt) {
        filter(inputSearch.getText());
    }
    public void filter(String query){
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(tblRecordmodel);
        tblPageObjects.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));

    }

    public boolean verifyURL(String url){
        boolean status=false;
        if (url.toLowerCase().startsWith("http://") || url.toLowerCase().startsWith("https://")) {
            status=true;
        }
        return status;
    }

    JSONObject getObjectInfo(String objName, String objPath, String objType){
        JSONObject obj = new JSONObject();
        obj.put("Name", objName);
        obj.put("Value", objPath);
        obj.put("ObjType",objType);
        //obj.put("Actions", actions);
        return obj ;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    class SelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting())
                return;
            int row = tblPageObjects.getSelectedRow();
            if(row < 0)
                return;
            int col = tblPageObjects.getSelectedColumn();
            if(col < 0)
                return;

            if (tblPageObjects.getRowSorter()!=null) {
                row = tblPageObjects.getRowSorter().convertRowIndexToModel(row);
            }
            //tblProjects.clearSelection();
            selected_table_row_objName = (String) tblPageObjects.getModel().getValueAt(row, 1);
            selected_table_row_objType = (String) tblPageObjects.getModel().getValueAt(row, 2);
            selected_table_row_objPath = (String) tblPageObjects.getModel().getValueAt(row, 3);

            selected_row = row;
            //  Constants.TABLE_SELECTED = selected_table_row;

           // tblPageObjects.setSelectionBackground(Color.ORANGE);



        }
    }
    // Variables declaration - do not modify
    public static javax.swing.JButton btnCapture;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnRecord;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel imgCignitiLogo;
    private javax.swing.JTextField inpuUrl;
    public static javax.swing.JTextField inputPageName;
    private javax.swing.JTextField inputSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblMaximize;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JTable tblPageObjects;
    private javax.swing.JLabel txtEnterUrl;
    private javax.swing.JLabel txtPageName;
    // End of variables declaration
}
