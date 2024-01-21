package Archive.util;

import lombok.Getter;

@Getter
public enum Paths {
    PROFILE_PICTURE("/resources/static/images/profilepictures/"),
    DOCUMENTS("/resources/static/documents/");

    private final String path;

    Paths(String path) {
        this.path = path;
    }

}
