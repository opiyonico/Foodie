package list.firebase.com.firebaselist;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico on 28/11/2016.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder>{

    ArrayList<GlycaemicFoodsWithIndex> filteredFoods;
    Context mContext;

    public FoodAdapter(Context context, ArrayList<GlycaemicFoodsWithIndex> foods){
        this.filteredFoods = foods;//Put the list that met the condition in another arraylist
        this.mContext = context;//Pass the context
    }

    @Override
    public FoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fooditem,parent,false);

        FoodHolder foodHolder = new FoodHolder(view);//For every food in the list, use the layout set above

        return foodHolder;
    }

    @Override
    public void onBindViewHolder(FoodHolder holder, int position) {
        GlycaemicFoodsWithIndex indexedFoods = filteredFoods.get(position);
        //For each food in the list, set the text in the view
        holder.tvBenefits.setText("Benefits:" + " " + indexedFoods.getBenefits());
        holder.tvLocalName.setText("Local Name:" + " " + indexedFoods.getLocal_name());
        holder.tvFoodName.setText("Food Name:" + " " + indexedFoods.getFood_name());
        holder.tvGlycaemicIndex.setText("Glycaemic Index:" + " " + String.valueOf(indexedFoods.getGlycaemic_index()));
        holder.tvCalories.setText("Calories:" + " " + String.valueOf(indexedFoods.getCalories()));

    }

    @Override
    public int getItemCount() {
        return filteredFoods.size();
    }

    public class FoodHolder extends RecyclerView.ViewHolder {
        public TextView tvBenefits, tvCalories,tvFoodName,tvGlycaemicIndex,tvLocalName;


        public FoodHolder(View itemView) {
            super(itemView);
            //Initalize all the textfields and views of the view you got above
            tvBenefits = (TextView) itemView.findViewById(R.id.tvBenefits);
            tvCalories = (TextView) itemView.findViewById(R.id.calories);
            tvFoodName = (TextView) itemView.findViewById(R.id.foodName);
            tvGlycaemicIndex = (TextView) itemView.findViewById(R.id.glycaemicIndex);
            tvLocalName = (TextView)itemView.findViewById(R.id.local_name);
        }
    }

}
