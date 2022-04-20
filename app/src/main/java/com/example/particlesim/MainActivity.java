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
import ParticleEngine.Type;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewGame;
    private Bitmap gameBitmap;
    private ParticleWorld particleWorld;
    private Handler tickHandler;
    private Canvas canvas;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameBitmap = Bitmap.createBitmap(350,350, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(gameBitmap);
        paint = new Paint();

        imageViewGame = (ImageView) findViewById(R.id.imageViewGame);
        imageViewGame.setImageBitmap(gameBitmap);

        particleWorld = new ParticleWorld(35,35);
        Particle sand = new SandParticle(particleWorld);
        particleWorld.setParticle(2,2, sand);
        particleWorld.setParticle(0,0, sand);
        particleWorld.setParticle(0,1, sand);
        particleWorld.setParticle(9,0, sand);
        particleWorld.setParticle(14,0, sand);
        particleWorld.setParticle(34,0, sand);
        particleWorld.setParticle(34,34, sand);

        tickHandler = new Handler();
        repeatedCode.run();
    }

    Runnable repeatedCode = new Runnable() {
        @Override
        public void run() {
            particleWorld.doPhysics();
            refresh();
            //particleWorld.fillWorldEmpty();
            //particleWorld.setParticle(20,20, new SandParticle(particleWorld));
            System.out.println("refreshed");
            particleWorld.doPhysics();
            refresh();

            tickHandler.postDelayed(repeatedCode, 1000);
        }
    };

    public void drawParticle(int x, int y, int color) {
        paint.setColor(color);
        canvas.drawRect(x*10,y*10,x*10+10,y*10+10, paint);
    }

    public void clearGame() {
        canvas.drawColor(Color.GREEN);
    }

    public void refresh() {
        clearGame();
        Particle[][] worldArray = particleWorld.getWorld();
        for (int colIndex = 0 ; colIndex < worldArray.length ; colIndex++) {
            for (int rowIndex = 0 ; rowIndex < worldArray[colIndex].length ; rowIndex++) {
                if (worldArray[colIndex][rowIndex].getType() != Type.EMPTY) {
                    drawParticle(colIndex, rowIndex, Color.RED);
                }
            }
        }
    }
}