/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitplantoon.common.factory;

import com.bitplantoon.common.constant.Names;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author mnqobi
 */
public class LogFactory {
    
    public static Logger getInstance(Class module, String moduleName){
        Map log4jMap = (Map) PropFactory.getYMLProperties(Names.MODULE_NAME).get("log4j");
        log4jMap.put("log4j.appender.file.File", log4jMap.get("log4j.file.location").toString() + moduleName + ".log");
        log4jMap.remove("log4j.file.location");
        
        Properties prop = new Properties();
        prop.putAll(log4jMap);
        
        PropertyConfigurator.configure(prop);
        Logger logger = Logger.getLogger(module);
        return logger;
    }
}
