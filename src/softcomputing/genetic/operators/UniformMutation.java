package softcomputing.genetic.operators;

import softcomputing.genetic.chromosome.Chromosome;
import java.util.Random;

public class UniformMutation implements MutationStrategy<Double> {
    private final Random random = new Random();
    private final double minValue;
    private final double maxValue;

    public UniformMutation(double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public void mutate(Chromosome<Double> chromosome, double mutationRate) {
        Double[] genes = chromosome.getGenes();

        for (int i = 0; i < genes.length; i++) {
            if (random.nextDouble() < mutationRate) {
                genes[i] = minValue + (maxValue - minValue) * random.nextDouble();
            }
        }

        chromosome.setGenes(genes);
    }
}
