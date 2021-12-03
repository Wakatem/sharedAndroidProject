package com.example.project242.Settings;

import static com.example.project242.R.drawable.app_bar_color;
import static com.example.project242.R.drawable.ic_mtrl_chip_checked_black;
import static com.example.project242.R.drawable.new_color;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import 	android.view.ViewGroup.LayoutParams;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project242.Home.HomeSection;
import com.example.project242.R;
import com.example.project242.SectionsMenu;

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
        setupToolbarAndMenu(includer, "Settings");

        //username initialization
        username = findViewById(R.id.currentName);
        username.setText(HomeSection.currentUser.getUsername());
        user = (RelativeLayout)findViewById(R.id.thelayout);
        edit = findViewById(R.id.change);
        save = findViewById(R.id.save);
        changeUName = findViewById(R.id.changeUName);

        //email initialization
        emailName = findViewById(R.id.currentEmail);
        emailName.setText(HomeSection.currentUser.getEmail());
        emailLayout = findViewById(R.id.emailLayout);
        saveEmail = findViewById(R.id.saveEmail);
        editEmail = findViewById(R.id.changeEmail);
        changedEmail = findViewById(R.id.changedEmail);

        //phone initialization
        currentPhone = findViewById(R.id.currentPhone);
        currentPhone.setText(HomeSection.currentUser.getPhoneNo());
        phoneLayout = findViewById(R.id.phoneLayout);
        savePhone = findViewById(R.id.savePhone);
        editPhone = findViewById(R.id.changePhone);
        newPhone = findViewById(R.id.newPhone);

        //password initialization
        currentPass = findViewById(R.id.currentPass);
        currentPass.setText(HomeSection.currentUser.getPassword());
        passwordLayout = findViewById(R.id.passwordLayout);
        savePass = findViewById(R.id.savePass);
        editPass = findViewById(R.id.changePass);
        newPass = findViewById(R.id.newPass);

        if(isEditing==true){
            edit.setClickable(false);
            editPhone.setClickable(false);
            editEmail.setClickable(false);
            editPass.setClickable(false);
        }


        //username editing
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEditing=true;
                LayoutParams params = user.getLayoutParams();
                params.height = 400;
                user.setLayoutParams(params);
                save.setVisibility(View.VISIBLE);
                changeUName.setVisibility(View.VISIBLE);
            }
        });
        //username saving
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEditing=false;
                if(!changeUName.getText().toString().equals("")) {
                    String newUserName = changeUName.getText().toString();
                    HomeSection.currentUser.setUsername(newUserName);
                    username.setText(HomeSection.currentUser.getUsername());
                    changeUName.setText("");
                }
                else {
                    username.setText(HomeSection.currentUser.getUsername());
                }
                save.setVisibility(View.INVISIBLE);
                changeUName.setVisibility(View.INVISIBLE);
                LayoutParams params = user.getLayoutParams();
                params.height = 250;
                user.setLayoutParams(params);

            }
        });


        //email editing
        editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutParams params = emailLayout.getLayoutParams();
                params.height = 400;
                emailLayout.setLayoutParams(params);
                saveEmail.setVisibility(View.VISIBLE);
                changedEmail.setVisibility(View.VISIBLE);
            }
        });
        //username saving
        saveEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!changedEmail.getText().toString().equals("")) {
                    String newEmail = changedEmail.getText().toString();
                    if(isValidEmail(newEmail)==true) {
                        HomeSection.currentUser.setEmail(newEmail);
                        emailName.setText(HomeSection.currentUser.getEmail());
                        changedEmail.setText("");
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();
                        changedEmail.setText("");
                    }
                }
                else {
                    emailName.setText(HomeSection.currentUser.getEmail());
                }
                saveEmail.setVisibility(View.INVISIBLE);
                changedEmail.setVisibility(View.INVISIBLE);
                LayoutParams params = emailLayout.getLayoutParams();
                params.height = 250;
                emailLayout.setLayoutParams(params);

            }
        });

        //phone no editing
        editPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutParams params = phoneLayout.getLayoutParams();
                params.height = 400;
                phoneLayout.setLayoutParams(params);
                savePhone.setVisibility(View.VISIBLE);
                newPhone.setVisibility(View.VISIBLE);
            }
        });
        //username saving
        savePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!newPhone.getText().toString().equals("")) {
                    String newPhoneNo = newPhone.getText().toString();
                    if(isValidPhone(newPhoneNo)==true) {
                        HomeSection.currentUser.setPhoneNo(newPhoneNo);
                        currentPhone.setText(HomeSection.currentUser.getPhoneNo());
                        newPhone.setText("");


                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Please enter valid phone", Toast.LENGTH_SHORT).show();
                        newPhone.setText("");
                    }
                }
                else {
                    currentPhone.setText(HomeSection.currentUser.getPhoneNo());
                }
                savePhone.setVisibility(View.INVISIBLE);
                newPhone.setVisibility(View.INVISIBLE);
                LayoutParams params = phoneLayout.getLayoutParams();
                params.height = 250;
                phoneLayout.setLayoutParams(params);

            }
        });

        //username editing
        editPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutParams params = passwordLayout.getLayoutParams();
                params.height = 400;
                passwordLayout.setLayoutParams(params);
                savePass.setVisibility(View.VISIBLE);
                newPass.setVisibility(View.VISIBLE);
            }
        });
        //username saving
        savePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!newPass.getText().toString().equals("")) {
                    String newPassword = newPass.getText().toString();
                    if(isValidPassword(newPassword)==true) {
                        HomeSection.currentUser.setPassword(newPassword);
                        currentPass.setText(HomeSection.currentUser.getPassword());
                        newPass.setText("");
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "min 6 characters", Toast.LENGTH_SHORT).show();
                        newPass.setText("");
                    }
                }
                else {
                    currentPass.setText(HomeSection.currentUser.getPassword());
                }
                savePass.setVisibility(View.INVISIBLE);
                newPass.setVisibility(View.INVISIBLE);
                LayoutParams params = passwordLayout.getLayoutParams();
                params.height = 250;
                passwordLayout.setLayoutParams(params);

            }
        });


    }



    private void setupToolbarAndMenu(View includer, String title){

        //setup Toolbar
        TextView screenTitle = (TextView) includer.findViewById(R.id.screenTitle);
        screenTitle.setText(title);
        ImageView menuButton = (ImageView) includer.findViewById(R.id.menu_button);
        menuButton.setImageResource(R.drawable.menu_icon);

        //setup Navigation menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        SectionsMenu menu = new SectionsMenu(this, drawer, includer);
        menu.initialize();
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

}