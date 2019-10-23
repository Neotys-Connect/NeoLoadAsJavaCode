package com.neotys.testing.framework.plugin.apm;

import com.neotys.neoload.model.v3.project.userpath.*;
import com.neotys.testing.framework.BaseNeoLoadDesign;

import javax.swing.text.html.Option;
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
    public com.neotys.neoload.model.v3.project.userpath.UserPath createAPMVirtualUser() {
        ImmutableCustomAction.Builder appd;

        appd= CustomAction.builder()
                .asRequest(false)
                .type("AppDynamics Monitoring ")
                .libraryPath(new File(customActionPath).toPath())
                .name("AppDynamics Monitoring ")
                .addParameters(CustomActionParameter.builder()
                        .name("appDynamicsURL")
                        .value(appDynamicsURL)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(CustomActionParameter.builder()
                        .name("appDynamicsApplicationName")
                        .value(appDynamicsApplicationName)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                );

        if(proxyName.isPresent())
        {
            appd.addParameters(CustomActionParameter.builder()
                    .name("proxyName")
                    .value(proxyName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }

        if(dataExchangeApiKey.isPresent())
        {
            appd.addParameters(CustomActionParameter.builder()
                    .name("dataExchangeApiKey")
                    .value(dataExchangeApiKey.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }
        if(dataExchangeApiUrl.isPresent())
        {
            appd.addParameters(CustomActionParameter.builder()
                .name("dataExchangeApiUrl")
                .type(CustomActionParameter.Type.TEXT)
                .value(dataExchangeApiUrl.get())
                .build());
        }

        if(appDynamicsAccountName.isPresent())
        {
            appd.addParameters(CustomActionParameter.builder()
                    .name("appDynamicsAccountName")
                    .value(appDynamicsAccountName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }
        if(appDynamicsUserName.isPresent())
        {
            appd.addParameters(CustomActionParameter.builder()
                    .name("appDynamicsUserName")
                    .value(appDynamicsUserName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }
        if(appDynamicsPassword.isPresent())
        {
            appd.addParameters(CustomActionParameter.builder()
                    .name("appDynamicsPassword")
                    .value(appDynamicsPassword.get())
                    .type(CustomActionParameter.Type.PASSWORD)
                    .build());
        }
        if(appDynamicsMetricPaths.isPresent())
        {
            appd.addParameters(CustomActionParameter.builder()
                    .name("appDynamicsMetricPaths")
                    .value(appDynamicsMetricPaths.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }


        Container action=Container.builder()
                .name("Actions")
                .addSteps(appd.build())
                .addSteps(Delay.builder()
                        .name("delay_monitoring")
                        .value(String.valueOf(duration))
                        .build())
                .build();


        return  UserPath.builder()
                .name(APPD_USERPATH_NAME)
                .init(Optional.empty())
                .actions(action)
                .end(Optional.empty())
                .build();


    }
}
