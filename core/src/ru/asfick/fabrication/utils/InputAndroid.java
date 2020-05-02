package ru.asfick.fabrication.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

import ru.asfick.fabrication.Main;

public class InputAndroid implements GestureListener {

    OrthographicCamera camera;

    public InputAndroid(OrthographicCamera camera){
        this.camera = camera;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.translate((-deltaX / Main.WIDTH_BOX_2D) * camera.zoom, (deltaY / Main.HEIGHT_BOX_2D * 2) * camera.zoom);

        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    float currentZoom = 1f;
    @Override
    public boolean zoom(float initialDistance, float distance) {
        currentZoom = initialDistance / distance / camera.zoom;

        if(currentZoom > 2f)
            currentZoom = 2f;
        else if (currentZoom < 0.25f)
            currentZoom = 0.25f;

        camera.zoom = currentZoom;
        camera.update();
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
