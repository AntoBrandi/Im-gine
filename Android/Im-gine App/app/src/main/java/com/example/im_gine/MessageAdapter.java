package com.example.im_gine;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import model.Message;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private static final int MSH_TYPE_LEFT = 0;
    private static final int MSH_TYPE_RIGHT = 1;

    private Context mContext;
    private List<Message> mMessages;

    public MessageAdapter(Context mContext, List<Message> mMessages){
        this.mContext = mContext;
        this.mMessages = mMessages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView message;

        public ViewHolder(View itemView){
            super(itemView);
            message = itemView.findViewById(R.id.show_message);
        }
    }


}
