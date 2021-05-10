package dev.bbs.study.csw.vos.apis;

public class CountVo {
    private final String field;
    private final String value;

    public CountVo(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }
}
