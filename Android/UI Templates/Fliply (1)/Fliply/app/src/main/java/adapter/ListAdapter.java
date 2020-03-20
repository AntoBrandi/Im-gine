package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wolfsoft.fliply.Chat_fliply;
import com.wolfsoft.fliply.Home;
import com.wolfsoft.fliply.Login;
import com.wolfsoft.fliply.Messages_fliply;
import com.wolfsoft.fliply.ProductPage;
import com.wolfsoft.fliply.Profile;
import com.wolfsoft.fliply.R;
import com.wolfsoft.fliply.Search_Results_fliply;
import com.wolfsoft.fliply.Splashscreen_fliply;

import java.util.ArrayList;

import model.ListModel;

/**
 * Created by wolfsoft4 on 24/9/18.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    Context context;
    private ArrayList<ListModel> listModelArrayList;

    public ListAdapter(Context context, ArrayList<ListModel> listModelArrayList) {
        this.context = context;
        this.listModelArrayList = listModelArrayList;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position) {

        holder.title.setText(listModelArrayList.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(position==0){
                    Intent i = new Intent(context, Splashscreen_fliply.class);
                    context.startActivity(i);}
                if(position==1){
                    Intent i = new Intent(context, Login.class);
                    context.startActivity(i);}
                if(position==2){
                    Intent i = new Intent(context, Home.class);
                    context.startActivity(i);}
                if(position==3){
                    Intent i = new Intent(context, ProductPage.class);
                    context.startActivity(i);}
                if(position==4){
                    Intent i = new Intent(context, Chat_fliply.class);
                    context.startActivity(i);}
                if(position==5){
                    Intent i = new Intent(context, Messages_fliply.class);
                    context.startActivity(i);}
                if(position==6){
                    Intent i = new Intent(context, Profile.class);
                    context.startActivity(i);}
                if(position==7){
                    Intent i = new Intent(context, Search_Results_fliply.class);
                    context.startActivity(i);}



            }
        });

    }

    @Override
    public int getItemCount() {
        return listModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;


        public ViewHolder(View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
        }
    }
}
