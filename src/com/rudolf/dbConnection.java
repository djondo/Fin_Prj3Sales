package com.rudolf;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by rudi on 12/14/2015.
 */

    public class dbConnection {
        private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
        private static String DB_NAME = "data";   //TODO CHANGE TO sales
        private static final String USER = "root";
        private static final String PASS = "Wrk4msql";      //TODO change!
        static Statement statement = null;
        static Connection conn = null;
        static ResultSet rs = null;

        public static void main(String[] args) throws Exception{
            setup();


//(If needed) create database and add sample data

            try {
//Query the database to fetch all of the data
                String getAllData = "SELECT * FROM Sales";
                rs = statement.executeQuery(getAllData);
                SalesModel salesModel = new SalesModel(rs);
//Create and show the GUI
                SalesInfoGui tableGUI = new SalesInfoGui(salesModel);
            } catch (Exception e) {
                e.printStackTrace();
                shutdown();
            }
        }
        public static void setup() {
            try {
                try {
                    String Driver = "com.mysql.jdbc.Driver";

                    Class.forName(Driver);
                } catch (ClassNotFoundException cnfe) {
                    System.out.println("No database drivers found. Quitting");
                    System.exit(-1);
                }
//    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/test";
                conn = DriverManager.getConnection(DB_CONNECTION_URL + DB_NAME, USER, PASS);
//The first argument allows us to move both forward and backwards through the RowSet.
//The TableModel will need to do this.
//by default, you can only move forward - it's what most apps do and it's less
//resource-intensive than being able to go in both directions.
//If you set one argument, you need the other. The second one means you will
//not be modifying the data in the RowSet (we'll change this later though)
                // Create the Album table
                statement = conn.createStatement();
                Scanner s = new Scanner(System.in);
                String createSales = "CREATE TABLE if not exists Sales (Sales int PRIMARY KEY AUTO_INCREMENT," +
                        "Album.albumID, dateSold Date, price double)";
                System.out.println("create sales sql = \n" + createSales);
                statement.execute(createSales);

//                Insert data into table
                System.out.println("adding test data");
                String exampleInsertStatement = "INSERT INTO Sales (SalesID, Album.albumID, dateSold, price) values ('example 1', " +
                        "'example Album.albumID', NOW(), price )";

                statement.execute(exampleInsertStatement);
                System.out.println("Added test data");
                String sales = "SELECT * FROM Sales";

            } catch (SQLException sqle) {
                System.out.println("The Sales table (probably) already exists, verify with following error message.");
                System.out.println(sqle);


//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
            }
        }


        public static void addNewAlbum(Sales s){
            //  String exampleInsertStatement = "INSERT INTO album (title, artist, price, consignorid, consigneddate) values ('example title', 'example artist', 5.99, 1, NOW() )";

            String albumInsertStatement = "INSERT INTO Sales (SalesID, albumID, dateSold, price) values ('" + s.SalesID + "', '" + s.albumID + "', "
                    + s.dateSold + ", price)";
            System.out.println(albumInsertStatement);
            try {
                statement.execute(albumInsertStatement);
            } catch (SQLException se) {
                se.printStackTrace();
            }



        }

        public static void shutdown() {
            //A finally block runs whether an exception is thrown or not. Close resources and tidy up whether this code worked or not.
            try {
                if (statement != null) {
                    statement.close();
                    System.out.println("Statement closed");
                }
            } catch (SQLException se) {
                //Closing the connection could throw an exception too
                se.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();  //Close connection to database
                    System.out.println("Database connection closed");
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            System.exit(0);
        }
    }

