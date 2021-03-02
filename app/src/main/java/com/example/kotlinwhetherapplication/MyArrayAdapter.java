package com.example.kotlinwhetherapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends BaseAdapter {

    private ArrayList<String> mListItems;
    private LayoutInflater mInflater;
    private static List<Boolean> checkSelected;    // store select/unselect information about the values in the list
    private static List<Integer> showNow;
    private ViewHolder holder;
    final String ALL = "all";
    final String ACTIVE = "active";
    final String COMPLETED = "completed";
    private String mode;


    public static List<Boolean> getCheckSelected(){
        return checkSelected;
    }
    public static Boolean getCheckSelected(int position){
        return checkSelected.get(position);
    }
    public ArrayList<String> getListItems(){
        return mListItems;
    }

    public void setCheckSelected(int position, boolean value){
        checkSelected.set(position, value);
    }
    public void addIntoCheckSelected(boolean value){
        checkSelected.add(value);
    }
    public void addIntoListItems(String value){
        mListItems.add(value);
    }

    public void setListItems(ArrayList<String> list){
        mListItems = list;
    }


    public void setModeSelected(String value){
        mode = value;
    }

    private void setShowNow(ArrayList<Integer> value){
        showNow.clear();
        showNow.addAll(value);
    }

    public void setShowNow(){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        switch (mode){
            case ALL:
                for(int i = 0; i < mListItems.size(); i++){
                    temp.add(i);
                }
                break;
            case ACTIVE:
                for(int i = 0; i < mListItems.size(); i++){
                    if(!checkSelected.get(i)) {
                        temp.add(i);
                    }
                }
                break;
            case COMPLETED:
                for(int i = 0; i < mListItems.size(); i++){
                    if(checkSelected.get(i)) {
                        temp.add(i);
                    }
                }
                break;
        }
        setShowNow(temp);
    }

    public MyArrayAdapter(Context context){
        mListItems = new ArrayList<String>();
        mInflater = LayoutInflater.from(context);
        checkSelected = new ArrayList<Boolean>();
        showNow = new ArrayList<Integer>();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (showNow == null) return 0;
        return showNow.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.drop_down_list_row, null);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.SelectOption);
            holder.chkbox = (CheckBox) convertView.findViewById(R.id.checkbox);
            holder.imbtn = (ImageButton) convertView.findViewById(R.id.deleteButton);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(mListItems.get(showNow.get(position)));
        if(checkSelected.get(showNow.get(position))){
            holder.tv.setTextColor(Color.GRAY);
        }
        else{
            holder.tv.setTextColor(Color.BLACK);
        }
        holder.chkbox.setChecked(checkSelected.get(showNow.get(position)));

        Log.println(Log.INFO, "MyLog", "Adapter getView");

        //whenever the checkbox is clicked the selected values textview is updated with new selected values
        holder.chkbox.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            Log.println(Log.INFO, "MyLog", "Adapter checkbox clickListener");
            checkSelected.set(showNow.get(position), !checkSelected.get(showNow.get(position)));
            Log.println(Log.INFO, "MyLog", String.valueOf(position));
            for (int i = 0; i < checkSelected.size(); i++) {
                Log.println(Log.INFO, "MyLog", String.valueOf(checkSelected.size()) +
                        " - " + String.valueOf(i) + "   " + checkSelected.get(i));
            }
            setShowNow();
            notifyDataSetChanged();
        });

        holder.imbtn.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            Log.println(Log.INFO, "MyLog", "Adapter button clickListener");
            int pos = showNow.get(position);
            mListItems.remove(pos);
            checkSelected.remove(pos);
            setShowNow();
            notifyDataSetChanged();
        });
        return convertView;
    }

    private static class ViewHolder {
        TextView tv;
        CheckBox chkbox;
        ImageButton imbtn;
    }
}