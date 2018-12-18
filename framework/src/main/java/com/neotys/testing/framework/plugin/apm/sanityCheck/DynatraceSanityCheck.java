package com.neotys.testing.framework.plugin.apm.sanityCheck;

import com.neotys.neoload.model.repository.*;
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
        dynatraceSanityCheck = ImmutableCustomAction.builder()
                .isHit(false)
                .type("DynatraceSanityCheck")
                .libraryPath(new File(customActionPath).toPath())
                .name("DynatraceSanityCheck")
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
                        .name("outPutReferenceFile")
                        .value(outPutReferenceFile)
                        .type(CustomActionParameter.Type.TEXT)
                        .build()
                );

        if (proxyName.isPresent()) {
            dynatraceSanityCheck.addParameters(ImmutableCustomActionParameter.builder()
                    .name("proxyName")
                    .value(proxyName.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
        }
        if (dynatraceManagedHostname.isPresent()) {
            dynatraceSanityCheck.addParameters(ImmutableCustomActionParameter.builder()
                    .name("dynatraceManagedHostname")
                    .value(dynatraceManagedHostname.get())
                    .type(CustomActionParameter.Type.TEXT)
                    .build()
            );
        }

        ImmutableContainerForMulti init = ImmutableContainerForMulti.builder()
                .name("Init")
                .tag("init-container")
                .build();

        ImmutableContainerForMulti end = ImmutableContainerForMulti.builder()
                .name("End")
                .tag("end-container")
                .build();

        ImmutableContainerForMulti action = ImmutableContainerForMulti.builder()
                .name("Actions")
                .tag("actions-container")
                .addChilds(dynatraceSanityCheck.build())
                .build();


        return ImmutableUserPath.builder()
                .name(DYNATRACE_USERPATH_NAME)
                .initContainer(init)
                .actionsContainer(action)
                .endContainer(end)
                .build();

    }
}
