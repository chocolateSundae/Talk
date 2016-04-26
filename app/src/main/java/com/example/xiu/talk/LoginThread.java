package com.example.xiu.talk;

import android.os.Handler;
import android.os.Message;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

import java.util.List;

/**
 * Created by xiu on 16/4/16.
 */
public class LoginThread extends Thread {

    String token ;
    String userName;
    String userPwd;
    Handler handlerLogin;

    public LoginThread(String userName, String userPwd, Handler handlerLogin){
        this.userName = userName;
        this.userPwd = userPwd;
        this.handlerLogin = handlerLogin;
    }

    public String leanCloudLogin(String userName,String userPwd){

        AVQuery<AVObject> query = new AVQuery<AVObject>("UserInfo");
        query.whereEqualTo("userName", userName);
        query.whereEqualTo("userPwd", userPwd);
        try {
            List<AVObject> avObjects = query.find();
            if(avObjects.size() != 0){
                AVObject userInfo = avObjects.get(0);
                token = userInfo.getString("token");
                System.out.println("登录成功 token:" + token);
            }else {
                token = null;
                System.out.println("用户名密码错误");
            }
        } catch (AVException e) {
            System.out.println("用户名密码错误");
            token = null;
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public void run() {
        Message message = new Message();
        if(leanCloudLogin(userName,userPwd) != null){
            message.arg1 = 1;
        }else {
            message.arg1 = 0;
        }
        handlerLogin.sendMessage(message);

    }
}
