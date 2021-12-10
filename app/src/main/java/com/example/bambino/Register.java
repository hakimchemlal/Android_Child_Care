package com.example.bambino;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bambino.db.DatabaseHelper;
import com.example.bambino.model.User;
import com.example.bambino.R;



public class Register extends AppCompatActivity {

    private TextView member;
    private EditText firstname;
    private EditText lastname;
    private EditText email;
    private EditText password;
    private RadioGroup type;
    private Button register;


    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        openHelper = new DatabaseHelper(this);

        firstname = (EditText) findViewById(R.id.inputFname);
        lastname = (EditText) findViewById(R.id.inputLname);
        email = (EditText) findViewById(R.id.inputEmail);
        password = (EditText) findViewById(R.id.inputPassword);
        type = (RadioGroup) findViewById(R.id.inputUserType);

        register = (Button) findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = openHelper.getWritableDatabase();
                User userModel = new User();
                userModel.setFirstname(firstname.getText().toString());
                userModel.setLastname(lastname.getText().toString());
                userModel.setFullname(userModel.getFirstname() +" "+userModel.getLastname());
                userModel.setPassword(password.getText().toString());
                userModel.setEmail(email.getText().toString());

                if(type.getCheckedRadioButtonId() == R.id.parent){
                    userModel.setType("Parents");
                }else if(type.getCheckedRadioButtonId() == R.id.helper){
                    userModel.setType("Helper");
                }

                if(userModel.isEmpty()){
                    Toast.makeText(Register.this, "Fill in all the fields", Toast.LENGTH_SHORT).show();
                    firstname.setError("FN is required");
                    lastname.setError("LN is required");
                    email.setError("email is required");
                    password.setError("password is required");

                }else if(!Patterns.EMAIL_ADDRESS.matcher(userModel.getEmail()).matches()){
                    Toast.makeText(Register.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                    email.setError("Enter A valid Email");
                }else if(userModel.getPassword().length() < 8){
                    Toast.makeText(Register.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                    password.setError("Password must contain 8 character at least");
                }else {

                    if(insertData(userModel)) {
                        firstname.setText(null);
                        lastname.setText(null);
                        password.setText(null);
                        email.setText(null);
                        Toast.makeText(Register.this,"You Have Been Registred Successfully",Toast.LENGTH_SHORT).show();
                        userMember();
                    }else {
                        Toast.makeText(Register.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        member = (TextView) findViewById(R.id.member);
        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userMember();
            }
        });
    }

    public Boolean insertData(User user){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2,user.getFullname());
        contentValues.put(DatabaseHelper.COL_3,user.getType());
        contentValues.put(DatabaseHelper.COL_4,user.getEmail());
        contentValues.put(DatabaseHelper.COL_5,user.getPassword());

        long id = db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
        user.setId((int) id);
        if(id == -1) {
            return false;
        }

        return true;
    }

    private void userMember(){
        Intent openLogin = new Intent(Register.this, Login.class);
        startActivity(openLogin);
    }
}