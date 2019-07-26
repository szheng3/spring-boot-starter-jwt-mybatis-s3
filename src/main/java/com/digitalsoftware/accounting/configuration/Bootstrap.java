package com.digitalsoftware.accounting.configuration;

import com.digitalsoftware.accounting.mapper.DAO.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Bootstrap {

    @Autowired
    private RoleMapper roleMapper;

    public void generateAll() {
        generateRole();
    }


    private void generateRole() {
//
//        roleDAO.insertSelective(new Role(null, RoleType.ROLE_ADMIN));
//        roleDAO.insertSelective(new Role(null, RoleType.ROLE_USER));
//        roleDAO.insertSelective(new Role(null, RoleType.ROLE_REGISTER));
//        roleDAO.insertSelective(new Role(null, RoleType.ROLE_TRUSTED_CLIENT));

    }


}
