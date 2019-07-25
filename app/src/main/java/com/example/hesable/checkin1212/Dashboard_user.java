package com.example.hesable.checkin1212;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class Dashboard_user extends AppCompatActivity {

    CardView senga, fida, loveTips,dating;
   // private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        dating  = (CardView) findViewById(R.id.dating);
        loveTips = (CardView) findViewById(R.id.love);
        fida = (CardView) findViewById(R.id.fida);
        senga = findViewById(R.id.couselor);

        senga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_user.this, SengaSection.class);
                startActivity(intent);
            }
        });

        loveTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_user.this, Lovetips.class);
                startActivity(intent);
            }
        });

        dating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_user.this,Login_dating.class);
                startActivity(intent);
            }
        });
        fida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_user.this,Divorce.class);
                startActivity(intent);
            }
        });

    }


}
