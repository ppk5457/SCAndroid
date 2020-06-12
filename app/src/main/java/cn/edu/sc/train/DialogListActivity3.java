package cn.edu.sc.train;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import cn.edu.sc.train.fragment.MainFragmentActivity;

public class DialogListActivity3 extends ListActivity {
    private String[] uilist={"标准Dialog","自定义Dialog",
            "菜单","ListView","RecyclerView","横向滚动RecyclerView","网格布局RecyclerView","瀑布流","Fragment","ViewPager"};
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new ArrayAdapter(DialogListActivity3.this,android.R.layout.simple_list_item_1,uilist);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position){
            case 0:
                AlertDialog.Builder builder=new AlertDialog.Builder(DialogListActivity3.this);
                //builder.setIcon(android.R.drawable.btn_star);
                //builder.setTitle("你喜欢实训课程么？");
                builder.setMessage("我非常喜欢我选择的课程！");
               // builder.setSingleChoiceItems()

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
                break;
            case 1:
//                AlertDialog.Builder builder1=new AlertDialog.Builder(DialogListActivity3.this);
//                View view=getLayoutInflater().inflate(R.layout.activity_main,null);
//                final EditText edtName=view.findViewById(R.id.edtUsername);
//                Button btn=view.findViewById(R.id.btnLogin);
//                btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(DialogListActivity3.this,edtName.getText().toString(),Toast.LENGTH_LONG).show();
//                    }
//                });
//                builder1.setView(view).create().show();
                Intent intent =new Intent(DialogListActivity3.this,MainActivity.class);
                startActivity(intent);
                break;
            case 2:
                Intent intent1 =new Intent(DialogListActivity3.this,MenuActivity3.class);
                startActivity(intent1);
                break;
            case 3:
                Intent intent2 =new Intent(DialogListActivity3.this,ListViewShowActivity.class);
                startActivity(intent2);
                break;
            case 4:
                Intent intent3 =new Intent(DialogListActivity3.this,RecyclerActivity.class);
                startActivity(intent3);
                break;
            case 5:
                Intent intent4 =new Intent(DialogListActivity3.this,RecyclerActivity2.class);
                startActivity(intent4);
                break;
            case 6:
                Intent intent5 =new Intent(DialogListActivity3.this,RecyclerActivity3.class);
                startActivity(intent5);
                break;
            case 7:
                Intent intent6 =new Intent(DialogListActivity3.this,RecyclerActivity4.class);
                startActivity(intent6);
                break;
            case 8:
                Intent intent7 =new Intent(DialogListActivity3.this, MainFragmentActivity.class);
                startActivity(intent7);
                break;
            case 9:
                Intent intent8 =new Intent(DialogListActivity3.this, ViewPagerActivity3.class);
                startActivity(intent8);
                break;




        }
    }
}