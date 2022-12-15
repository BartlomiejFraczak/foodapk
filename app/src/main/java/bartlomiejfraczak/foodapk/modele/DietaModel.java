package bartlomiejfraczak.foodapk.modele;

import java.util.Arrays;
import java.util.List;

import bartlomiejfraczak.foodapk.encje.Dieta;

public class DietaModel {
    public static final Dieta GLUTEN_FREE = new Dieta("gluten+free", "Gluten Free", "Eliminating gluten means avoiding wheat, barley, rye, and other gluten-containing grains and foods made from them (or that may have been cross contaminated).");
    public static final Dieta KETOGENIC = new Dieta("ketogenic", "Ketogenic", "The keto diet is based more on the ratio of fat, protein, and carbs in the diet rather than specific ingredients. Generally speaking, high fat, protein-rich foods are acceptable and high carbohydrate foods are not. The formula we use is 55-80% fat content, 15-35% protein content, and under 10% of carbohydrates.");
    public static final Dieta VEGETARIAN = new Dieta("vegetarian", "Vegetarian", "No ingredients may contain meat or meat by-products, such as bones or gelatin.");
    public static final Dieta LACTO_VEGETARIAN = new Dieta("lacto-vegetarian", "Lacto-Vegetarian", "All ingredients must be vegetarian and none of the ingredients can be or contain egg.");
    public static final Dieta OVO_VEGETARIAN = new Dieta("ovo-vegetarian", "Ovo-Vegetarian", "All ingredients must be vegetarian and none of the ingredients can be or contain dairy.");
    public static final Dieta VEGAN = new Dieta("vegan", "Vegan", "No ingredients may contain meat or meat by-products, such as bones or gelatin, nor may they contain eggs, dairy, or honey.");
    public static final Dieta PESCETARIAN = new Dieta("pescetarian", "Pescetarian", "Everything is allowed except meat and meat by-products - some pescetarians eat eggs and dairy, some do not.");
    public static final Dieta PALEO = new Dieta("paleo", "Paleo", "Allowed ingredients include meat (especially grass fed), fish, eggs, vegetables, some oils (e.g. coconut and olive oil), and in smaller quantities, fruit, nuts, and sweet potatoes. We also allow honey and maple syrup (popular in Paleo desserts, but strict Paleo followers may disagree). Ingredients not allowed include legumes (e.g. beans and lentils), grains, dairy, refined sugar, and processed foods.");
    public static final Dieta PRIMAL = new Dieta("primal", "Primal", "Very similar to Paleo, except dairy is allowed - think raw and full fat milk, butter, ghee, etc.");
    public static final Dieta LOW_FODMAP = new Dieta("low+fodmap", "Low FODMAP", "FODMAP stands for \"fermentable oligo-, di-, mono-saccharides and polyols\". Our ontology knows which foods are considered high in these types of carbohydrates (e.g. legumes, wheat, and dairy products).");
    public static final Dieta WHOLE30 = new Dieta("whole30", "Whole30", "Allowed ingredients include meat, fish/seafood, eggs, vegetables, fresh fruit, coconut oil, olive oil, small amounts of dried fruit and nuts/seeds. Ingredients not allowed include added sweeteners (natural and artificial, except small amounts of fruit juice), dairy (except clarified butter or ghee), alcohol, grains, legumes (except green beans, sugar snap peas, and snow peas), and food additives, such as carrageenan, MSG, and sulfites.");

    public static List<Dieta> getLista() {
        return Arrays.asList(GLUTEN_FREE, KETOGENIC, VEGETARIAN, LACTO_VEGETARIAN, OVO_VEGETARIAN, VEGAN, PESCETARIAN, PALEO, PRIMAL, LOW_FODMAP, WHOLE30);
    }
}
