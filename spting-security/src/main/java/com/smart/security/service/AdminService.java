package com.smart.security.service;

import com.smart.security.entity.Admin;

/**
 * @author Hzx
 */
public interface AdminService {
    /**
     *  通过id查询
     * @param id
     * @return
     */
    Admin selectAdminById(int id);
}
