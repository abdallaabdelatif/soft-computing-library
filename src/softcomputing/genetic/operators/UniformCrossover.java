package softcomputing.genetic.operators;

import java.util.Arrays;
import java.util.Random;

import softcomputing.genetic.chromosome.Chromosome;

public class UniformCrossover<T> implements CrossoverStrategy<T>{
    int chromosomeLength;
    double pc;

    public UniformCrossover(int chromosomeLength, double pc){
        this.chromosomeLength = chromosomeLength;
        this.pc = pc;
    }

    @Override
    public void operate(Chromosome<T> parent1, Chromosome<T> parent2, Chromosome<T> child1, Chromosome<T> child2){

        double r2 = Math.random();
        T[] parent1Genes = parent1.getGenes();
        T[] parent2Genes = parent2.getGenes();

        if (parent1Genes.length != parent2Genes.length)
            throw new IllegalArgumentException("Parents differ in length");
        if (parent1Genes.length != chromosomeLength)
            throw new IllegalArgumentException("chromosomeLength mismatch");
        if (chromosomeLength < 2)
            throw new IllegalArgumentException("chromosomeLength must be ≥ 2");
        if (pc < 0.0 || pc > 1.0)
            throw new IllegalArgumentException("pc must be in [0,1]");

        if(r2 > this.pc){
            child1.setGenes(Arrays.copyOf(parent1Genes, chromosomeLength));
            child2.setGenes(Arrays.copyOf(parent2Genes, chromosomeLength));
            return;
        }
        
        @SuppressWarnings("unchecked")
        T[] child1Genes = (T[]) java.lang.reflect.Array
            .newInstance(parent1Genes.getClass().getComponentType(), chromosomeLength);
        @SuppressWarnings("unchecked")
        T[] child2Genes = (T[]) java.lang.reflect.Array
            .newInstance(parent2Genes.getClass().getComponentType(), chromosomeLength);


        Random random = new Random();
        int bit;
        for(int i = 0; i < chromosomeLength; i++){
            bit = random.nextInt(2); 
            if(bit == 0){
                child1Genes[i] = parent1Genes[i];
                child2Genes[i] = parent2Genes[i];
            }
            else{
                child1Genes[i] = parent2Genes[i];
                child2Genes[i] = parent1Genes[i];
            }
        }
        
        child1.setGenes(child1Genes);
        child2.setGenes(child2Genes);
    }
}
