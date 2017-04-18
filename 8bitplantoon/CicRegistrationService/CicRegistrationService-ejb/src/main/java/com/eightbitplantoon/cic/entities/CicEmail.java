/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eightbitplantoon.cic.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mnqobi Ngubane
 */
@Entity
@Table(name = "CIC_EMAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CicEmail.findAll", query = "SELECT c FROM CicEmail c")
    , @NamedQuery(name = "CicEmail.findByEmailId", query = "SELECT c FROM CicEmail c WHERE c.emailId = :emailId")
    , @NamedQuery(name = "CicEmail.findByFromAddress", query = "SELECT c FROM CicEmail c WHERE c.fromAddress = :fromAddress")
    , @NamedQuery(name = "CicEmail.findByToAddress", query = "SELECT c FROM CicEmail c WHERE c.toAddress = :toAddress")
    , @NamedQuery(name = "CicEmail.findBySentDate", query = "SELECT c FROM CicEmail c WHERE c.sentDate = :sentDate")
    , @NamedQuery(name = "CicEmail.findByRecievedDate", query = "SELECT c FROM CicEmail c WHERE c.recievedDate = :recievedDate")})
public class CicEmail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EMAIL_ID", nullable = false)
    private Long emailId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 175)
    @Column(name = "FROM_ADDRESS", nullable = false, length = 175)
    private String fromAddress;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 175)
    @Column(name = "TO_ADDRESS", nullable = false, length = 175)
    private String toAddress;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "EMAIL_BODY", nullable = false)
    private byte[] emailBody;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "SENT_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentDate;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "RECIEVED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date recievedDate;
    
    public CicEmail() {
    }

    public CicEmail(Long emailId) {
        this.emailId = emailId;
    }

    public CicEmail(Long emailId, String fromAddress, String toAddress, byte[] emailBody, Date sentDate, Date recievedDate) {
        this.emailId = emailId;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.emailBody = emailBody;
        this.sentDate = sentDate;
        this.recievedDate = recievedDate;
    }
    
    public CicEmail(String fromAddress, String toAddress, byte[] emailBody, Date sentDate, Date recievedDate) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.emailBody = emailBody;
        this.sentDate = sentDate;
        this.recievedDate = recievedDate;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public byte[] getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(byte[] emailBody) {
        this.emailBody = emailBody;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public Date getRecievedDate() {
        return recievedDate;
    }

    public void setRecievedDate(Date recievedDate) {
        this.recievedDate = recievedDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailId != null ? emailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CicEmail)) {
            return false;
        }
        CicEmail other = (CicEmail) object;
        if ((this.emailId == null && other.emailId != null) || (this.emailId != null && !this.emailId.equals(other.emailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CicEmail[ emailId=" + emailId + " ]";
    }
    
}
