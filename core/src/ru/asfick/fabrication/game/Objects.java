package ru.asfick.fabrication.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.Serializable;
import java.util.ArrayList;

import ru.asfick.fabrication.Main;
import ru.asfick.fabrication.obj.Object;

public class Objects implements Serializable {
    private static final long serialVersionUID = 2996460929349290082L;
    private static ArrayList<Object> objectsOnMap = new ArrayList<Object>();
    private static ArrayList<Object> objectsInWarehouse = new ArrayList<Object>();

    private static int delEnergy = 0;
    private static int addEnergy = 0;
    private static int delMoney = 0;
    private static int addMoney = 0;

    /**
     * Добавить объект на карту
     * @param obj - объект
     */
    public void addObjectOnMap(Object obj){
        Objects.objectsOnMap.add(obj);
        delMoney += obj.getMoney();
    }

    /**
     * Добавить объект на склад
     * @param obj - объект
     */
    void addObjectInWarehouse(Object obj){
        this.objectsInWarehouse.add(obj);
    }

    /**
     * Продать объект с карты или из склада
     * @param obj - продать
     * @return - удалось ли продать объект
     */
    public boolean sellObject(Object obj){
        boolean seller = false;

        for (Object object : objectsOnMap){
            if (object == obj){
                addMoney += object.getMoney();
                seller = true;
                objectsOnMap.remove(object);
                break;
            }
        }

        if (!seller) {
            for (Object object : objectsInWarehouse) {
                if (object == obj) {
                    addMoney += object.getMoney();
                    seller = true;
                    objectsInWarehouse.remove(obj);
                    break;
                }
            }
        }

        return seller;
    }

    /**
     * Перенести объект со склада на карту
     * @param obj - объект
     */
    void transferWarehouseToMap(Object obj){
        for (Object object : objectsInWarehouse){
            if (obj == object) {
                objectsOnMap.add(obj);
                objectsInWarehouse.remove(obj);
                break;
            }
        }
    }

    /**
     * Перенести объект с карты на склад
     * @param obj - объект
     */
    void transferMapToWarehouse(Object obj){
        for (Object object : objectsOnMap){
            if (obj == object){
                objectsInWarehouse.add(obj);
                objectsOnMap.remove(obj);
                break;
            }
        }
    }

    /**
     * Расчет колличества затрачиваемой энергии и денег
     */
    void preRender(){
        addEnergy = 0;
        delEnergy = 0;
        addMoney = 0;
        delMoney = 0;


        for(Object object : objectsOnMap)
            if (!object.isItem())
                if(object.updateStatistics()) {
                    if (object.isGenerator())
                        addEnergy += object.getEnergy();
                    else
                        delEnergy += object.getEnergy();
                }
    }

    /**
     * Отрисовать объекты
     * @param batch - слой
     * @param stateTime - прошедшее время (для анимации)
     */
    void render(SpriteBatch batch, float stateTime){
        for (Object object : objectsOnMap)
            if (!object.isItem())
                object.render(batch, stateTime);

        for (Object object : objectsOnMap)
            if (object.isItem())
                object.render(batch, stateTime);
    }

    /**
     * Обновление позиции объектов
     */
    void postRender(){
        for (Object object : objectsOnMap)
            object.updatePosition();
    }

    /**
     * Отчистка из памяти
     */
    void dispose(){

    }

    void destroyObjects(){
        for (Object object : objectsOnMap)
            if (object.isDestroy())
                if (sellObject(object)) {
                    object.destroyObj();
                    break;
                }
    }

    /**
     * Кол-во затраченной энергии за прошедшую секунду
     * @return - int
     */
    static int getDelEnergy(){
        return delEnergy;
    }

    /**
     * Кол-во сгенерированной энергии за прошедшую секунду
     * @return - int
     */
    static int getAddEnergy(){
        return addEnergy;
    }

    /**
     * Кол-во полученных денег за прошедшую секунду
     * @return - int
     */
    static int getAddMoney(){
        return addMoney;
    }

    /**
     * Кол-во потраченных денег за прошедшую секунду
     * @return - int
     */
    static int getDelMoney(){
        return delMoney;
    }
}
