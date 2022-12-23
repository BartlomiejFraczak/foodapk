package bartlomiejfraczak.foodapk.encje;

import java.util.List;

public class PrzepisSzczegolowy extends Przepis{
    private int readyInMinutes;
    private int servings;
    private String sourceUrl;
    private String summary;
    private List<String> cuisines;
    private List<String> dishTypes;
    private List<String> diets;
    private List<String> occasions;
    private List<String> pairedWines;
    private String pairingText;
    private String instructions;
    private boolean vegetarian;
    private boolean vegan;
    private boolean glutenFree;
    private boolean dairyFree;
    private boolean veryHealthy;
    private boolean cheap;
    private boolean veryPopular;
    private boolean sustainable;
    private boolean lowFodmap;
    private int weightWatcherSmartPoints;
    private String gaps;
    private int preparationMinutes;
    private int cookingMinutes;
    private int aggregateLikes;
    private float healthScore;
    private String creditsText;
    private String license;
    private String sourceName;
    private float pricePerServing;
    private List<Skladnik> extendedIngredients;
    private String spoonacularSourceUrl;
    private String notatka;
    private boolean ulubiony;

    public PrzepisSzczegolowy(int id, String title, int readyInMinutes, int servings, String sourceUrl, String image, String imageType, String summary, List<String> cuisines, List<String> dishTypes, List<String> diets, List<String> occasions, List<String> pairedWines, String pairingText, String instructions, boolean vegetarian, boolean vegan, boolean glutenFree, boolean dairyFree, boolean veryHealthy, boolean cheap, boolean veryPopular, boolean sustainable, boolean lowFodmap, int weightWatcherSmartPoints, String gaps, int preparationMinutes, int cookingMinutes, int aggregateLikes, float healthScore, String creditsText, String license, String sourceName, float pricePerServing, List<Skladnik> extendedIngredients, String spoonacularSourceUrl, String notatka, boolean ulubiony) {
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.sourceUrl = sourceUrl;
        this.image = image;
        this.imageType = imageType;
        this.summary = summary;
        this.cuisines = cuisines;
        this.dishTypes = dishTypes;
        this.diets = diets;
        this.occasions = occasions;
        this.pairedWines = pairedWines;
        this.pairingText = pairingText;
        this.instructions = instructions;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.veryHealthy = veryHealthy;
        this.cheap = cheap;
        this.veryPopular = veryPopular;
        this.sustainable = sustainable;
        this.lowFodmap = lowFodmap;
        this.weightWatcherSmartPoints = weightWatcherSmartPoints;
        this.gaps = gaps;
        this.preparationMinutes = preparationMinutes;
        this.cookingMinutes = cookingMinutes;
        this.aggregateLikes = aggregateLikes;
        this.healthScore = healthScore;
        this.creditsText = creditsText;
        this.license = license;
        this.sourceName = sourceName;
        this.pricePerServing = pricePerServing;
        this.extendedIngredients = extendedIngredients;
        this.spoonacularSourceUrl = spoonacularSourceUrl;
        this.notatka = notatka;
        this.ulubiony = ulubiony;
    }

    @Override
    public String toString() {
        return "PrzepisSzczegolowy{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", readyInMinutes=" + readyInMinutes +
                ", servings=" + servings +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", image='" + image + '\'' +
                ", imageType='" + imageType + '\'' +
                ", summary='" + summary + '\'' +
                ", cuisines=" + cuisines +
                ", dishTypes=" + dishTypes +
                ", diets=" + diets +
                ", occasions=" + occasions +
                ", pairedWines=" + pairedWines +
                ", pairingText='" + pairingText + '\'' +
                ", instructions='" + instructions + '\'' +
                ", vegetarian=" + vegetarian +
                ", vegan=" + vegan +
                ", glutenFree=" + glutenFree +
                ", dairyFree=" + dairyFree +
                ", veryHealthy=" + veryHealthy +
                ", cheap=" + cheap +
                ", veryPopular=" + veryPopular +
                ", sustainable=" + sustainable +
                ", lowFodmap=" + lowFodmap +
                ", weightWatcherSmartPoints=" + weightWatcherSmartPoints +
                ", gaps='" + gaps + '\'' +
                ", preparationMinutes=" + preparationMinutes +
                ", cookingMinutes=" + cookingMinutes +
                ", aggregateLikes=" + aggregateLikes +
                ", healthScore=" + healthScore +
                ", creditsText='" + creditsText + '\'' +
                ", license='" + license + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", pricePerServing=" + pricePerServing +
                ", extendedIngredients=" + extendedIngredients +
                ", spoonacularSourceUrl='" + spoonacularSourceUrl + '\'' +
                ", notatka='" + notatka + '\'' +
                ", ulubiony='" + ulubiony + '\'' +
                '}';
    }


    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }

    public List<String> getDishTypes() {
        return dishTypes;
    }

    public void setDishTypes(List<String> dishTypes) {
        this.dishTypes = dishTypes;
    }

    public List<String> getDiets() {
        return diets;
    }

    public void setDiets(List<String> diets) {
        this.diets = diets;
    }

    public List<String> getOccasions() {
        return occasions;
    }

    public void setOccasions(List<String> occasions) {
        this.occasions = occasions;
    }

    public List<String> getPairedWines() {
        return pairedWines;
    }

    public void setPairedWines(List<String> pairedWines) {
        this.pairedWines = pairedWines;
    }

    public String getPairingText() {
        return pairingText;
    }

    public void setPairingText(String pairingText) {
        this.pairingText = pairingText;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public boolean isDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(boolean dairyFree) {
        this.dairyFree = dairyFree;
    }

    public boolean isVeryHealthy() {
        return veryHealthy;
    }

    public void setVeryHealthy(boolean veryHealthy) {
        this.veryHealthy = veryHealthy;
    }

    public boolean isCheap() {
        return cheap;
    }

    public void setCheap(boolean cheap) {
        this.cheap = cheap;
    }

    public boolean isVeryPopular() {
        return veryPopular;
    }

    public void setVeryPopular(boolean veryPopular) {
        this.veryPopular = veryPopular;
    }

    public boolean isSustainable() {
        return sustainable;
    }

    public void setSustainable(boolean sustainable) {
        this.sustainable = sustainable;
    }

    public boolean isLowFodmap() {
        return lowFodmap;
    }

    public void setLowFodmap(boolean lowFodmap) {
        this.lowFodmap = lowFodmap;
    }

    public int getWeightWatcherSmartPoints() {
        return weightWatcherSmartPoints;
    }

    public void setWeightWatcherSmartPoints(int weightWatcherSmartPoints) {
        this.weightWatcherSmartPoints = weightWatcherSmartPoints;
    }

    public String getGaps() {
        return gaps;
    }

    public void setGaps(String gaps) {
        this.gaps = gaps;
    }

    public int getPreparationMinutes() {
        return preparationMinutes;
    }

    public void setPreparationMinutes(int preparationMinutes) {
        this.preparationMinutes = preparationMinutes;
    }

    public int getCookingMinutes() {
        return cookingMinutes;
    }

    public void setCookingMinutes(int cookingMinutes) {
        this.cookingMinutes = cookingMinutes;
    }

    public int getAggregateLikes() {
        return aggregateLikes;
    }

    public void setAggregateLikes(int aggregateLikes) {
        this.aggregateLikes = aggregateLikes;
    }

    public float getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(float healthScore) {
        this.healthScore = healthScore;
    }

    public String getCreditsText() {
        return creditsText;
    }

    public void setCreditsText(String creditsText) {
        this.creditsText = creditsText;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public float getPricePerServing() {
        return pricePerServing;
    }

    public void setPricePerServing(float pricePerServing) {
        this.pricePerServing = pricePerServing;
    }

    public List<Skladnik> getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(List<Skladnik> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    public String getSpoonacularSourceUrl() {
        return spoonacularSourceUrl;
    }

    public void setSpoonacularSourceUrl(String spoonacularSourceUrl) {
        this.spoonacularSourceUrl = spoonacularSourceUrl;
    }

    public String getNotatka() {
        return notatka;
    }

    public void setNotatka(String notatka) {
        this.notatka = notatka;
    }

    public boolean getUlubiony() {
        return ulubiony;
    }

    public void setUlubiony(boolean ulubiony) {
        this.ulubiony = ulubiony;
    }
}
