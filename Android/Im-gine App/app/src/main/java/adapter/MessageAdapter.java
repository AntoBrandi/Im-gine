package adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.im_gine.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.List;
import model.Message;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private static final int MSH_TYPE_LEFT = 0;
    private static final int MSH_TYPE_RIGHT = 1;

    // Firebase variables
    FirebaseUser firebaseUser;

    private Context mContext;
    private List<Message> mMessages;

    public MessageAdapter(Context mContext, List<Message> mMessages){
        this.mContext = mContext;
        this.mMessages = mMessages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSH_TYPE_RIGHT){
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        } else{
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = mMessages.get(position);
        holder.showMessage.setText(message.get_messageText());
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (mMessages.get(position).get_sender().equals(firebaseUser.getUid())){
            return MSH_TYPE_RIGHT;
        } else{
            return MSH_TYPE_LEFT;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView showMessage;

        public ViewHolder(View itemView){
            super(itemView);
            showMessage = itemView.findViewById(R.id.show_message);
        }
    }
}
