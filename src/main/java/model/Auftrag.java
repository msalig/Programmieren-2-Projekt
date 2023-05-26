package main.java.model;

public class Auftrag {

    private String importExport;

    private String kind;

    private String type;

    private String size;

    private String price;

    public Auftrag(String importExport, String kind, String type, String size, String price) {
        this.importExport = importExport;
        this.kind = kind;
        this.type = type;
        this.size = size;
        this.price = price;
    }

    public String getImportExport() {
        return importExport;
    }

    public String getKind() {
        return kind;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getPrice() {
        return price;
    }
}
