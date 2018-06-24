package com.example.chelsi.practicalretake;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by Chelsi on 6/10/2018.
 */

public class CardViewHolder extends RecyclerView.ViewHolder {

    ImageView card;
    private int lastPosition = -1;
    private Context context;

    public CardViewHolder(View itemView) {
        super(itemView);

        card = itemView.findViewById(R.id.cardImage);
    }


    public void bind(CardModel cardModel) {
        Picasso.with(card.getContext()).load(cardModel.getImage()).into(card);
    }

    private void setAnimation(View viewToAnimate, int position){
        if (position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
