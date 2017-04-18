/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eightbitplantoon.cic.ws;

import com.eightbitplantoon.cic.bean.CicRegistrationEngineBean;
import com.eightbitplantoon.cic.entities.CicEmail;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author mnqobi
 */
@WebService(serviceName = "CicRegistrationEngineService")
@Stateless()
public class CicRegistrationEngineService {

    @EJB
    private CicRegistrationEngineBean ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "registerEmailCic")
    public CicEmail registerEmailCic(
            @WebParam(name = "fromAddress")     @XmlElement( required = true ) String fromAddress, 
            @WebParam(name = "toAddress")       @XmlElement( required = true ) String toAddress, 
            @WebParam(name = "emailBody")       @XmlElement( required = true ) String emailBody, 
            @WebParam(name = "sentDate")        @XmlElement( required = true ) Date sentDate, 
            @WebParam(name = "recievedDate")    @XmlElement( required = true ) Date recievedDate) {
        return ejbRef.registerEmailCic(fromAddress, toAddress, emailBody, sentDate, recievedDate);
    }

    @WebMethod(operationName = "retrieveEmailCicInfo")
    public List<CicEmail> retrieveEmailCicInfo() {
        return ejbRef.retrieveEmailCicInfo();
    }
    
}
