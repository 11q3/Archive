package Archive.util;

import lombok.Getter;

@Getter
public enum Params {

    DOCUMENTS_PER_PAGE("2");
    private final String param;

    Params(String param) {
        this.param = param;
    }

}
