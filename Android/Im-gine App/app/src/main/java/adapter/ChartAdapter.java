package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.im_gine.R;
import java.util.ArrayList;
import model.Chart;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Chart> charts;

    public ChartAdapter(Context context, ArrayList<Chart> charts){
        this.context = context;
        this.charts = charts;
    }


    @NonNull
    @Override
    public ChartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_chart, parent, false);
        return new ChartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChartAdapter.ViewHolder holder, int position) {
        final Chart chart = charts.get(position);
        holder.chart_image.setImageResource(charts.get(position).getChart_image());
    }

    @Override
    public int getItemCount() {
        return charts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView chart_image;

        public ViewHolder(View itemView) {
            super(itemView);
            chart_image = itemView.findViewById(R.id.card_post_image);
        }
    }
}
