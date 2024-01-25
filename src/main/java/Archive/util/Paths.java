package Archive.util;

import lombok.Getter;

@Getter
public enum Paths {
    PROFILE_PICTURE("target/classes/static/documents/"),
    DOCUMENTS("target/classes/static/images/profilepictures/");

    private final String path;

    Paths(String path) {
        this.path = path;
    }

}
