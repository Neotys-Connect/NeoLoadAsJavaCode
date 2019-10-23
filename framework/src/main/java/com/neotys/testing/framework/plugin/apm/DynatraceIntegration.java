package com.neotys.testing.framework.plugin.apm;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.neotys.neoload.model.v3.project.userpath.*;
import com.neotys.testing.framework.BaseNeoLoadDesign;
import com.neotys.testing.framework.plugin.apm.data.DynatraceAnomalie;

import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    private Optional<String> jsonAnomalieDetectionFile;
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
        jsonAnomalieDetectionFile=getOptionnalProperty("jsonAnomalieDetectionFile");
        dynatraceManagedHostname=getOptionnalProperty("dynatraceManagedHostname");
    }

    private DynatraceAnomalie getDynatraceAnomalie() throws FileNotFoundException {
        DynatraceAnomalie dynatraceAnomalies;
        Gson gson=new Gson();

        if(jsonAnomalieDetectionFile.isPresent())
        {
            JsonReader reader = new JsonReader(new FileReader(jsonAnomalieDetectionFile.get()));
            return dynatraceAnomalies=gson.fromJson(reader,DynatraceAnomalie.class);
        }
        else
        {
            if(jsonAnomalieDetection.isPresent())
            {
                return  dynatraceAnomalies =gson.fromJson(jsonAnomalieDetection.get(), DynatraceAnomalie.class);

            }
        }
        return null;

    }

    private List<ImmutableCustomAction> getAnomaliesDetectionActions() throws ParseException, FileNotFoundException {
        List<ImmutableCustomAction> customActionList =new ArrayList<>();
        if(jsonAnomalieDetection.isPresent() || jsonAnomalieDetectionFile.isPresent())
        {

            DynatraceAnomalie dynatraceAnomalies =getDynatraceAnomalie();
            if(dynatraceAnomalies!=null) {
                customActionList = dynatraceAnomalies.getDynatraceAnomalieList().stream().map(dynatraceAnomalies1 -> {
                    ImmutableCustomAction.Builder custoBuilder = CustomAction.builder()
                            .name("DynatraceSetAnomalieDetection")
                            .type("DynatraceSetAnomalieDetection")
                            .asRequest(false)
                            .libraryPath(new File(customActionPath).toPath())
                            .addParameters(CustomActionParameter.builder()
                                    .name("dynatraceApiKey")
                                    .value(dynatraceApiKey)
                                    .type(CustomActionParameter.Type.TEXT)
                                    .build())
                            .addParameters(CustomActionParameter.builder()
                                    .name("dynatraceId")
                                    .value(dynatraceId)
                                    .type(CustomActionParameter.Type.TEXT)
                                    .build())
                            .addParameters(CustomActionParameter.builder()
                                    .name("tags")
                                    .value(tags.get())
                                    .type(CustomActionParameter.Type.TEXT)
                                    .build())
                            .addParameters(CustomActionParameter.builder()
                                    .name("dynatraceMetricName")
                                    .value(dynatraceAnomalies1.getDynatraceMetricName())
                                    .type(CustomActionParameter.Type.TEXT)
                                    .build())
                            .addParameters(CustomActionParameter.builder()
                                    .name("operator")
                                    .value(dynatraceAnomalies1.getOperator())
                                    .type(CustomActionParameter.Type.TEXT)
                                    .build())
                            .addParameters(CustomActionParameter.builder()
                                    .name("value")
                                    .value(dynatraceAnomalies1.getValue())
                                    .type(CustomActionParameter.Type.TEXT)
                                    .build())
                            .addParameters(CustomActionParameter.builder()
                                    .name("typeOfAnomalie")
                                    .value(dynatraceAnomalies1.getTypeOfAnomalie())
                                    .type(CustomActionParameter.Type.TEXT)
                                    .build());
                    if (proxyName.isPresent()) {
                        custoBuilder.addParameters(CustomActionParameter.builder()
                                .name("proxyName")
                                .value(proxyName.get())
                                .type(CustomActionParameter.Type.TEXT)
                                .build());
                    }
                    if (dynatraceManagedHostname.isPresent()) {
                        custoBuilder.addParameters(CustomActionParameter.builder()
                                .name("dynatraceManagedHostname")
                                .value(dynatraceManagedHostname.get())
                                .type(CustomActionParameter.Type.TEXT)
                                .build());
                    }
                    return custoBuilder.build();
                }).collect(Collectors.toList());

                return customActionList;
            }
        }
        return customActionList;
    }
    private ImmutableCustomAction getDeleteAnomalieAction()
    {
        ImmutableCustomAction.Builder deleteaction= CustomAction.builder()
                .name("DynatraceDeleteAnomalieDetection")
                .type("DynatraceDeleteAnomalieDetection")
                .asRequest(false)
                .libraryPath(new File(customActionPath).toPath())
                .addParameters(CustomActionParameter.builder()
                        .name("dynatraceApiKey")
                        .value(dynatraceApiKey)
                        .type(CustomActionParameter.Type.TEXT)
                        .build())
                .addParameters(CustomActionParameter.builder()
                        .name("dynatraceId")
                        .value(dynatraceId)
                        .type(CustomActionParameter.Type.TEXT)
                        .build());

        if(proxyName.isPresent())
        {
            deleteaction.addParameters(CustomActionParameter.builder()
                    .name("proxyName")
                    .value(proxyName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }
        if(dynatraceManagedHostname.isPresent())
        {
            deleteaction.addParameters(CustomActionParameter.builder()
                    .name("dynatraceManagedHostname")
                    .value(dynatraceManagedHostname.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build());
        }

        return deleteaction.build();
    }
    @Override
    public com.neotys.neoload.model.v3.project.userpath.UserPath createAPMVirtualUser() {

        ImmutableCustomAction.Builder dynatracEvents;
        ImmutableCustomAction.Builder dynatracMonitoring;
        ImmutableCustomAction.Builder dynatracConfiguration;

        //---Handle required parameters------
        dynatracEvents = CustomAction.builder()
                .asRequest(false)
                .type("DynatraceEventAction")
                .libraryPath(new File(customActionPath).toPath())
                .name("DynatraceEvents")
                .addParameters(CustomActionParameter.builder()
                        .name("dynatraceApiKey")
                        .value(dynatraceApiKey)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(CustomActionParameter.builder()
                        .name("dynatraceId")
                        .value(dynatraceId)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(CustomActionParameter.builder()
                        .name("tags")
                        .value(tags.get())
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                );

        dynatracMonitoring = CustomAction.builder()
                .asRequest(false)
                .type("DynatraceMonitoringAction")
                .libraryPath(new File(customActionPath).toPath())
                .name("DynatraceMonitoring")
                .addParameters(CustomActionParameter.builder()
                        .name("dynatraceApiKey")
                        .value(dynatraceApiKey)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(CustomActionParameter.builder()
                        .name("dynatraceId")
                        .value(dynatraceId)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(CustomActionParameter.builder()
                        .name("tags")
                        .value(tags.get())
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                );

        dynatracConfiguration = CustomAction.builder()
                .asRequest(false)
                .type("DynatraceConfiguration")
                .libraryPath(new File(customActionPath).toPath())
                .name("DynatraceConfiguration")
                .addParameters(CustomActionParameter.builder()
                        .name("dynatraceApiKey")
                        .value(dynatraceApiKey)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(CustomActionParameter.builder()
                        .name("dynatraceId")
                        .value(dynatraceId)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                )
                .addParameters(CustomActionParameter.builder()
                        .name("tags")
                        .value(tags.get())
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                );



        if (proxyName.isPresent()) {
            dynatracConfiguration.addParameters(CustomActionParameter.builder()
                    .name("proxyName")
                    .value(proxyName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
            dynatracEvents.addParameters(CustomActionParameter.builder()
                    .name("proxyName")
                    .value(proxyName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
            dynatracMonitoring.addParameters(CustomActionParameter.builder()
                    .name("proxyName")
                    .value(proxyName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
        }
        if (dynatraceManagedHostname.isPresent()) {
            dynatracConfiguration.addParameters(CustomActionParameter.builder()
                    .name("dynatraceManagedHostname")
                    .value(dynatraceManagedHostname.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
            dynatracEvents.addParameters(CustomActionParameter.builder()
                    .name("dynatraceManagedHostname")
                    .value(dynatraceManagedHostname.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
            dynatracMonitoring.addParameters(CustomActionParameter.builder()
                    .name("dynatraceManagedHostname")
                    .value(dynatraceManagedHostname.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );

        }

        if (dataExchangeApiKey.isPresent()) {
            dynatracMonitoring.addParameters(CustomActionParameter.builder()
                    .name("dataExchangeApiKey")
                    .value(dataExchangeApiKey.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
        }
        if(dataExchangeApiUrl.isPresent())
        {
            dynatracMonitoring.addParameters(CustomActionParameter.builder()
                    .name("dataExchangeApiUrl")
                    .type(CustomActionParameter.Type.TEXT)
                    .value(dataExchangeApiUrl.get())
                    .build());
        }

        Container.Builder init=Container.builder()
                .name("Init")
                .addSteps(dynatracConfiguration.build());

        List<ImmutableCustomAction> customActionList;
        if(jsonAnomalieDetection.isPresent() || jsonAnomalieDetectionFile.isPresent())
        {
            try {
                customActionList=getAnomaliesDetectionActions();
                if(customActionList.size()>0)
                {
                    init.addAllSteps(customActionList);
                }
            } catch (ParseException e) {
                customActionList=new ArrayList<>();
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                customActionList=new ArrayList<>();
                e.printStackTrace();
            }
        }
        else
        {
            customActionList=new ArrayList<>();
        }

       Container.Builder end=Container.builder()
                .name("End")
                .addSteps(dynatracEvents.build());

        if(customActionList.size()>0)
        {
            end.addSteps(getDeleteAnomalieAction());
        }

        Container action=Container.builder()
                .name("Actions")
                .addSteps(dynatracMonitoring.build())
                .addSteps(Delay.builder()
                        .name("delay_monitoring")
                        .value(String.valueOf(duration))
                        .build())
                .build();


        return UserPath.builder()
                .name(DYNATRACE_USERPATH_NAME)
                .init(init.build())
                .actions(action)
                .end(end.build())
                .build();
    }






}
