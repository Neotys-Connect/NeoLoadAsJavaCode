package com.neotys.testingFramework.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by hrexed on 05/07/18.
 */
public final class NeoloadConfig {


     private static URL neoload = NeoloadConfig.class.getClassLoader().getResource("neoload.properties");
     private static String tmp=neoload.toString();

     static  File neoloadconf= new File(tmp);

     static File projectRootDir = neoloadconf.getParentFile().getParentFile().getParentFile();

     public static String  mavenTargetDirectory = projectRootDir.getAbsolutePath()+"/target";
     public static String neoLoadTargetDirectory = mavenTargetDirectory + "/neoload";


     public static String neoLoadJsonFileName;

     //----return the NeoLaod json file name----
     public static String retrieveJsonFileName() throws IOException {
          Properties prop = new Properties();
          FileInputStream input ;
          input= new FileInputStream(tmp);
          prop.load(input);

          neoLoadJsonFileName =prop.getProperty("NeolaodJsonScenario");
          input.close();
          return neoLoadJsonFileName;
     }
}
