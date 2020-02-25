package edu.temple.vsowlnetchat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ch.ethz.inf.vs.a3.solution.message.Message;

public class MessageAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Message> msgArr;

    public MessageAdapter(Context context, ArrayList<Message> msgArr) {
        this.context = context;
        this.msgArr = msgArr;
    }

    @Override
    public int getCount() {
        return this.msgArr.size();
    }

    @Override
    public Object getItem(int position) {
        return msgArr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(msgArr.get(position).toString());
        textView.setTextSize(20);
        return textView;
    }
}
