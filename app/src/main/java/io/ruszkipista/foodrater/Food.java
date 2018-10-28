package io.ruszkipista.foodrater;

import android.content.Context;

import java.util.HashMap;
import java.util.Random;

public class Food {
  private String mName;
  private int mImageResourceId;
  private float mRating;
  private Random mRandom = new Random();

  public static final HashMap<Integer, Integer> sFoodImageMap;
  static {
    sFoodImageMap = new HashMap<>();
    sFoodImageMap.put(R.string.food_banana, R.drawable.banana);
    sFoodImageMap.put(R.string.food_broccoli, R.drawable.broccoli);
    sFoodImageMap.put(R.string.food_bread, R.drawable.bread);
    sFoodImageMap.put(R.string.food_chicken, R.drawable.chicken);
    sFoodImageMap.put(R.string.food_chocolate, R.drawable.chocolate);
    sFoodImageMap.put(R.string.food_icecream, R.drawable.icecream);
    sFoodImageMap.put(R.string.food_limabeans, R.drawable.limabeans);
    sFoodImageMap.put(R.string.food_steak, R.drawable.steak);
  }

  public Food(Context context) {
    mName = context.getResources().getString(getRandomFoodResourceId());
    mImageResourceId = sFoodImageMap.get(mName);
    mRating = 1.0f;
  }

  private Integer getRandomFoodResourceId() {
    Object[] foods = Food.sFoodImageMap.keySet().toArray();
    return (Integer)foods[mRandom.nextInt(foods.length)];
  }

  public String getName() {
    return mName;
  }

  public float getRating() {
    return mRating;
  }

  public void setRating(float rating) {
    mRating = rating;
  }

  public int getImageResourceId() {
    return mImageResourceId;
  }
}
