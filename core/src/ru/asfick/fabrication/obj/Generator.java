package ru.asfick.fabrication.obj;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Generator extends Object {
    public Generator(World world, float x, float y){
        this.world = world;
        this.name = "block_generator";
        this.setPosition(x, y);
        this.money = 10000;
        this.energy = 1500;
        this.generator = true;
        this.setForce(new Vector2(0, 0));

        this.body = this.createBody(BodyDef.BodyType.StaticBody, false);
        this.addBoxShapeToBody(0, true);
        this.body.getFixtureList().get(0).setUserData(this);
    }

    @Override
    public void destroyObj(){
        super.destroyObj();
    }
}
