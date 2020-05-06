package ru.asfick.fabrication.game;

public class Statistics {
    private int count = 150000;

    /**
     * Возращает значение статистики
     * @return - int
     */
    public int getCount(){
        return count;
    }

    /**
     * Добавляет значение к текущей статистике
     * @param count - int
     */
    public void addCount(int count){
        this.count += count;
    }

    /**
     * Убавляет значение из текущей ститстики
     * @param count - int
     */
    public void delCount(int count){
        this.count -= count;
    }

    /**
     * Проверяет, хватает ли энергии/денег на текущую операцию
     * @param count - int
     * @return - boolean
     */
    public boolean isSeize(int count){
        return this.getCount() - count >= 0;
    }
}
