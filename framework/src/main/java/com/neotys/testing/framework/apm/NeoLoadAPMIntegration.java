package com.neotys.testing.framework.apm;

import com.neotys.neoload.model.repository.UserPath;
import com.neotys.testing.framework.BaseNeoLoadDesign;
import com.neotys.testing.framework.BaseNeoLoadUserPath;

import java.nio.file.Files;
import java.util.Optional;
import java.util.Properties;

public abstract class NeoLoadAPMIntegration extends BaseNeoLoadUserPath {

    String dataExchangeApiUrl;
    Optional<String> dataExchangeApiKey;
    Optional<String> proxyName;
    String customActionPath;


    public NeoLoadAPMIntegration(BaseNeoLoadDesign design) {

        super(design);


    }

    public abstract void init();

    public void initProperties()
    {
        Properties props = System.getProperties();
        dataExchangeApiKey=getOptionnalProperty("dataExchangeApiKey");
        proxyName=getOptionnalProperty("proxyName");
        dataExchangeApiUrl=props.getProperty("dataExchangeApiUrl");
        customActionPath=props.getProperty("customActionPath");
        init();
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
    public abstract UserPath createVirtualUser(BaseNeoLoadDesign design);

}
