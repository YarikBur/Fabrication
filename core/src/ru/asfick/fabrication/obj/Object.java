package ru.asfick.fabrication.obj;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Map;

import ru.asfick.fabrication.utils.Tiles;

public class Object {
    World world;
    Vector2 position;
    Vector2 size;
    Vector2 force;
    Body body;
    Map<String, TextureRegion> textureRegionMap;
    ArrayList<Animation<TextureRegion>> animations = new ArrayList<Animation<TextureRegion>>();
    ArrayList<Fixture> fixtures = new ArrayList<Fixture>();
    Tiles tiles;
    boolean item;
    String name;

    /**
     * Обновляет позицию объекта
     */
    public void updatePosition(){
        this.body.setLinearVelocity(force);
        this.position = this.body.getPosition();
    }

    /**
     * Применяет силу объекта
     * @param force - вектор силы
     */
    public void setForce(Vector2 force) {
        this.force = force;
    }

    /**
     * Возращает силу объекта
     * @return Vector2
     */
    public Vector2 getForce(){
        return force;
    }

    /**
     * Является этот объект предметом
     * @return boolean
     */
    public boolean isItem(){
        return item;
    }

    /**
     * Возращает имя объекта
     * @return String
     */
    public String getName(){
        return name;
    }

    /**
     * Устанавливает объект со смещением в право и вверх
     * @param x - координата по x
     * @param y - координата по y
     */
    void setPosition(float x, float y){
        position = new Vector2(x + size.x, y + size.y);
    }

    /**
     * Создает тайл-текстуру для объекта
     * @param tiles - название текстуры
     * @param lines - кол-во линий в тайле
     * @param columns - кол-во столбцов в тайле
     */
    void setTextureRegionMap(String tiles, int lines, int columns){
        this.tiles = new Tiles();
        this.tiles.createAtlas(tiles, lines, columns);
        textureRegionMap = this.tiles.getTextureRegion();
    }

    /**
     * Рендер текстуры объекта
     * @param batch - слой отрисовки
     * @param textureRegion - регион текстуры
     */
    void renderTexture(SpriteBatch batch, TextureRegion textureRegion, float rotation, float scale){
        batch.draw(textureRegion, position.x - textureRegion.getRegionWidth() / 2f, position.y - textureRegion.getRegionHeight() / 2f,
                textureRegion.getRegionWidth() / 2f, textureRegion.getRegionHeight() / 2f,
                textureRegion.getRegionWidth() - 2f, textureRegion.getRegionHeight() - 2f,
                scale / 15f, scale / 15f, rotation);
    }

    /**
     * Добавляет тело объекту
     * @param type - тип тела
     * @param fixedRotation - возможность разворота фикстур
     * @return Body
     */
    Body createBody(BodyDef.BodyType type, boolean fixedRotation){
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = fixedRotation;
        bodyDef.type = type;
        bodyDef.position.set(position);

        return world.createBody(bodyDef);
    }

    /**
     * Добавляет квадратную фикстуру к объекту
     * @param density - плотность
     * @param sensor - сенсорная ли фикстура
     */
    void addBoxShapeToBody(float density, boolean sensor){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x, size.y);
        Fixture fixture = body.createFixture(shape, density);
        fixture.setSensor(!sensor);
        shape.dispose();

        fixtures.add(fixture);
    }

    /**
     * Добавляет круглую фикстуру к объекту
     * @param r - радиус фикстуры
     * @param density - плотность
     * @param sensor - сенсорная ли фикстура
     */
    void addCircleShapeToBody(float r, float density, boolean sensor){
        CircleShape shape = new CircleShape();
        shape.setRadius(r);
        Fixture fixture = body.createFixture(shape, density);
        fixture.setSensor(!sensor);
        shape.dispose();

        fixtures.add(fixture);
    }

    /**
     * Удаляет все фикстуры и текстуры у объекта
     */
    void destroyObj(){
        for (Fixture fixture : fixtures)
            body.destroyFixture(fixture);
    }
}
