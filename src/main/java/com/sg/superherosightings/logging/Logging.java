/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.logging;

import com.sg.superherosightings.dao.PersistenceException;
import com.sg.superherosightings.dao.SuperHeroAuditDao;
import com.sg.superherosightings.service.SuperHumanTypeException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Roger Brock
 */
public class Logging {

    SuperHeroAuditDao auditDao;

    public Logging(SuperHeroAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }

        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (PersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void doAfterThrowing(SuperHumanTypeException ex) {
        String auditEntry = ex.toString();
        
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (PersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

}
