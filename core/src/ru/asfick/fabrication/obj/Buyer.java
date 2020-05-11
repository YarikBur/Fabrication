package ru.asfick.fabrication.obj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import ru.asfick.fabrication.game.Objects;

public class Buyer extends Object {
    private float timer;

    public Buyer(World world, float x, float y, Vector2 side){
        this.world = world;
        this.name = "block_buyer";
        this.money = 500;
        this.energy = 250;
        this.setForce(side);
        this.setPosition(x, y);

        this.body = this.createBody(BodyDef.BodyType.StaticBody, true);
        this.addBoxShapeToBody(1, false);
        this.body.getFixtureList().get(0).setUserData(this);
    }

    public void setBuy(Object object){
        //this.buy = ;
    }

    @Override
    public void render(SpriteBatch batch, float stateTime){
        timer += Gdx.graphics.getRawDeltaTime();

        if (timer > 1){
            //if (buy != null && buy.isItem())
                //Objects.addObjectOnMap(new Iron(world, this.position.x, this.position.y));

            timer -= 1;
        }
    }
}
