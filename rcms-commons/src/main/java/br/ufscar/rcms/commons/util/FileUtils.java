package br.ufscar.rcms.commons.util;

public abstract class FileUtils {

    public static String extractFileName(final String fileName) {
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }

    public static String extractFileExtension(final String fileName) {
        return fileName.substring(fileName.lastIndexOf('.'));
    }

    public static String generateReasearcherPhotoName(final String fileLocation, final String lattesCode, final String fileExtension) {
        return fileLocation + lattesCode + (fileExtension.charAt(0) == '.' ? fileExtension : '.' + fileExtension);
    }

    public static String generateGaleriaPhotoName(final String fileLocation, final Long idProjeto,
            final String fileName, final String fileExtension) {

        return fileLocation + idProjeto.toString() + "/" + fileName
                + (fileExtension.charAt(0) == '.' ? fileExtension : '.' + fileExtension);
    }

    public static String generateFileName(final String fileLocation, final String id, final String fileExtension) {
        return fileLocation + id + (fileExtension.charAt(0) == '.' ? fileExtension : '.' + fileExtension);
    }
}