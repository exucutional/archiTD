package objects;

abstract class GameObject extends SpriteObject {
    private Boolean isActive = true;
    private Boolean isStatic = false;
    private String tag;

    public void setActive(Boolean act) {
        isActive = act;
    }

    public void setStatic(Boolean st) {
        isStatic = st;
    }

    public void setTag(String str) {
        tag = str;
    }

    public Boolean isActive() {
        return isActive;
    }

    public Boolean isStatic() {
        return isStatic;
    }

    public String getTag() {
        return tag;
    }
}
