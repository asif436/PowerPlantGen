package com.pk.rpklawyers.common.services;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import com.pk.lawyers.util.StatePowerGenDAO;
import com.uae.adnoc.backend.model.Country;

@RequestScoped
public class PowerGenServiceBean implements PowerGenService {

	@EJB
	private StatePowerGenDAO lawyersDAO;

	public Country loadExcellFileData(){
		
		return this.lawyersDAO.loadExcellFileData();
	
	}
	

}
