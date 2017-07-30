package com.example.outlier.prictace_1.AboutMe.myFriend.Value;

import com.example.outlier.prictace_1.BaseApplication;

/**
 * Created by outlier on 2017/7/24.
 */
public class Values {
    private static String host = BaseApplication.get("hostaddress","http://169.254.80.218:8080/trip/");
    public static String registerURL=host+"register";
    public static String loginURL=host+"login";
    public static String createGroupURL=host+"createGroup";
    public static String addGroupURL=host+"addGroup";
    public static String quitGroupURL=host+"quitGroup";
    public static String queryContact = host+"queryContact";
    public static String addContact = host+"addContact";
    public static String queryGroupRS=host+"queryGroupRS";
    public static String deleteContact=host+"deleteContact";
}
