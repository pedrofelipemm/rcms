package br.ufscar.rcms.util;

public abstract class FileUtils {

    public static String extractFileExtension(final String fileName) {
        return fileName.substring(fileName.lastIndexOf('.'));
    }

    public static String generateReasearcherPhotoName(final String fileLocation, final String lattesCode, final String fileExtension) {
        return fileLocation + lattesCode + (fileExtension.charAt(0) == '.' ? fileExtension : '.' + fileExtension);
    }
}