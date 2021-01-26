package com.eijproject.swarmandhive.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.eijproject.swarmandhive.SwarmAndHive;
import com.eijproject.swarmandhive.enums.Font;

public class TextUtils {
    private static final FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
    private static BitmapFont titleFont = generateTitleFont();
    private static BitmapFont normalFont = generateNormalFont();
    private static BitmapFont smallFont = generateSmallFont();
    private static BitmapFont smallsmallFont = generateSmallSmallFont();

    public static void drawTextCenter(SpriteBatch spriteBatch, String text, Font fontType) {
        GlyphLayout layout = new GlyphLayout(getFont(fontType), text);
        drawText(spriteBatch, getFont(fontType), layout, (SwarmAndHive.getWidth() - layout.width) / 2, (SwarmAndHive.getHeight() - layout.height) / 2);
    }

    public static void drawTextCenterX(SpriteBatch spriteBatch, String text, Font fontType, Float y) {
        GlyphLayout layout = new GlyphLayout(getFont(fontType), text);
        drawText(spriteBatch, getFont(fontType), layout, (SwarmAndHive.getWidth() - layout.width) / 2, y);
    }

    public static void drawTextCenterY(SpriteBatch spriteBatch, String text, Font fontType, Float x) {
        GlyphLayout layout = new GlyphLayout(getFont(fontType), text);
        drawText(spriteBatch, getFont(fontType), layout, x, (SwarmAndHive.getHeight() - layout.height) / 2);
    }

    public static void drawTextXY(SpriteBatch spriteBatch, String text, Font fontType, Float x, Float y) {
        GlyphLayout layout = new GlyphLayout(getFont(fontType), text);
        drawText(spriteBatch, getFont(fontType), layout, x, y);
    }

    public static void drawTextXYCentered(SpriteBatch spriteBatch, String text, Font fontType, Float x, Float y) {
        GlyphLayout layout = new GlyphLayout(getFont(fontType), text);
        drawText(spriteBatch, getFont(fontType), layout, x - layout.width / 2, y - layout.height / 2);
    }

    private static BitmapFont generateTitleFont() {
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 96;
        fontParameter.borderWidth = 1;
        fontParameter.borderColor = Color.valueOf("E3EB0D");
        fontParameter.color = Color.valueOf("E2E9AE");
        return fontGenerator.generateFont(fontParameter);
    }

    private static BitmapFont generateNormalFont() {
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 48;
        fontParameter.borderWidth = 1;
        fontParameter.borderColor = Color.valueOf("E3EB0D");
        fontParameter.color = Color.valueOf("E2E9AE");
        return fontGenerator.generateFont(fontParameter);
    }

    private static BitmapFont generateSmallFont() {
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 32;
        fontParameter.borderWidth = 1;
        fontParameter.borderColor = Color.valueOf("E3EB0D");
        fontParameter.color = Color.valueOf("E2E9AE");
        return fontGenerator.generateFont(fontParameter);
    }

    private static BitmapFont generateSmallSmallFont() {
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 14;
        fontParameter.borderWidth = 1;
        fontParameter.borderColor = Color.valueOf("E3EB0D");
        fontParameter.color = Color.valueOf("E2E9AE");
        return fontGenerator.generateFont(fontParameter);
    }


    private static void drawText(SpriteBatch spriteBatch, BitmapFont font, GlyphLayout layout, Float x, Float y) {
        font.draw(spriteBatch, layout, x, y);
    }

    public static AreaInScreen getTextCenterAreaInScreen(String text, Font fontType) {
        GlyphLayout layout = new GlyphLayout(getFont(fontType), text);

        return new AreaInScreen(
                (SwarmAndHive.getHeight() - layout.height) / 2,
                (SwarmAndHive.getWidth() + layout.width) / 2,
                (SwarmAndHive.getHeight() + layout.height) / 2,
                (SwarmAndHive.getWidth() - layout.width) / 2
        );
    }

    public static AreaInScreen getTextCenterXAreaInScreen(String text, Font fontType, Float y) {
        GlyphLayout layout = new GlyphLayout(getFont(fontType), text);

        return new AreaInScreen(
                y - layout.height,
                (SwarmAndHive.getWidth() + layout.width) / 2,
                y,
                (SwarmAndHive.getWidth() - layout.width) / 2
        );
    }

    public static AreaInScreen getTextCenterYAreaInScreen(String text, Font fontType, Float x) {
        GlyphLayout layout = new GlyphLayout(getFont(fontType), text);

        return new AreaInScreen(
                (SwarmAndHive.getHeight() - layout.height) / 2,
                x + layout.width,
                (SwarmAndHive.getHeight() + layout.height) / 2,
                x
        );
    }

    public static AreaInScreen getTextXYAreaInScreen(String text, Font fontType, Float x, Float y) {
        GlyphLayout layout = new GlyphLayout(getFont(fontType), text);

        return new AreaInScreen(
                y - layout.height,
                x + layout.width,
                y,
                x
        );
    }

    public static AreaInScreen getTextXYCenteredAreaInScreen(String text, Font fontType, Float x, Float y) {
        GlyphLayout layout = new GlyphLayout(getFont(fontType), text);

        return new AreaInScreen(
                y - layout.height / 2,
                x + layout.width / 2,
                y + layout.height / 2,
                x - layout.width / 2
        );
    }

    private static BitmapFont getFont(Font fontType) {
        switch (fontType.getDescription()) {
            case "title":
                return titleFont;
            case "small":
                return smallFont;
            case "smallsmall":
                return smallsmallFont;
            default:
                return normalFont;
        }
    }
}
