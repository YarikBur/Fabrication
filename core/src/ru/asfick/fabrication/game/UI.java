package ru.asfick.fabrication.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class UI {
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private static float size;

    UI(){
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        size = Gdx.graphics.getWidth() / 5f;
    }

    BitmapFont font = new BitmapFont();

    void render(SpriteBatch batch){
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.draw(batch, "Hello, World!", 10, 10);
        batch.end();


        shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(0, 0, size, size);
        shapeRenderer.rect(Gdx.graphics.getWidth() - size, 0, size, size);
        shapeRenderer.end();
    }

    public static float getSize(){
        return size;
    }
}
