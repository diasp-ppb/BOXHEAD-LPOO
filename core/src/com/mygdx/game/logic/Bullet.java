package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Catarina Ramos on 29/05/2016.
 */
public class Bullet extends Sprite {
    private Vector2 direction;
    private int damage;
    private final int velocity = 40;
    private int range;

    private World world;
    public Body body2d;

    public Bullet(Vector2 direction , int damage,Texture text,World world){
        this.direction = direction;
        this.damage = damage;
        super.setTexture(text);
        range = 10; //temp

        this.world = world;
        defineBullet();
    }

    public void defineBullet(){
        FixtureDef fixdef;
        BodyDef bdef;
        PolygonShape shape;

        //Body definition
        bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.KinematicBody;

        body2d = world.createBody(bdef);

        fixdef = new FixtureDef();
        shape = new PolygonShape();
        shape.setAsBox(5,5);//width/2,height/2

        fixdef.shape = shape;
        body2d.createFixture(fixdef);
    }

    public final int getDamage(){
        return damage;
    }

    public final Vector2 getDirection(){
        return direction;
    }

    public void incPosition(){
        setPosition(getX() + velocity * direction.x, getY() + velocity * direction.y);
        range--;
    }

    public boolean outOfRange(){
        return (range <= 0);
    }

    @Override
    public void setPosition(float x, float y){
        super.setPosition(x,y);
        body2d.setTransform(x,y,0);
    }

    public void dispose(){
        world.destroyBody(body2d);
    }
}
