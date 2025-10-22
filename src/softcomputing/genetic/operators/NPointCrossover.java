package softcomputing.genetic.operators;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import softcomputing.genetic.chromosome.Chromosome;

public class NPointCrossover<T> implements CrossoverStrategy<T>{
    int chromosomeLength;
    int N;
    double pc;
    
//    public NPointCrossover(int chromosomeLength, int n, double pc){
//        this.chromosomeLength = chromosomeLength;
//        this.N = n;
//        this.pc = pc;
//    }

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
        if (N < 0 || N >= chromosomeLength)
            throw new IllegalArgumentException("N must be in [0, chromosomeLength-1]");
        if (pc < 0.0 || pc > 1.0)
            throw new IllegalArgumentException("pc must be in [0,1]");

        if(r2 > this.pc){
            child1.setGenes(Arrays.copyOf(parent1Genes, chromosomeLength));
            child2.setGenes(Arrays.copyOf(parent2Genes, chromosomeLength));
            return;
        }

        // @SuppressWarnings("unchecked")
        // T[] child1Genes = (T[]) new Object[chromosomeLength];
        // @SuppressWarnings("unchecked")
        // T[] child2Genes = (T[]) new Object[chromosomeLength];
        
        @SuppressWarnings("unchecked")
        T[] child1Genes = (T[]) java.lang.reflect.Array
            .newInstance(parent1Genes.getClass().getComponentType(), chromosomeLength);
        @SuppressWarnings("unchecked")
        T[] child2Genes = (T[]) java.lang.reflect.Array
            .newInstance(parent2Genes.getClass().getComponentType(), chromosomeLength);

        Random rng = new Random();
        Set<Integer> cutsSet = new TreeSet<>();
        while (cutsSet.size() < N) {
            int cut = 1 + rng.nextInt(chromosomeLength - 1); // [1, len-1]
            cutsSet.add(cut);
        }
        int[] cuts = new int[N + 1];
        int k = 0;
        for (int c : cutsSet) cuts[k++] = c;
        cuts[N] = chromosomeLength;

        int start = 0;
        boolean flip = false;
        for (int idx = 0; idx < cuts.length; idx++) {
            int end = cuts[idx];
            int segLen = end - start;
            if (!flip) {
                System.arraycopy(parent1Genes, start, child1Genes, start, segLen);
                System.arraycopy(parent2Genes, start, child2Genes, start, segLen);
            } else {
                System.arraycopy(parent2Genes, start, child1Genes, start, segLen);
                System.arraycopy(parent1Genes, start, child2Genes, start, segLen);
            }
            flip = !flip;
            start = end;
        }

        child1.setGenes(child1Genes);
        child2.setGenes(child2Genes);
    }
}
