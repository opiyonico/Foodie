package list.firebase.com.firebaselist;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    private ListAdapter listAdapter;
    ArrayList<GlycaemicFoodsWithIndex> foods;//Arraylist that holds the foods to be passed to the adapter
    private int setValue = 20;//Setting the value of the value fo greater than manually
    RecyclerView recyclerView;
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        header = (TextView)findViewById(R.id.header);
        //Setting the header at the top showing what set value is
        header.setText("Foods with Glycaemic Index > " + String.valueOf(setValue));

        foods = new ArrayList<>();//Initialize the array list
        final FoodAdapter foodsAdapter = new FoodAdapter(this, foods);//Initalize the adapter
        //Initialize the reference to the database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Antonio").child("proteins");//Initialize the database reference

        //Adding all the values of foods into an array list so that we can populate our listview
        databaseReference.addValueEventListener(new ValueEventListener() {//Query database for all the proteins
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.e("Running event listener","Inside");
                for(DataSnapshot singleDataSnapshot: dataSnapshot.getChildren()){//Loop through all the proteins received from Firebase

                    String foodIndex = singleDataSnapshot.getKey();//Get the key of the foods obtained
                    //Log.e("Key",foodIndex);
                    GlycaemicFoods glycaemicFoods = singleDataSnapshot.getValue(GlycaemicFoods.class);//Put the protein in the Glycaemic Foods class

                    Log.e("gIndex",glycaemicFoods.getBenefits());
                    //Add food and its index to the glycaemic food class
                    GlycaemicFoodsWithIndex glycaemicFoodsWithIndex = new GlycaemicFoodsWithIndex(foodIndex,glycaemicFoods);
                    //Check if the value of the proteins glycaemic index is greater than the value you want
                    if(glycaemicFoods.getGlycaemic_index() > setValue){

                        //If the value is greater, add it to the list that you'll pass to the adapter, if not don't bother with it
                        foods.add(glycaemicFoodsWithIndex);
                        Log.e("index",String.valueOf(glycaemicFoods.getGlycaemic_index()));
                    }

                    Log.d("Firebase ValuesX",foodIndex + " " + glycaemicFoods.getFood_name() + " " + glycaemicFoods.getBenefits() + " " + glycaemicFoods.getCalories() + " " + glycaemicFoods.getGlycaemic_index());

                    //Alert the adapter that we have some food :-)
                    foodsAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("databaseError",databaseError.toString());
            }
        });
        //Initalize the recycler view - Please note that all these lines are critical especially the layout manager
        recyclerView = (RecyclerView)findViewById(R.id.indexRecycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(foodsAdapter);//Set the adapter to the one you set



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
