package com.example.particlesim;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import ParticleEngine.EmptyParticle;
import ParticleEngine.Particle;
import ParticleEngine.ParticleWorld;
import ParticleEngine.SandParticle;
import ParticleEngine.StoneParticle;
import ParticleEngine.Type;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewGame;
    private Bitmap gameBitmap;
    private ParticleWorld particleWorld;
    private Handler tickHandler;
    private Canvas canvas;
    private Paint paint;

    private int backgroundColor;
    private int simSpeed;
    private Type selectedType;

    private boolean pressing;
    private boolean halfTimePress;
    private int latestX;
    private int latestY;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Config
        backgroundColor = Color.GREEN;
        simSpeed = 50;
        selectedType = Type.SAND;
        pressing = false;
        halfTimePress = true;

        //
        gameBitmap = Bitmap.createBitmap(350,350, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(gameBitmap);
        paint = new Paint();

        imageViewGame = (ImageView) findViewById(R.id.imageViewGame);
        imageViewGame.setImageBitmap(gameBitmap);

        //Testing stuff
        particleWorld = new ParticleWorld(35,35);
        Particle sand = new SandParticle(particleWorld);
        //particleWorld.setParticle(2,6, new StoneParticle(particleWorld));
        particleWorld.setParticle(2,2, sand);
        particleWorld.setParticle(0,0, sand);
        particleWorld.setParticle(4,0, sand);
        particleWorld.setParticle(6,0, sand);
        particleWorld.setParticle(7,0, sand);
        particleWorld.setParticle(0,1, sand);
        particleWorld.setParticle(9,0, sand);
        particleWorld.setParticle(14,0, sand);
        particleWorld.setParticle(34,0, sand);
        particleWorld.setParticle(34,34, sand);
        particleWorld.setParticle(30,11, new SandParticle(particleWorld));

        //Repeats code
        tickHandler = new Handler();
        repeatedCode.run();

        //Imageview Listener
        imageViewGame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    int x = (int) event.getX();
                    int parsedX = (int) Math.floor(x/(imageViewGame.getWidth()/35));
                    int y = (int) event.getY();
                    int parsedY = (int) Math.floor(y/(imageViewGame.getHeight()/35));

                    latestX = parsedX;
                    latestY = parsedY;
                    newParticle(parsedX, parsedY, selectedType);
                    halfTimePress = true;
                    pressing = true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    pressing = false;
                }
                else if (event.getAction() == MotionEvent.ACTION_MOVE){
                    if (pressing) {
                        int x = (int) event.getX();
                        int parsedX = (int) Math.floor(x/(imageViewGame.getWidth()/35));
                        int y = (int) event.getY();
                        int parsedY = (int) Math.floor(y/(imageViewGame.getHeight()/35));

                        latestX = parsedX;
                        latestY = parsedY;
                    }
                    // code
                }
                return true;
            }
        });
    }

    Runnable repeatedCode = new Runnable() {
        @Override
        public void run() {
            particleWorld.doPhysics();
            refresh();

            //Pressing stuff
            if (pressing) {
                if (halfTimePress) {
                    halfTimePress = false;
                }
                else {
                    halfTimePress = true;
                    newParticle(latestX,latestY,selectedType);
                }
            }



            //System.out.println("refreshed");

            tickHandler.postDelayed(repeatedCode, simSpeed);
        }
    };

    public void newParticle(int x, int y, Type type) {
        Particle newParticle = new EmptyParticle(particleWorld);
        switch (type) {
            case EMPTY:
                new EmptyParticle(particleWorld);
                break;
            case STONE:
                newParticle = new StoneParticle(particleWorld);
                break;
            case SAND:
                newParticle = new SandParticle(particleWorld);
                break;
            default: newParticle = new EmptyParticle(particleWorld);
            //case GAS:   newParticle = new GasParticle(particleWorld
                //TODO add other particleTypes
        }

        particleWorld.setParticle(x,y,newParticle);
        drawParticle(x,y,newParticle.getColor());
    }

    public void drawParticle(int x, int y, int color) {
        paint.setColor(color);
        canvas.drawRect(x*10,y*10,x*10+10,y*10+10, paint);
        imageViewGame.invalidate();
    }

    public void clearGame() {
        gameBitmap.eraseColor(backgroundColor);
    }

    public void refresh() {
        clearGame();
        Particle[][] worldArray = particleWorld.getWorld();
        for (int colIndex = 0 ; colIndex < worldArray.length ; colIndex++) {
            for (int rowIndex = 0 ; rowIndex < worldArray[colIndex].length ; rowIndex++) {
                if (worldArray[colIndex][rowIndex].getType() != Type.EMPTY) {
                    drawParticle(colIndex, rowIndex, particleWorld.getParticle(colIndex, rowIndex).getColor());
                }
            }
        }
        imageViewGame.invalidate();
    }
}