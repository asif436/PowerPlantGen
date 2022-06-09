package com.pk.rpklawyers.config;

import org.glassfish.jersey.server.ResourceConfig;

import com.pk.rpklawyers.common.resources.StatePowerGenManager;



public class ResConfig extends ResourceConfig {

	public ResConfig() {
		register(new ApplicationBinder());
		
		register(StatePowerGenManager.class);
		packages(true, "com.pk.rpklawyers.common.resources");
		packages("com.pk.rpklawyers.config");
		
		//Register Auth Filter here
        register(AuthenticationFilter.class);
        register(CORSResponseFilter.class);



	}

}
