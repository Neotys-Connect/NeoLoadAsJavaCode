package com.neotys.testingFramework; /**
 * Created by hrexed on 02/07/18.
 */
import com.neotys.converters.datamodel.repository.*;

import java.util.HashMap;

public abstract class BaseNeoLoadTest {
    public HashMap<String,BaseNeoLoadUserPath> list_Of_Vu;
    public HashMap<String,Server> List_Of_Server;
    public HashMap<String,Variable> List_Of_Variable;
    //---------------------------------------------------------
    //--  init() : Method to initialize variables and servers
    //-----------------------------------------------------
    public  void init()
    {
        createVariables();
        createServers();
        createNeoLoadUserPaths();
    }

    public BaseNeoLoadTest()
    {
        List_Of_Server=new HashMap<String, Server>();
        list_Of_Vu =new HashMap<String, BaseNeoLoadUserPath>();
        List_Of_Variable=new HashMap<String, Variable>();
        init();
    }


    public void addServer(Server server)
    {
        List_Of_Server.put(server.getName(),server);
    }

    /**
     *
     */
    public abstract void createNeoLoadUserPaths();

    public abstract void createVariables();

    public abstract void createServers();

    public void addVariable(Variable var)
    {
        List_Of_Variable.put(var.getName(),var);
    }

    public void addVirtualUser(BaseNeoLoadUserPath vu)
    {
        list_Of_Vu.put(vu.getName(),vu);
    }

}
