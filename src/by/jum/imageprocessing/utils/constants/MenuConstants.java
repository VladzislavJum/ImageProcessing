package by.jum.imageprocessing.utils.constants;

public enum MenuConstants {
    PROCESSING("Processing"),
    FILTER_1("Filter 1"),
    FILTER_2("Filter 2"),
    FILTER_3("Filter 3"),
    FILE("File"),
    LOAD_IMAGE("Load Image"),
    SAVE_IMAGE("Save Image"),
    ANAGLIF("Anaglif"),
    SEGMENT("Segmentation");


    private String name;

    MenuConstants(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}

