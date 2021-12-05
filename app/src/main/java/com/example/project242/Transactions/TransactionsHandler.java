package com.example.project242.Transactions;

import androidx.annotation.Nullable;

import com.example.project242.Home.HomeSection;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

public class TransactionsHandler extends ArrayList<Transaction> {

    public ArrayList<Transaction> backup;
    private SortingMethods sort;
    private FilteringMethods filter;

    private boolean sortByAscendingDate;
    private boolean sortByDescendingDate;
    private boolean sortByAscendingAmount;
    private boolean sortByDescendingAmount;

    private boolean filterByAllOption;
    private boolean filterByIncomeOption;
    private boolean filterByExpenseOption;

    public TransactionsHandler() {
        backup = new ArrayList<>();
        sort = new SortingMethods();
        filter = new FilteringMethods();
    }


    public void chooseSortingMethod(boolean sortByAscendingDate, boolean sortByDescendingDate, boolean sortByAscendingAmount, boolean sortByDescendingAmount) {

        this.sortByAscendingDate = sortByAscendingDate;
        this.sortByDescendingDate = sortByDescendingDate;
        this.sortByAscendingAmount = sortByAscendingAmount;
        this.sortByDescendingAmount = sortByDescendingAmount;

    }

    public void sortTransactions() {
        if (sortByAscendingDate) {
            sort.sortByAscendingDate();
            return;
        }

        if (sortByDescendingDate) {
            sort.sortByDescendingDate();
            return;
        }

        if (sortByAscendingAmount) {
            sort.sortByAscendingAmount();
            return;
        }

        if (sortByDescendingAmount) {
            sort.sortByDescendingAmount();
            return;
        }
    }


    public void chooseFilteringMethod(boolean filterByAllOption, boolean filterByIncomeOption, boolean filterByExpenseOption) {

        this.filterByAllOption = filterByAllOption;
        this.filterByIncomeOption = filterByIncomeOption;
        this.filterByExpenseOption = filterByExpenseOption;
    }



    public void filterTransactions(){

        if (filterByAllOption) {
            filter.filterByAllOption();
        }

        if (filterByIncomeOption) {
            filter.filterByIncomeOption();
        }

        if (filterByExpenseOption) {
            filter.filterByExpenseOption();
        }

    }

    public String getFilteringMethod(){
        String method="";

        if (filterByAllOption) {
           method="All";
        }

        if (filterByIncomeOption) {
            method="Income";
        }

        if (filterByExpenseOption) {
            method="Expense";
        }
        return method;
    }

    public String getSortingMethod(){
        String method="";

        if (sortByAscendingDate) {
            method = "Ascending Date";
        }

        if (sortByDescendingDate) {
            method = "Descending Date";
        }

        if (sortByAscendingAmount) {
            method = "Ascending Amount";
        }

        if (sortByDescendingAmount) {
            method = "Descending Amount";
        }

        return method;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    private class FilteringMethods {


        public void filterByAllOption() {
            //restore any transaction type existing in backup list
            if (backup.size() != 0) {
                for (int i = 0; i < backup.size(); i++) {
                    //add back
                    add(backup.get(i));
                    backup.remove(i);
                    i = -1;

                }
            }
        }


        public void filterByIncomeOption() {
            //incomes are in backup list, restore them
            if (size() != 0) {
                for (int i = 0; i < backup.size(); i++) {
                    //add back
                    add(backup.get(i));
                    backup.remove(i);
                    i = -1;

                }

            }
            //remove expenses
            for (int i = 0; i < size(); i++) {

                Transaction t = get(i);
                if (t.getTransactionType() == TransactionTypes.EXPENSE) {
                    backup.add(t);
                    remove(t);
                    i = -1;
                }
            }
        }


        public void filterByExpenseOption() {

            //expenses are in backup list, restore them
            if (size() != 0) {
                for (int i = 0; i < backup.size(); i++) {
                    //add back
                    add(backup.get(i));
                    backup.remove(i);
                    i = -1;

                }

            }

            //remove incomes
            for (int i = 0; i < size(); i++) {

                Transaction t = get(i);
                if (t.getTransactionType() == TransactionTypes.INCOME) {
                    backup.add(t);
                    remove(i);
                    i = -1;
                }
            }
        }
    }


    private class SortingMethods {

        public void sortByAscendingDate() {

            for (int i = 0; i < size(); i++) {
                if (i + 1 < size()) {
                    Transaction currentTransaction = get(i);
                    long currentTime = currentTransaction.getDate().getTime();

                    Transaction nextTransaction = get(i + 1);
                    long nextTime = nextTransaction.getDate().getTime();  //assuming it is farther to current date

                    if (nextTime < currentTime) {
                        set(i, nextTransaction);
                        set(i + 1, currentTransaction);

                        //recursive if replacement occurred
                        if (i >= 1) {
                            i = i - 2;
                        }
                    }
                }

            }//for

        }


        public void sortByDescendingDate() {

            for (int i = 0; i < size(); i++) {
                if (i + 1 < size()) {
                    Transaction currentTransaction = get(i);
                    long currentTime = currentTransaction.getDate().getTime();

                    Transaction nextTransaction = get(i + 1);
                    long nextTime = nextTransaction.getDate().getTime();  //assuming it is closer to current date

                    if (currentTime < nextTime) {
                        set(i, nextTransaction);
                        set(i + 1, currentTransaction);

                        //recursive if replacement occurred
                        if (i >= 1) {
                            i = i - 2;
                        }
                    }
                }

            }//for
        }


        public void sortByAscendingAmount() {
            for (int i = 0; i < size(); i++) {
                if (i + 1 < size()) {
                    Transaction currentTransaction = get(i);
                    int currentAmount = currentTransaction.getAmount();

                    Transaction nextTransaction = get(i + 1);
                    int nextAmount = nextTransaction.getAmount();

                    if (nextAmount < currentAmount) {
                        set(i, nextTransaction);
                        set(i + 1, currentTransaction);

                        //recursive if replacement occurred
                        if (i >= 1) {
                            i = i - 2;
                        }
                    }
                }

            }//for
        }


        public void sortByDescendingAmount() {

            for (int i = 0; i < size(); i++) {
                if (i + 1 < size()) {
                    Transaction currentTransaction = get(i);
                    int currentAmount = currentTransaction.getAmount();

                    Transaction nextTransaction = get(i + 1);
                    int nextAmount = nextTransaction.getAmount();

                    if (currentAmount < nextAmount) {
                        set(i, nextTransaction);
                        set(i + 1, currentTransaction);

                        //recursive if replacement occurred
                        if (i >= 1) {
                            i = i - 2;
                        }
                    }
                }

            }//for

        }


    }//class



}//parent class
