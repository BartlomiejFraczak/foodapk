package bartlomiejfraczak.foodapk.encje;

import java.util.List;

public class Skladnik {

    private int id;
    private String aisle;
    private String image;
    private String consistency;
    private String name;
    private String nameClean;
    private String original;
    private String originalName;
    private float amount;
    private String unit;
    private List<String> meta;
    private Miara measure_metric;
    private Miara measure_us;

    public Skladnik(int id, String aisle, String image, String consistency, String name, String nameClean, String original, String originalName, float amount, String unit, List<String> meta, Miara measure_metric, Miara measure_us) {
        this.id = id;
        this.aisle = aisle;
        this.image = image;
        this.consistency = consistency;
        this.name = name;
        this.nameClean = nameClean;
        this.original = original;
        this.originalName = originalName;
        this.amount = amount;
        this.unit = unit;
        this.meta = meta;
        this.measure_metric = measure_metric;
        this.measure_us = measure_us;
    }

    @Override
    public String toString() {
        return "Skladnik{" +
                "id=" + id +
                ", aisle='" + aisle + '\'' +
                ", image='" + image + '\'' +
                ", consistency='" + consistency + '\'' +
                ", name='" + name + '\'' +
                ", nameClean='" + nameClean + '\'' +
                ", original='" + original + '\'' +
                ", originalName='" + originalName + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", meta=" + meta +
                ", measure_metric=" + measure_metric +
                ", measure_us=" + measure_us +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getConsistency() {
        return consistency;
    }

    public void setConsistency(String consistency) {
        this.consistency = consistency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameClean() {
        return nameClean;
    }

    public void setNameClean(String nameClean) {
        this.nameClean = nameClean;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<String> getMeta() {
        return meta;
    }

    public void setMeta(List<String> meta) {
        this.meta = meta;
    }

    public Miara getMeasure_metric() {
        return measure_metric;
    }

    public void setMeasure_metric(Miara measure_metric) {
        this.measure_metric = measure_metric;
    }

    public Miara getMeasure_us() {
        return measure_us;
    }

    public void setMeasure_us(Miara measure_us) {
        this.measure_us = measure_us;
    }
}
