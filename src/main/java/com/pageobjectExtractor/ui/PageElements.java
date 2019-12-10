package com.pageobjectExtractor.ui;


import com.pageobjectExtractor.utils.DomExtractor;
import com.pageobjectExtractor.utils.GlobalConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author E005397
 */
public class PageElements extends javax.swing.JFrame {

    public static WebBrowserCapture webBrowserCapture;

    public PageElements() {
        initComponents();

    }

    public void loadInit(){
        chkSelectAll.setSelected(false);
        btnCapture.setEnabled(false);
    }


    private void initComponents() {

        chkSelectAll = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        txtPageElements = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        pnlPageObjects = new javax.swing.JPanel();
        chkLink = new javax.swing.JCheckBox();
        chkButton = new javax.swing.JCheckBox();
        chkInput = new javax.swing.JCheckBox();
        chkRadioButton = new javax.swing.JCheckBox();
        chkComboBox = new javax.swing.JCheckBox();
        chkCheckBox = new javax.swing.JCheckBox();
        chkText = new javax.swing.JCheckBox();
        btnStart = new javax.swing.JButton();
        btnCapture = new javax.swing.JButton();
        //chkSelectAll2 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2,new Color(0,122,181) ));

        jPanel1.setBackground(new java.awt.Color(0, 122, 181));
        chkSelectAll.setFont(new java.awt.Font("Tahoma", 0, 14));
        chkSelectAll.setSelected(true);
        chkSelectAll.setText("Select All");

        chkSelectAll.addActionListener(actionListener);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

      //  jPanel1.setBackground(new java.awt.Color(0, 102, 255));

        txtPageElements.setFont(new java.awt.Font("Tahoma", 1, 15));
        txtPageElements.setForeground(new java.awt.Color(255, 255, 255));
        txtPageElements.setText("Select Page Elements");

        lblClose.setForeground(new java.awt.Color(255, 255, 255));
        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/multiply_18px.png")));
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblCloseMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtPageElements)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtPageElements)
                                        .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(19, Short.MAX_VALUE))
        );

        pnlPageObjects.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));

        chkLink.setFont(new java.awt.Font("Tahoma", 0, 14));
        chkLink.setText("Link");

        chkButton.setFont(new java.awt.Font("Tahoma", 0, 14));
        chkButton.setText("Button");

        chkInput.setFont(new java.awt.Font("Tahoma", 0, 14));
        chkInput.setText("Input");

        chkRadioButton.setFont(new java.awt.Font("Tahoma", 0, 14));
        chkRadioButton.setText("RadioButton");

        chkComboBox.setFont(new java.awt.Font("Tahoma", 0, 14));
        chkComboBox.setText("ComboBox");

        chkCheckBox.setFont(new java.awt.Font("Tahoma", 0, 14));
        chkCheckBox.setText("CheckBox");

        chkText.setFont(new java.awt.Font("Tahoma", 0, 14));
        chkText.setText("Text (Headers)");

        javax.swing.GroupLayout pnlPageObjectsLayout = new javax.swing.GroupLayout(pnlPageObjects);
        pnlPageObjects.setLayout(pnlPageObjectsLayout);
        pnlPageObjectsLayout.setHorizontalGroup(
                pnlPageObjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlPageObjectsLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(pnlPageObjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(chkLink)
                                        .addComponent(chkInput))
                                .addGap(70, 70, 70)
                                .addGroup(pnlPageObjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(pnlPageObjectsLayout.createSequentialGroup()
                                                .addComponent(chkButton)
                                                .addGap(127, 127, 127)
                                                .addComponent(chkComboBox))
                                        .addGroup(pnlPageObjectsLayout.createSequentialGroup()
                                                .addComponent(chkRadioButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(chkCheckBox)))
                                .addGap(80, 80, 80)
                                .addComponent(chkText)
                                .addContainerGap(26, Short.MAX_VALUE))
        );
        pnlPageObjectsLayout.setVerticalGroup(
                pnlPageObjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlPageObjectsLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(pnlPageObjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlPageObjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(chkComboBox)
                                                .addComponent(chkText)
                                                .addComponent(chkButton))
                                        .addComponent(chkLink))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(pnlPageObjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(chkInput)
                                        .addComponent(chkRadioButton)
                                        .addComponent(chkCheckBox))
                                .addGap(26, 26, 26))
        );


        btnStart.setFont(new java.awt.Font("Calibri", 1, 15));
//        btnFind.setForeground(new java.awt.Color(0, 153, 102));
//        btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/detective_26px.png")));
        btnStart.setFocusPainted(false);
        btnStart.setBackground(new Color(240, 240, 240));
        btnStart.setText("START");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });


        btnCapture.setFont(new java.awt.Font("Calibri", 1, 15));
//        btnFind.setForeground(new java.awt.Color(0, 153, 102));
//        btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/detective_26px.png")));
        btnCapture.setFocusPainted(false);
        btnCapture.setBackground(new Color(240, 240, 240));
        btnCapture.setText("CAPTURE");
        btnCapture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaptureActionPerformed(evt);
            }
        });

//        chkSelectAll2.setFont(new java.awt.Font("Tahoma", 0, 14));
//        chkSelectAll2.setSelected(true);
//        chkSelectAll2.setText("Select All");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(chkSelectAll)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                                .addComponent(btnCapture, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(pnlPageObjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(28, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(chkSelectAll)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCapture, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(132, 132, 132)
                                        .addComponent(pnlPageObjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(110, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void lblCloseMousePressed(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
        btnStart.setEnabled(false);
        try{
            Thread t = new Thread() {
                public void run() {
                    webBrowserCapture = new WebBrowserCapture();
                    webBrowserCapture.browserLaunch(GlobalConstants.BASE_URL);

                }
            };
            t.start();
            btnCapture.setEnabled(true);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void btnCaptureActionPerformed(java.awt.event.ActionEvent evt) {
        Main.inputPageName.setText(GlobalConstants.PAGE_TITLE);
        ArrayList<String> objList=new ArrayList<String>();

        if (chkLink.isSelected()) {
            objList.add("Link");
        }
        if (chkButton.isSelected()) {
            objList.add("Button");
        }
        if (chkCheckBox.isSelected()) {
            objList.add("CheckBox");
        }
        if (chkInput.isSelected()) {
            objList.add("EditBox");
        }

        if (chkRadioButton.isSelected()) {
            objList.add("RadioButton");
        }
        if (chkComboBox.isSelected()) {
            objList.add("ComboBox");
        }
        if(chkText.isSelected()){
            objList.add("Text");
        }

        DomExtractor domExtractor = new DomExtractor();
        domExtractor.smartExtractor(GlobalConstants.PAGE_SOURCE, objList);

        String[] args=null;
        String ObjectType=null;
        String identifier=null;
        GlobalConstants.tblRowno_incrementor = 0;
        for (Map.Entry<String, String> entry : GlobalConstants.pageObjectsHashMap.entrySet()) {
            args=entry.getValue().split("xpath = ");
            identifier="xpath";
            if(entry.getKey().contains("lnk_")){
                ObjectType = "Link";
            } else if(entry.getKey().contains("btn_")){
                ObjectType = "Button";
            } else if(entry.getKey().contains("radio_")){
                ObjectType = "RadioButton";
            }else if(entry.getKey().contains("input_")){
                ObjectType = "TextBox";
            } else if(entry.getKey().contains("chkbox_")){
                ObjectType = "CheckBox";
            } else if(entry.getKey().contains("list_")){
                ObjectType = "List";
            }
            GlobalConstants.tblRowno_incrementor = GlobalConstants.tblRowno_incrementor+1;
            Main.tblRecordmodel.addRow(new Object[]{GlobalConstants.tblRowno_incrementor,entry.getKey(),ObjectType,args[1].trim()});
            Main.pageElements.setVisible(false);
            Main.btnCapture.setEnabled(true);
            Thread.currentThread().interrupt();
        }
    }


    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
            boolean selected = abstractButton.getModel().isSelected();

            if(selected){
                chkLink.setSelected(true);
                chkButton.setSelected(true);
                chkCheckBox.setSelected(true);
                chkRadioButton.setSelected(true);
                chkComboBox.setSelected(true);
                chkInput.setSelected(true);
                chkText.setSelected(true);
            } else {

                chkLink.setSelected(false);
                chkButton.setSelected(false);
                chkCheckBox.setSelected(false);
                chkRadioButton.setSelected(false);
                chkComboBox.setSelected(false);
                chkInput.setSelected(false);
                chkText.setSelected(false);
            }
            // abstractButton.setText(newLabel);
        }
    };
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PageElements().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnCapture;
    private javax.swing.JButton btnStart;
    private javax.swing.JCheckBox chkButton;
    private javax.swing.JCheckBox chkCheckBox;
    private javax.swing.JCheckBox chkInput;
    private javax.swing.JCheckBox chkLink;
    private javax.swing.JCheckBox chkComboBox;
    private javax.swing.JCheckBox chkRadioButton;
    private javax.swing.JCheckBox chkSelectAll;
    private javax.swing.JCheckBox chkText;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblClose;
    private javax.swing.JPanel pnlPageObjects;
    private javax.swing.JLabel txtPageElements;
    // End of variables declaration
}
