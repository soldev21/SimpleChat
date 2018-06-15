package main.registry;

/**
 * Created by Sherif on 6/15/2018.
 */
public class RegistryHolder {
    private static ClientHandlerRegistry registry;

    public static void setRegistry(ClientHandlerRegistry registryS){
        registry = registryS;
    }

    public static ClientHandlerRegistry getRegistry(){
        return registry;
    }
}
