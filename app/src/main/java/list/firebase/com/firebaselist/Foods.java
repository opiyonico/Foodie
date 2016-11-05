package list.firebase.com.firebaselist;

/**
 * Created by Nico on 05/11/2016.
 */


//Model class that interprets the values of a food
public class Foods {

    private String name;//Name of the food
    private String local_name; //Local name of the food
    private String description; //Short description of the food
    private String nutritional_value; // The nutritional value of the food

    public Foods(){
        //Mandatory required empty constructor for Firebase adapter to work well
    }


    //Main constructor for model class so as to initialize the values into the calss
    public Foods(String name, String local_name, String description, String nutritional_value) {
        this.name = name;
        this.local_name = local_name;
        this.description = description;
        this.nutritional_value = nutritional_value;
    }
    //Getter and setter for food name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ////Getter and setter for local name
    public String getLocal_name() {
        return local_name;
    }

    public void setLocal_name(String local_name) {
        this.local_name = local_name;
    }


    //Getter and setter for food description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Getter and setter for food nutritonal value
    public String getNutritional_value() {
        return nutritional_value;
    }

    public void setNutritional_value(String nutritional_value) {
        this.nutritional_value = nutritional_value;
    }
}
