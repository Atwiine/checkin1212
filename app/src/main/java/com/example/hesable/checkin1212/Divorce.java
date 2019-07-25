package com.example.hesable.checkin1212;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Divorce extends AppCompatActivity {

    String[] NAMES ={"FIDA","UWONET","MIFUMI","NETPIL","Uganda Law Society","Law advocacy for women in Uganda"};
    String[] DECRIPTION ={"..........","...........","...........",".............",".........","..........."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divorce);

        ListView listView = (ListView)findViewById(R.id.listview);

        CustomerAdapter customerAdapter = new CustomerAdapter();
        listView.setAdapter(customerAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // need to find out how to connect each organization to its respective site pages
                Toast.makeText(Divorce.this,"Organistion"+NAMES[i].toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    class  CustomerAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
            //return IMAGES.Length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        //have to fing the company logos to use as images

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout_divorce,null,false);

            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            TextView textView_name =(TextView)view.findViewById(R.id.textView_name);
            TextView textView_descrpition =(TextView)view.findViewById(R.id.textView_description);

            //imageView.setImageResource(IMAGES[i]);
            textView_name.setText(NAMES[i]);
            textView_descrpition.setText((DECRIPTION[i]));
            return view;

        }
    }
}
