package com.example.hesable.checkin1212;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragamentVideos extends Fragment {

    View v;

    private RecyclerView myrecyclerview;
    private List<Contact> lstcontact;



    public FragamentVideos() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contact_fragrament,container,false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.contact_recyclerview);
        //the get contect method has been ste in a different recyclerview class

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),lstcontact);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstcontact = new ArrayList<>();
        lstcontact.add(new Contact("Harman","(111) 455 6776",R.drawable.download));
    }
}
