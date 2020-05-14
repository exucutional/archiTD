package objects;

import java.util.HashMap;
import java.util.Map;

import resources.ResourceType;

public class Connector extends Structure {

    private Map<ResourceType, Boolean> resourceMap;

    public Connector() {
        this.resourceMap = new HashMap<>();
        initResourceMap();
    }

    private void initResourceMap() {
        for (ResourceType rType : ResourceType.values()) {
            resourceMap.put(rType, false);
        }
    }

}
