import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Created by jens on 13.03.15.
 */
public class Diffusion extends PApplet {

    private int stepLimit = 0;
    private int particleAmount;
    private int currentStep = 0;
    private int stage;
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
        this.particleAmount = 200;

        //        Plassere ut particleAmount antall partikler
        for (int i = 0; i < particleAmount; i++) {
            particles.add(i, new Particle((150), (150), (i % 255), (i % 255), (0)));

        }
    }

    public void run(int particleAmount, int numSteps) {
        System.out.println("Kjører " + numSteps + " steps med " + particleAmount + " partikler");

    }

    public void draw() {
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
        }


    }

}
