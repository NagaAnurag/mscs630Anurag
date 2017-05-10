package in.co.tpsolutions.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Anurag on 3/21/2016.
 */
public class suggestionRecyclerAdapter extends RecyclerView.Adapter<suggestionRecyclerAdapter.suggestionRecyclerViewHolder> {

    String[] bm_name,bm_email,bm_gender,bm_pho,bm_age,bm_city,bm_nationality,bm_post_type;

    public suggestionRecyclerAdapter(String[] bm_name, String[] bm_email){
        this.bm_name = bm_name;
        this.bm_email = bm_email;
    }
    @Override
    public suggestionRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestions_layout, parent, false);
        suggestionRecyclerViewHolder suggestionRecyclerViewHolder = new suggestionRecyclerViewHolder(view);
        return suggestionRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(suggestionRecyclerViewHolder holder, int position) {
        holder.tx_name_data.setText(bm_name[position]);
        holder.tx_email_data.setText(bm_email[position]);
    }

    @Override
    public int getItemCount() {
        return bm_email.length;
    }

    public static class suggestionRecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView tx_name_data,tx_email_data;
        public suggestionRecyclerViewHolder(View view){
            super(view);
           // tx_name_data =(TextView) view.findViewById(R.id.bm_name_Data);
            //tx_email_data =(TextView) view.findViewById(R.id.bm_email_Data);
        }
    }
}
