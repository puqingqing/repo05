package com.itheima.ssm.service.imp;

import com.itheima.domain.Syslog;
import com.itheima.ssm.dao.ISyslogDao;
import com.itheima.ssm.service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyslogService implements ISyslogService {

    @Autowired
    private ISyslogDao iSyslogDao;

    @Override
    public void saveOne(Syslog syslog) {
        iSyslogDao.saveOne(syslog);
    }

    /**
     * 保存所有的日志
     * @return
     */
    @Override
    public List<Syslog> findAll() {
        List<Syslog> syslogs= iSyslogDao.findAll();
        return syslogs;
    }
}
