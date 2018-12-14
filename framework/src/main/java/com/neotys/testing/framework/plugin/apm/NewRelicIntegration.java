package com.neotys.testing.framework.plugin.apm;

import com.neotys.neoload.model.repository.*;
import com.neotys.testing.framework.BaseNeoLoadDesign;

import java.io.File;
import java.util.Optional;
import java.util.Properties;

public class NewRelicIntegration extends NeoLoadAPMIntegration {
    public static final String NEWRELIC_USERPATH_NAME="NewRelic_Integration";
    private String newRelicAPIKey;
    private String newRelicApplicationName;
    private String sendNLWebDataToNewRelic;
    private Optional<String> newRelicLicenseKey;
    private Optional<String> newRelicAccountId;
    private Optional<String> newRelicInsightsAPIKey;
    private Optional<String> newRelicRelevantMetricNames;
    private Optional<String> newRelicRelevantMetricValues;


    public NewRelicIntegration(BaseNeoLoadDesign design) {
        super(design);
    }

    @Override
    public UserPath createAPMVirtualUser() {
       ImmutableCustomAction.Builder newrelic;

        newrelic=ImmutableCustomAction.builder()
                .isHit(false)
                .type("NewRelicMonitoringAction")
                .libraryPath(new File(customActionPath).toPath())
                .name("NewRelic Monitoring")
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("newRelicAPIKey")
                        .value(newRelicAPIKey)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("newRelicApplicationName")
                        .value(newRelicApplicationName)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("sendNLWebDataToNewRelic")
                        .value(sendNLWebDataToNewRelic)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("dataExchangeApiUrl")
                        .type(CustomActionParameter.Type.TEXT)
                        .value(dataExchangeApiUrl)
                        .build()
                );

        if(newRelicLicenseKey.isPresent())
        {
            newrelic.addParameters(ImmutableCustomActionParameter.builder()
                    .name("newRelicLicenseKey")
                    .value(newRelicLicenseKey.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }
        if(newRelicAccountId.isPresent())
        {
            newrelic.addParameters(ImmutableCustomActionParameter.builder()
                    .name("newRelicAccountId")
                    .value(newRelicAccountId.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }
        if(newRelicInsightsAPIKey.isPresent())
        {
            newrelic.addParameters(ImmutableCustomActionParameter.builder()
                    .name("newRelicInsightsAPIKey")
                    .value(newRelicInsightsAPIKey.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }
        if(newRelicRelevantMetricNames.isPresent())
        {
            newrelic.addParameters(ImmutableCustomActionParameter.builder()
                    .name("newRelicRelevantMetricNames")
                    .value(newRelicRelevantMetricNames.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }
        if(newRelicRelevantMetricValues.isPresent())
        {
            newrelic.addParameters(ImmutableCustomActionParameter.builder()
                    .name("newRelicRelevantMetricValues")
                    .value(newRelicRelevantMetricValues.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }

        if(proxyName.isPresent())
        {
            newrelic.addParameters(ImmutableCustomActionParameter.builder()
                    .name("proxyName")
                    .value(proxyName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }

        if(dataExchangeApiKey.isPresent())
        {
            newrelic.addParameters(ImmutableCustomActionParameter.builder()
                    .name("dataExchangeApiKey")
                    .value(dataExchangeApiKey.get())
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
                .addChilds(newrelic.build())
                .addChilds(ImmutableDelay.builder()
                        .name("delay_monitoring")
                        .isThinkTime(false)
                        .delay(String.valueOf(duration))
                        .build())
                .build();


        return  ImmutableUserPath.builder()
                .name(NEWRELIC_USERPATH_NAME)
                .initContainer(init)
                .actionsContainer(action)
                .endContainer(end)
                .build();
    }

    @Override
    public void init()
    {
        Properties props = System.getProperties();
        newRelicAPIKey=props.getProperty("newRelicAPIKey");
        sendNLWebDataToNewRelic=props.getProperty("sendNLWebDataToNewRelic");
        newRelicLicenseKey=getOptionnalProperty("newRelicLicenseKey");
        newRelicAccountId=getOptionnalProperty("newRelicAccountId");
        newRelicInsightsAPIKey=getOptionnalProperty("newRelicInsightsAPIKey");
        newRelicRelevantMetricNames=getOptionnalProperty("newRelicRelevantMetricNames");
        newRelicRelevantMetricValues=getOptionnalProperty("newRelicRelevantMetricValues");

    }

}
