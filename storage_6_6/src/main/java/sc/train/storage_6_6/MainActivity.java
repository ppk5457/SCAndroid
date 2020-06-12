package sc.train.storage_6_6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView txtName, txtAge, txtApart;
    private Button btnAdd;
    private ListView stuffListView;

    private MyDatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private ArrayList<Stuff> stuffList;
    private StuffAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtAge = findViewById(R.id.txtAge);
        txtApart = findViewById(R.id.txtApart);
        btnAdd = findViewById(R.id.btnAdd);
        stuffListView = findViewById(R.id.stuff_list_view);


//        //事先建好数据库+并新建Stuff表
        databaseHelper = new MyDatabaseHelper(this, "StuffInfo.db", null, 1);
        database = databaseHelper.getWritableDatabase();
        queryAndRefreshView();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行两步操作：添加数据到DB,并且更新员工列表
                String name = txtName.getText().toString();
                String age = txtAge.getText().toString();
                String apartment = txtApart.getText().toString();
                if(name.equals("") || age.equals("") || apartment.equals("")){
                    Toast.makeText(MainActivity.this,"信息不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                database.execSQL("insert into Stuff (name, age, apartment) values (?, ?, ?)",
                        new String[]{name, age, apartment});

                Toast.makeText(MainActivity.this,"员工 " + name +" 信息添加成功!",Toast.LENGTH_SHORT).show();

                txtName.setText("");
                txtAge.setText("");
                txtApart.setText("");

                //添加数据后更新列表
                queryAndRefreshView();
            }
        });

    }


    //查询Stuff表中的所有数据
    private ArrayList<Stuff> queryStuffFromDB(){
        if(stuffList != null){
            stuffList = null;
        }
        stuffList = new ArrayList<Stuff>();
        Cursor cursor = database.query("Stuff",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String age = cursor.getString(cursor.getColumnIndex("age"));
                String apart = cursor.getString(cursor.getColumnIndex("apartment"));
                Stuff stuff = new Stuff(name, age,apart);
                stuffList.add(stuff);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return stuffList;
    }

    private void initStuffListView(final ArrayList<Stuff> data){
        if(adapter != null){
            adapter = null;
        }
        adapter = new StuffAdapter(MainActivity.this, data);
        stuffListView.setAdapter(adapter);
        stuffListView.setClickable(false);
    }


    private void queryAndRefreshView(){
        //查询得到数据库中的员工信息
        queryStuffFromDB();
        initStuffListView(stuffList);
    }

    //内部类：便于直接调用方法和数据
    class StuffAdapter extends BaseAdapter {

        private ArrayList<Stuff> stuffList;

        private Context context;

        private SQLiteDatabase database;

        public StuffAdapter(@NonNull Context context, ArrayList<Stuff> list) {
            this.stuffList = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return stuffList.size();
        }

        @Override
        public Object getItem(int position) {
            return stuffList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final Stuff stuff = (Stuff) getItem(position);
            View view;
            if (convertView == null) {
                view = LayoutInflater.from(context).inflate(R.layout.stuff_item, parent, false);
            } else {
                view = convertView;
            }
            TextView nameItem = view.findViewById(R.id.nameItem);
            TextView ageItem = view.findViewById(R.id.ageItem);
            TextView apartItem = view.findViewById(R.id.apartItem);
            ImageView imgChange = view.findViewById(R.id.imgChange);
            ImageView imgDelete = view.findViewById(R.id.imgDelete);

            nameItem.setText(stuff.getName());
            ageItem.setText(stuff.getAge());
            apartItem.setText(stuff.getApartment());

            nameItem.setClickable(false);
            ageItem.setClickable(false);
            ageItem.setClickable(false);

            imgChange.setImageResource(R.drawable.change);
            imgDelete.setImageResource(R.drawable.delete);
            imgChange.setClickable(true);
            imgDelete.setClickable(true);
            imgChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowChangeDialog(stuff.getName());
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowDeleteDialog(stuff.getName(), stuff.getApartment());
                }
            });

            return view;
        }

        //修改员工信息对话框
        private void ShowChangeDialog(String name){

            LayoutInflater factory = LayoutInflater.from(MainActivity.this);
            View changeDialogView = factory.inflate(R.layout.update_dialog,null);

            final EditText edtName = changeDialogView.findViewById(R.id.edtChangeName);
            final EditText edtAge = changeDialogView.findViewById(R.id.edtChangeAge);
            final EditText edtApart = changeDialogView.findViewById(R.id.edtChangeApart);
            edtName.setText(name);
            edtName.setEnabled(false);

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("修改员工信息：");
            builder.setIcon(R.drawable.change);
            builder.setView(changeDialogView);

            builder.setPositiveButton("修  改", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    database = databaseHelper.getWritableDatabase();

                    //数据库更新操作
                    String name = edtName.getText().toString();
                    String age = edtAge.getText().toString();
                    String apart = edtApart.getText().toString();


                    if(name.equals("") || age.equals("") || apart.equals("")){
                        Toast.makeText(MainActivity.this,"输入信息无效！请重新修改！",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String updateSQL = "update Stuff set age = " + age +
                            ", apartment = '" + apart +
                            "' where name = '" + name + "'" ;
                    database.execSQL(updateSQL);

                    //重新获取数据、刷新列表
                    queryAndRefreshView();
                }
            });

            builder.setNegativeButton("取  消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.create().show();
        }

        //显示删除确认框
        private void ShowDeleteDialog(final String name, final String apartment){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setIcon(R.drawable.delete);
            builder.setTitle("提示");
            builder.setMessage("将要删除员工 " + name + " 的信息？");

            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    database = databaseHelper.getWritableDatabase();

                    String deleteSQL = "delete from Stuff where name = '" + name +"' and apartment = '" + apartment + "'";
                    database.execSQL(deleteSQL);
                    Toast.makeText(MainActivity.this, "已删除 " + name + " 信息", Toast.LENGTH_SHORT).show();
                    //重新获取数据、刷新列表
                    queryAndRefreshView();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "取消" + which, Toast.LENGTH_SHORT).show();
                }
            });
            builder.create().show();
        }
    }
}
