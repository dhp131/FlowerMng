package Model;

import java.io.Serializable;

public class Flower implements Serializable{
    private static final long serialVersionUID = 1L;
    String id;
    String name;
    String importDate;
    Double unitPrice;
    String category;

    public Flower() {
    }

    /**
     *
     * @param id
     * @param name
     * @param importDate
     * @param unitPrice
     * @param category
     */
    public Flower(String id, String name, String importDate, Double unitPrice, String category) {
        this.id = id;
        this.name = name;
        this.importDate = importDate;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getImportDate() {
        return importDate;
    }

    /**
     *
     * @param importDate
     */
    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    /**
     *
     * @return
     */
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     *
     * @param unitPrice
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     *
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Show info của flower
     */
    public void showInfo() {
        String str = String.format("|%11s|%15s|%14s|%14s|%10s|", this.id, this.name, this.importDate,
                this.unitPrice, this.category);
        System.out.println(str);
    }

    /**
     * return string
     *
     * @return
     */
    @Override
    public String toString() {
        String str = String.format("%s,%s,%s,%f,%s", this.id, this.name,
                this.importDate, this.unitPrice, this.category);
        return str;
    }
}
