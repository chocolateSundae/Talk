package com.example.xiu.talk;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVOSCloud;
import java.util.Timer;
import java.util.TimerTask;
public class LoginActivity extends AppCompatActivity {

    String userNameFormat;
    String userPwdFormat;

    Handler handlerLogin = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.arg1 == 1 ){
                Toast.makeText(LoginActivity.this,"LoginSuccess",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }else{
                Toast.makeText(LoginActivity.this,"LoginFailed",Toast.LENGTH_SHORT).show();
            }
        }
    };

    Handler handlerSignInCN = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.arg1 == 1){
                Toast.makeText(LoginActivity.this,"CNSignSuccess",Toast.LENGTH_SHORT).show();
                AVOSCloud.initialize(LoginActivity.this,"u82MN1iUnjoSUfMNipgjgzkB-MdYXbMMI","6fbr10W88xL7dFj344ntw64z");
                AVOSCloud.useAVCloudUS();
                new SignThread(userNameFormat,userPwdFormat,handlerSignInUS).start();
            }else {
                Toast.makeText(LoginActivity.this,"CNSignFailed",Toast.LENGTH_SHORT).show();
            }
        }
    };

    Handler handlerSignInUS = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.arg1 == 1){
                Toast.makeText(LoginActivity.this,"USSignSuccess",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }else{
                Toast.makeText(LoginActivity.this,"USignFailed",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText userName = (EditText)findViewById(R.id.userName);
        final EditText userPwd = (EditText)findViewById(R.id.userPwd);
        final Button login = (Button)findViewById(R.id.login);
        final Button sign = (Button)findViewById(R.id.sign);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameFormat = userName.getText().toString().toLowerCase();
                String userPwdFormat = userPwd.getText().toString().toLowerCase();

                AVOSCloud.initialize(LoginActivity.this,"V3o5V2M27mCoAwRx0P5xLulA-gzGzoHsz","lkDiLfRgYh1B15YdYJEz0p2P");
                AVOSCloud.useAVCloudCN();

                new LoginThread(userNameFormat,userPwdFormat,handlerLogin).start();
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 userNameFormat = userName.getText().toString().toLowerCase();
                 userPwdFormat = userPwd.getText().toString().toLowerCase();

                AVOSCloud.initialize(LoginActivity.this,"V3o5V2M27mCoAwRx0P5xLulA-gzGzoHsz","lkDiLfRgYh1B15YdYJEz0p2P");
                AVOSCloud.useAVCloudCN();

                new SignThread(userNameFormat,userPwdFormat,handlerSignInCN).start();
            }
        });

    }
}
