package cn.edu.sc.scu_litepal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import cn.edu.sc.scu_litepal.db.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private EditText edtName,edtType,edtAge;
    private Button btnAdd,btnSearch;
    private ListView listUsers;
    private List<User> arrayList;
    private UserAdapter adapter;

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.drawer_layout);
        textView=findViewById(R.id.txtView);
        edtName=findViewById(R.id.edtName);
        edtAge=findViewById(R.id.edtAge);
        edtType=findViewById(R.id.edtType);

        drawerLayout=findViewById(R.id.drawer_layout);

        btnAdd=findViewById(R.id.btnAdd);
        btnSearch=findViewById(R.id.btnSearch);

        listUsers=findViewById(R.id.listUsers);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_help);
        }

        NavigationView n=findViewById(R.id.nav_view);
        n.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });

        //LitePal.getDatabase();
        arrayList=LitePal.findAll(User.class);

        adapter=new UserAdapter(MainActivity.this,R.layout.item_layout,arrayList);
        listUsers.setAdapter(adapter);

        btnSearch.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        listUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("您是否确认删除该条内容！");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index) {
                            User user=arrayList.get(i);
                            if(user.delete()!=0){
                                arrayList.remove(i);
                                adapter.notifyDataSetChanged();
                            }

                    }
                });
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                Dialog dialog=builder.create();
                dialog.show();

                return false;
            }
        });
        listUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                View dialogView= LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_layout,null);
                builder.setView(dialogView);
                Button btnSubmit=dialogView.findViewById(R.id.btnSubmit);
                Button btnCancle=dialogView.findViewById(R.id.btnCancle);
                final User user=arrayList.get(i);
                EditText edtName=dialogView.findViewById(R.id.edtName);
                edtName.setText(user.getUsername());
                final EditText edtType=dialogView.findViewById(R.id.edtType);
                edtType.setText(user.getType());
                final EditText edtAge=dialogView.findViewById(R.id.edtAge);
                edtAge.setText(""+user.getAge());
                final Dialog dialog=builder.create();
                dialog.show();
                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ContentValues contentValues=new ContentValues();
                        contentValues.put("age",edtAge.getText().toString());
                        contentValues.put("type",edtType.getText().toString());
                        LitePal.updateAll(User.class,contentValues,"username=?",user.getUsername());
                        arrayList.get(i).setAge(Integer.parseInt(edtAge.getText().toString()));
                        arrayList.get(i).setType(edtType.getText().toString());
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


            }
        });

    }
    @Override
    public void onClick(View view) {
        String username=edtName.getText().toString();
        String ageStr=edtAge.getText().toString();
        String type=edtType.getText().toString();
        switch (view.getId()){
            case R.id.btnAdd:
                if(TextUtils.isEmpty(username)){
                    edtName.setError("用户名不能为空！");
                }else if(TextUtils.isEmpty(ageStr)){
                    edtAge.setError("年龄不能为空！");
                }else if(TextUtils.isEmpty(type)){
                    edtType.setError("员工类别不能为空");
                }else{
                    User user=new User(username,Integer.parseInt(ageStr),type);
                    Log.i("litepal",user.toString());
                    if(user.save()){
                        arrayList.add(user);
                        adapter.notifyDataSetChanged();
                    }
                }

                break;
            case R.id.btnSearch:
                if(TextUtils.isEmpty(username)){
                    arrayList=LitePal.findAll(User.class);
                }else{
                    arrayList=LitePal.where("username=?",username).find(User.class);
                }
                adapter=new UserAdapter(MainActivity.this,R.layout.item_layout,arrayList);
                listUsers.setAdapter(adapter);
                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;

        }
        return true;
    }
}
