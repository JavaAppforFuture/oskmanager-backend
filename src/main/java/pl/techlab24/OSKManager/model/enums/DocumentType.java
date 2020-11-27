package pl.techlab24.OSKManager.model.enums;

public enum DocumentType {

    PhotoId("PhotoId"),
    Passport("Passport"),
    Other("Other");

    private final String documentTypeDescription;

    DocumentType(String documentTypeDescription) {
        this.documentTypeDescription = documentTypeDescription;
    }
}
