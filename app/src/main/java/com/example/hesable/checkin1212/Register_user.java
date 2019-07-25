package com.example.hesable.checkin1212;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Register_user extends AppCompatActivity {

    TextView textClose;
    EditText Uname, Fname, Phone, Location, Password, ConfPass;
    Button Create;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        Uname = findViewById(R.id.user_name);
        Fname = findViewById(R.id.fullname);
        Phone = findViewById(R.id.phone_number);
        Location = findViewById(R.id.location);
        Password = findViewById(R.id.password);
        ConfPass = findViewById(R.id.confirm_password);
        mAuth = FirebaseAuth.getInstance();

        textClose = findViewById(R.id.txtlogin);
        Create = findViewById(R.id.register);

        //need to add firebase code on this button
        Create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View view) {

                final String Firstname = Fname.getText().toString();
                final String Locate = Location.getText().toString();
                final String Contact = Phone.getText().toString();
                final String UName = Uname.getText().toString();
                final String Pass = Password.getText().toString();
                final String CPass = ConfPass.getText().toString();

                if (Firstname.isEmpty() && Fname.length() < 3) {
                    Toast.makeText(Register_user.this, "Please enter name that is not less than three character", Toast.LENGTH_LONG).show();
                } else if (Locate.isEmpty()) {
                    Toast.makeText(Register_user.this, "Please enter your NIN ", Toast.LENGTH_LONG).show();

                } else if (Contact.isEmpty()) {
                    Toast.makeText(Register_user.this, "Please enter phone number", Toast.LENGTH_LONG).show();

                } else if (UName.isEmpty()) {
                    Toast.makeText(Register_user.this, "Please enter User name", Toast.LENGTH_LONG).show();

                } else if (Pass.isEmpty()) {
                    Toast.makeText(Register_user.this, "Please enter password", Toast.LENGTH_LONG).show();

                } else if (!CPass.equals(Pass)) {
                    Toast.makeText(Register_user.this, "Password fields not matching", Toast.LENGTH_LONG).show();

                } else {
                    final ProgressDialog dialog = new ProgressDialog(Register_user.this);
                    dialog.setTitle("CREATING ACCOUNT");
                    dialog.setMessage("Please wait........");
                    dialog.show();
                    mAuth.createUserWithEmailAndPassword(UName, Pass).addOnCompleteListener(Register_user.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                dialog.dismiss();
                                Toast.makeText(Register_user.this, "Account creation failed", Toast.LENGTH_LONG).show();
                                //Log.e("hahah","account creation");
                            } else {
                                String users = mAuth.getCurrentUser().getUid();
                                Map userInformation = new HashMap();
                                userInformation.put("FullName", Fname);
                                userInformation.put("Location", Location);
                                userInformation.put("Phonenumber", Contact);
                                userInformation.put("UserName", UName);
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App_Users").child(users);
                                databaseReference.setValue(userInformation);
                                Toast.makeText(Register_user.this, "Account creation successfull", Toast.LENGTH_LONG).show();


                                Intent intent = new Intent(Register_user.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });

                }
            }
        });


        textClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register_user.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

