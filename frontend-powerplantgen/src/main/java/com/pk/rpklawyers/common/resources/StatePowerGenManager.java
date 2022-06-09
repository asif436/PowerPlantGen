package com.pk.rpklawyers.common.resources;

import java.io.IOException;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pk.rpklawyers.common.services.PowerGenService;
import com.uae.adnoc.backend.model.Country;

@Path("/statepowerplantmanager")
public class StatePowerGenManager {
	
	private static final Logger logger = LoggerFactory.getLogger(StatePowerGenManager.class);
	
	@Inject
	private PowerGenService powerGenServiceImpl;


	@GET
	@Path("/getcountrypowerproductiondata")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCountryPowerProductionData() throws IOException {

		logger.info("===== Start =====StatePowerGenManager==========" + "getCountryPowerProductionData" );
		logger.debug("==== Start ======StatePowerGenManager==========" + "getCountryPowerProductionData" );
		
		Country country = powerGenServiceImpl.loadExcellFileData();
			
		Jsonb jsonb = JsonbBuilder.create();
		String result = jsonb.toJson(country);
		
		logger.info("===== End =====StatePowerGenManager==========" + "getCountryPowerProductionData" );
		logger.debug("==== End ======StatePowerGenManager==========" + "getCountryPowerProductionData" );
		logger.debug("=====End =====StatePowerGenManager==========" + result );
		
		return result;
		
	}
	

}
