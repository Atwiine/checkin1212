package com.example.hesable.checkin1212;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Register_dating extends AppCompatActivity {

    TextView textClose;
    EditText Uname, Fname, Phone, Location,birth,Lname,Pass;
    Button Create;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_dating);

        Uname = findViewById(R.id.dating_username);
        Fname = findViewById(R.id.dating_firstname);
        Lname = (EditText)findViewById(R.id.dating_lastname);
        Phone = (EditText)findViewById(R.id.dating_contact);

        Location = findViewById(R.id.dating_location);
       birth = (EditText) findViewById(R.id.dating_birth);
       Pass = (EditText) findViewById(R.id.dating_password);
        mAuth = FirebaseAuth.getInstance();

        textClose = findViewById(R.id.txtlogin);
        Create = findViewById(R.id.register);

        //need to add firebase code on this button
        Create.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View view) {

                final String Firstname = Fname.getText().toString();
                final String Locate = Location.getText().toString();
                final String Contact = Phone.getText().toString();
                final String UName = Uname.getText().toString();
                final String Lastname = Lname.getText().toString();
                final String birthdate = birth.getText().toString();
                final String Password = Pass.getText().toString();


                if (Firstname.isEmpty() && Fname.length() < 3) {
                    Toast.makeText(Register_dating.this, "Please enter name that is not less than three character", Toast.LENGTH_LONG).show();
                } else if (Locate.isEmpty()) {
                    Toast.makeText(Register_dating.this, "Please enter your Address ", Toast.LENGTH_LONG).show();

                } else if (Contact.isEmpty()) {
                    Toast.makeText(Register_dating.this, "Please enter phone number", Toast.LENGTH_LONG).show();

                } else if (UName.isEmpty()) {
                    Toast.makeText(Register_dating.this, "Please enter User name", Toast.LENGTH_LONG).show();
                } else if (Lastname.isEmpty()) {
                    Toast.makeText(Register_dating.this, "Please enter Last name", Toast.LENGTH_LONG).show();


                } else if (birthdate.isEmpty()) {
                    Toast.makeText(Register_dating.this, "Please enter Date of birth", Toast.LENGTH_LONG).show();
                } else if (Password.isEmpty()) {
                    Toast.makeText(Register_dating.this, "Incorrect password", Toast.LENGTH_LONG).show();


                } else {
                    final ProgressDialog dialog = new ProgressDialog(Register_dating.this);
                    dialog.setTitle("CREATING ACCOUNT");
                    dialog.setMessage("Please wait........");
                    dialog.show();
                    mAuth.createUserWithEmailAndPassword(UName,Password).addOnCompleteListener(Register_dating.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                dialog.dismiss();
                                Toast.makeText(Register_dating.this, "Account creation failed", Toast.LENGTH_LONG).show();
                                //Log.e(this,"")
                            } else {
                                String users = mAuth.getCurrentUser().getUid();
                                Map userInformation = new HashMap();
                                userInformation.put("FullName", Firstname);
                                userInformation.put("Loaction", Locate);
                                userInformation.put("Phonenumber", Contact);
                                userInformation.put("UserName", UName);
                                userInformation.put("LastName", Lastname);
                                userInformation.put("Age", birthdate);
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Dating_Users").child(users);
                                databaseReference.setValue(userInformation);
                                Toast.makeText(Register_dating.this, "Account creation successfull", Toast.LENGTH_LONG).show();


                                Intent intent = new Intent(Register_dating.this, Login_dating.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        }
                    });

                }
            }
        });

    }
}
