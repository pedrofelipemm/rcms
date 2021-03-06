package br.ufscar.rcms.modelo.entidades;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TransientFile implements Serializable{

    private static final long serialVersionUID = 2778879220299272904L;

    private byte[] file;
    private String fileName;
    private String fileLocation;
    private String fileExtension;

    public TransientFile() { /* Serialization */ }

    public TransientFile(final byte[] file, final String fileName, final String fileExtension) {
        this.file = file;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    public String getFullName() {
        return fileLocation + fileName + fileExtension;
    }

    public byte[] getFile() {

        if (file == null) {
            return new byte[0];
        }

        byte[] copyOfFile = new byte[file.length];
        System.arraycopy(file, 0, copyOfFile, 0, file.length);

        return copyOfFile;
    }

    public void setFile(final byte[] file) {

        if (file == null) {
            return;
        }

        byte[] copyOfFile = new byte[file.length];
        System.arraycopy(file, 0, copyOfFile, 0, file.length);

        this.file = copyOfFile;
    }

    public static boolean isEmpty(final TransientFile file) {
        return file == null || file.getFile().length == 0;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(final String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(final String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}