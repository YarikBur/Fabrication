package ru.asfick.fabrication.obj;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class AssemblyLine extends Object {
    public AssemblyLine(World world, float x, float y, Vector2 force){
        this.world = world;
        this.size = new Vector2(1, 1);
        this.setPosition(x, y);
        this.item = false;
        this.force = force;
        this.name = "block_assembly-line";

        this.body = this.createBody(BodyDef.BodyType.StaticBody, true);
        this.addBoxShapeToBody(0, true);
        this.body.getFixtureList().get(0).setUserData(this);
    }

    public void destroyObj(){
        super.destroyObj();
    }
}
