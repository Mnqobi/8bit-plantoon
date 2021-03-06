/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitplantoon.eightbitplantoon.rest;

import com.bitplantoon.common.factory.EJBFactory;
import com.eightbitplantoon.cic.entities.CicEmail;
import com.eightbitplantoon.cic.inteface.CicRegistrationEngineRemote;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * REST Web Service
 *
 * @author mnqobi
 */
@Path("cic")
public class CicRegistrationEngineResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CicRegistrationEngineResource
     */
    public CicRegistrationEngineResource() {
    }

    /**
     * Retrieves representation of an instance of com.bitplantoon.eightbitplantoon.rest.CicRegistrationEngineResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveEmailCicInfo () {
        CicRegistrationEngineRemote engineRemote = EJBFactory.lookup(CicRegistrationEngineRemote.JNDI_LOCATION, CicRegistrationEngineRemote.class);
        List<CicEmail> retrieveEmailCicInfo = engineRemote.retrieveEmailCicInfo();
        String json = new Gson().toJson(retrieveEmailCicInfo);
        return Response.status(Status.OK).entity(json).build();
    }

    /**
     * PUT method for updating or creating an instance of CicRegistrationEngineResource
     * @param fromAddress
     * @param toAddress
     * @param emailBody
     * @param sentDate
     * @param recievedDate
     * @param dateFormat
     * @return 
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response putJson(@FormParam( "fromAddress" ) String fromAddress, 
            @FormParam( "toAddress" ) String toAddress, 
            @FormParam( "emailBody" ) String emailBody, 
            @FormParam( "sentDate" ) String sentDate, 
            @FormParam( "recievedDate" ) String recievedDate,
            @FormParam( "dateFormat" ) String dateFormat) {
        
        try {
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

                CicRegistrationEngineRemote engineRemote = EJBFactory.lookup(CicRegistrationEngineRemote.JNDI_LOCATION, CicRegistrationEngineRemote.class);
                CicEmail registerEmailCic = engineRemote.registerEmailCic(fromAddress, toAddress, emailBody, sdf.parse(sentDate), sdf.parse(recievedDate));
                String json = new Gson().toJson(registerEmailCic);
                return Response.status(Status.OK).entity(json).build();
        } catch (ParseException ex) {
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
}
