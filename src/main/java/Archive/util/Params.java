package Archive.util;

import lombok.Getter;

@Getter
public enum Params {
    DOCUMENTS_PER_PAGE("16");

    private final String param;

    Params(String param) {
        this.param = param;
    }

}
