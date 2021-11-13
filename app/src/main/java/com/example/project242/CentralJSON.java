package com.example.project242;

import android.content.Context;

import com.example.project242.MonetaryRates.Costs.Cost;
import com.example.project242.MonetaryRates.Costs.CostTypes;
import com.example.project242.MonetaryRates.Costs.CostsHandler;
import com.example.project242.MonetaryRates.Discount.Discount;
import com.example.project242.MonetaryRates.Discount.DiscountsHandler;
import com.example.project242.Students.Guardian;
import com.example.project242.Students.Student;
import com.example.project242.Transactions.ExpenseTypes;
import com.example.project242.Transactions.PaymentMethods;
import com.example.project242.Transactions.Transaction;
import com.example.project242.Transactions.TransactionTypes;
import com.example.project242.Transactions.TransactionsHandler;

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

    public CentralJSON() {
    }

    public static void loadJSON(Context context) {
        //open file
        InputStreamReader inputStreamReader = new InputStreamReader(context.getResources().openRawResource(R.raw.nursery));
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer();

        try {
            //read file
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line);
            }

            JSONObject root = new JSONObject(stringBuffer.toString());
            CentralJSON.root = root;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static CostsHandler parseCosts(){
        CostsHandler list = new CostsHandler();

        try {
            JSONObject MonetaryRates_obj = root.getJSONObject("Monetary Rates");
            JSONArray allCosts = MonetaryRates_obj.getJSONArray("Costs");

            for (int i = 0; i < allCosts.length(); i++) {
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


                list.add(new Cost(amount, type, hours));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }


    public static DiscountsHandler parseDiscounts(){
        DiscountsHandler list = new DiscountsHandler();
        

        try {
            JSONObject MonetaryRates_obj = root.getJSONObject("Monetary Rates");
            JSONArray alldiscounts = MonetaryRates_obj.getJSONArray("Discounts");

            for (int i = 0; i < alldiscounts.length(); i++) {
                JSONObject discount = alldiscounts.getJSONObject(i);

                String discounttype = discount.getString("Title");
                String percentage1 = discount.getString("Percentage");
                int percentage= Integer.valueOf(percentage1);
                String switchValue = discount.getString("Active");
                boolean switchValue1 = Boolean.valueOf(switchValue);

                list.add(new Discount(discounttype,percentage,switchValue1));



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }

    public static User parseUsers(String username, String password){
       // UsersHandler userList = new UsersHandler();
        User newUser = new User();
        try {

            JSONArray  users = root.getJSONArray("Users");

            // users = Users_obj.getJSONArray("Users");
            for(int i = 0; i <users.length();i++) {
                JSONObject user = users.getJSONObject(i);
                if (user.getString("Username").equals(username)) {
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






    public static TransactionsHandler parseTransactions(){
        TransactionsHandler list = new TransactionsHandler();

        try {
            JSONObject Transactions_obj = root.getJSONObject("Transactions");
            JSONArray transactionType;


            //parsing Incomes
            transactionType = Transactions_obj.getJSONArray("Income");
            for (int i = 0; i < transactionType.length(); i++) {
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
                list.add(t);

            }


            //parsing Expenses
            transactionType = Transactions_obj.getJSONArray("Expense");
            for (int i = 0; i < transactionType.length(); i++) {
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
                list.add(t);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }



        list.sortTransactions();

        return list;
    }




    public static Guardian parseStudentGuardian(JSONObject studentDetails) {
        Guardian guardian = new Guardian();

        try {
            JSONObject guardianDetails = studentDetails.getJSONObject("Guardian");

            guardian.setGuardianName(guardianDetails.getString("Name"));
            guardian.setRelationship(guardianDetails.getString("Relationship"));
            guardian.setGuardianPhoneNumber(guardianDetails.getString("Phone Number"));
            guardian.setGuardianEmail(guardianDetails.getString("Email"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return guardian;
    }


    public static ArrayList<Student> parseAllStudents() {
        ArrayList<Student> list = new ArrayList<>();

        try {
            JSONObject Students = root.getJSONObject("Students");
            JSONArray studentsJSONArray = Students.getJSONArray("All Students");

            for (int i = 0; i < studentsJSONArray.length(); ++i) {
                JSONObject studentDetails = studentsJSONArray.getJSONObject(i);

                list.add(new Student());
                list.get(i).setStudentID(studentDetails.getInt("ID"));
                list.get(i).setStudentName(studentDetails.getString("Name"));
                list.get(i).setStudentDOB(studentDetails.getString("DOB"));
                list.get(i).setStudentGender(studentDetails.getString("Gender"));
                list.get(i).setGuardian(parseStudentGuardian(studentDetails));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }


    public static ArrayList<Student> parseCurrentStudents() {
        ArrayList<Student> list = new ArrayList<>();

        try {
            JSONObject Students = root.getJSONObject("Students");
            JSONArray studentsJSONArray = Students.getJSONArray("Current Students");

            for (int i = 0; i < studentsJSONArray.length(); ++i) {
                JSONObject studentDetails = studentsJSONArray.getJSONObject(i);

                list.add(new Student());
                list.get(i).setStudentID(studentDetails.getInt("ID"));
                list.get(i).setStudentName(studentDetails.getString("Name"));
                list.get(i).setStudentDOB(studentDetails.getString("DOB"));
                list.get(i).setStudentGender(studentDetails.getString("Gender"));
                list.get(i).setGuardian(parseStudentGuardian(studentDetails));
                list.get(i).setCheckedInFlag(true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
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

        if (paymentString.equals("CASH"))
            return PaymentMethods.CASH;
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
