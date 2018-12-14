package com.neotys.testing.framework.plugin;

import com.neotys.neoload.model.repository.UserPath;
import com.neotys.testing.framework.BaseNeoLoadDesign;
import com.neotys.testing.framework.BaseNeoLoadUserPath;

import java.util.Optional;
import java.util.Properties;

public abstract class NeoloadIntegration extends BaseNeoLoadUserPath {

    public String customActionPath;


    public NeoloadIntegration(BaseNeoLoadDesign design) {

        super(design);


    }

    public abstract void initProperties();

    public void initplugin()
    {
        Properties props = System.getProperties();
        customActionPath=props.getProperty("customActionPath");
        initProperties();
    }
    public void setCustomActionPath(String customActionPath) {
        this.customActionPath = customActionPath;
    }

    public Optional<String> getOptionnalProperty(String key)
    {

        Properties props = System.getProperties();
        String temp;
        temp=props.getProperty(key);
        if(temp==null)
            return Optional.empty();
        else
            return Optional.of(temp);
    }

    @Override
    public UserPath createVirtualUser(BaseNeoLoadDesign design)
    {
        initplugin();
        return createPluginUserPath();
    };


    public abstract UserPath createPluginUserPath();
}
