package cn.edu.sc.scu_litepal;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.edu.sc.scu_litepal.db.User;

public class UserAdapter extends BaseAdapter {
    private int resource;
    private Context context;
    private List<User> list;

    public UserAdapter(Context context,int resource,List<User> list) {
        this.resource = resource;
        this.context = context;
        this.list = list;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {

            View view;
            ViewHolder holder;
            if(convertView==null){
                view= LayoutInflater.from(context).inflate(R.layout.item_layout,viewGroup,false);
                holder=new ViewHolder();
                holder.txtAge=view.findViewById(R.id.txtAge);
                holder.txtName=view.findViewById(R.id.txtName);
                holder.txtType=view.findViewById(R.id.txtType);

                view.setTag(holder);
            }else{
                view=convertView;
                holder=(ViewHolder) view.getTag();
            }
            User user=(User)list.get(position);
            holder.txtType.setText(user.getType());
            holder.txtName.setText(user.getUsername());
            holder.txtAge.setText(""+user.getAge());

            return view;
        }
        class ViewHolder {
            TextView txtName,txtAge,txtType;
        }

}
