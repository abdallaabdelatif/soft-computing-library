package softcomputing.genetic.chromosome;

import java.util.Arrays;
import java.util.Random;

public class BinaryChromosome implements Chromosome<Boolean>{
    private Boolean[] genes;
    private double fitness;

    public BinaryChromosome(int length) {
        this.genes = new Boolean[length];
    }

    @Override
    public Boolean[] getGenes() {
        return genes;
    }

    @Override
    public void setGenes(Boolean[] genes) {
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
        for (int i = 0; i < genes.length; i++) {
            genes[i] = random.nextBoolean();
        }
    }

    @Override
    public Chromosome<Boolean> copy() {
        BinaryChromosome clone = new BinaryChromosome(genes.length);
        clone.setFitness(fitness);
        Boolean[] copiedGenes = new Boolean[genes.length];
        for(int i = 0; i < genes.length; i++) {
            copiedGenes[i] = genes[i];
        }
        clone.setGenes(copiedGenes);
        return clone;
    }

    @Override
    public String toString() {
        return "BinaryChromosome{" +
                "genes=" + Arrays.toString(genes) +
                '}';
    }
}
