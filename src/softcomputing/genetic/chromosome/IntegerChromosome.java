package softcomputing.genetic.chromosome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class IntegerChromosome implements Chromosome<Integer> {
    private Integer[] genes;
    private double fitness;
    private final int minValue;
    private final int maxValue;

    public IntegerChromosome(int length, int minValue, int maxValue) {
        this.genes = new Integer[length];
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public Integer[] getGenes() {
        return genes;
    }

    @Override
    public void setGenes(Integer[] genes) {
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
    public void randomizeGenes(Random random) {
        if (minValue == 0 && maxValue == genes.length - 1) {
            // Generate unique permutation for job scheduling (0 to length-1)
            List<Integer> perm = new ArrayList<>();
            for (int i = minValue; i <= maxValue; i++) perm.add(i);
            Collections.shuffle(perm, random);
            for (int i = 0; i < genes.length; i++) genes[i] = perm.get(i);
        } else {
            // Random values (may have duplicates)
            for (int i = 0; i < genes.length; i++) {
                genes[i] = random.nextInt(maxValue - minValue + 1) + minValue;
            }
        }
    }

    @Override
    public Chromosome<Integer> copy() {
        IntegerChromosome clone = new IntegerChromosome(genes.length, minValue, maxValue);
        clone.setFitness(fitness);
        Integer[] copiedGenes = new Integer[genes.length];
        for (int i = 0; i < genes.length; i++) {
            copiedGenes[i] = genes[i];
        }
        clone.setGenes(copiedGenes);
        return clone;
    }

    @Override
    public String toString() {
        return Arrays.toString(genes);
    }
}