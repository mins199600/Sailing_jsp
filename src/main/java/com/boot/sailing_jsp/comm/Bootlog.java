package com.boot.sailing_jsp.comm;

import com.boot.sailing_jsp.v2.dao.MenuDaoV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Bootlog {

    @Autowired
    MenuDaoV2 menuDaoV2;

    public int doBootLog(String strClass){
        int int1= menuDaoV2.doBootLog(strClass);
        return int1;
    }
}
