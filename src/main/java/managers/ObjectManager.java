package managers;

import java.util.ArrayList;

import objects.Structure;

public class ObjectManager {

    private ArrayList<Structure> structureList = new ArrayList<Structure>();

    public void update(double dt) {
        for (Structure structure : structureList) {
            structure.update(dt);
        }
    }

    public void addStructure(Structure structure) {
        structureList.add(structure);
    }
}
