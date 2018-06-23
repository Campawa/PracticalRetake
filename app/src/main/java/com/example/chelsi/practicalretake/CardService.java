package com.example.chelsi.practicalretake;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Chelsi on 6/23/2018.
 */

public interface CardService {

    String BASE_URL =  "https://deckofcardsapi.com/api/deck/";

    @GET("new/shuffle/")
    Call<CardResponse> getCardResponse();

    @GET("{deck_id}/draw/?count")
    Call<CardResponse> getNewCards(@Path("deck_id") String newCards);
}
