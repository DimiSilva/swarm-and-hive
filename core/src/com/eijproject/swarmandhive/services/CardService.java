package com.eijproject.swarmandhive.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.eijproject.swarmandhive.SwarmAndHive;
import com.eijproject.swarmandhive.entities.Card;

import java.util.ArrayList;

public class CardService {
    private boolean waitingResponse = false;
    private String endpoint = "/cards";

    public ArrayList<Card> getDeckCards() {
        final ArrayList<Card> cards = new ArrayList<>();

        Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);
        httpGet.setUrl(SwarmAndHive.apiUrl + endpoint + "/deck-cards");
        httpGet.setHeader("Authorization", SwarmAndHive.getToken());
        System.out.println("token: " + SwarmAndHive.getToken());
        waitingResponse = true;

        SwarmAndHive.setLoading(1);
        Gdx.net.sendHttpRequest(httpGet, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {

                JsonReader json = new JsonReader();
                JsonValue jsonResult = json.parse(httpResponse.getResultAsString());
                for (JsonValue jsonCard : jsonResult) {
                    cards.add(Card.fromJson(jsonCard));
                }
                waitingResponse = false;
                SwarmAndHive.setLoading(-1);
            }

            @Override
            public void failed(Throwable t) {
                System.out.println(t.getMessage());
                waitingResponse = false;
                SwarmAndHive.setLoading(-1);
            }

            @Override
            public void cancelled() {
                System.out.println("cancelled");
                waitingResponse = false;
                SwarmAndHive.setLoading(-1);
            }
        });
        try {
            while (waitingResponse)
                Thread.sleep(10);
        } catch (InterruptedException error) {
            System.out.println(error.getMessage());
        }
        System.out.println(cards.toString());
        return cards;
    }
}
