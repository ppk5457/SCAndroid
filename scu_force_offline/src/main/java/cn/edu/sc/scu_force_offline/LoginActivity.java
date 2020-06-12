package cn.edu.sc.scu_force_offline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends BaseActivity {
    private EditText edtUsername,edtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtPassword=findViewById(R.id.edtPassword);
        edtUsername=findViewById(R.id.edtUsername);
        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edtUsername.getText().toString())){
                   edtUsername.setError("用户名不能为空！");
                }else if(TextUtils.isEmpty(edtPassword.getText().toString())){
                    edtPassword.setError("密码不能为空！");
                }else{
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}