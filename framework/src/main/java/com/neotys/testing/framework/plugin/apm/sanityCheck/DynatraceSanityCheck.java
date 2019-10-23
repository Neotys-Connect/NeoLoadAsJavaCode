package com.neotys.testing.framework.plugin.apm.sanityCheck;

import com.neotys.neoload.model.v3.project.userpath.*;
import com.neotys.testing.framework.BaseNeoLoadDesign;
import com.neotys.testing.framework.plugin.NeoloadIntegration;

import java.io.File;
import java.util.Optional;
import java.util.Properties;

public class DynatraceSanityCheck extends NeoloadIntegration {
    Optional<String> proxyName;
    private String dynatraceApiKey;
    private String dynatraceId;
    private Optional<String> tags;
    private Optional<String> dynatraceManagedHostname;
    public static final String DYNATRACE_USERPATH_NAME="Dynatrace_SanityCheck";
   private String outPutReferenceFile;
    public DynatraceSanityCheck(BaseNeoLoadDesign design) {
        super(design);
    }

    @Override
    public void initProperties() {
        Properties props = System.getProperties();
        dynatraceApiKey=props.getProperty("dynatraceApiKey");
        dynatraceId=props.getProperty("dynatraceId");
        tags=getOptionnalProperty("tags");
        outPutReferenceFile=props.getProperty("outPutReferenceFile");
        proxyName=getOptionnalProperty("proxyName");
        dynatraceManagedHostname=getOptionnalProperty("dynatraceManagedHostname");
    }



    @Override
    public UserPath createPluginUserPath() {
        ImmutableCustomAction.Builder dynatraceSanityCheck;
        dynatraceSanityCheck = CustomAction.builder()
                .asRequest(false)
                .type("DynatraceSanityCheck")
                .libraryPath(new File(customActionPath).toPath())
                .name("DynatraceSanityCheck")
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
                )
                .addParameters(CustomActionParameter.builder()
                        .name("outPutReferenceFile")
                        .value(outPutReferenceFile)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                );

        if (proxyName.isPresent()) {
            dynatraceSanityCheck.addParameters(CustomActionParameter.builder()
                    .name("proxyName")
                    .value(proxyName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
        }
        if (dynatraceManagedHostname.isPresent()) {
            dynatraceSanityCheck.addParameters(CustomActionParameter.builder()
                    .name("dynatraceManagedHostname")
                    .value(dynatraceManagedHostname.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
        }



        Container action = Container.builder()
                .name("Actions")
                .addSteps(dynatraceSanityCheck.build())
                .build();


        return UserPath.builder()
                .name(DYNATRACE_USERPATH_NAME)
                .init(Optional.empty())
                .actions(action)
                .end(Optional.empty())
                .build();

    }
}
