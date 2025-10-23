package softcomputing.genetic.chromosome;

import java.util.Arrays;
import java.util.Random;

public class FloatingPointChromosome implements Chromosome<Double> {
    private Double[] genes;
    private double fitness;
    private final double minValue;
    private final double maxValue;

    public FloatingPointChromosome(int length, double minValue, double maxValue) {
        this.genes = new Double[length];
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public Double[] getGenes() {
        return genes;
    }

    @Override
    public void setGenes(Double[] genes) {
        this.genes = genes;
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public Double getMax() {
        return maxValue;
    }

    @Override
    public Double getMin() {
        return minValue;
    }

    @Override
    public void randomizeGenes(Random random) {
        for (int i = 0; i < genes.length; i++) {
            genes[i] = random.nextDouble();
        }
    }

    @Override
    public Chromosome<Double> copy() {
        FloatingPointChromosome clone = new FloatingPointChromosome(genes.length, minValue, maxValue);
        clone.setFitness(fitness);
        Double[] copiedGenes = new Double[genes.length];
        for(int i = 0; i < genes.length; i++) {
            copiedGenes[i] = genes[i];
        }
        clone.setGenes(copiedGenes);
        return clone;
    }

    @Override
    public String toString() {
        return "FloatingPointChromosome{" +
                "genes=" + Arrays.toString(genes) +
                '}';
    }
}
