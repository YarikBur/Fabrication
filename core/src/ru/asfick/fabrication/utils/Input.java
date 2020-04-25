package ru.asfick.fabrication.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

import ru.asfick.fabrication.Main;
import ru.asfick.fabrication.game.UI;

public class Input implements InputProcessor {
    OrthographicCamera camera;

    public Input(OrthographicCamera camera){
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (screenX >= 0 && screenX <= UI.getSize() &&
                screenY <= Gdx.graphics.getHeight() && screenY >= Gdx.graphics.getHeight() - UI.getSize())
            camera.zoom -= .25f;
        if (screenX >= Gdx.graphics.getWidth() - UI.getSize() && screenX <= Gdx.graphics.getWidth() &&
                screenY <= Gdx.graphics.getHeight() && screenY >= Gdx.graphics.getHeight() - UI.getSize())
            camera.zoom += .25f;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        float x = Gdx.input.getDeltaX() / Main.WIDTH_BOX_2D;
        float y = Gdx.input.getDeltaY() / (Main.WIDTH_BOX_2D * Gdx.graphics.getHeight() / Gdx.graphics.getWidth()) * 2;

        camera.translate(-x * camera.zoom, y * camera.zoom);

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
