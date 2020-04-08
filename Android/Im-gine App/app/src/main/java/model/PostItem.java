package model;

public class PostItem {
    private String name;
    private int image;
    private int type;

    public PostItem(String name, int image, int type){
        this.name = name;
        this.image = image;
        this.type = type;
    }

    public PostItem(String name, int image){
        this.name = name;
        this.image = image;
        this.type = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
