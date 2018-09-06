package com.neotys.testingFramework;

import com.neotys.converters.datamodel.repository.*;

import java.util.HashMap;

/**
 * Created by hrexed on 04/07/18.
 */
public abstract  class BaseNeoLoadUserPath {
    UserPath virtualUser;

    public BaseNeoLoadUserPath(HashMap<String,Server> serverlist,HashMap<String,Variable> variablelist)
    {
        virtualUser= createVirtualUser(serverlist,variablelist);
    }

    public abstract UserPath createVirtualUser(HashMap<String,Server> serverlist, HashMap<String,Variable> variablelist);


    public String getName()
    {
        return virtualUser.getName();
    }
}
