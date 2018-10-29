package io.ruszkipista.foodrater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
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

    public void removeFood(int position){
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
        Food food = mFoods.get(i);
        foodViewHolder.mPictureImageView.setImageResource(food.getImageResourceId());
        foodViewHolder.mNameTextView.setText(food.getName());
        foodViewHolder.mRatingBar.setRating(food.getRating());
    }

    @Override
    public int getItemCount() {
        return mFoods.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {
        private ImageView mPictureImageView;
        private TextView mNameTextView;
        private RatingBar mRatingBar;

        public FoodViewHolder(View itemView){
            super(itemView);
            mPictureImageView = itemView.findViewById(R.id.food_picture);
            mNameTextView = itemView.findViewById(R.id.food_name);
            mRatingBar = itemView.findViewById(R.id.food_ratingbar);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    removeFood(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}