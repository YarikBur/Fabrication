package ru.asfick.fabrication.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Map;

import ru.asfick.fabrication.Main;
import ru.asfick.fabrication.utils.Tiles;

class UI {
    private static final float WIDTH = Gdx.graphics.getWidth(),
            HEIGHT = Gdx.graphics.getHeight();
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private static Map<String, TextureRegion> textureRegionMap;
    private static TextureRegion icon_add,
            icon_remove,
            icon_arrow,
            icon_lighting,
            icon_money,
            icon_settings,
            icon_edit,
            icon_warehouse,
            icon_store;

    public static final int MODE_NORMAL = 0;
    public static final int MODE_EDIT = 1;
    public static final int MODE_WAREHOUSE = 2;
    public static final int MODE_STORE = 3;
    public static final int MODE_SELECTED = 4;
    public static int mode;

    private static float wightTextureRegion;
    private static BitmapFont font;

    /**
     * Инициализация шрифта для будущей отрисовки
     */
    private static void initFont(){
        FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Muli-BoldItalic.ttf"));

        FreeTypeFontParameter freeTypeFontParameter = new FreeTypeFontParameter();
        freeTypeFontParameter.size = (int) (WIDTH / Main.WIDTH_BOX_2D);
        freeTypeFontParameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS;

        font = freeTypeFontGenerator.generateFont(freeTypeFontParameter);

        freeTypeFontGenerator.dispose();
    }

    /**
     * Инициализация всех иконок для будущей отрисовки
     * Высчитывание требуемой ширины иконки в зависимости от ширины экрана
     */
    private static void initIcons(){
        Tiles tiles = new Tiles();
        tiles.createAtlas("UI/icon_tiles.png", 3, 3);
        textureRegionMap = tiles.getTextureRegion();

        icon_add = textureRegionMap.get("tiles_0_0");
        icon_remove = textureRegionMap.get("tiles_0_1");
        icon_arrow = textureRegionMap.get("tiles_0_2");
        icon_lighting = textureRegionMap.get("tiles_1_0");
        icon_money = textureRegionMap.get("tiles_1_1");
        icon_settings = textureRegionMap.get("tiles_1_2");
        icon_edit = textureRegionMap.get("tiles_2_0");
        icon_warehouse = textureRegionMap.get("tiles_2_1");
        icon_store = textureRegionMap.get("tiles_2_2");

        float scale = textureRegionMap.get("tiles_0_0").getRegionWidth() / (WIDTH / 8f);
        wightTextureRegion = (int) (textureRegionMap.get("tiles_0_0").getRegionWidth() / scale);
    }

    UI(){
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        UI.initFont();
        UI.initIcons();

        mode = UI.MODE_NORMAL;
    }


    /**
     * Отрисовывает масштабированные иконки на слое
     * @param batch - слой
     * @param textureRegion - регион текстуры отвечающий за текстуру
     * @param x - х координата иконки
     * @param y - у координата иконки
     */
    private void renderIcon(SpriteBatch batch, TextureRegion textureRegion, float x, float y, float rotation, float scale){
        batch.begin();
        batch.draw(textureRegion, x, y, 0, 0, textureRegion.getRegionWidth(), textureRegion.getRegionHeight(),
                wightTextureRegion / textureRegion.getRegionWidth() * scale, wightTextureRegion / textureRegion.getRegionWidth() * scale, rotation);
        batch.end();
    }

    /**
     * Отрисовывает статистики энергии и денег
     * @param batch - слой
     */
    private void renderStats(SpriteBatch batch){
        /* Отрисовать задний фон для статистики */
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.end();

        /* Отрисовать иконки */


        /* Отрисовать текст */
        int x = 25, y = (int) (HEIGHT - wightTextureRegion - wightTextureRegion / 2);
        batch.begin();
        String energy = "Energy: " + Main.ENERGY.getCount();
        String money = "Money: " + Main.MONEY.getCount();


        font.draw(batch, energy, x, y);
        font.draw(batch, "EPS: " + Objects.getAddEnergy(), font.getXHeight() * 1.2f * energy.length() + x, y);//energy per second
        font.draw(batch, "SEPS: " + Objects.getDelEnergy(), font.getXHeight() * energy.length() * 2 + x, y);//spent energy per second

        font.draw(batch, money, x, y - font.getXHeight() * 2);
        font.draw(batch, "RPS: " + Objects.getAddMoney(), font.getXHeight() * 1.2f * energy.length() + x, y - font.getXHeight() * 2); //revenue per second
        font.draw(batch, "SMPS: " + Objects.getDelMoney(), font.getXHeight() * energy.length() * 2 + x, y - font.getXHeight() * 2);//spent money per seconds
        batch.end();
    }

    /**
     * Отрисовать верхние кнопки в UI
     * @param batch - слой
     */
    private void renderUpperButton(SpriteBatch batch){
        /* Отрисовать задний фон для кнопки */


        /* Отрисовать иконки */
        //Настройки
        renderIcon(batch, icon_settings, WIDTH - wightTextureRegion - wightTextureRegion / 2f, HEIGHT - wightTextureRegion - wightTextureRegion / 2f, 0, 1);
    }

    /**
     * Отрисовать нижние кнопки в UI
     * @param batch - слой
     */
    private void renderBottomButtons(SpriteBatch batch){
        /* Отрисовать задний фон для кнопок */
        shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(WIDTH / 4f - wightTextureRegion, wightTextureRegion / 4f, WIDTH - (WIDTH / 4f), wightTextureRegion * 1.5f);

        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.line(WIDTH / 3f + wightTextureRegion / 4f, wightTextureRegion / 2f, WIDTH / 3f + wightTextureRegion / 4f, wightTextureRegion * 1.5f);
        shapeRenderer.line(WIDTH / 1.5f - wightTextureRegion / 4f, wightTextureRegion / 2f, WIDTH / 1.5f - wightTextureRegion / 4f, wightTextureRegion * 1.5f);
        shapeRenderer.end();

        /* Отрисовать иконки */
        //Редактор
        renderIcon(batch, icon_edit, WIDTH / 4f - wightTextureRegion / 2f,  wightTextureRegion / 2f, 0, 1);
        //Склад
        renderIcon(batch, icon_warehouse, WIDTH / 4f * 2f - wightTextureRegion / 2f,  wightTextureRegion / 2f, 0, 1);
        //Магазин
        renderIcon(batch, icon_store, WIDTH / 4f * 3f - wightTextureRegion / 2f,  wightTextureRegion / 2f, 0, 1);
    }

    /**
     * Отрисовать магазин
     * @param batch - слой
     */
    private void renderStore(SpriteBatch batch){
        /* Отрисовать фон для магазина */


        /* Отрисовать магазин */
    }

    /**
     * Отрисовать склад
     * @param batch - слой
     */
    private void renderWarehouse(SpriteBatch batch){
        /* Отрисовать фон для склада */


        /* Отрисовать склад */
    }

    /**
     * Отрисовать редактор карты
     * @param batch - слой
     */
    private void renderEditor(SpriteBatch batch){
        /* Отрисовать фон для редактора */


        /* Отрисовать иконки редактора */
    }

    /**
     * Отрисовать описание выбранного элемента
     * @param batch - слой
     */
    private void renderDescription(SpriteBatch batch){
        /* Отрисовать фон для описания */


        /* Отрисовать текст описания выбранного элемента */
    }

    /**
     * Отрисовать UI игры fabrication
     * @param batch - слой
     */
    void render(SpriteBatch batch){
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        switch (mode){
            case UI.MODE_EDIT:
                renderStats(batch);
                renderUpperButton(batch);
                renderEditor(batch);
                break;
            case UI.MODE_WAREHOUSE:
                renderStats(batch);
                renderUpperButton(batch);
                renderWarehouse(batch);
                break;
            case UI.MODE_STORE:
                renderStats(batch);
                renderUpperButton(batch);
                renderStore(batch);
                break;
            case UI.MODE_SELECTED:
                renderStats(batch);
                renderUpperButton(batch);
                renderDescription(batch);
                break;
            default:
                renderStats(batch);
                renderUpperButton(batch);
                renderBottomButtons(batch);
                break;
        }
    }
}
