package com.eijproject.swarmandhive.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.eijproject.swarmandhive.SwarmAndHive;
import com.eijproject.swarmandhive.entities.AreaInScreen;

import java.awt.geom.Area;

public class TextUtils {
    private static FreeTypeFontGenerator fontGenerator;
    private static BitmapFont font;

    public static void drawTextCenter(SpriteBatch spriteBatch, String text, int size, int borderWidth, Color borderColor, Color color) {
        setupFont(size, borderWidth, borderColor, color);
        GlyphLayout layout = new GlyphLayout(font, text);
        drawText(spriteBatch, layout, (SwarmAndHive.getWidth() - layout.width) / 2, (SwarmAndHive.getHeight() - layout.height) / 2);
    }

    public static void drawTextCenterX(SpriteBatch spriteBatch, String text, int size, int borderWidth, Color borderColor, Color color, Float y) {
        setupFont(size, borderWidth, borderColor, color);
        GlyphLayout layout = new GlyphLayout(font, text);
        drawText(spriteBatch, layout, (SwarmAndHive.getWidth() - layout.width) / 2, y);
    }

    public static void drawTextCenterY(SpriteBatch spriteBatch, String text, int size, int borderWidth, Color borderColor, Color color, Float x) {
        setupFont(size, borderWidth, borderColor, color);
        GlyphLayout layout = new GlyphLayout(font, text);
        drawText(spriteBatch, layout, x, (SwarmAndHive.getHeight() - layout.height) / 2);
    }

    public static void drawTextXY(SpriteBatch spriteBatch, String text, int size, int borderWidth, Color borderColor, Color color, Float x, Float y) {
        setupFont(size, borderWidth, borderColor, color);
        GlyphLayout layout = new GlyphLayout(font, text);
        drawText(spriteBatch, layout, x, y);
    }


    private static void setupFont(int size, int borderWidth, Color borderColor, Color color) {
        if (fontGenerator == null)
            fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = size;
        fontParameter.borderWidth = borderWidth;
        fontParameter.borderColor = borderColor;
        fontParameter.color = color;
        font = fontGenerator.generateFont(fontParameter);
    }

    private static void setupFont(int size, int borderWidth) {
        if (fontGenerator == null)
            fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = size;
        fontParameter.borderWidth = borderWidth;
        font = fontGenerator.generateFont(fontParameter);
    }

    private static void drawText(SpriteBatch spriteBatch, GlyphLayout layout, Float x, Float y) {
        font.draw(spriteBatch, layout, x, y);
    }

    public static AreaInScreen getTextCenterAreaInScreen(String text, int size, int borderWidth) {
        setupFont(size, borderWidth);
        GlyphLayout layout = new GlyphLayout(font, text);

        return new AreaInScreen(
                (SwarmAndHive.getHeight() - layout.height) / 2,
                (SwarmAndHive.getWidth() + layout.width) / 2,
                (SwarmAndHive.getHeight() + layout.height) / 2,
                (SwarmAndHive.getWidth() - layout.width) / 2
        );
    }

    public static AreaInScreen getTextCenterXAreaInScreen(String text, int size, int borderWidth, Float y) {
        setupFont(size, borderWidth);
        GlyphLayout layout = new GlyphLayout(font, text);

        return new AreaInScreen(
                y - layout.height,
                (SwarmAndHive.getWidth() + layout.width) / 2,
                y,
                (SwarmAndHive.getWidth() - layout.width) / 2
        );
    }

    public static AreaInScreen getTextCenterYAreaInScreen(String text, int size, int borderWidth, Float x) {
        setupFont(size, borderWidth);
        GlyphLayout layout = new GlyphLayout(font, text);

        return new AreaInScreen(
                (SwarmAndHive.getHeight() - layout.height) / 2,
                x + layout.width,
                (SwarmAndHive.getHeight() + layout.height) / 2,
                x
        );
    }

    public static AreaInScreen getTextXYAreaInScreen(String text, int size, int borderWidth, Float x, Float y) {
        setupFont(size, borderWidth);
        GlyphLayout layout = new GlyphLayout(font, text);

        return new AreaInScreen(
                y - layout.height,
                x + layout.width,
                y,
                x
        );
    }
}
