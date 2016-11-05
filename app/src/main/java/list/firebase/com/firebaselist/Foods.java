package list.firebase.com.firebaselist;

/**
 * Created by Nico on 05/11/2016.
 */

public class Foods {

    private String name;
    private String local_name;
    private String description;
    private String nutritional_value;

    public Foods(){

    }

    public Foods(String name, String local_name, String description, String nutritional_value) {
        this.name = name;
        this.local_name = local_name;
        this.description = description;
        this.nutritional_value = nutritional_value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocal_name() {
        return local_name;
    }

    public void setLocal_name(String local_name) {
        this.local_name = local_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNutritional_value() {
        return nutritional_value;
    }

    public void setNutritional_value(String nutritional_value) {
        this.nutritional_value = nutritional_value;
    }
}
