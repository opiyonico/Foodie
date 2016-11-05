package list.firebase.com.firebaselist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    private FirebaseListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Initilaize the reference to teh database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("foods");

        //Initialize the food listview
        ListView foodList = (ListView)findViewById(R.id.food_list_view);



        /*Initialize the listAdapter and set properties such as the model class, the food_layout and
        the reference to the realtime database.
         */
        listAdapter = new FirebaseListAdapter<Foods>(this,Foods.class,R.layout.food_card,databaseReference) {
            @Override
            protected void populateView(View v, Foods model, int position) {
                TextView name = (TextView)v.findViewById(R.id.food_name);
                TextView local_name = (TextView)v.findViewById(R.id.local_name);
                TextView description = (TextView)v.findViewById(R.id.description);
                TextView nutritional_value = (TextView)v.findViewById(R.id.nutritional_value);


                name.setText(model.getName());
                local_name.setText(model.getLocal_name());
                description.setText(model.getDescription());
                nutritional_value.setText(model.getNutritional_value());
            }
        };


        //Asssogn the list to the adapter
        foodList.setAdapter(listAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NewFood.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
