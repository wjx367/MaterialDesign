package com.viewwang.materialdesign;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/2/17.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private List<String> mDataSet = null;
    private OnItemClickListener mListener;
    public RecycleViewAdapter(List<String> mDataSet) {
        this.mDataSet=mDataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final  View  itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null) {
                    mListener.onItemClick(v, (String) itemview.getTag());
                }
            }
        });
        itemview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        v.setTranslationZ(120);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.setTranslationZ(0);
                        break;
                    default:
                        return false;
                }

                return false;
            }
        });
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String data = mDataSet.get(position);
        holder.bindData(data);
        holder.itemView.setTag(data);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.tv);
        }
        public void bindData(String s)
        {
            if(s != null)
                tv.setText(s);
        }
    }
    public interface OnItemClickListener
    {
        public void onItemClick(View view,String data);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
    }
}
