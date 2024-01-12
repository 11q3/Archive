package Archive.util;

import lombok.Getter;

@Getter
public enum Paths {
    PROFILE_PICTURE("/home/elevenqtwo/Desktop/Archive/src/main/resources/static/images/profilepictures"),
    DOCUMENTS("/home/elevenqtwo/Desktop/Archive/src/main/resources/static/documents");

    private final String path;

    Paths(String path) {
        this.path = path;
    }

}
