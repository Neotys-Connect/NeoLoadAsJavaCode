package com.neotys.testing.framework.plugin.apm;

import com.neotys.neoload.model.repository.UserPath;
import com.neotys.testing.framework.BaseNeoLoadDesign;
import com.neotys.testing.framework.BaseNeoLoadUserPath;
import com.neotys.testing.framework.plugin.NeoloadIntegration;

import java.nio.file.Files;
import java.util.Optional;
import java.util.Properties;

public abstract class NeoLoadAPMIntegration extends NeoloadIntegration {

    Optional<String> dataExchangeApiUrl;
    Optional<String> dataExchangeApiKey;
    Optional<String> proxyName;
    public static final long duration=30000;


    public NeoLoadAPMIntegration(BaseNeoLoadDesign design) {

        super(design);


    }

    public abstract void init();

    public void initProperties()
    {
        Properties props = System.getProperties();
        dataExchangeApiKey=getOptionnalProperty("dataExchangeApiKey");
        proxyName=getOptionnalProperty("proxyName");
        dataExchangeApiUrl=getOptionnalProperty("dataExchangeApiUrl");
        init();
    }


    @Override
    public  UserPath createPluginUserPath()
    {
        initProperties();
        return createAPMVirtualUser();
    };

    public abstract UserPath createAPMVirtualUser();
}
