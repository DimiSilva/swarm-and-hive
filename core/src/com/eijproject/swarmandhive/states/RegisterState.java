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
import com.eijproject.swarmandhive.services.Services;


public class RegisterState extends State {
    private AreaInScreen btnRegister;
    private AreaInScreen btnBack;

    private Stage stage;
    private TextField emailInput;
    private TextField usernameInput;
    private TextField passwordInput;

    public RegisterState(GameStateManager gsm) {
        super(gsm);
        setupScreenAreas();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        setupInputs();
    }

    private void setupScreenAreas() {
        btnRegister = TextUtils.getTextCenterXAreaInScreen("Cadastrar", Font.NORMAL, (float)300);
        btnBack = TextUtils.getTextCenterXAreaInScreen("Voltar", Font.SMALL, (float)200);
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
        if(btnRegister.checkIfInArea(Gdx.input.getX(), Gdx.input.getY())) {
            Services.getInstance().register(emailInput.getText(), usernameInput.getText(), passwordInput.getText());
            if(SwarmAndHive.getToken() != null && SwarmAndHive.getToken() != ""  ){
                  gsm.set(new LoginState(gsm));
            }
            dispose();
        }else if(btnBack.checkIfInArea(Gdx.input.getX(), Gdx.input.getY())) {
            gsm.set(new LoginState(gsm));
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
        TextUtils.drawTextCenterX(spriteBatch, "Cadastrar", Font.NORMAL, (float)300);
        TextUtils.drawTextCenterX(spriteBatch, "Voltar", Font.SMALL, (float)200);
    }

    private void setupInputs() {
        Skin skin = new Skin(Gdx.files.internal("level-plane-ui.json"));
        float width = Gdx.graphics.getWidth();

        emailInput = new TextField("Email", skin);
        emailInput.setSize(450, 80);

        usernameInput = new TextField("Usuario", skin);
        usernameInput.setSize(450, 80);

        passwordInput = new TextField("Senha", skin);
        passwordInput.setSize(450, 80);

        emailInput.setPosition((width / 2) - (passwordInput.getWidth() / 2), 650);
        usernameInput.setPosition((width / 2) - (usernameInput.getWidth() / 2), 550);
        passwordInput.setPosition((width / 2) - (passwordInput.getWidth() / 2), 450);

        TextField.TextFieldStyle textFieldStyle = skin.get(TextField.TextFieldStyle.class);
        textFieldStyle.font.getData().setScale(2.5f);
        textFieldStyle.font.getData().setScale(2.5f);

        emailInput.setStyle(textFieldStyle);
        usernameInput.setStyle(textFieldStyle);
        passwordInput.setStyle(textFieldStyle);

        emailInput.addListener(new FocusListener() {
            public void keyboardFocusChanged(FocusListener.FocusEvent event, Actor actor, boolean focused) {
                if(focused == true) {
                    if(emailInput.getText().contains("Email")){
                        emailInput.setText("");
                    }
                }
                else if(focused == false){
                    if(emailInput.getText() == ""){
                        emailInput.setText("Email");
                    }
                }
            }
        });

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

        stage.addActor(emailInput);
        stage.addActor(usernameInput);
        stage.addActor(passwordInput);
    }

    @Override
    public void dispose() {
    }
}
