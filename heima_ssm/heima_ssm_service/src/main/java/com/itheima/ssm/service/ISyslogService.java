package com.itheima.ssm.service;

import com.itheima.domain.Syslog;

import java.util.List;

public interface ISyslogService {

    /**
     * 保存访问的日志
     *
     * @param syslog
     */
    void saveOne(Syslog syslog);

    /**
     * 查询所有访问的日志
     *
     * @return
     */

    List<Syslog> findAll();
}
