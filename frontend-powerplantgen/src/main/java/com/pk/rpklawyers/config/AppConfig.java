package com.pk.rpklawyers.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import com.pk.rpklawyers.common.resources.StatePowerGenManager;

@ApplicationPath("/services")
public class AppConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(StatePowerGenManager.class);
        classes.add(AuthenticationFilter.class);
        classes.add(CORSResponseFilter.class);
        classes.add(MOXyJsonProvider.class);     
        classes.add(MultiPartFeature.class);
        return classes;
    }
}