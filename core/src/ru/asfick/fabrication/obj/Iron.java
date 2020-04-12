package ru.asfick.fabrication.obj;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Iron extends Object {
    public Iron(World world, float x, float y){
        this.world = world;
        this.item = true;
        this.size = new Vector2(.35f, .35f);
        this.force = new Vector2(0, 0);
        this.setPosition(x, y);
        this.name = "item_iron";

        this.body = this.createBody(BodyDef.BodyType.DynamicBody, false);
        this.addCircleShapeToBody(this.size.x, 0, true);
        this.body.getFixtureList().get(0).setUserData(this);
    }

    public void destroyObj(){
        super.destroyObj();
    }
}
