package ru.asfick.fabrication.obj;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import ru.asfick.fabrication.Main;

public class AssemblyLine extends Object {
    private Vector2 mainForce;
    private TextureRegion currentTextureRegion;

    public AssemblyLine(World world, float x, float y, Vector2 force){
        this.world = world;
        this.setPosition(x, y);
        mainForce = force;
        this.force = mainForce;
        this.name = "block_assembly-line";
        this.setTextureRegionMap("obj/assembly_line/assembly_line_straight.png", 8, 1);
        this.money = 2000;
        this.energy = 120;

        TextureRegion[] anim = new TextureRegion[8];
        for (int i = 0; i < anim.length; i++)
            anim[i] = this.textureRegionMap.get("tiles_" + i + "_0");
        this.animations.add(new Animation<TextureRegion>(.035f, anim));

        this.body = this.createBody(BodyDef.BodyType.StaticBody, true);
        this.addBoxShapeToBody(0, true);
        this.body.getFixtureList().get(0).setUserData(this);
    }

    private void notEnergy(){
        this.force = new Vector2(0, 0);
    }

    private void haveEnergy(float stateTime){
        this.force = mainForce;
        currentTextureRegion = this.animations.get(0).getKeyFrame(stateTime, true);
        if (Main.ENERGY.getCount() < 1000)
            System.out.println("menus");
    }


    public void render(SpriteBatch batch, float stateTime){
        if(this.work)
            haveEnergy(stateTime);
        else
            notEnergy();

        if (Math.abs(mainForce.y) > Math.abs(mainForce.x)){
            if (mainForce.y >= .1f){
                this.renderTexture(batch, currentTextureRegion, 180,1);
            } else if (mainForce.y <= .1f){
                this.renderTexture(batch, currentTextureRegion, 0, 1);
            }
        } else {
            if (mainForce.x >= .1f){
                this.renderTexture(batch, currentTextureRegion, 90, 1);
            } else if (mainForce.x <= .1f){
                this.renderTexture(batch, currentTextureRegion, 270, 1);
            }
        }
    }

    @Override
    public void destroyObj(){
        super.destroyObj();
    }
}
