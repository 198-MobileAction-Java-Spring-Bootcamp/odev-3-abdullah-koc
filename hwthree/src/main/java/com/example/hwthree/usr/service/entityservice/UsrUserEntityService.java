package com.example.hwthree.usr.service.entityservice;

import com.example.hwthree.gen.service.BaseEntityService;
import com.example.hwthree.usr.dao.UsrUserDao;
import com.example.hwthree.usr.entity.UsrUser;
import org.springframework.stereotype.Service;

@Service
public class UsrUserEntityService extends BaseEntityService<UsrUser, UsrUserDao> {
    public UsrUserEntityService(UsrUserDao dao) {
        super(dao);
    }

    public UsrUser findByUsername(String username) {
        return getDao().findByUsername(username);
    }
}
