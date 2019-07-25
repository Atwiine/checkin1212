package com.example.hesable.checkin1212;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_dating extends AppCompatActivity {


    TextView textRegister;
    EditText name, Pass;
    Button Login;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dating);

        name = findViewById(R.id.username);
        Pass = findViewById(R.id.password);
        Login = findViewById(R.id.login_btn);
        textRegister = findViewById(R.id.txtregister);
        mAuth = FirebaseAuth.getInstance();

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_dating.this, Register_dating.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String password = Pass.getText().toString();
                if(username.isEmpty()){
                    Toast.makeText(Login_dating.this,"Please field username is empty",Toast.LENGTH_LONG).show();

                } else  if(password.isEmpty()){
                    Toast.makeText(Login_dating.this,"Please field password is empty",Toast.LENGTH_LONG).show();

                }else {
                    final ProgressDialog dialog = new ProgressDialog(Login_dating.this);
                    dialog.setTitle("SIGNING IN");
                    dialog.setMessage("Please wait..........");
                    dialog.show();
                    mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(Login_dating.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        dialog.dismiss();
                                        Toast.makeText(Login_dating.this,"Login failed",Toast.LENGTH_LONG).show();

                                    }else {
                                        Intent homescreen = new Intent(Login_dating.this,DatingSection.class);
                                        startActivity(homescreen);
                                        dialog.dismiss();
                                    }
                                }
                            });
                }
            }
        });

    }
}


