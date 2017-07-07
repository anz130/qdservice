package com.paopao.roblistapp.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.paopao.roblistapp.R;
import com.paopao.roblistapp.base.BaseBaseAdapter;
import com.paopao.roblistapp.model.IndentInfo;
import com.paopao.roblistapp.model.IndentResult;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */

public class IndentAdapter extends BaseBaseAdapter<IndentInfo> {
    private final LayoutInflater layoutInflater;

    public IndentAdapter(Context context, List<IndentInfo> list) {
        super(list);
        layoutInflater = LayoutInflater.from(context);
    }

    public void addData(List<IndentInfo> data){
        getList().addAll(data);
        notifyDataSetChanged();
    }

    public void clear(){
        getList().clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.scramble_alone_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        IndentInfo indentInfo = getList().get(position);
        viewHolder.sendLocation.setText(indentInfo.getSendLocation());
        viewHolder.receiveLocation.setText(indentInfo.getReceiveLocation());
        viewHolder.price.setText(indentInfo.getPrice());
        viewHolder.date.setText(indentInfo.getDate());
        viewHolder.state.setText(indentInfo.getState());
        viewHolder.distance.setText(indentInfo.getDistance());
        viewHolder.startstate.setText(indentInfo.getStartstate());
        return convertView;
    }
    public int getItemCount() {
        return getList().size();
    }

    public static class ViewHolder {
        private final TextView startstate;
        private TextView price;
        private TextView state;
        private TextView sendLocation;
        private TextView receiveLocation;
        private TextView date;
        private TextView distance;

        public ViewHolder(View convertView) {
            price = (TextView) convertView.findViewById(R.id.price);
            state = (TextView) convertView.findViewById(R.id.state);
            sendLocation = (TextView) convertView.findViewById(R.id.sendLocation);
            receiveLocation = (TextView) convertView.findViewById(R.id.receiveLocation);
            date = (TextView) convertView.findViewById(R.id.date);
            distance = (TextView) convertView.findViewById(R.id.distance);
            startstate = (TextView) convertView.findViewById(R.id.startstate);
        }
    }
}
