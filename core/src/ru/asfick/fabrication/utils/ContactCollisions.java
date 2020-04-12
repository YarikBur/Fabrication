package ru.asfick.fabrication.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import ru.asfick.fabrication.obj.Object;

public class ContactCollisions implements ContactListener {

    /**
     * Срабатывает после обнаружения столкновения, но перед его обработкой.
     * Это позволяет нам как-то изменить контакт до его обработки.
     * @param contact
     * @param oldManifold
     */
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        WorldManifold worldManifold = contact.getWorldManifold();

        if (contact.getFixtureA().getUserData() != null &&
                contact.getFixtureB().getUserData() != null) {
            Object fa = (Object) contact.getFixtureA().getUserData();
            Object fb = (Object) contact.getFixtureB().getUserData();

            for (int i = 0; i < worldManifold.getNumberOfContactPoints(); i++) {
                //Добавить возможность проходить фикстурам предметов сквозь фикстуру ассемблерной линии
                //Добавить силу перемещения предмену ассемблерной линии
                if (fa.isItem() && fb.getName().equals("block_assembly-line")) {
                    contact.setEnabled(false);
                    setAssemblyForceToItem(fb, fa);
                }

                if (fb.isItem() && fa.getName().equals("block_assembly-line")) {
                    contact.setEnabled(false);
                    setAssemblyForceToItem(fa, fb);
                }
            }
        }
    }

    /**
     * Добавить предмету силу перемещения объектов ассемблерной линии, на которой он находится
     * @param assemblyLine
     * @param item
     */
    private void setAssemblyForceToItem(Object assemblyLine, Object item){
        item.setForce(assemblyLine.getForce());
    }

    /**
     * Обнуляет силу у предмета
     * @param item
     */
    private void setNotForceToItem(Object item){
        item.setForce(new Vector2(0, 0));
    }

    /**
     * Срабатывает, когда два объекта начинают накладываться. Прокает только в рамках шага.
     * @param contact
     */
    @Override
    public void beginContact(Contact contact) {

    }

    /**
     * Срабатывает, когда два объекта прекращают соприкасаться.
     * Может быть вызван, когда тело разрушено, таким образом, это событие может иметь место вне временного шага.
     * @param contact
     */
    @Override
    public void endContact(Contact contact) {
        WorldManifold worldManifold = contact.getWorldManifold();

        if (contact.getFixtureA().getUserData() != null &&
                contact.getFixtureB().getUserData() != null) {
            Object fa = (Object) contact.getFixtureA().getUserData();
            Object fb = (Object) contact.getFixtureB().getUserData();

            if (fa.isItem())
                setNotForceToItem(fa);
            else
                setNotForceToItem(fb);
        }
    }

    /**
     * Метод позволяет осуществить логику игры, которая изменяет физику после контакта.
     * @param contact
     * @param impulse
     */
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
