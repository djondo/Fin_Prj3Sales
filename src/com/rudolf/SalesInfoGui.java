package com.rudolf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;

/**
 * Created by rudi on 12/12/2015.
 */
        public class SalesInfoGui extends JFrame implements WindowListener {
            private JPanel rootPanel;
            private JButton quitButton;
            private JTextField priceTextField;
            private JTextField dateSoldTextField;
            private JTextField AlbumIDTextField;
            private JTextField salesIDTextField;
            private JButton addSalesButton;
            private JTable salesTable;

    protected SalesInfoGui(SalesModel sml) {
        setContentPane(rootPanel);
                setContentPane(rootPanel);
                pack();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                addWindowListener(this);
                setVisible(true);

                setSize(300, 150);

                //Create a data model and tell our table to use it
                // SalesDataModel salesModel = new SalesDataModel();
                salesTable.setModel(sml);
                //Grid color default is white, change it so we can see it
                salesTable.setGridColor(Color.BLACK);
                //Also make the columns wider
                salesTable.getColumnModel().getColumn(0).setWidth(200);
                //Quit application when user closes window, otherwise app keeps running
                //Sometimes this is what you want, sometimes it isn't.
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //Close button code.
                quitButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //Call Main's shutdown method - one class is the entry and exit point of the program
                        //  Main.shutdown();
                        System.exit(0);
                    }
                });

        addSalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int SalesID = Integer.parseInt(salesIDTextField.getText());
                int  albumID = Integer.parseInt(AlbumIDTextField.getText());
                double price = Integer.parseInt(priceTextField.getText());
                String dateSold = dateSoldTextField.getText();
               // String consignedDate = ConsignedDateTextField.getText();
                Sales newSales = new Sales(SalesID, albumID, new Date(), price);
                dbConnection.addNewAlbum(newSales);

            }// Album newAlbum = new Album(artist, title, p, 1, new Date());
        });
    }

            //Method provided by WindowListener interface
            //Called when user clicks button to close application
            public void windowClosing(WindowEvent e) {
                System.out.println("Window closing");
                //Main.shutdown();
            }

            //A WindowListener is required to provide these methods
            //We can choose what they do - in this case, nothing
            public void windowClosed(WindowEvent e) {
            }

            public void windowOpened(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowActivated(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
            }

        }



