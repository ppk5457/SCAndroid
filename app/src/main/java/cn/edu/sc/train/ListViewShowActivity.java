package cn.edu.sc.train;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewShowActivity extends AppCompatActivity {
    private ListView listView;
   // private ArrayAdapter arrayAdapter;
   // private SimpleAdapter adapter;
    private MyAdapter adapter;
    private ArrayList list;
    private String[] animal={"鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪"};
    private int[] images={R.drawable.su,R.drawable.niu,R.drawable.hu,R.drawable.tu,
    R.drawable.dragon,R.drawable.se,R.drawable.ma,R.drawable.yang,R.drawable.hou,
    R.drawable.ji,R.drawable.gou,R.drawable.zhu};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_show);

       // arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,animal);
        listView=findViewById(R.id.listView);
        initArrayList();
//        adapter=new SimpleAdapter(ListViewShowActivity.this,
//                list,
//                R.layout.item_layout,
//                new String[]{"name","image"},
//                new int[]{R.id.txtName,R.id.imageView}
//                );
        adapter=new MyAdapter(ListViewShowActivity.this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               String message="你选择的属相是："+animal[i];
                Toast.makeText(ListViewShowActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
    public void initArrayList(){
        list=new ArrayList();

        for(int i=0;i<12;i++){
            HashMap map=new HashMap();
            map.put("name",animal[i]);
            map.put("image",images[i]);
            list.add(map);
        }

    }
}