package list.firebase.com.firebaselist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico on 28/11/2016.
 */

public class FoodAdapter extends ArrayAdapter<GlycaemicFoodsWithIndex>{
    public FoodAdapter(Context context, ArrayList<GlycaemicFoodsWithIndex> foods) {
        super(context, 0, foods);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GlycaemicFoodsWithIndex foods = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fooditem, parent, false);
        }

        TextView tvBenefits = (TextView) convertView.findViewById(R.id.benefits);
        TextView tvCalories = (TextView) convertView.findViewById(R.id.calories);
        TextView tvName = (TextView) convertView.findViewById(R.id.foodName);
        TextView tvIndex = (TextView)convertView.findViewById(R.id.g_index);
        TextView tvLocalName = (TextView)convertView.findViewById(R.id.local_name);

        tvBenefits.setText(foods.getBenefits());
        tvCalories.setText(foods.getCalories());
        tvIndex.setText(foods.getGlycaemic_index());
        tvName.setText(foods.getFood_name());
        tvLocalName.setText(foods.getLocal_name());


        // Return the completed view to render on screen
        return convertView;


    }
}
