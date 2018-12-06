package com.itheima.ssm.dao;

import com.itheima.domain.Syslog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISyslogDao {

    @Insert("insert into syslog (VISITTIME,USERNAME,IP,URL,EXECUTIONTIME,METHOD)values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void saveOne(Syslog syslog);

    @Select("select * from syslog")
    List<Syslog> findAll();
}
