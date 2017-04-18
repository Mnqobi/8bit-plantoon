/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitplantoon.common.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author mnqobi
 */
public class PropFactory {
    public static Map getYMLProperties(String moduleName){
       try {   
               String location = System.getProperty("yaml.properties.directory");
               if(StringUtils.isBlank(location)){
                   //SET DEFAULT LOCATION
                   location = "/tmp/system.8bitplantoon/properties/";
               }
               return (Map) new Yaml().load(new BufferedReader(new FileReader(new File(location + "/" + moduleName + ".yml"))));
       } catch (FileNotFoundException ex) {
           throw new RuntimeException("The YML file for " + moduleName + " was not found, ensure its within you resources/properties forlder");
       }
   }
}
