package cn.edu.sc.train;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAdapter extends BaseAdapter {
    private ArrayList list;
    private Context context;
    public MyAdapter(Context context,ArrayList list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;
        ViewHolder holder;
        if(view==null){
            view1= LayoutInflater.from(context).inflate(R.layout.item_layout,viewGroup,false);
            holder=new ViewHolder();
            holder.image=view1.findViewById(R.id.imageView);
            holder.name=view1.findViewById(R.id.txtName);
            view1.setTag(holder);
        }else{
            view1=view;
            holder=(ViewHolder) view1.getTag();
        }


        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"文本被单击",Toast.LENGTH_LONG).show();
            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"图片被单击",Toast.LENGTH_LONG).show();
            }
        });
        HashMap map=(HashMap)getItem(i);
        holder.image.setImageResource((Integer) map.get("image"));
        holder.name.setText((CharSequence) map.get("name"));
       return view1;
    }
    class ViewHolder{
        ImageView image;
        TextView name;
    }
}
