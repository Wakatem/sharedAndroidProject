package com.example.project242.Transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

public class TransactionsHandler extends ArrayList<Transaction> {

    public TransactionsHandler(){

    }

    ////////////////////////////////////////////////////////////////////////

    //all methods are inherited from parent


    //TODO: here you create the functions you want (all will be merged after finishing)

    public void sortTransactions(){
        //initial sorting based on ascending date

        for (int i = 0; i <size() ; i++) {
            if (i+1 < size()){
                Transaction currentTransaction = get(i);
                long currentTime = currentTransaction.getDate().getTime();

                Transaction nextTransaction = get(i+1);
                long nextTime = nextTransaction.getDate().getTime();

                if (currentTime < nextTime) {
                     set(i, nextTransaction);
                     set(i+1, currentTransaction);

                     //recursive if replacement occurred
                    if (i >= 1) {
                        i = i - 2;
                    }
                }
            }

        }//for



    }


}//class
