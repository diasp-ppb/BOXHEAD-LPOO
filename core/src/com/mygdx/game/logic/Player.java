package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Vector;

public class Player extends Sprite {
    private Vector2 direction;
    private int life;
    private int damage = 1; //init

    private World world;
    public Body body2d;


    public Player(World world){
       // bag = new ArrayList<Item>();
        direction = new Vector2(0,1);
        life = 100;
        damage = 1;
        super.setPosition(0, 0);
        super.setTexture(new Texture("play.png")); //teste

        this.world = world;
        definePlayer();
    }

    public void definePlayer(){
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
        shape.setAsBox(100 / 2, 100 / 2);//tmp

        fixdef.shape = shape;
        body2d.createFixture(fixdef);
    }

    public void setLife(int life){
        life = life;
    }

    public void decLife(int damage){
        life -= damage;
    }

    public int getLife(){
        return life;
    }

    public final boolean isDead(){
        return (life <= 0);
    }

    public final Vector2 getDirection(){
        return direction;
    }

    public void setDirection(Vector2 d){
        direction = d;
    }

    @Override
    public void setPosition(float x, float y){
        super.setPosition(x,y);
        body2d.setTransform(x,y,0);
    }

    public void dispose(){
        super.getTexture().dispose();
        world.destroyBody(body2d);
    }


}
