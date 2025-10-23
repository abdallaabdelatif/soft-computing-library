package softcomputing.genetic.operators;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import softcomputing.genetic.chromosome.Chromosome;

public class SinglePointCrossover<T> implements CrossoverStrategy<T>{
    int chromosomeLength;
    double pc;

   public SinglePointCrossover(int length, double pc){
       this.chromosomeLength = length;
       this.pc = pc;
   }

    @Override
    public void operate(Chromosome<T> parent1, Chromosome<T> parent2, Chromosome<T> child1, Chromosome<T> child2){
        double r2 = Math.random();
        T[] parent1Genes = parent1.getGenes();
        T[] parent2Genes = parent2.getGenes();
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
        

        
        int r1 = ThreadLocalRandom.current().nextInt(1, chromosomeLength);
        
        for(int i = 0; i < r1; i++){
            child1Genes[i] = parent1Genes[i];
            child2Genes[i] = parent2Genes[i];
        }
        for(int i = r1; i < chromosomeLength; i++){
            child1Genes[i] = parent2Genes[i];
            child2Genes[i] = parent1Genes[i];
        }
        child1.setGenes(child1Genes);
        child2.setGenes(child2Genes);
    }

}
