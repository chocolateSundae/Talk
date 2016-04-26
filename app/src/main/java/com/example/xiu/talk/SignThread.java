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
public class SignThread extends Thread {
    String userName;
    String userPwd;
    String token = "getFromNIM:default";
    Handler handlerSignIn;

    public SignThread(String userName, String userPwd, Handler handlerSignIn){
        this.userName = userName;
        this.userPwd = userPwd;
        this.handlerSignIn = handlerSignIn;
    }

    public Boolean signLeanCloud(String userName , String userPwd,String token){
        AVObject userInfo = new AVObject("UserInfo");
        userInfo.put("userName", userName);
        userInfo.put("userPwd", userPwd);
        userInfo.put("token",token);
        try {
            userInfo.save();
            return true;
        } catch (AVException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean exsistUserLeanCloud(String userName){
        boolean exsit = true ;
        AVQuery<AVObject> query = new AVQuery<AVObject>("UserInfo");
        query.whereEqualTo("userName", userName);
        try {
            List<AVObject> userInfos = query.find();
            if(userInfos.isEmpty()){
                System.out.println("这一步 empty");
                exsit = false;
            }else {
                System.out.println("这一步 存在");
                exsit = true;
            }
        } catch (AVException e) {
            e.printStackTrace();
        }

        return exsit;
    }
    @Override
    public void run() {
        Message message = new Message();
        if(exsistUserLeanCloud(userName)){
            System.out.println("用户名已存在");
            message.arg1 = 0;
        }else{
            System.out.println("用户名不存在");
            if(signLeanCloud(userName,userPwd,token)){
                message.arg1 = 1;
            }else {
                message.arg1 = 0;
            }
        }
        handlerSignIn.sendMessage(message);
    }
}
