package by.jum.imageprocessing.utils.constants;

public enum Path {

    LOG4J_CONF_PATH("resource/config/log4j.properties"),
    DEFAULT_IMAGE("resource/images/James.png"),
    FILTER1("resource/filters/filter1.txt"),
    FILTER2("resource/filters/filter2.txt"),
    FILTER3("resource/filters/filter3.txt");

    private String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
