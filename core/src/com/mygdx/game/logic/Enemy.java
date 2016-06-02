package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Enemy extends Sprite{
    int damage;
    int life;
    private World world;
    public Body body2d;

    public Enemy(int life, int damage, World world){
        this.life = life;
        this.damage = damage;

        super.setPosition(0, 0);
        super.setTexture(new Texture("play.png")); //teste


        this.world = world;
        defineEnemy();
    }

    public void defineEnemy(){
        FixtureDef fixdef;
        BodyDef bdef;
        PolygonShape shape;

        //Body definition
        bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.DynamicBody;

        body2d = world.createBody(bdef);

        fixdef = new FixtureDef();
        shape = new PolygonShape();
        shape.setAsBox(100 / 2, 100 / 2);//tmp
        fixdef.shape = shape;
        body2d.createFixture(fixdef);

        //body2d.setUserData(this);

        //shape.dispose();
    }

    public void setLife(int life){
        this.life = life;
    }
    public void decLife(int damage){
        life -= damage;
    }
    public int getLife(){
        return life;
    }
    public void setDamage(int d){
        damage = d;
    }
    public int getDamage(){
        return damage;
    }
    public boolean isDead(){
        return (life <= 0);
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
