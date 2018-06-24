package com.example.chelsi.practicalretake;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
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

import static com.example.chelsi.practicalretake.CardService.BASE_URL;

public class MainActivity extends AppCompatActivity {

    private List<CardResponse> cardList;
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private Button shuffleButton;
    private Button drawButton;
    private EditText typeIn;
    private TextView cardText;
    private Retrofit retrofit;
    private CardService cardService;
    private int leftoverCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shuffleButton = findViewById(R.id.shuffle);
        drawButton = findViewById(R.id.draw);
        typeIn = findViewById(R.id.editText);
        cardText = findViewById(R.id.cardsLeft);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recyclerView = findViewById(R.id.cardRecycler);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setAdapter(cardAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        cardAdapter = new CardAdapter(new ArrayList<CardModel>());
        recyclerView.setAdapter(cardAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);


        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardService = retrofit.create(CardService.class);
                Call<CardResponse> cardResponseCall = cardService.getCardResponse();
                cardResponseCall.enqueue(new Callback<CardResponse>() {
                    @Override
                    public void onResponse(Call<CardResponse> call, Response<CardResponse> response) {
                        String newDeck = response.body().getDeck_id();
                        leftoverCards = response.body().getRemaining();
                        cardText.setText(leftoverCards + "remaining");
                        Log.d("Shuffle", "Is this right?");

                    }

                    @Override
                    public void onFailure(Call<CardResponse> call, Throwable t) {
                        Log.d("Not Successful", t.getLocalizedMessage());
                    }
                });

            }
        });

        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cardsDrawn = Integer.parseInt(typeIn.getText().toString());
                if (cardsDrawn < 1) {
                    typeIn.setText("You must draw at least 1 card");
                } else if (cardsDrawn > leftoverCards) {
                    typeIn.setText("There are only" + leftoverCards + " cards remaining.");
                } else {

                        InputMethodManager inputMethodManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);

                    cardService = retrofit.create(CardService.class);
                    Call<CardResponse> cardResponseCall = cardService.getCardResponse();
                    cardResponseCall.enqueue(new Callback<CardResponse>() {
                        @Override
                        public void onResponse(Call<CardResponse> call, Response<CardResponse> response) {
                            List<CardModel> drawnCards = response.body().getCards();
                            cardAdapter.drawingCards(drawnCards);
                            cardAdapter.notifyDataSetChanged();
                            typeIn.getText().clear();
                        }

                        @Override
                        public void onFailure(Call<CardResponse> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }
            }
        });

    }
}



