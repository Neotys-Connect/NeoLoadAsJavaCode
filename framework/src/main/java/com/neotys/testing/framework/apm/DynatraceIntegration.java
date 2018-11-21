package com.neotys.testing.framework.apm;

import com.google.common.collect.ImmutableList;
import com.neotys.neoload.model.core.Element;
import com.neotys.neoload.model.repository.*;
import com.neotys.testing.framework.BaseNeoLoadDesign;
import com.neotys.testing.framework.BaseNeoLoadUserPath;

import javax.swing.text.html.Option;
import java.io.File;
import java.util.Optional;
import java.util.Properties;

public class DynatraceIntegration extends NeoLoadAPMIntegration {
    private String dynatraceApiKey;
    private String dynatraceId;
    private Optional<String> tags;
    private Optional<String> dynatraceManagedHostname;
    private static final long duration=30000;
    public static final String DYNATRACE_USERPATH_NAME="Dynatrace_Integration";

    public DynatraceIntegration(BaseNeoLoadDesign design) {

        super(design);

    }

    @Override
    public void init()
    {
        Properties props = System.getProperties();
        dynatraceApiKey=props.getProperty("dynatraceApiKey");
        dynatraceId=props.getProperty("dynatraceId");
        tags=getOptionnalProperty("tags");
        dynatraceManagedHostname=getOptionnalProperty("dynatraceManagedHostname");
    }


    @Override
    public UserPath createVirtualUser(BaseNeoLoadDesign design) {
        super.initProperties();

        ImmutableCustomAction.Builder dynatracEvents;
        ImmutableCustomAction.Builder dynatracMonitoring;
        ImmutableCustomAction.Builder dynatracConfiguration;

        //---Handle required parameters------
        dynatracEvents = ImmutableCustomAction.builder()
                .isHit(false)
                .type("DynatraceEventAction")
                .libraryPath(new File(customActionPath).toPath())
                .name("DynatraceEvents")
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("dynatraceApiKey")
                        .value(dynatraceApiKey)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("dynatraceId")
                        .value(dynatraceId)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("tags")
                        .value(tags.get())
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                );

        dynatracMonitoring = ImmutableCustomAction.builder()
                .isHit(false)
                .type("DynatraceMonitoringAction")
                .libraryPath(new File(customActionPath).toPath())
                .name("DynatraceMonitoring")
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("dynatraceApiKey")
                        .value(dynatraceApiKey)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("dynatraceId")
                        .value(dynatraceId)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("tags")
                        .value(tags.get())
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("dataExchangeApiUrl")
                        .type(CustomActionParameter.Type.TEXT)
                        .value(dataExchangeApiUrl)
                        .build()
                );

        dynatracConfiguration = ImmutableCustomAction.builder()
                .isHit(false)
                .type("DynatraceConfiguration")
                .libraryPath(new File(customActionPath).toPath())
                .name("DynatraceConfiguration")
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("dynatraceApiKey")
                        .value(dynatraceApiKey)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("dynatraceId")
                        .value(dynatraceId)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                );


        if (proxyName.isPresent()) {
            dynatracConfiguration.addParameters(ImmutableCustomActionParameter.builder()
                    .name("proxyName")
                    .value(proxyName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
            dynatracEvents.addParameters(ImmutableCustomActionParameter.builder()
                    .name("proxyName")
                    .value(proxyName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
            dynatracMonitoring.addParameters(ImmutableCustomActionParameter.builder()
                    .name("proxyName")
                    .value(proxyName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
        }
        if (dynatraceManagedHostname.isPresent()) {
            dynatracConfiguration.addParameters(ImmutableCustomActionParameter.builder()
                    .name("dynatraceManagedHostname")
                    .value(dynatraceManagedHostname.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
            dynatracEvents.addParameters(ImmutableCustomActionParameter.builder()
                    .name("dynatraceManagedHostname")
                    .value(dynatraceManagedHostname.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
            dynatracMonitoring.addParameters(ImmutableCustomActionParameter.builder()
                    .name("dynatraceManagedHostname")
                    .value(dynatraceManagedHostname.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );

        }

        if (dataExchangeApiKey.isPresent()) {
            dynatracMonitoring.addParameters(ImmutableCustomActionParameter.builder()
                    .name("dataExchangeApiKey")
                    .value(dataExchangeApiKey.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
        }

        ImmutableContainerForMulti init=ImmutableContainerForMulti.builder()
                .name("Init")
                .tag("init-container")
                .addChilds(dynatracConfiguration.build())
                .build();

        ImmutableContainerForMulti end=ImmutableContainerForMulti.builder()
                .name("End")
                .tag("end-container")
                .addChilds(dynatracEvents.build())
                .build();

        ImmutableContainerForMulti action=ImmutableContainerForMulti.builder()
                .name("Actions")
                .tag("actions-container")
                .addChilds(dynatracMonitoring.build())
                .addChilds(ImmutableDelay.builder()
                        .name("delay_monitoring")
                        .isThinkTime(false)
                        .delay(String.valueOf(duration))
                        .build())
                .build();


        return ImmutableUserPath.builder()
                .name(DYNATRACE_USERPATH_NAME)
                .initContainer(init)
                .actionsContainer(action)
                .endContainer(end)
                .build();
    }






}
