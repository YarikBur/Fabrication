package ru.asfick.fabrication.obj;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Seller extends Object {
    public Seller(World world, float x, float y){
        this.world = world;
        this.name = "block_seller";
        this.money = 500;
        this.energy = 250;
        this.setPosition(x, y);

        this.body = this.createBody(BodyDef.BodyType.StaticBody, true);
        this.addBoxShapeToBody(1, true);
        this.body.getFixtureList().get(0).setUserData(this);
    }
}
