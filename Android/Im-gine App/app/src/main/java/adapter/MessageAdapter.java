package adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.im_gine.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.List;
import model.Message;


public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int MSH_TYPE_LEFT = 0;
    private static final int MSH_TYPE_RIGHT = 1;

    // Firebase variables
    FirebaseUser firebaseUser;

    private Context context;
    private List<Message> messages;

    public MessageAdapter(Context context, List<Message> messages){
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSH_TYPE_RIGHT){
            View view = LayoutInflater.from(context).inflate(R.layout.item_chat_right, parent, false);
            return new MessageAdapter.ViewHolderRight(view);
        } else{
            View view = LayoutInflater.from(context).inflate(R.layout.item_chat_left, parent, false);
            return new MessageAdapter.ViewHolderLeft(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        if (holder.getItemViewType()==MSH_TYPE_RIGHT){
            ViewHolderRight viewHolder = (ViewHolderRight) holder;
            viewHolder.messageContent.setText(message.get_messageText());
            viewHolder.messageTime.setText((message.get_timestamp()));
            // TODO: change also the status if the message is received
        }
        else{
            ViewHolderLeft viewHolder = (ViewHolderLeft) holder;
            viewHolder.messageContent.setText(message.get_messageText());
            viewHolder.messageTime.setText((message.get_timestamp()));
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (messages.get(position).get_sender().equals(firebaseUser.getUid())){
            return MSH_TYPE_RIGHT;
        } else{
            return MSH_TYPE_LEFT;
        }
    }

    public class ViewHolderRight extends RecyclerView.ViewHolder{
        public TextView messageContent;
        public TextView messageTime;
        public ImageView messageStatus;

        public ViewHolderRight(View view){
            super(view);
            messageContent = view.findViewById(R.id.chat_message_item);
            messageTime = view.findViewById(R.id.chat_message_time);
            messageStatus = view.findViewById(R.id.chat_message_status);
        }
    }
    public class ViewHolderLeft extends RecyclerView.ViewHolder{
        public TextView messageContent;
        public TextView messageTime;

        public ViewHolderLeft(@NonNull View view) {
            super(view);
            messageContent = view.findViewById(R.id.chat_message_item);
            messageTime = view.findViewById(R.id.chat_message_time);
        }
    }
}
