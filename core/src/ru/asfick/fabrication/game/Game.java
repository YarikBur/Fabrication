package ru.asfick.fabrication.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.io.IOException;

import ru.asfick.fabrication.Main;
import ru.asfick.fabrication.obj.Buyer;
import ru.asfick.fabrication.obj.Generator;
import ru.asfick.fabrication.obj.Iron;
import ru.asfick.fabrication.obj.AssemblyLine;
import ru.asfick.fabrication.obj.Seller;
import ru.asfick.fabrication.utils.ContactCollisions;
import ru.asfick.fabrication.utils.InputAndroid;

public class Game implements Screen {
    private UI ui;
    private final Main main;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private OrthographicCamera cam;

    private Objects objects;

    private float stateTime;
    private float timer = 0;

    public Game(final Main main) {
        this.main = main;
    }

    public void setBatch(SpriteBatch batch){
        this.batch = batch;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, 0), true);
        objects = new Objects();
        world.setContactListener(new ContactCollisions());
        box2DDebugRenderer = new Box2DDebugRenderer();
        ui = new UI();
        cam = new OrthographicCamera(Main.WIDTH_BOX_2D, Main.HEIGHT_BOX_2D);

        GestureDetector detector = new GestureDetector(new InputAndroid(cam));
        Gdx.input.setInputProcessor(detector);

        cam.position.set(Main.WIDTH_BOX_2D / 2, Main.HEIGHT_BOX_2D / 2, 0);
        cam.update();



        /*objects.addObjectOnMap(new AssemblyLine(world, 2, 48, new Vector2(-.5f, -2f)));
        objects.addObjectOnMap(new AssemblyLine(world, 2, 46, new Vector2(0, -2f)));
        objects.addObjectOnMap(new AssemblyLine(world, 2, 44, new Vector2(2, -.5f)));
        objects.addObjectOnMap(new AssemblyLine(world, 4, 44, new Vector2(2, 0f)));
        objects.addObjectOnMap(new AssemblyLine(world, 6, 44, new Vector2(.5f, 2f)));
        objects.addObjectOnMap(new AssemblyLine(world, 6, 46, new Vector2(0, 2f)));
        objects.addObjectOnMap(new AssemblyLine(world, 6, 48, new Vector2(-2f, .5f)));
        objects.addObjectOnMap(new AssemblyLine(world, 4, 48, new Vector2(-2, 0f)));
        objects.addObjectOnMap(new Iron(world, 3, 48));
        objects.addObjectOnMap(new Generator(world, 0, 0));
        objects.addObjectOnMap(new Generator(world, 0, 2));*/

        Buyer buyer = new Buyer(world, 4, 14, new Vector2(0, -1));
        objects.addObjectOnMap(buyer);
        objects.addObjectOnMap(new AssemblyLine(world, 4, 12, new Vector2(0, -2)));
        objects.addObjectOnMap(new AssemblyLine(world, 4, 10, new Vector2(0, -2)));
        objects.addObjectOnMap(new Generator(world, 2, 10));
        objects.addObjectOnMap(new Seller(world, 4, 8));

        stateTime = 0;
    }

    private void preRender(float delta){
        if (!world.isLocked())
            objects.destroyObjects();
        world.step(delta, 4, 4);
        batch.setProjectionMatrix(cam.combined);
        cam.update();
        stateTime += delta;

        timer += Gdx.graphics.getRawDeltaTime();


        if (timer > 1) {
            objects.preRender();

            /*try {
                Pref.save(objects);
                Gdx.app.debug("SAVE", "successfully");
            } catch (IOException e){
                Gdx.app.error("SAVE", e.getMessage());
            }*/
            objects.addObjectOnMap(new Iron(world, 5, 13));
            timer -= 1;
        }
    }

    @Override
    public void render(float delta) {
        preRender(delta);

        Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        objects.render(batch, stateTime);

        batch.end();

        if (main.DEBUG)
            box2DDebugRenderer.render(world, cam.combined);

        ui.render(batch);

        postRender();
    }

    private void postRender(){
        objects.postRender();
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
        objects.dispose();
        box2DDebugRenderer.dispose();
        world.dispose();
    }
}
