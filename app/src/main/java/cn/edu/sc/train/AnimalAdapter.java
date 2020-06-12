package cn.edu.sc.train;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {
    private ArrayList list;

    public AnimalAdapter(ArrayList list){
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item2_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        holder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap map=(HashMap)list.get(position);
        holder.imageView.setImageResource((Integer) map.get("image"));
        holder.txtName.setText((CharSequence) map.get("name"));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName;
        ImageView imageView;
        public ViewHolder(View view){
            super(view);
            txtName=view.findViewById(R.id.txtName);
            imageView=view.findViewById(R.id.imageView);
        }
    }
}
