package com.rudolf;

import java.util.Date;

/**
 * Created by rudi on 12/14/2015.
 */

    public class Sales {
    int SalesID;
    int albumID;
    Date dateSold;
    double price;
    double balanceOwed;

    Sales(int SalesID, int albumID, Date dateSold, double price) {
        this.SalesID = SalesID;
        this.albumID = albumID;
        this.dateSold = dateSold;
        this.price = price;

    }
}
