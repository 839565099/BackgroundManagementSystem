package com.zyq.utils;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

//修复DBCP的BUG，https://issues.apache.org/jira/browse/DBCP-332
public class MyServletContextListener implements ServletContextListener{


    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }


    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //这里如果Web应用拥有多个数据库的连接，可以一并关闭
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver driver = null;
        while (drivers.hasMoreElements()) {
            try {
                driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            } catch (SQLException ex) {
            }
        }
        AbandonedConnectionCleanupThread.checkedShutdown();
    }


}
