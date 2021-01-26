package com.eijproject.swarmandhive.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.eijproject.swarmandhive.SwarmAndHive;

public class AuthService {
    private boolean waitingResponse = false;
    private String token;

    public void login(String email, String password) {
        Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
        httpPost.setUrl(SwarmAndHive.apiUrl + "/login");

        httpPost.setContent("{\n" +
                "    \"email\": \"" + email +"\",\n" +
                "    \"password\": \"" + password + "\"\n" +
                "}");

        waitingResponse = true;
        SwarmAndHive.setLoading(1);

        Gdx.net.sendHttpRequest(httpPost, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                System.out.println(httpResponse.getStatus().getStatusCode());
                String token = httpResponse.getHeader("Authorization");
                SwarmAndHive.setToken(token);

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
    }

    public void register(String email, String username, String password) {
        Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
        httpPost.setUrl(SwarmAndHive.apiUrl + "/users/register");
        httpPost.setHeader("Content-Type", "application/json");

        httpPost.setContent("{\n" +
                "    \"email\": \"" + email +"\",\n" +
                "    \"username\": \"" + username +"\",\n" +
                "    \"password\": \"" + password + "\"\n" +
                "}");

        waitingResponse = true;
        SwarmAndHive.setLoading(1);

        Gdx.net.sendHttpRequest(httpPost, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {

                JsonReader json = new JsonReader();
                System.out.println("123");
                JsonValue jsonResult = json.parse(httpResponse.getResultAsString());
                System.out.println(httpResponse.getStatus().getStatusCode());

                System.out.println(jsonResult);
                waitingResponse = false;
                SwarmAndHive.setLoading(-1);
            }

            @Override
            public void failed(Throwable t) {
                System.out.println(t.getMessage());
                System.out.println("3");
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



    }
}
