package com.neotys.testing.framework.plugin.apm;

import com.google.gson.Gson;
import com.neotys.neoload.model.repository.*;
import com.neotys.testing.framework.BaseNeoLoadDesign;
import com.neotys.testing.framework.plugin.apm.data.DynatraceAnomalie;

import org.json.simple.parser.ParseException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

public class DynatraceIntegration extends NeoLoadAPMIntegration {
    private String dynatraceApiKey;
    private String dynatraceId;
    private Optional<String> tags;
    private Optional<String> dynatraceManagedHostname;
    public static final String DYNATRACE_USERPATH_NAME="Dynatrace_Integration";
    private Optional<String> jsonAnomalieDetection;
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
        jsonAnomalieDetection=getOptionnalProperty("jsonAnomalieDetection");
        dynatraceManagedHostname=getOptionnalProperty("dynatraceManagedHostname");
    }

    private List<ImmutableCustomAction> getAnomaliesDetectionActions() throws ParseException {
        List<ImmutableCustomAction> customActionList =new ArrayList<>();
        if(jsonAnomalieDetection.isPresent())
        {
            Gson gson=new Gson();
            DynatraceAnomalie dynatraceAnomalies =gson.fromJson(jsonAnomalieDetection.get(), DynatraceAnomalie.class);
            customActionList=dynatraceAnomalies.getDynatraceAnomalieList().stream().map(dynatraceAnomalies1 -> {
                ImmutableCustomAction.Builder custoBuilder= ImmutableCustomAction.builder()
                        .name("DynatraceSetAnomalieDetection")
                        .type("DynatraceSetAnomalieDetection")
                        .isHit(false)
                        .libraryPath(new File(customActionPath).toPath())
                        .addParameters(ImmutableCustomActionParameter.builder()
                            .name("dynatraceApiKey")
                            .value(dynatraceApiKey)
                            .type(CustomActionParameter.Type.TEXT)
                            .build())
                        .addParameters(ImmutableCustomActionParameter.builder()
                                .name("dynatraceId")
                                .value(dynatraceId)
                                .type(CustomActionParameter.Type.TEXT)
                                .build())
                        .addParameters(ImmutableCustomActionParameter.builder()
                                .name("tags")
                                .value(tags.get())
                                .type(CustomActionParameter.Type.TEXT)
                                .build())
                        .addParameters(ImmutableCustomActionParameter.builder()
                                .name("dynatraceMetricName")
                                .value(dynatraceAnomalies1.getDynatraceMetricName())
                                .type(CustomActionParameter.Type.TEXT)
                                .build())
                        .addParameters(ImmutableCustomActionParameter.builder()
                                .name("operator")
                                .value(dynatraceAnomalies1.getOperator())
                                .type(CustomActionParameter.Type.TEXT)
                                .build())
                        .addParameters(ImmutableCustomActionParameter.builder()
                                .name("value")
                                .value(dynatraceAnomalies1.getValue())
                                .type(CustomActionParameter.Type.TEXT)
                                .build())
                        .addParameters(ImmutableCustomActionParameter.builder()
                                .name("typeOfAnomalie")
                                .value(dynatraceAnomalies1.getTypeOfAnomalie())
                                .type(CustomActionParameter.Type.TEXT)
                                .build());
                if(proxyName.isPresent())
                {
                    custoBuilder.addParameters(ImmutableCustomActionParameter.builder()
                            .name("proxyName")
                            .value(proxyName.get())
                            .type(CustomActionParameter.Type.TEXT)
                            .build());
                }
                if(dynatraceManagedHostname.isPresent())
                {
                    custoBuilder.addParameters(ImmutableCustomActionParameter.builder()
                            .name("dynatraceManagedHostname")
                            .value(dynatraceManagedHostname.get())
                            .type(CustomActionParameter.Type.TEXT)
                            .build());
                }
                return custoBuilder.build();
            }).collect(Collectors.toList());

            return customActionList;
        }
        return customActionList;
    }
    private ImmutableCustomAction getDeleteAnomalieAction()
    {
        ImmutableCustomAction.Builder deleteaction=ImmutableCustomAction.builder()
                .name("DynatraceDeleteAnomalieDetection")
                .type("DynatraceDeleteAnomalieDetection")
                .isHit(false)
                .libraryPath(new File(customActionPath).toPath())
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("dynatraceApiKey")
                        .value(dynatraceApiKey)
                        .type(CustomActionParameter.Type.TEXT)
                        .build())
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("dynatraceId")
                        .value(dynatraceId)
                        .type(CustomActionParameter.Type.TEXT)
                        .build());

        if(proxyName.isPresent())
        {
            deleteaction.addParameters(ImmutableCustomActionParameter.builder()
                    .name("proxyName")
                    .value(proxyName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }
        if(dynatraceManagedHostname.isPresent())
        {
            deleteaction.addParameters(ImmutableCustomActionParameter.builder()
                    .name("dynatraceManagedHostname")
                    .value(dynatraceManagedHostname.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }

        return deleteaction.build();
    }
    @Override
    public UserPath createAPMVirtualUser() {

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
                )
                .addParameters(ImmutableCustomActionParameter.builder()
                        .name("tags")
                        .value(tags.get())
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

        ImmutableContainerForMulti.Builder init=ImmutableContainerForMulti.builder()
                .name("Init")
                .tag("init-container")
                .addChilds(dynatracConfiguration.build());

        List<ImmutableCustomAction> customActionList;
        if(jsonAnomalieDetection.isPresent())
        {
            try {
                customActionList=getAnomaliesDetectionActions();
                if(customActionList.size()>0)
                {
                    init.addAllChilds(customActionList);
                }
            } catch (ParseException e) {
                customActionList=new ArrayList<>();
                e.printStackTrace();
            }
        }
        else
        {
            customActionList=new ArrayList<>();
        }

        ImmutableContainerForMulti.Builder end=ImmutableContainerForMulti.builder()
                .name("End")
                .tag("end-container")
                .addChilds(dynatracEvents.build());

        if(customActionList.size()>0)
        {
            end.addChilds(getDeleteAnomalieAction());
        }

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
                .initContainer(init.build())
                .actionsContainer(action)
                .endContainer(end.build())
                .build();
    }






}
