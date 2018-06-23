package com.example.chelsi.practicalretake;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by Chelsi on 6/10/2018.
 */

public class CardViewHolder extends RecyclerView.ViewHolder {

    ImageView card;

    public CardViewHolder(View itemView) {
        super(itemView);

        card = itemView.findViewById(R.id.cardImage);
    }


    public void bind(CardModel cardModel) {
        Picasso.with(card.getContext()).load(cardModel.getImage()).into(card);
    }
}
