package ParticleEngine;

import android.graphics.Color;
import java.util.Random;
public class GasParticle extends Particle {

    public GasParticle(ParticleWorld world) {
        super(world);
        this.type = Type.GAS;
        this.color = Color.LTGRAY;
        this.isSolid = false;
    }

    @Override
    public void doPhysics(int x, int y,ParticleWorld copyWorld, ParticleWorld realworld) {
        if(realworld.getParticle(x,y).getType() == type.GAS )
        if (!copyWorld.locSolid(x, y-1) && !CheckUnderGas(x, y, copyWorld)) {

                realworld.switchParticle(x, y, x, y - 1);

        }
        else{
            if(Checkleft(x, y,copyWorld)== true && Checkrigth(x, y,copyWorld)==true)  {
                Random rand = new Random();
                int rand_C = rand.nextInt(2);
                if (rand_C==1) {
                    realworld.switchParticle(x, y, x+1, y);
                }
                else {
                    realworld.switchParticle(x, y, x-1, y);
                }
            }

            if(Checkleft(x, y,copyWorld)==true && Checkrigth(x, y,copyWorld)==false) {
                realworld.switchParticle(x, y, x-1, y);
            }

            if(Checkleft(x, y,copyWorld)==false && Checkrigth(x, y,copyWorld)==true) {
                realworld.switchParticle(x, y, x+1, y);
            }
        }
    }



    @Override
    public boolean getIsSolid() {
        return false;
    }

    @Override
    public Particle clone( ParticleWorld cloneWorld) {
        return null;
    }

    public Boolean Checkrigth(int x, int y,ParticleWorld copyWorld) {
        if (!copyWorld.locSolid(x+1, y)) {
            return true;
        }
        else {
            return false;
        }

    }

    public Boolean Checkleft(int x, int y,ParticleWorld copyWorld) {
        if (!copyWorld.locSolid(x - 1, y)) {
            return true;
        }
        else {
            return false;
        }
    }
    public Boolean CheckUnderGas(int x ,int y,ParticleWorld copyWorld){
        if(copyWorld.getParticle(x,y-1).getType() == Type.GAS ) {
            return true;
        }
        else {
            return false;
        }
    }
}
