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

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("foods");

        edit_name = (EditText)findViewById(R.id.edit_food_name);
        edit_local_name = (EditText)findViewById(R.id.edit_local_name);
        edit_description = (EditText)findViewById(R.id.edit_description);
        edit_nutritional_value = (EditText)findViewById(R.id.edit_nutritional_value);
        addFood = (Button)findViewById(R.id.addFoodButton);

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFood();
            }
        });




    }

    public void newFood(){

        DatabaseReference newCafeteria = databaseReference.push();

        newCafeteria.child("name").setValue(edit_name.getText());
        newCafeteria.child("local_name").setValue(edit_local_name.getText());
        newCafeteria.child("description").setValue(edit_description.getText());
        newCafeteria.child("nutritional_value").setValue(edit_nutritional_value.getText());
    }

}
