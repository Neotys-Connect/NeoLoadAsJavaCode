package com.neotys.testing.framework.plugin.apm;

import com.neotys.neoload.model.repository.*;
import com.neotys.testing.framework.BaseNeoLoadDesign;

import java.io.File;
import java.util.Optional;
import java.util.Properties;

public class AppDynamicsIntegration extends NeoLoadAPMIntegration {
    String appDynamicsURL;
    Optional<String> appDynamicsAccountName;
    Optional<String> appDynamicsUserName;
    Optional<String> appDynamicsPassword;
    Optional<String> appDynamicsMetricPaths;
    String appDynamicsApplicationName;
    public static final String APPD_USERPATH_NAME="AppDynamics_Integration";
    public AppDynamicsIntegration(BaseNeoLoadDesign design) {
        super(design);
    }

    @Override
    public void init() {
        Properties props = System.getProperties();
        appDynamicsURL=props.getProperty("appDynamicsURL");
        appDynamicsApplicationName=props.getProperty("appDynamicsApplicationName");
        appDynamicsAccountName=getOptionnalProperty("appDynamicsAccountName");
        appDynamicsUserName=getOptionnalProperty("appDynamicsUserName");
        appDynamicsPassword=getOptionnalProperty("appDynamicsPassword");
        appDynamicsMetricPaths=getOptionnalProperty("appDynamicsMetricPaths");

    }

    @Override
    public UserPath createAPMVirtualUser() {
        ImmutableCustomAction.Builder appd;

        appd= ImmutableCustomAction.builder()
                .isHit(false)
                .type("AppDynamics Monitoring ")
                .libraryPath(new File(customActionPath).toPath())
                .name("AppDynamics Monitoring ")
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("appDynamicsURL")
                        .value(appDynamicsURL)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("appDynamicsApplicationName")
                        .value(appDynamicsApplicationName)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("dataExchangeApiUrl")
                        .type(CustomActionParameter.Type.TEXT)
                        .value(dataExchangeApiUrl)
                        .build()
                );

        if(proxyName.isPresent())
        {
            appd.addParameters(ImmutableCustomActionParameter.builder()
                    .name("proxyName")
                    .value(proxyName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }

        if(dataExchangeApiKey.isPresent())
        {
            appd.addParameters(ImmutableCustomActionParameter.builder()
                    .name("dataExchangeApiKey")
                    .value(dataExchangeApiKey.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }

        if(appDynamicsAccountName.isPresent())
        {
            appd.addParameters(ImmutableCustomActionParameter.builder()
                    .name("appDynamicsAccountName")
                    .value(appDynamicsAccountName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }
        if(appDynamicsUserName.isPresent())
        {
            appd.addParameters(ImmutableCustomActionParameter.builder()
                    .name("appDynamicsUserName")
                    .value(appDynamicsUserName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }
        if(appDynamicsPassword.isPresent())
        {
            appd.addParameters(ImmutableCustomActionParameter.builder()
                    .name("appDynamicsPassword")
                    .value(appDynamicsPassword.get())
                    .type(CustomActionParameter.Type.PASSWORD)
                    .build());
        }
        if(appDynamicsMetricPaths.isPresent())
        {
            appd.addParameters(ImmutableCustomActionParameter.builder()
                    .name("appDynamicsMetricPaths")
                    .value(appDynamicsMetricPaths.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }

        ImmutableContainerForMulti init=ImmutableContainerForMulti.builder()
                .name("Init")
                .tag("init-container")
                .build();

        ImmutableContainerForMulti end=ImmutableContainerForMulti.builder()
                .name("End")
                .tag("end-container")
                .build();

        ImmutableContainerForMulti action=ImmutableContainerForMulti.builder()
                .name("Actions")
                .tag("actions-container")
                .addChilds(appd.build())
                .addChilds(ImmutableDelay.builder()
                        .name("delay_monitoring")
                        .isThinkTime(false)
                        .delay(String.valueOf(duration))
                        .build())
                .build();


        return  ImmutableUserPath.builder()
                .name(APPD_USERPATH_NAME)
                .initContainer(init)
                .actionsContainer(action)
                .endContainer(end)
                .build();


    }
}
