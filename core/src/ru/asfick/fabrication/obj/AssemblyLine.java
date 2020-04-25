package ru.asfick.fabrication.obj;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
        this.setTextureRegionMap("obj/assembly_line/assembly_line_straight.png", 8, 1);
        TextureRegion[] anim = new TextureRegion[8];
        for (int i = 0; i < anim.length; i++)
            anim[i] = this.textureRegionMap.get("tiles_" + i + "_0");
        this.animations.add(new Animation<TextureRegion>(.035f, anim));

        this.body = this.createBody(BodyDef.BodyType.StaticBody, true);
        this.addBoxShapeToBody(0, true);
        this.body.getFixtureList().get(0).setUserData(this);
    }

    public void render(SpriteBatch batch, float stateTime){
        TextureRegion currentTextureRegion = this.animations.get(0).getKeyFrame(stateTime, true);

        if (Math.abs(force.y) > Math.abs(force.x)){
            if (force.y >= .1f){
                this.renderTexture(batch, currentTextureRegion, 180,1);
            } else if (force.y <= .1f){
                this.renderTexture(batch, currentTextureRegion, 0, 1);
            }
        } else {
            if (force.x >= .1f){
                this.renderTexture(batch, currentTextureRegion, 90, 1);
            } else if (force.x <= .1f){
                this.renderTexture(batch, currentTextureRegion, 270, 1);
            }
        }
    }

    public void destroyObj(){
        super.destroyObj();
    }
}
