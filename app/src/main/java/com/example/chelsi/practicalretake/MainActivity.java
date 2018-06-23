package com.example.chelsi.practicalretake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://deckofcardsapi.com/api/deck/";
    private List<CardResponse> cardList;
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private Button shuffleButton;
    private Button drawButton;
    private EditText typeIn;
    private TextView cardText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shuffleButton = findViewById(R.id.shuffle);
        drawButton = findViewById(R.id.draw);
        typeIn = findViewById(R.id.editText);
        cardText = findViewById(R.id.cardsLeft);

        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        recyclerView = findViewById(R.id.cardRecycler);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setAdapter(cardAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);




        class RetrofitHelper{

        private RetrofitHelper(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            CardService cardService = retrofit.create(CardService.class);
            Call<CardResponse> cardResponseCall = cardService.getCardResponse();
            cardResponseCall.enqueue(new Callback<CardResponse>() {
                @Override
                public void onResponse(Call<CardResponse> call, Response<CardResponse> response) {
                    cardAdapter = new CardAdapter(CardResponse);
                    recyclerView.setAdapter(cardAdapter);
                    recyclerView.setLayoutManager(gridLayoutManager);


                }

                @Override
                public void onFailure(Call<CardResponse> call, Throwable t) {

                }
            });
        }
        }
        }
    }

