/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eightbitplantoon.cic.dao;

import com.eightbitplantoon.cic.entities.CicEmail;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Mnqobi Ngubane
 */
public class CicRegistrationDAO {
    private EntityManager em;

    public CicRegistrationDAO(EntityManager em) {
        this.em = em;
    }
    
    public CicEmail registerEmailCic(String fromAddress, String toAddress, String emailBody, Date sentDate, Date recievedDate){
        CicEmail cicEmail = new CicEmail(fromAddress, toAddress, emailBody.getBytes(), sentDate, recievedDate);
        em.persist(cicEmail);
        return cicEmail;
    }

    public List<CicEmail> retrieveEmailCicInfo() {
        return em.createNamedQuery("CicEmail.findAll").getResultList();
    }
}
