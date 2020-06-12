package cn.edu.sc.train;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerActivity2 extends AppCompatActivity {

    private ArrayList list;
    private String[] animal={"鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪"};
    private int[] images={R.drawable.su,R.drawable.niu,R.drawable.hu,R.drawable.tu,
            R.drawable.dragon,R.drawable.se,R.drawable.ma,R.drawable.yang,R.drawable.hou,
            R.drawable.ji,R.drawable.gou,R.drawable.zhu};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initArrayList();
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        AnimalAdapter animalAdapter=new AnimalAdapter(list);
        recyclerView.setAdapter(animalAdapter);
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