package com.yupi.maker.meta.enums;

/**
 * 文件类型枚举
 */
public enum FileTypeEnum {
    DIR("目录", "dir"),
    File("文件", "file");

    private String text;
    private String value;

    FileTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
