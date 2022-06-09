package com.pk.rpklawyers.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.pk.lawyers.util.StatePowerGenDAO;
import com.pk.lawyers.util.StatePowerGenDAOBean;
import com.pk.rpklawyers.common.services.PowerGenService;
import com.pk.rpklawyers.common.services.PowerGenServiceBean;

public class ApplicationBinder extends AbstractBinder {

	@Override
	protected void configure() {


		bind(PowerGenServiceBean.class).to(PowerGenService.class);
		bind(StatePowerGenDAOBean.class).to(StatePowerGenDAO.class);


	}

}
