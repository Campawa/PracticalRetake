package com.example.chelsi.practicalretake;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Chelsi on 6/10/2018.
 */

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private List<CardModel> list;
    public CardAdapter(List<CardModel> list) {this.list = list;}

    public void drawingCards(List<CardModel> cardModelList){
        cardModelList.addAll(cardModelList);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder( CardViewHolder holder, int position) {
        CardModel cardModel = list.get(position);
        holder.bind(cardModel);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}