package ru.asfick.fabrication.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Map;

import ru.asfick.fabrication.utils.Tiles;

public class UI {
    private static final float WIDTH = Gdx.graphics.getWidth(),
            HEIGHT = Gdx.graphics.getHeight();
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private static float size;
    private static Map<String, TextureRegion> textureRegionMap;
    private static float wightTextureRegion;

    UI(){
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        size = Gdx.graphics.getWidth() / 5f;

        Tiles tiles = new Tiles();
        tiles.createAtlas("UI/icon_tiles.png", 2, 3);
        textureRegionMap = tiles.getTextureRegion();
        float scale = textureRegionMap.get("tiles_0_0").getRegionWidth() / (WIDTH / 8f);
        wightTextureRegion = (int) (textureRegionMap.get("tiles_0_0").getRegionWidth() / scale);
    }

    BitmapFont font = new BitmapFont();

    private void renderIconScale(SpriteBatch batch, TextureRegion textureRegion, ShapeRenderer shapeRenderer, float x, float y){
        //RENDER BG
        /*shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(x, y, wightTextureRegion, wightTextureRegion);
        shapeRenderer.end();*/

        //RENDER ICON
        batch.begin();
        batch.draw(textureRegion, x, y, 0, 0, textureRegion.getRegionWidth(), textureRegion.getRegionHeight(),
                wightTextureRegion / textureRegion.getRegionWidth(), wightTextureRegion / textureRegion.getRegionWidth(), 0);
        batch.end();
    }

    void render(SpriteBatch batch){
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        /*shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(0, 0, size, size);
        shapeRenderer.rect(Gdx.graphics.getWidth() - size, 0, size, size);
        shapeRenderer.end();*/

        shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(WIDTH / 4f - wightTextureRegion, wightTextureRegion / 4f, WIDTH - (WIDTH / 4f), wightTextureRegion * 1.5f);

        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.line(WIDTH / 3f + wightTextureRegion / 4f, wightTextureRegion / 2f, WIDTH / 3f + wightTextureRegion / 4f, wightTextureRegion * 1.5f);
        shapeRenderer.line(WIDTH / 1.5f - wightTextureRegion / 4f, wightTextureRegion / 2f, WIDTH / 1.5f - wightTextureRegion / 4f, wightTextureRegion * 1.5f);
        shapeRenderer.end();

        //SETTINGS
        renderIconScale(batch, textureRegionMap.get("tiles_1_1"), shapeRenderer, WIDTH - wightTextureRegion - wightTextureRegion / 2f, HEIGHT - wightTextureRegion - wightTextureRegion / 2f);
        //EDIT
        renderIconScale(batch, textureRegionMap.get("tiles_0_1"), shapeRenderer, WIDTH / 4f - wightTextureRegion / 2f,  wightTextureRegion / 2f);
        //WAREHOUSE
        renderIconScale(batch, textureRegionMap.get("tiles_1_2"), shapeRenderer, WIDTH / 4f * 2f - wightTextureRegion / 2f,  wightTextureRegion / 2f);
        //STORE
        renderIconScale(batch, textureRegionMap.get("tiles_0_2"), shapeRenderer, WIDTH / 4f * 3f - wightTextureRegion / 2f,  wightTextureRegion / 2f);
    }

    public static float getSize(){
        return size;
    }
}
