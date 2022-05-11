package ParticleEngine;

import android.widget.Switch;

public class WorldParser {

    private WorldParser() { }

    public static String worldToString(Particle[][] world) {
        String tempString = "";

        for (Particle[] col : world) {
            for (Particle particle : col) {

                switch (particle.getType()) {
                    //TODO: ADD ALL ELEMENTS
                    case GAS:
                        tempString += "4";
                        break;
                    case WATER:
                        tempString += "3";
                        break;
                    case STONE:
                        tempString += "2";
                        break;
                    case SAND:
                        tempString += "1";
                        break;
                    default:
                        tempString += "0";
                        break;
                }
            }
        }
        System.out.println(tempString.length());
        return tempString;
    }

    public static Particle[][] stringToWorld(String worldString, ParticleWorld world) {
        Particle[][] tempWorld = new Particle[35][35];

        System.out.print("TempChars:");
        for (int colIndex = 0 ; colIndex < 35 ; colIndex++) {
            for (int rowIndex = 0 ; rowIndex < 35 ; rowIndex++) {
                char tempChar = worldString.toCharArray()[rowIndex + colIndex*35];

                switch (tempChar) {
                    //TODO: ADD ALL ELEMENTS
                    case '4':
                        tempWorld[colIndex][rowIndex] = new GasParticle(world);
                        break;
                    case '3':
                        tempWorld[colIndex][rowIndex] = new WaterParticle(world);
                        break;
                    case '2':
                        tempWorld[colIndex][rowIndex] = new StoneParticle(world);
                        break;
                    case '1':
                        tempWorld[colIndex][rowIndex] = new SandParticle(world);
                        break;
                    default:
                        tempWorld[colIndex][rowIndex] = new EmptyParticle(world);
                }

                System.out.print(tempChar);
            }
        }

        return tempWorld;
    }
}
