package com.example.particlesim;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Config
        backgroundColor = Color.TRANSPARENT;
        simSpeed = 50;

        //
        gameBitmap = Bitmap.createBitmap(350,350, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(gameBitmap);
        paint = new Paint();

        imageViewGame = (ImageView) findViewById(R.id.imageViewGame);
        imageViewGame.setImageBitmap(gameBitmap);

        //Testing stuff
        particleWorld = new ParticleWorld(35,35);
        Particle sand = new SandParticle(particleWorld);
        particleWorld.setParticle(2,6, new StoneParticle(particleWorld));
        particleWorld.setParticle(2,2, sand);
        particleWorld.setParticle(0,0, sand);
        particleWorld.setParticle(0,1, sand);
        particleWorld.setParticle(9,0, sand);
        particleWorld.setParticle(14,0, sand);
        particleWorld.setParticle(34,0, sand);
        particleWorld.setParticle(34,34, sand);
        particleWorld.setParticle(30,11, new SandParticle(particleWorld));

        //Repeats code
        tickHandler = new Handler();
        repeatedCode.run();
    }

    Runnable repeatedCode = new Runnable() {
        @Override
        public void run() {
            particleWorld.doPhysics();
            refresh();

            System.out.println("refreshed");

            tickHandler.postDelayed(repeatedCode, simSpeed);
        }
    };

    public void drawParticle(int x, int y, int color) {
        paint.setColor(color);
        canvas.drawRect(x*10,y*10,x*10+10,y*10+10, paint);
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