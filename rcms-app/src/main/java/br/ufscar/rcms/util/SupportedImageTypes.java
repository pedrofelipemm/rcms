package br.ufscar.rcms.util;

public enum SupportedImageTypes {

    JPEG("jpeg"),
    JPG("jpg"),
    PNG("png");

    private String fileType;

    private SupportedImageTypes(final String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }
}