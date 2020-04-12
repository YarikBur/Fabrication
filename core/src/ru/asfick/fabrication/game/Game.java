package ru.asfick.fabrication.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import ru.asfick.fabrication.Main;
import ru.asfick.fabrication.obj.Iron;
import ru.asfick.fabrication.obj.AssemblyLine;
import ru.asfick.fabrication.utils.ContactCollisions;
import ru.asfick.fabrication.utils.Input;

public class Game implements Screen {
    private final Main main;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private OrthographicCamera cam;
    private ArrayList<AssemblyLine> assemblyLines = new ArrayList<AssemblyLine>();
    private Iron iron;

    public Game(final Main main) {
        this.main = main;
    }

    public void setBatch(SpriteBatch batch){
        this.batch = batch;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new ContactCollisions());
        box2DDebugRenderer = new Box2DDebugRenderer();
        cam = new OrthographicCamera(Main.WIDTH_BOX_2D, Main.HEIGHT_BOX_2D);

        Gdx.input.setInputProcessor(new Input(cam));

        cam.position.set(Main.WIDTH_BOX_2D / 2, Main.HEIGHT_BOX_2D / 2, 0);
        cam.update();

        assemblyLines.add(new AssemblyLine(world, 2, 48, new Vector2(-.5f, -2f)));
        assemblyLines.add(new AssemblyLine(world, 2, 46, new Vector2(0, -2f)));
        assemblyLines.add(new AssemblyLine(world, 2, 44, new Vector2(2, -.5f)));
        assemblyLines.add(new AssemblyLine(world, 4, 44, new Vector2(2, 0f)));
        assemblyLines.add(new AssemblyLine(world, 6, 44, new Vector2(.5f, 2f)));
        assemblyLines.add(new AssemblyLine(world, 6, 46, new Vector2(0, 2f)));
        assemblyLines.add(new AssemblyLine(world, 6, 48, new Vector2(-2f, .5f)));
        assemblyLines.add(new AssemblyLine(world, 4, 48, new Vector2(-2, 0f)));

        iron = new Iron(world, 3f, 48);

    }

    @Override
    public void render(float delta) {
        world.step(delta, 4, 4);
        batch.setProjectionMatrix(cam.combined);
        cam.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //batch.begin();
        //batch.end();

        if (main.DEBUG)
            box2DDebugRenderer.render(world, cam.combined);

        iron.updatePosition();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for (AssemblyLine line : assemblyLines) {
            line.destroyObj();
        }
        iron.destroyObj();
        box2DDebugRenderer.dispose();
        world.dispose();
    }
}
