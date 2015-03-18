import processing.core.PApplet;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import java.io.IOException;

/**
 * Created by jens on 13.03.15.
 */
public class Diffusion extends PApplet {

    private int stepLimit = 0;
    private int particleAmount;
    private int currentStep = 0;
    private int stage = 1;
    boolean pause = false;


    private ArrayList<Particle> particles = new ArrayList<Particle>();

    public static void main(String[] args) {
        Diffusion diffusion = new Diffusion();
        PApplet.main(new String[]{"Diffusion"});

    }

    public void setup() {
        //  size(displayWidth, displayHeight);    // Fullskjerm
        size(300, 300);
        smooth();
        background(255);
        noStroke();
        fill(0, 255, 0);
        frameRate(100); // med 1000 steps vil simulering vare i 10 sek med frameRate = 100
    }

    public Diffusion() {
        this.stepLimit = 1000;
        this.particleAmount = 1000;

        //        Plassere ut particleAmount antall partikler
        for (int i = 0; i < particleAmount; i++) {
            particles.add(i, new Particle((150), (150), (i % 255), (i % 255), (0)));

        }
    }

    public void calcDistances() {
        for (int i = 0; i < particleAmount; i++) {
            particles.get(i).calcDistanceFromZero();

        }
    }

    public double avgDistance() {
        double avg=0;
        for (int i = 0; i < particleAmount; i++) {
            avg = avg + particles.get(i).getDistanceFromZero();

        }
        return avg/this.particleAmount;
    }

    public int getParticleAmount() {
        return particleAmount;
    }

    public void draw() {

        if (stage == 1) {
            if (currentStep < stepLimit) {
                background(255);
                text(currentStep + 1, 20, 20);
                //     System.out.println("particles har " + particles.size() + " objekter");
                for (int i = 0; i < particles.size(); i++) {
                    fill(particles.get(i).getColR(), particles.get(i).getColG(), particles.get(i).getColB());
                    ellipse(particles.get(i).getXpos(), particles.get(i).getYpos(), particles.get(i).getWidth(), particles.get(i).getHeight());

                    //         Flytte de
                    particles.get(i).move(300, 300);

                }

                currentStep++;

        } else {
                // Ferdig å simulere
                stage = 2;
            }
        if (stage == 2) {
            int runCalc=1;

            if (runCalc==1){




                double totDistance = 0;
                double avgDistance = 0;
                double avgDistanceSquared = 0;



                try
                {
                    FileWriter writer = new FileWriter("testing.csv");

                    writer.append("PARTIKKEL");
                    writer.append(',');
                    writer.append("XPOS");
                    writer.append(',');
                    writer.append("YPOS");
                    writer.append('\n');

                    for (int i = 0; i < particles.size(); i++) {


                        int xpos = abs(150 - particles.get(i).getXpos());
                        int ypos = abs(150 - particles.get(i).getYpos());

                        writer.append("" + i);
                        writer.append(',');
                        writer.append("" + xpos);
                        writer.append(',');
                        writer.append("" + ypos);
                        writer.append('\n');

                        double distance = Math.sqrt( Math.pow(xpos, 2) + Math.pow(ypos, 2) );
                        double distanceSquared = Math.pow(distance, 2);
                        totDistance = totDistance + distance;
                        avgDistance = totDistance/i;
                        avgDistanceSquared = avgDistanceSquared + distanceSquared;

                        System.out.println("Partikkel nr " + i + " har x: " + xpos + " og y: " + ypos + " Distansen er da " + distance + " og avg distanse er " + avgDistance + " avg distance ² er " + avgDistanceSquared);
                        runCalc=0;
                    }



                    //generate whatever data you want

                    writer.flush();
                    writer.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }



            }



        }


            /*
            double avgDistance = 0;
            for (int i = 0; i < particles.size(); i++) {
                int xpos = abs(150 - particles.get(i).getXpos());
                int ypos = abs(150 - particles.get(i).getYpos());
                double distance = Math.sqrt(xpos^2 + ypos^2 );
                avgDistance = avgDistance+ distance;

            }

            System.out.println(avgDistance/particleAmount);
*/
        }


    }

}
