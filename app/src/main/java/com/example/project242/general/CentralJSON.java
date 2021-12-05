package com.example.project242.general;

import android.content.Context;

import com.example.project242.MonetaryRates.Costs.Cost;
import com.example.project242.MonetaryRates.Costs.CostTypes;
import com.example.project242.MonetaryRates.Costs.CostsHandler;
import com.example.project242.MonetaryRates.Discount.Discount;
import com.example.project242.MonetaryRates.Discount.DiscountsHandler;
import com.example.project242.R;
import com.example.project242.Students.Guardian;
import com.example.project242.Students.Student;
import com.example.project242.Transactions.ExpenseTypes;
import com.example.project242.Transactions.PaymentMethods;
import com.example.project242.Transactions.Transaction;
import com.example.project242.Transactions.TransactionTypes;
import com.example.project242.Transactions.TransactionsHandler;
import com.example.project242.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CentralJSON {

    private static JSONObject root;
    private static CostsHandler costsList;
    private static DiscountsHandler discountsList;
    private static TransactionsHandler transactionsList;
    private static ArrayList<Student> allStudentsList;
    private static ArrayList<Student> currentStudentsList;

    public CentralJSON() { }

    public static void loadJSON(Context context) {
        //open file
        InputStreamReader inputStreamReader = new InputStreamReader(context.getResources().openRawResource(R.raw.nursery));
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder builder = new StringBuilder();

        try {
            //read file
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            root = new JSONObject(builder.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        costsList = new CostsHandler();
        discountsList = new DiscountsHandler();
        transactionsList = new TransactionsHandler();
        allStudentsList = new ArrayList<>();
        currentStudentsList = new ArrayList<>();
    }


    public static ArrayList[] loadLists(){

        boolean dataRemaining;

        try {
            JSONObject MonetaryRates_obj = root.getJSONObject("Monetary Rates");
            JSONObject Students = root.getJSONObject("Students");
            JSONObject Transactions_obj = root.getJSONObject("Transactions");

            //costs
            JSONArray allCosts = MonetaryRates_obj.getJSONArray("Costs");
            int costsSize = allCosts.length();

            //discounts
            JSONArray alldiscounts = MonetaryRates_obj.getJSONArray("Discounts");
            int discountsSize = alldiscounts.length();

            //income transactions
            JSONArray incomeTransactions = Transactions_obj.getJSONArray("Income");
            int incomeTransactionsSize = incomeTransactions.length();

            //expense transaction
            JSONArray expenseTransaction = Transactions_obj.getJSONArray("Expense");
            int expenseTransactionSize = expenseTransaction.length();

            //all students
            JSONArray allStudentsJSONArray = Students.getJSONArray("All Students");
            int allStudentsSize = allStudentsJSONArray.length();

            //current students
            JSONArray currentStudentsJSONArray= Students.getJSONArray("Current Students");
            int currentStudentsSize = currentStudentsJSONArray.length();


            int i=0;
            while (true){
                dataRemaining = false;


                //load costs
                if (i < costsSize){
                    dataRemaining = true;
                    parseCost(i, allCosts);
                }


                //load discounts
                if (i < discountsSize){
                    dataRemaining = true;
                    parseDiscount(i, alldiscounts);
                }


                //load transactions
                if (i < incomeTransactionsSize){ dataRemaining = true; parseTransaction(i, incomeTransactions, true);}
                if (i < expenseTransactionSize){ dataRemaining = true; parseTransaction(i, expenseTransaction, false);}


                //load all students list
                if (i < allStudentsSize){
                    dataRemaining = true;
                    parseAllStudents(i, allStudentsJSONArray);
                }


                //load current students list




                if (dataRemaining)
                    i++;
                else
                    break;
            }


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


        transactionsList.chooseFilteringMethod(true, false, false);
        transactionsList.chooseSortingMethod(false, true, false, false);

        transactionsList.filterTransactions();
        transactionsList.sortTransactions();

        return new ArrayList[]{costsList, discountsList, transactionsList, allStudentsList, currentStudentsList};
    }


    public static void parseCost(int i, JSONArray allCosts){


        try {

                JSONObject cost = allCosts.getJSONObject(i);

                //get cost duration details
                String durationString = cost.getString("duration");
                char arr[] = durationString.toCharArray();
                CostTypes type;

                if (arr[1] == '>'){
                    type = CostTypes.GREATER;
                }else if(arr[1] == '='){
                    type = CostTypes.EQUAL;
                }else {
                    type = CostTypes.LESS;
                }

                int hours= Integer.valueOf(String.valueOf(arr[2]));


                //get cost amount
                String amountString = cost.getString("amount");
                int amount = Integer.valueOf(amountString);


                costsList.add(new Cost(amount, type, hours));

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


    public static void parseDiscount(int i, JSONArray alldiscounts){

        try {

                JSONObject discount = alldiscounts.getJSONObject(i);

                String discounttype = discount.getString("Title");
                String percentage1 = discount.getString("Percentage");
                int percentage= Integer.valueOf(percentage1);

                discountsList.add(new Discount(discounttype,percentage));



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public static void parseTransaction(int i, JSONArray transactionType, boolean parseIncome){


        try {
            if (parseIncome){

                //parsing Incomes
                JSONObject transaction = transactionType.getJSONObject(i);

                long transactionID        = transaction.getLong("transactionID");
                int senderAccNumber       = transaction.getInt("Sender AccNumber");
                String senderAccName      = transaction.getString("Sender AccName");
                int recipientAccNumber    = transaction.getInt("Recipient AccNumber");
                String recipientAccName   = transaction.getString("Recipient AccName");
                int amount                = transaction.getInt("Amount");
                Date date                 = getDate(transaction.getString("Date"));
                PaymentMethods method     = getPaymentMethod(transaction.getString("PaymentMethod"));

                Transaction t = new Transaction(transactionID, senderAccNumber, senderAccName, recipientAccNumber, recipientAccName, amount, date, TransactionTypes.INCOME, method);
                transactionsList.add(t);
            }


            else {

                //parsing Expenses
                JSONObject transaction = transactionType.getJSONObject(i);

                long transactionID        = transaction.getLong("transactionID");
                int senderAccNumber       = transaction.getInt("Sender AccNumber");
                String senderAccName      = transaction.getString("Sender AccName");
                int recipientAccNumber    = transaction.getInt("Recipient AccNumber");
                String recipientAccName   = transaction.getString("Recipient AccName");
                int amount                = transaction.getInt("Amount");
                Date date                 = getDate(transaction.getString("Date"));
                ExpenseTypes expense      = getExpense(transaction.getString("Expense Type"));
                PaymentMethods method     = getPaymentMethod(transaction.getString("PaymentMethod"));

                Transaction t = new Transaction(transactionID, senderAccNumber, senderAccName, recipientAccNumber, recipientAccName, amount, date, expense, method);
                transactionsList.add(t);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }



    public static void parseAllStudents(int i, JSONArray allStudentsJSONArray) {

        try {
                JSONObject studentDetails = allStudentsJSONArray.getJSONObject(i);

                allStudentsList.add(new Student());
                allStudentsList.get(i).setStudentID(studentDetails.getInt("ID"));
                allStudentsList.get(i).setStudentName(studentDetails.getString("Name"));
                allStudentsList.get(i).setStudentDOB(studentDetails.getString("DOB"));
                allStudentsList.get(i).setStudentGender(studentDetails.getString("Gender"));
                allStudentsList.get(i).setGuardian(parseStudentGuardian(studentDetails));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public static void parseCurrentStudents(int i, JSONArray currentStudentsJSONArray) {

        try {
                JSONObject studentDetails = currentStudentsJSONArray.getJSONObject(i);

                currentStudentsList.add(new Student());
                currentStudentsList.get(i).setStudentID(studentDetails.getInt("ID"));
                currentStudentsList.get(i).setStudentName(studentDetails.getString("Name"));
                currentStudentsList.get(i).setStudentDOB(studentDetails.getString("DOB"));
                currentStudentsList.get(i).setStudentGender(studentDetails.getString("Gender"));
                currentStudentsList.get(i).setGuardian(parseStudentGuardian(studentDetails));
                currentStudentsList.get(i).setCheckedInFlag(true);

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


    private static Guardian parseStudentGuardian(JSONObject studentDetails) {
        Guardian guardian = new Guardian();

        try {
            JSONObject guardianDetails = studentDetails.getJSONObject("Guardian");

            guardian.setGuardianName(guardianDetails.getString("Name"));
            guardian.setRelationship(guardianDetails.getString("Relationship"));
            guardian.setGuardianPhoneNumber(guardianDetails.getString("Phone Number"));
            guardian.setGuardianEmail(guardianDetails.getString("Email"));
            guardian.setGuardianAccountNumber(guardianDetails.getString("Sender AccNumber"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return guardian;
    }


    public static User findCurrentUser(String username, String password){
        // UsersHandler userList = new UsersHandler();
        User newUser = null;
        try {

            JSONArray users = root.getJSONArray("Users");

            // users = Users_obj.getJSONArray("Users");
            for(int i = 0; i <users.length();i++) {
                JSONObject user = users.getJSONObject(i);
                if (user.getString("Username").equals(username) && user.getString("Password").equals(password)) {
                    newUser = new User();
                    int userID = user.getInt("ID");
                    String name = user.getString("Name");
                    String username1 = username;
                    String password1 = password;
                    Date DOB = getDate(user.getString("DOB"));
                    String gender = user.getString("Gender");
                    String phoneNo = user.getString("Phone Number");
                    String email = user.getString("Email");

                    //newUser = new User(userID,name,username1,password1,DOB,gender,phoneNo,email);
                    newUser.setUserID(userID);
                    newUser.setName(name);
                    newUser.setUsername(username1);
                    newUser.setPassword(password1);
                    newUser.setDOB(DOB);
                    newUser.setGender(gender);
                    newUser.setPhoneNo(phoneNo);
                    newUser.setEmail(email);


                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newUser;

    }




    ////////////////////////////////////////////////////////////       Helper Methods    ////////////////////////////////////////////////////////////////////


    private static Date getDate(String dateString){

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date=null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    private static PaymentMethods getPaymentMethod(String paymentString){

        if (paymentString.equals("ONLINE_PAYMENT"))
            return PaymentMethods.ONLINE_PAYMENT;
        else if (paymentString.equals("CHEQUE"))
            return PaymentMethods.CHEQUE;
        else
            return PaymentMethods.BANK_TRANSFER;

    }


    private static ExpenseTypes getExpense(String expenseString){
        if (expenseString.equals("Salary"))
            return ExpenseTypes.SALARY;
        else if (expenseString.equals("Utility"))
            return ExpenseTypes.UTILITY;
        else if(expenseString.equals("Rent"))
            return ExpenseTypes.RENT;
        else
            return ExpenseTypes.OTHER;
    }


}
