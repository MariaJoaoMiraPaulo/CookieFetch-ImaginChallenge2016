package com.imaginchallenge.cookiefetch.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.imaginchallenge.cookiefetch.CookieFetch;
import com.imaginchallenge.cookiefetch.Logic.Cookie;
import com.imaginchallenge.cookiefetch.Logic.Plate;

import java.util.ArrayList;

/**
 * Created by Nuno on 04/11/2016.
 */

public class PlayScreen implements Screen, InputProcessor {

    private CookieFetch game;
    private ArrayList<Plate> plates;

    //Variables relationed to the touch events
    private Vector2 finishingPoint;
    private Vector2 startingPoint;
    private float distance;
    private Texture background;

    private Cookie cookie;

    public PlayScreen(CookieFetch game){

        this.game = game;

        finishingPoint = new Vector2(0,0);
        startingPoint = new Vector2(0,0);

        background=new Texture("game.png");

        //Creation of Plates and cookie

        cookie=new Cookie(background.getWidth(),background.getHeight(), Cookie.Type.BROWN);



        //Telling Libgdx what it input process so it can be called when a new input event arrives
        Gdx.input.setInputProcessor(this);
    }

    public void fillPlatesArray(){
        for(int i = 0;i < 4; i++){
            //Plate p = new Plate();
            //p.setRandomTexture();
            //plates.add(i,p);
        }
    }

    @Override
    public void show() {

    }

    public void update(float delta){

        cookie.update(delta);
    }

    @Override
    public void render(float delta) {

        update(delta);

        game.batch.begin();
        game.batch.draw(background,0,0);
        cookie.render(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Gdx.app.log("Toque", " "+ screenX + " " + screenY);

        startingPoint.set(screenX, screenY);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        finishingPoint.set(screenX, screenY);

        Gdx.app.log("Toque", "FINAL "+ screenX + " " + screenY);

        Gdx.app.log("Toque", "INICIAL "+ startingPoint.x + " " + startingPoint.y);

        Vector2 tmpVetor1=new Vector2(finishingPoint.x-startingPoint.x,finishingPoint.y-startingPoint.y);

        Vector2 tmpVetor2=new Vector2(0,0-startingPoint.y);

        cookie.setCookiePressed(true);

        float ang = ((float)Math.PI*tmpVetor1.angle(tmpVetor2))/180;


        cookie.setCookieSpeed(-1*(float)(100*Math.sin(ang)),(float)(100*Math.cos(ang)));

        //distance = startingPoint.dst(finishingPoint.x, finishingPoint.y);

        Gdx.app.log("Toque", "Angulo "+ang);
        Gdx.app.log("Toque", "Valores "+(float)(100*Math.sin(ang)) + " " + (float)(100*Math.cos(ang)));

      /*  double screenDistance = Math.sqrt(Math.pow(Gdx.graphics.getWidth(),2) + Math.pow(Gdx.graphics.getHeight(),2));

        Gdx.app.log("CENAS", "Screen Distancia "+screenDistance);

        if(distance > (screenDistance*0.2)){
            Gdx.app.log("CENAS", "Aqui vai");
            //imgPosition.set(finishingPoint.x, Gdx.graphics.getHeight()-img.getHeight());
        }*/

        startingPoint.set(0,0);
        finishingPoint.set(0,0);

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        //finishingPoint.set(screenX, screenY);

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
