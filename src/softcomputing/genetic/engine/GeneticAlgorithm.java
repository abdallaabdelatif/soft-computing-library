package softcomputing.genetic.engine;

import softcomputing.genetic.chromosome.Chromosome;
import softcomputing.genetic.chromosome.ChromosomeFactory;
import softcomputing.genetic.chromosome.ChromosomeType;
import softcomputing.genetic.fitness.FitnessFunction;
import softcomputing.genetic.operators.CrossoverStrategy;
import softcomputing.genetic.operators.MutationStrategy;
import softcomputing.genetic.operators.ReplacementStrategy;
import softcomputing.genetic.operators.SelectionStrategy;

import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm<T> {

    private final ChromosomeFactory factory;
    private final SelectionStrategy<T> selection;
    private final CrossoverStrategy<T> crossover;
    private final MutationStrategy<T> mutation;
    private final ReplacementStrategy<T> replacement;
    private final FitnessFunction<T> fitness;
    private final GAParameters params;

    private List<Chromosome<T>> population;



    public GeneticAlgorithm(
            ChromosomeFactory factory,
            SelectionStrategy<T> selection,
            CrossoverStrategy<T> crossover,
            MutationStrategy<T> mutation,
            ReplacementStrategy<T> replacement,
            FitnessFunction<T> fitness,
            GAParameters params) {
        this.factory = factory;
        this.selection = selection;
        this.crossover = crossover;
        this.mutation = mutation;
        this.replacement = replacement;
        this.fitness = fitness;
        this.params = params;
    }

    public void initializePopulation(ChromosomeType type, int geneLength, T min, T max) {
        population = new ArrayList<>();
        for (int i = 0; i < params.getPopulationSize(); i++) {
            Chromosome<T> c = factory.createChromosome(type, geneLength, min, max);
            population.add(c);
        }
    }

    public void run() {
    }
}
