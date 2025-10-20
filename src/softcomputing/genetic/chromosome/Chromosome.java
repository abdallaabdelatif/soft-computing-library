package softcomputing.genetic.chromosome;

import java.util.Random;

public interface Chromosome<T> extends Cloneable{
    T[] getGenes();
    void setGenes(T[] genes);
    double getFitness();
    void setFitness(double fitness);
    void randomizeGenes(Random random);
    Chromosome<T> copy();
}
