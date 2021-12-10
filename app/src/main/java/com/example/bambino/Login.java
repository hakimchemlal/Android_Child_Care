package com.example.bambino;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bambino.db.DatabaseHelper;
import com.example.bambino.model.User;
import com.example.bambino.R;

public class Login extends AppCompatActivity {

    private TextView notmember;
    private EditText email;
    private EditText password;
    private Button login;
    private TextView log;

    private SQLiteDatabase db;
    private SQLiteOpenHelper openHelper;
    private Cursor cursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();

        email = (EditText) findViewById(R.id.inputEmail);
        password = (EditText) findViewById(R.id.inputPassword);

        login = (Button) findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = email.getText().toString();
                String pass = password.getText().toString();

                if(mail.isEmpty() || pass.isEmpty()){
                    Toast.makeText( Login.this, "Fill in all fields", Toast.LENGTH_SHORT).show();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                    email.setError("Enter A valid Email");
                }else{

                    cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE  Gmail = ? AND Password = ?", new String[]{mail, pass});
                    if(cursor.getCount() > 0){
                        User user = new User();
                        user.setFullname(cursor.getString((int) cursor.getColumnIndex(DatabaseHelper.COL_2)));
                        user.setEmail(cursor.getString((int) cursor.getColumnIndex(DatabaseHelper.COL_4)));
                        user.setType(cursor.getString((int) cursor.getColumnIndex(DatabaseHelper.COL_3)));
                        for(int i = 0 ; i < 4; i++){
                            System.out.println(cursor.getString(i));
                        }

                        email.setText(null);
                        password.setText(null);
                        loginMember();
                    }else{
                        Toast.makeText(Login.this, "Password or email is incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        notmember = (TextView) findViewById(R.id.notMember);
        notmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNotMember();
            }
        });

        log = (TextView) findViewById(R.id.log);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLog();
            }
        });

    }

    private void userNotMember(){
        Intent openRegister = new Intent(this, Register.class);
        startActivity(openRegister);
        this.finish();
    }

    private void loginMember(){
        Intent openNextActivity = new Intent(this, PickAgeRange.class);
        startActivity(openNextActivity);
        this.finish();
    }

    private void userLog(){
        Intent openLog = new Intent(this, PickAgeRange.class);
        startActivity(openLog);
        this.finish();
    }

}
