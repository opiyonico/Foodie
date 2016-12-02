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
    ArrayList<GlycaemicFoodsWithIndex> foods;
    private int setValue = 20;
    ListView foodList;
    FoodAdapter foodsAdapter;
    RecyclerView recyclerView;
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        header = (TextView)findViewById(R.id.header);
        header.setText("Foods with Glycaemic Index > " + String.valueOf(setValue));

        foods = new ArrayList<>();
        final FoodAdapter foodsAdapter = new FoodAdapter(this, foods);
        //Initialize the reference to the database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Antonio").child("proteins");

        //Adding all the values of foods into an array list so that we can populate our listview
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.e("Running event listener","Inside");
                for(DataSnapshot singleDataSnapshot: dataSnapshot.getChildren()){

                    String foodIndex = singleDataSnapshot.getKey();
                    //Log.e("Key",foodIndex);
                    GlycaemicFoods glycaemicFoods = singleDataSnapshot.getValue(GlycaemicFoods.class);

                    Log.e("gIndex",glycaemicFoods.getBenefits());

                    GlycaemicFoodsWithIndex glycaemicFoodsWithIndex = new GlycaemicFoodsWithIndex(foodIndex,glycaemicFoods);
                    if(glycaemicFoods.getGlycaemic_index() > setValue){
                        foods.add(glycaemicFoodsWithIndex);
                        Log.e("index",String.valueOf(glycaemicFoods.getGlycaemic_index()));
                    }

                    Log.d("Firebase ValuesX",foodIndex + " " + glycaemicFoods.getFood_name() + " " + glycaemicFoods.getBenefits() + " " + glycaemicFoods.getCalories() + " " + glycaemicFoods.getGlycaemic_index());

                    foodsAdapter.notifyDataSetChanged();
                }

                //foodsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("databaseError",databaseError.toString());
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.indexRecycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(foodsAdapter);



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
