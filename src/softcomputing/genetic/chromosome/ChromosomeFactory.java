package softcomputing.genetic.chromosome;

import java.util.Random;

public class ChromosomeFactory {

    public <T>Chromosome<T> createChromosome(ChromosomeType type, int length, T min, T max) {
        switch (type) {
            case BINARY -> {
                BinaryChromosome c = new BinaryChromosome(length);
                c.randomizeGenes(new Random());
                return (Chromosome<T>) c;
            }
            case INTEGER -> {
                IntegerChromosome c = new IntegerChromosome(length, (int) min, (int) max);
                c.randomizeGenes(new Random());
                return (Chromosome<T>) c;
            }
            case FLOATING_POINT -> {
                FloatingPointChromosome c = new FloatingPointChromosome(length, 0, 1);
                c.randomizeGenes(new Random());
                return (Chromosome<T>) c;
            }
            default -> throw new IllegalArgumentException("Unknown chromosome type.");
        }
    }
}
