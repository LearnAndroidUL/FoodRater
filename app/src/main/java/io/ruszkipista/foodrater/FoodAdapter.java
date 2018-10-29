package io.ruszkipista.foodrater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{
    private List<Food> mFoods = new ArrayList<>();
    private Context mContext;
    private RecyclerView mRecyclerView;

    public FoodAdapter(Context context) {mContext = context;}

    public void addFood(){
        mFoods.add(0,new Food(mContext));
//      notifyDataSetChanged();  // works OK, but we want animation
        notifyItemInserted(0);
        notifyItemRangeChanged(0, mFoods.size());
        mRecyclerView.scrollToPosition(0);
    }

    public void removeName(int position){
        mFoods.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, mFoods.size());
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_view,viewGroup, false);
        return new FoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder foodViewHolder, int i) {
        foodViewHolder.mNameTextView.setText(mFoods.get(i).getName());
        foodViewHolder.mDescriptionTextView.setText(foodViewHolder.itemView.getContext().getResources().getString(R.string.description_template, (i+1)));
    }

    @Override
    public int getItemCount() {
        return mFoods.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTextView;
        private TextView mDescriptionTextView;

        public FoodViewHolder(View itemView){
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.name);
            mDescriptionTextView = itemView.findViewById(R.id.description);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    removeName(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}