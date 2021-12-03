package com.example.project242.Settings;

import androidx.appcompat.app.AppCompatActivity;

import 	android.view.ViewGroup.LayoutParams;

import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project242.DataContainer;
import com.example.project242.R;
import com.example.project242.zNavigationMenu.SectionsMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsSection extends AppCompatActivity {

    //username changes
    TextView username;
    RelativeLayout user;
    Button save;
    ImageButton edit;
    EditText changeUName;

    //email changes
    TextView emailName;
    RelativeLayout emailLayout;
    Button saveEmail;
    ImageButton editEmail;
    EditText changedEmail;

    //phone changes
    TextView currentPhone;
    RelativeLayout phoneLayout;
    Button savePhone;
    ImageButton editPhone;
    EditText newPhone;

    //Password changes
    TextView currentPass;
    RelativeLayout passwordLayout;
    Button savePass;
    ImageButton editPass;
    EditText newPass;

    boolean isEditing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        View includer = findViewById(R.id.includer);
        setupToolbarAndMenu(includer, "Settings", 4);

        //username initialization
        username = findViewById(R.id.currentName);
        username.setText(DataContainer.currentUser.getUsername());
        user = (RelativeLayout) findViewById(R.id.thelayout);
        edit = findViewById(R.id.change);
        save = findViewById(R.id.save);
        changeUName = findViewById(R.id.changeUName);

        //email initialization
        emailName = findViewById(R.id.currentEmail);
        emailName.setText(DataContainer.currentUser.getEmail());
        emailLayout = findViewById(R.id.emailLayout);
        saveEmail = findViewById(R.id.saveEmail);
        editEmail = findViewById(R.id.changeEmail);
        changedEmail = findViewById(R.id.changedEmail);

        //phone initialization
        currentPhone = findViewById(R.id.currentPhone);
        currentPhone.setText(DataContainer.currentUser.getPhoneNo());
        phoneLayout = findViewById(R.id.phoneLayout);
        savePhone = findViewById(R.id.savePhone);
        editPhone = findViewById(R.id.changePhone);
        newPhone = findViewById(R.id.newPhone);

        //password initialization
        currentPass = findViewById(R.id.currentPass);
        currentPass.setText(DataContainer.currentUser.getPassword());
        passwordLayout = findViewById(R.id.passwordLayout);
        savePass = findViewById(R.id.savePass);
        editPass = findViewById(R.id.changePass);
        newPass = findViewById(R.id.newPass);

        if (isEditing == true) {
            edit.setClickable(false);
            editPhone.setClickable(false);
            editEmail.setClickable(false);
            editPass.setClickable(false);
        }


        //username editing
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEditing == false) {
                    preventEditing(edit);
                    LayoutParams params = user.getLayoutParams();
                    params.height = 400;
                    user.setLayoutParams(params);
                    save.setVisibility(View.VISIBLE);
                    changeUName.setVisibility(View.VISIBLE);
                    edit.setImageResource(R.drawable.ic_baseline_close_24);
                    isEditing = true;
                } else {
                    changeUName.setText("");
                    save.setVisibility(View.INVISIBLE);
                    changeUName.setVisibility(View.INVISIBLE);
                    LayoutParams params = user.getLayoutParams();
                    params.height = 250;
                    user.setLayoutParams(params);
                    edit.setImageResource(R.drawable.ic_baseline_edit_24);
                    enableEditing();
                    isEditing = false;
                }

            }
        });

        //username saving
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!changeUName.getText().toString().equals("")) {
                    String newUserName = changeUName.getText().toString();
                    if (isValidPassword(newUserName) == true) {
                        DataContainer.currentUser.setUsername(newUserName);
                        username.setText(DataContainer.currentUser.getUsername());
                        changeUName.setText("");
                        save.setVisibility(View.INVISIBLE);
                        changeUName.setVisibility(View.INVISIBLE);
                        LayoutParams params = user.getLayoutParams();
                        params.height = 250;
                        user.setLayoutParams(params);
                        edit.setImageResource(R.drawable.ic_baseline_edit_24);
                        isEditing = false;
                        enableEditing();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter username with minimum 6 characters", Toast.LENGTH_SHORT).show();
                        changeUName.setText("");
                    }
                } else {
                    username.setText(DataContainer.currentUser.getUsername());
                    Toast.makeText(getApplicationContext(), "Enter new username", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //email editing
        editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEditing == false) {
                    preventEditing(editEmail);
                    LayoutParams params = emailLayout.getLayoutParams();
                    params.height = 400;
                    emailLayout.setLayoutParams(params);
                    saveEmail.setVisibility(View.VISIBLE);
                    changedEmail.setVisibility(View.VISIBLE);
                    editEmail.setImageResource(R.drawable.ic_baseline_close_24);
                    isEditing = true;
                } else {
                    changedEmail.setText("");
                    saveEmail.setVisibility(View.INVISIBLE);
                    changedEmail.setVisibility(View.INVISIBLE);
                    LayoutParams params = emailLayout.getLayoutParams();
                    params.height = 250;
                    emailLayout.setLayoutParams(params);
                    editEmail.setImageResource(R.drawable.ic_baseline_edit_24);
                    enableEditing();
                    isEditing = false;
                }

            }
        });
        //username saving
        saveEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!changedEmail.getText().toString().equals("")) {
                    String newEmail = changedEmail.getText().toString();
                    if (isValidEmail(newEmail) == true) {
                        DataContainer.currentUser.setEmail(newEmail);
                        emailName.setText(DataContainer.currentUser.getEmail());
                        changedEmail.setText("");
                        saveEmail.setVisibility(View.INVISIBLE);
                        changedEmail.setVisibility(View.INVISIBLE);
                        LayoutParams params = emailLayout.getLayoutParams();
                        params.height = 250;
                        emailLayout.setLayoutParams(params);
                        editEmail.setImageResource(R.drawable.ic_baseline_edit_24);
                        isEditing = false;
                        enableEditing();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();
                        changedEmail.setText("");
                    }
                } else {
                    emailName.setText(DataContainer.currentUser.getEmail());
                    Toast.makeText(getApplicationContext(), "Please enter new valid email", Toast.LENGTH_SHORT).show();
                    changedEmail.setText("");
                }


            }
        });

        //phone no editing
        editPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEditing == false) {
                    preventEditing(editPhone);
                    LayoutParams params = phoneLayout.getLayoutParams();
                    params.height = 400;
                    phoneLayout.setLayoutParams(params);
                    savePhone.setVisibility(View.VISIBLE);
                    newPhone.setVisibility(View.VISIBLE);
                    editPhone.setImageResource(R.drawable.ic_baseline_close_24);
                    isEditing = true;
                } else {
                    newPhone.setText("");
                    savePhone.setVisibility(View.INVISIBLE);
                    newPhone.setVisibility(View.INVISIBLE);
                    LayoutParams params = phoneLayout.getLayoutParams();
                    params.height = 250;
                    phoneLayout.setLayoutParams(params);
                    editPhone.setImageResource(R.drawable.ic_baseline_edit_24);
                    enableEditing();
                    isEditing = false;
                }
            }
        });
        //username saving
        savePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!newPhone.getText().toString().equals("")) {
                    String newPhoneNo = newPhone.getText().toString();
                    if (isValidPhone(newPhoneNo) == true) {
                        DataContainer.currentUser.setPhoneNo(newPhoneNo);
                        currentPhone.setText(DataContainer.currentUser.getPhoneNo());
                        newPhone.setText("");
                        savePhone.setVisibility(View.INVISIBLE);
                        newPhone.setVisibility(View.INVISIBLE);
                        LayoutParams params = phoneLayout.getLayoutParams();
                        params.height = 250;
                        phoneLayout.setLayoutParams(params);
                        editPhone.setImageResource(R.drawable.ic_baseline_edit_24);
                        isEditing = false;
                        enableEditing();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter valid phone No", Toast.LENGTH_SHORT).show();
                        newPhone.setText("");
                    }
                } else {
                    currentPhone.setText(DataContainer.currentUser.getPhoneNo());
                    Toast.makeText(getApplicationContext(), "Please enter new valid phone No", Toast.LENGTH_SHORT).show();
                    newPhone.setText("");
                }


            }
        });

        //username editing
        editPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEditing == false) {
                    preventEditing(editPass);
                    LayoutParams params = passwordLayout.getLayoutParams();
                    params.height = 400;
                    passwordLayout.setLayoutParams(params);
                    savePass.setVisibility(View.VISIBLE);
                    newPass.setVisibility(View.VISIBLE);
                    editPass.setImageResource(R.drawable.ic_baseline_close_24);
                    isEditing = true;
                } else {
                    newPass.setText("");
                    savePass.setVisibility(View.INVISIBLE);
                    newPass.setVisibility(View.INVISIBLE);
                    LayoutParams params = passwordLayout.getLayoutParams();
                    params.height = 250;
                    passwordLayout.setLayoutParams(params);
                    editPass.setImageResource(R.drawable.ic_baseline_edit_24);
                    enableEditing();
                    isEditing = false;
                }

            }
        });
        //username saving
        savePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!newPass.getText().toString().equals("")) {
                    String newPassword = newPass.getText().toString();
                    if (isValidPassword(newPassword) == true) {
                        DataContainer.currentUser.setPassword(newPassword);
                        currentPass.setText(DataContainer.currentUser.getPassword());
                        newPass.setText("");
                        savePass.setVisibility(View.INVISIBLE);
                        newPass.setVisibility(View.INVISIBLE);
                        LayoutParams params = passwordLayout.getLayoutParams();
                        params.height = 250;
                        passwordLayout.setLayoutParams(params);
                        editPass.setImageResource(R.drawable.ic_baseline_edit_24);
                        isEditing = false;
                        enableEditing();
                    } else {
                        Toast.makeText(getApplicationContext(), "min 6 characters", Toast.LENGTH_SHORT).show();
                        newPass.setText("");
                    }
                } else {
                    currentPass.setText(DataContainer.currentUser.getPassword());
                    Toast.makeText(getApplicationContext(), "please enter new password", Toast.LENGTH_SHORT).show();
                    newPass.setText("");
                }
                savePass.setVisibility(View.INVISIBLE);
                newPass.setVisibility(View.INVISIBLE);
                LayoutParams params = passwordLayout.getLayoutParams();
                params.height = 250;
                passwordLayout.setLayoutParams(params);

            }
        });
    }


    private void setupToolbarAndMenu(View includer, String title, int checkedSection){

        //setup Toolbar
        TextView screenTitle = (TextView) includer.findViewById(R.id.screenTitle);
        screenTitle.setText(title);
        ImageView menuButton = (ImageView) includer.findViewById(R.id.menu_button);
        menuButton.setImageResource(R.drawable.menu_icon);

        //setup Navigation menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer, includer);
        menu.initialize(checkedSection);
        menu.EnableMenu();
    }



    public boolean isValidEmail(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }

    public boolean isValidPhone(String phone) {
        String regex = "^\\(\\d{3}\\) \\d{3}-\\d{4}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);

        return  matcher.matches();
    }
    public boolean isValidPassword(String pass)
    {
        //String pass = passwordEditText.getText().toString();
        if(pass.length() < 6)
        {

            return false;
        }

        return true;

    }
    public void preventEditing(ImageButton current){
        edit.setClickable(false);
        editPhone.setClickable(false);
        editEmail.setClickable(false);
        editPass.setClickable(false);
        current.setClickable(true);
    }

    public void enableEditing(){
        edit.setClickable(true);
        editPhone.setClickable(true);
        editEmail.setClickable(true);
        editPass.setClickable(true);
    }
}
