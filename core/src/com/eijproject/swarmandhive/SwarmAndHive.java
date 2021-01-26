package com.eijproject.swarmandhive;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.eijproject.swarmandhive.services.AuthService;
import com.eijproject.swarmandhive.services.CardService;
import com.eijproject.swarmandhive.states.GameStateManager;
import com.eijproject.swarmandhive.states.LoadingState;
import com.eijproject.swarmandhive.states.LoginState;

public class SwarmAndHive extends ApplicationAdapter {

	private static int width;
	private static int height;

	public static int isLoading = 0;

	public static String apiUrl = "https://swarm-and-hive-api.herokuapp.com";

	private static GameStateManager gsm;
	public SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;
	private static String token;

	private CardService cardService;
	private AuthService authService;

	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		gsm = new GameStateManager();

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		Gdx.gl.glClearColor(1, 0, 0, 1);

		cardService = new CardService();
		authService = new AuthService();

		gsm.push(new LoginState(gsm, cardService, authService));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(spriteBatch, shapeRenderer);

	}

	public static int getWidth() {
		return width;
	}
	public static String getToken() {
		return token;
	}

	public static void setToken(String tok) {
		token = tok;
	}


	public static void setLoading(int loadingAdd) {
		//Caso seja maior que 1 o loading é ativato, é melhor que um controle booleado quando se trata de multi requisições assíncronas
		isLoading += loadingAdd;

		if(isLoading > 0){
			Gdx.app.log("app", "esta carregando");
			gsm.push(new LoadingState(gsm));
		}else {
			Gdx.app.log("app", "terminou de carregar");
			gsm.pop();
		}
	}
	public static int getHeight() {
		return height;
	}
}
