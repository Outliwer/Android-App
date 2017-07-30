package com.example.outlier.prictace_1.AboutMe.AllClass;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class personInfo {
    private String account;
    private String password;
    private String name;
    private int headId;


    public personInfo(String account, String password, String name, int headId){
        this.account=account;
        this.password=password;
        this.name=name;
        this.headId=headId;
    }

    public String getAccount(){
        return account;
    }
    public void setAccount(String newAccount){
        account=newAccount;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String newPassword){
        password=newPassword;
    }
    public String getName(){
        return name;
    }
    public void setName(String newName){
        name=newName;
    }
    public int getHeadId(){
        return headId;
    }
    public void setHeadId(int newHeadId){
        headId=newHeadId;
    }
}
