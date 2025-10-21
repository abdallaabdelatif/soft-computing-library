package softcomputing.genetic.operators;

import softcomputing.genetic.chromosome.Chromosome;
import java.util.Random;

public class NonUniformMutation implements MutationStrategy<Double> {
    private final Random random = new Random();
    private final double minValue;
    private final double maxValue;
    private int currentGeneration = 1;
    private int maxGenerations = 100;
    private final double b = 5.0;

    public NonUniformMutation(double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public void setGenerationInfo(int current, int max) {
        this.currentGeneration = current;
        this.maxGenerations = max;
    }

    @Override
    public void mutate(Chromosome<Double> chromosome, double mutationRate) {
        Double[] genes = chromosome.getGenes();

        for (int i = 0; i < genes.length; i++) {
            if (random.nextDouble() < mutationRate) {
                double y, delta;
                double gene = genes[i];

                if (random.nextBoolean()) {
                    y = maxValue - gene;
                    delta = y * (1 - Math.pow(random.nextDouble(),
                            Math.pow(1 - (double) currentGeneration / maxGenerations, b)));
                    gene += delta;
                } else {
                    y = gene - minValue;
                    delta = y * (1 - Math.pow(random.nextDouble(),
                            Math.pow(1 - (double) currentGeneration / maxGenerations, b)));
                    gene -= delta;
                }


                if (gene < minValue) gene = minValue;
                if (gene > maxValue) gene = maxValue;
                genes[i] = gene;
            }
        }

        chromosome.setGenes(genes);
    }
}
