package list.firebase.com.firebaselist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewFood extends AppCompatActivity {

    EditText edit_name,edit_local_name,edit_description,edit_nutritional_value;

    Button addFood;

    //Create the database refernce object
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize the reference to the database pointing to a child(table) named foods
        databaseReference = FirebaseDatabase.getInstance().getReference().child("foods");


        //Initialize the EditTexts
        edit_name = (EditText)findViewById(R.id.edit_food_name);
        edit_local_name = (EditText)findViewById(R.id.edit_local_name);
        edit_description = (EditText)findViewById(R.id.edit_description);
        edit_nutritional_value = (EditText)findViewById(R.id.edit_nutritional_value);

        //Initialize the add food button
        addFood = (Button)findViewById(R.id.addFoodButton);

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFood();//Call function that adds new foods to firebase on clicking the add food button


                //Clear fields of
                edit_name.setText("");
                edit_local_name.setText("");
                edit_description.setText("");
                edit_nutritional_value.setText("");
            }
        });




    }

    //Function that adds the foods to the reatime firebase database
    public void newFood(){
        //Assign the values of the EditTexts to Strings
        String name = edit_name.getText().toString();
        String local_name = edit_local_name.getText().toString();
        String description = edit_description.getText().toString();
        String nutritional_value = edit_nutritional_value.getText().toString();

        //Add the food to the firebase database with a unique identifier
        //This is done by use of the model
        //The arguments passed in the Foods constructor should be the same
        databaseReference.push().setValue(new Foods(name,local_name,description,nutritional_value));



    }

}
