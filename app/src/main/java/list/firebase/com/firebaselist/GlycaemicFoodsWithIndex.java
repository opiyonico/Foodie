package list.firebase.com.firebaselist;

/**
 * Created by Nico on 28/11/2016.
 */

public class GlycaemicFoodsWithIndex {

    private String foodIndex;
    private String benefits;
    private int calories;
    private String food_name;
    private int glycaemic_index;
    private String local_name;

    public GlycaemicFoodsWithIndex(String index,GlycaemicFoods foods){
        this.food_name = foods.getFood_name();
        this.glycaemic_index = foods.getGlycaemic_index();
        this.benefits = foods.getBenefits();
        this.calories = foods.getCalories();
        this.local_name = foods.getLocal_name();
        this.foodIndex = index;
    }

    public int getGlycaemic_index() {
        return glycaemic_index;
    }

    public void setGlycaemic_index(int glycaemic_index) {
        this.glycaemic_index = glycaemic_index;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getLocal_name() {
        return local_name;
    }

    public void setLocal_name(String local_name) {
        this.local_name = local_name;
    }



}
