/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitplantoon.common.factory;

import com.bitplantoon.common.constant.Names;
import java.util.Map;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import org.apache.log4j.Logger;

/**
 *
 * @author mnqobi
 */
public class EJBFactory {
    
    public static <T> T lookup(String jndiName, Class<T> interfaceClass) {
        Logger log = LogFactory.getInstance(EJBFactory.class, Names.MODULE_NAME);
        try {
            log.info("EJBFactory: Lookup START");
            
            Map ejbRemoteProps = (Map) PropFactory.getYMLProperties(Names.MODULE_NAME).get("ejb.remote.properties");
            Properties prop = new Properties();
            prop.putAll(ejbRemoteProps);
            log.debug("Properties: " + prop);

            final Context ctx = new InitialContext(prop);
            
            log.info("Connection string: " + "ejb:" + jndiName);
            
            return (T) PortableRemoteObject.narrow(ctx.lookup("ejb:" + jndiName), interfaceClass);
        } catch (NamingException ex) {
            log.error("An Exception Orccured: ", ex);           throw new RuntimeException(ex);
        } finally {
            log.info("EJBFactory: Lookup FINISH");
        }
    }
}
