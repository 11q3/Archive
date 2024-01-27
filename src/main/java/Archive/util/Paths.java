package Archive.util;

import lombok.Getter;

@Getter
public enum Paths {
    PROFILE_PICTURE("target/classes/static/images/profilepictures"),

    DOCUMENTS("target/classes/static/documents/");

    private final String path;

    Paths(String path) {
        this.path = path;
    }

}
