package com.eijproject.swarmandhive.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.eijproject.swarmandhive.SwarmAndHive;
import com.eijproject.swarmandhive.enums.Font;
import com.eijproject.swarmandhive.lib.AreaInScreen;
import com.eijproject.swarmandhive.lib.TextUtils;
import com.eijproject.swarmandhive.services.AuthService;
import com.eijproject.swarmandhive.services.CardService;

public class LoginState extends State {
    private AreaInScreen btnLoginArea;
    private AreaInScreen btnRegisterArea;
    private CardService cardService;
    private AuthService authService;

    private Stage stage;
    private TextField usernameInput;
    private TextField passwordInput;

    public LoginState(GameStateManager gsm, CardService cardService, AuthService authService) {
        super(gsm);
        setupScreenAreas();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        setupInputs();
        this.cardService = cardService;
        this.authService = authService;
    }

    private void setupScreenAreas() {
        btnLoginArea = TextUtils.getTextCenterXAreaInScreen("Entrar", Font.NORMAL, (float)300);
        btnRegisterArea = TextUtils.getTextCenterXAreaInScreen("Cadastrar", Font.SMALL, (float)200);
    }

    @Override
    public void handleInput() {
        setupOnTouchHandlers();
    }

    private void setupOnTouchHandlers() {
        if(Gdx.input.justTouched()) {
            buttonsTouchHandler();
        }
    }

    private void buttonsTouchHandler() {
        if(btnLoginArea.checkIfInArea(Gdx.input.getX(), Gdx.input.getY())) {
            authService.login(usernameInput.getText(), passwordInput.getText());

            if(SwarmAndHive.getToken() != null && SwarmAndHive.getToken() != ""  ){
                gsm.set(new MainMenuState(gsm, cardService));
            }

            dispose();
        }else if(btnRegisterArea.checkIfInArea(Gdx.input.getX(), Gdx.input.getY())){
              gsm.set(new RegisterState(gsm, cardService, authService));

            dispose();
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        renderBackground(shapeRenderer);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        spriteBatch.begin();
        renderTitle(spriteBatch);
        renderActionButtons(spriteBatch);
        spriteBatch.end();
    }

    private void renderBackground(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.valueOf("41444b"));
        shapeRenderer.rect(0, 0, SwarmAndHive.getWidth(), SwarmAndHive.getHeight());
        shapeRenderer.end();
    }

    private void renderTitle(SpriteBatch spriteBatch) {
        TextUtils.drawTextCenterX(spriteBatch, "Swarm and Hive", Font.TITLE, (float)SwarmAndHive.getHeight() - 150);
    }

    private void renderActionButtons(SpriteBatch spriteBatch) {
        TextUtils.drawTextCenterX(spriteBatch, "Entrar", Font.NORMAL, (float)300);
        TextUtils.drawTextCenterX(spriteBatch, "Cadastrar", Font.SMALL, (float)200);
    }

    private void setupInputs() {
        Skin skin = new Skin(Gdx.files.internal("level-plane-ui.json"));
        float width = Gdx.graphics.getWidth();

        usernameInput = new TextField("Usuario", skin);
        usernameInput.setSize(450, 80);

        passwordInput = new TextField("Senha", skin);
        passwordInput.setSize(450, 80);

        usernameInput.setPosition((width / 2) - (usernameInput.getWidth() / 2), 600);
        passwordInput.setPosition((width / 2) - (passwordInput.getWidth() / 2), 500);

        TextField.TextFieldStyle textFieldStyle = skin.get(TextField.TextFieldStyle.class);
        textFieldStyle.font.getData().setScale(2.5f);
        textFieldStyle.font.getData().setScale(2.5f);

        usernameInput.setStyle(textFieldStyle);
        passwordInput.setStyle(textFieldStyle);

        usernameInput.addListener(new FocusListener() {
            public void keyboardFocusChanged(FocusListener.FocusEvent event, Actor actor, boolean focused) {
                if(focused == true) {
                    if(usernameInput.getText().contains("Usuario")){
                        usernameInput.setText("");
                    }
                }
                else if(focused == false){
                    if(usernameInput.getText() == ""){
                        usernameInput.setText("Usuario");
                    }
                }
            }
        });

        passwordInput.addListener(new FocusListener() {
            public void keyboardFocusChanged(FocusListener.FocusEvent event, Actor actor, boolean focused) {
                if(focused == true) {
                    if(passwordInput.getText().contains("Senha")){
                        passwordInput.setText("");
                    }
                }
                else if(focused == false){
                    if(passwordInput.getText() == ""){
                        passwordInput.setText("Senha");
                    }
                }
            }
        });
        stage.addActor(usernameInput);
        stage.addActor(passwordInput);
    }

    @Override
    public void dispose() {
    }
}
