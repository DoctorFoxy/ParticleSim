package com.example.particlesim;
import ParticleEngine.WorldParser;
import  StartScreen.SettingsScreen;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import ParticleEngine.EmptyParticle;
import ParticleEngine.GasParticle;
import ParticleEngine.Particle;
import ParticleEngine.ParticleWorld;
import ParticleEngine.SandParticle;
import ParticleEngine.StoneParticle;
import ParticleEngine.Type;
import StartScreen.StartScreen;
import ParticleEngine.WaterParticle;

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

        //
        gameBitmap = Bitmap.createBitmap(350,350, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(gameBitmap);
        paint = new Paint();

        imageViewGame = (ImageView) findViewById(R.id.imageViewGame);
        imageViewGame.setImageBitmap(gameBitmap);

        particleWorld = new ParticleWorld(35,35);
        if (getIntent().getSerializableExtra("World") != null) {
            particleWorld.setWorld(WorldParser.stringToWorld((String) getIntent().getSerializableExtra("World"), particleWorld));
        }


        //Repeats code
        tickHandler = new Handler();
        repeatedCode.run();

        //Imageview Listener (Game listener)
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

        //Button Listeners
        findViewById(R.id.selectButtonSand).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedType = Type.SAND;
            }
        });

        findViewById(R.id.selectButtonStone).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedType = Type.STONE;
            }
        });

        findViewById(R.id.selectButtonWater).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedType = Type.WATER;
            }
        });

        findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                particleWorld.fillWorldEmpty();
                refresh();
            }
        });
    }

    public void On_Gas_Pressed(View caller){
        selectedType = Type.GAS;
    }

    Runnable repeatedCode = new Runnable() {
        @Override
        public void run() {
            ParticleWorld cloneWorld = particleWorld.cloneWorld(particleWorld);
            particleWorld.doPhysics(cloneWorld,particleWorld);
            refresh();

            //Pressing stuff
            if (pressing) {
                newParticle(latestX,latestY,selectedType);
            }

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
            case WATER:
                newParticle = new WaterParticle(particleWorld);
                break;
            case GAS:
                newParticle = new GasParticle(particleWorld);
                break;
            default: newParticle = new EmptyParticle(particleWorld);

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

    public void onMainMenuButton_Pressed(View caller){
        Intent intent = new Intent(this, StartScreen.class);
        intent.putExtra("World", WorldParser.worldToString(particleWorld.getWorld()));
        startActivity(intent);
    }

    public void onSettingsScreen_Pressed(View caller){
        Intent intent = new Intent(this, SettingsScreen.class);
        intent.putExtra("World", WorldParser.worldToString(particleWorld.getWorld()));
        startActivity(intent);
    }

}