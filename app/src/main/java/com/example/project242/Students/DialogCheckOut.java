package com.example.project242.Students;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.project242.MonetaryRates.Discount.Discount;
import com.example.project242.MonetaryRates.Discount.DiscountsHandler;
import com.example.project242.Transactions.PaymentMethods;
import com.example.project242.Transactions.Transaction;
import com.example.project242.Transactions.TransactionTypes;
import com.example.project242.Transactions.TransactionsHandler;
import com.example.project242.general.DataContainer;
import com.example.project242.R;

import java.util.Date;

public class DialogCheckOut extends AppCompatDialogFragment {
    private TextView studentNameTextView;
    private TextView studentIDTextView;
    private RadioButton onlinePayment;
    private RadioButton bankTransfer;
    private RadioButton cheque;
    private Spinner discountsAvailable;
    private TextView amountValue;

    private AdapterCurrentStudents adapter;
    private DiscountsHandler adapterD = DataContainer.discountsHandler;
    private TransactionsHandler Tlist = DataContainer.transactionsHandler;
    private ListView listView;

    DialogCheckOut(AdapterCurrentStudents adapter, ListView listView) {
        this.adapter = adapter;
        this.listView = listView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_check_out, null);

        Bundle bundle = getArguments();
        Student student = bundle.getParcelable("Student");

        discountsAvailable = view.findViewById(R.id.discountsAvailable);


                ArrayAdapter<Discount> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, adapterD);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                discountsAvailable.setAdapter(arrayAdapter);


        builder.setView(view)
                .setTitle("Check-out Confirmation")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                int transactionID = 654567;
                                int senderAccNo = Integer.parseInt(student.getGuardian().getGuardianAccountNumber());
                                String senderAccName = String.valueOf(student.getGuardian().getGuardianName());
                                int recipientAccNo = 739136;
                                String recipientAccName = "Little Bunnies Nursery";
                                Date date = new Date(System.currentTimeMillis());
                                TransactionTypes TransactionType = TransactionTypes.INCOME;
                                PaymentMethods paymentMethod = null;
                                if(onlinePayment.isChecked()){
                                    paymentMethod = PaymentMethods.ONLINE_PAYMENT;
                                }
                                else if(bankTransfer.isChecked()){
                                    paymentMethod = PaymentMethods.BANK_TRANSFER;
                                }
                                else if(cheque.isChecked()){
                                    paymentMethod = PaymentMethods.CHEQUE;
                                }

                                student.setCheckOutTime(System.currentTimeMillis());

                                long checkInDuration = student.getCheckOutTime() - student.getCheckInTime();
                                int hours = (int) checkInDuration / 60;
                                int minutes = (int) checkInDuration % 60;

                                int checkOutCosts = DataContainer.costsHandler.getCost(hours, minutes);

                                String discount;
                                double finalDiscount=0;
                                String selectedDiscount = discountsAvailable.getSelectedItem().toString();

                                for(int j =0; j < adapterD.size();j++){
                                    if(selectedDiscount == String.valueOf(adapterD.get(j).getDiscountName())){
                                        discount = String.valueOf(adapterD.get(j).getPercent());
                                        finalDiscount = Double.parseDouble(discount);
                                    }
                                }
                                double discountValue = finalDiscount / 100;
                                double calc = checkOutCosts - (checkOutCosts * discountValue);
                                int amount = (int)Math.round(calc);


                                // if(discountsAvailable.getSelectedItem().)

                                Transaction newTransaction =  new Transaction(transactionID, senderAccNo,senderAccName, recipientAccNo, recipientAccName,amount,date,TransactionType,paymentMethod);
                                //Transaction newTransaction =  new Transaction(123456, 3212345,"hello", 324212, "recipientAccName",43,date,TransactionTypes.INCOME,PaymentMethods.ONLINE_PAYMENT);
                                Tlist.add(newTransaction);


                                DataContainer.currentStudentsArrayList.remove(student);
                                for (int j = 0; j < DataContainer.allStudentsArrayList.size(); ++j) {
                                    if (DataContainer.allStudentsArrayList.get(j).getStudentID() == student.getStudentID()) {
                                        DataContainer.allStudentsArrayList.get(j).setCheckedInFlag(false);
                                        break;
                                    }
                                }
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void unused) {
                                super.onPostExecute(unused);
                                adapter.notifyDataSetChanged();
                                listView.invalidateViews();
                                Toast.makeText(getActivity(),"Confirmed!", Toast.LENGTH_SHORT).show();
                            }
                        }.execute();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "Canceled!", Toast.LENGTH_SHORT).show();
                    }
                });

        studentNameTextView = view.findViewById(R.id.dialog_check_out_textView_student_name_1);
        studentIDTextView = view.findViewById(R.id.dialog_check_out_textView_student_id_1);
        onlinePayment = view.findViewById(R.id.onlinePayment);
        bankTransfer = view.findViewById(R.id.bankTransfer);
        cheque = view.findViewById(R.id.cheque);
        amountValue = view.findViewById(R.id.amountValue);


        studentNameTextView.setText("Name:" + student.getStudentName());
        studentIDTextView.setText("ID:" + String.valueOf(student.getStudentID()));


        return builder.create();
    }

    public void checkOutStudent() {

    }
}
