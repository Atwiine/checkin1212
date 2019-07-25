package com.example.hesable.checkin1212;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DatingAdapter extends RecyclerView.Adapter<DatingAdapter.ViewHolder> {

    private Context mContext;
    private ClickHandler mClickHandler;
    private ArrayList<Daters> mData;
    private ArrayList<String> mDataId;
    private ArrayList<String> mSelectedItem;
    private View mEmptyView;



    public DatingAdapter(Context context, ArrayList<Daters> data, ArrayList<String> dataId, View emptyView,
                          ClickHandler handler) {


        mContext = context;
        mData = data;
        mDataId = dataId;
        mEmptyView = emptyView;
        mSelectedItem = new ArrayList<>();
        mClickHandler = handler;

    }
    public  void updateEmptyView(){
        if(mData.size()==0)
            mEmptyView.setVisibility(View.VISIBLE);
        else
            mEmptyView.setVisibility(View.GONE);


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder myholder, int i) {
        Daters daterAdapter = mData.get(i);
        myholder.FullName.setText( daterAdapter.getName());
        myholder.Phone.setText(daterAdapter.getPhone());
        myholder.itemView.setSelected(mSelectedItem.contains(mDataId.get(i)));

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        final TextView FullName;
        final TextView Phone;


        ViewHolder(View itemView){

            super(itemView);
            FullName = itemView.findViewById(R.id.fullname_id);
            Phone = itemView.findViewById(R.id.phone_id);


            itemView.setFocusable(true);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }


        @Override
        public void onClick(View v) {
            mClickHandler.onItemClick(getAdapterPosition());


        }

        @Override
        public boolean onLongClick(View v) {

            return mClickHandler.onItemLongClick(getAdapterPosition());
        }
    }
    interface ClickHandler{
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }

}
