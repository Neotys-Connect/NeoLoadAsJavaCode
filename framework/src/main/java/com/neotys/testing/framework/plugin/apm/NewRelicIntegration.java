package com.neotys.testing.framework.plugin.apm;

import com.neotys.neoload.model.v3.project.Element;
import com.neotys.neoload.model.v3.project.userpath.*;
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

        newrelic= CustomAction.builder()
                .asRequest(false)
                .type("NewRelicMonitoringAction")
                .libraryPath(new File(customActionPath).toPath())
                .name("NewRelic Monitoring")
                .addParameters(CustomActionParameter.builder()
                        .name("newRelicAPIKey")
                        .value(newRelicAPIKey)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(CustomActionParameter.builder()
                        .name("newRelicApplicationName")
                        .value(newRelicApplicationName)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(CustomActionParameter.builder()
                        .name("sendNLWebDataToNewRelic")
                        .value(sendNLWebDataToNewRelic)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                );

        if(newRelicLicenseKey.isPresent())
        {
            newrelic.addParameters(CustomActionParameter.builder()
                    .name("newRelicLicenseKey")
                    .value(newRelicLicenseKey.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }
        if(dataExchangeApiUrl.isPresent())
        {
            newrelic.addParameters(CustomActionParameter.builder()
                .name("dataExchangeApiUrl")
                .type(CustomActionParameter.Type.TEXT)
                .value(dataExchangeApiUrl.get())
                .build());
        }
        if(newRelicAccountId.isPresent())
        {
            newrelic.addParameters(CustomActionParameter.builder()
                    .name("newRelicAccountId")
                    .value(newRelicAccountId.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }
        if(newRelicInsightsAPIKey.isPresent())
        {
            newrelic.addParameters(CustomActionParameter.builder()
                    .name("newRelicInsightsAPIKey")
                    .value(newRelicInsightsAPIKey.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }
        if(newRelicRelevantMetricNames.isPresent())
        {
            newrelic.addParameters(CustomActionParameter.builder()
                    .name("newRelicRelevantMetricNames")
                    .value(newRelicRelevantMetricNames.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }
        if(newRelicRelevantMetricValues.isPresent())
        {
            newrelic.addParameters(CustomActionParameter.builder()
                    .name("newRelicRelevantMetricValues")
                    .value(newRelicRelevantMetricValues.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }

        if(proxyName.isPresent())
        {
            newrelic.addParameters(CustomActionParameter.builder()
                    .name("proxyName")
                    .value(proxyName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }

        if(dataExchangeApiKey.isPresent())
        {
            newrelic.addParameters(CustomActionParameter.builder()
                    .name("dataExchangeApiKey")
                    .value(dataExchangeApiKey.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }


        Container action=Container.builder()
                .name("Actions")
                .addSteps(newrelic.build())
                .addSteps(Delay.builder()
                        .name("delay_monitoring")
                        .value(String.valueOf(duration))
                        .build())
                .build();


        return  UserPath.builder()
                .name(NEWRELIC_USERPATH_NAME)
                .init(Optional.empty())
                .actions(action)
                .end(Optional.empty())
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
