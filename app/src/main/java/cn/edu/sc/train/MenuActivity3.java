package cn.edu.sc.train;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity3 extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu3);
        textView=findViewById(R.id.textView2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(MenuActivity3.this,textView);
                MenuInflater inflater=popupMenu.getMenuInflater();
                inflater.inflate(R.menu.mymenu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.itemNew:
                                Toast.makeText(MenuActivity3.this,"新建菜单项",Toast.LENGTH_LONG).show();
                                return true;
                            case R.id.itemImport:
                                Toast.makeText(MenuActivity3.this,"导入菜单项",Toast.LENGTH_LONG).show();
                                return true;
                            case R.id.itemOpen:
                                Toast.makeText(MenuActivity3.this,"打开菜单项",Toast.LENGTH_LONG).show();
                                return true;
                            case R.id.itemPaste:
                                Toast.makeText(MenuActivity3.this,"粘贴菜单项",Toast.LENGTH_LONG).show();
                                return true;
                            case R.id.itemCopy:
                                Toast.makeText(MenuActivity3.this,"复制菜单项",Toast.LENGTH_LONG).show();
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });


        //registerForContextMenu(textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemNew:
                Toast.makeText(MenuActivity3.this,"新建菜单项",Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemImport:
                Toast.makeText(MenuActivity3.this,"导入菜单项",Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemOpen:
                Toast.makeText(MenuActivity3.this,"打开菜单项",Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemPaste:
                Toast.makeText(MenuActivity3.this,"粘贴菜单项",Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemCopy:
                Toast.makeText(MenuActivity3.this,"复制菜单项",Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemNew:
                Toast.makeText(MenuActivity3.this,"新建菜单项",Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemImport:
                Toast.makeText(MenuActivity3.this,"导入菜单项",Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemOpen:
                Toast.makeText(MenuActivity3.this,"打开菜单项",Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemPaste:
                Toast.makeText(MenuActivity3.this,"粘贴菜单项",Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemCopy:
                Toast.makeText(MenuActivity3.this,"复制菜单项",Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }
}