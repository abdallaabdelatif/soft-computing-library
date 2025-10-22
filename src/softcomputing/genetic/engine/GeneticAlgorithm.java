package softcomputing.genetic.engine;

import java.util.ArrayList;
import java.util.List;
import softcomputing.genetic.chromosome.Chromosome;
import softcomputing.genetic.chromosome.ChromosomeFactory;
import softcomputing.genetic.chromosome.ChromosomeType;
import softcomputing.genetic.fitness.FitnessFunction;
import softcomputing.genetic.operators.CrossoverStrategy;
import softcomputing.genetic.operators.MutationStrategy;
import softcomputing.genetic.operators.ReplacementStrategy;
import softcomputing.genetic.operators.SelectionStrategy;
import softcomputing.genetic.population.Population;

public class GeneticAlgorithm<T> {

    private final ChromosomeFactory factory;
    private final SelectionStrategy<T> selection;
    private final CrossoverStrategy<T> crossover;
    private final MutationStrategy<T> mutation;
    private final ReplacementStrategy<T> replacement;
    private final FitnessFunction<T> fitness;
    private final GAParameters params;

    public List<Chromosome<T>> population;



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
    // for binary
    public void initializePopulation(ChromosomeType type, int geneLength) {
        population = new ArrayList<>();
        for (int i = 0; i < params.getPopulationSize(); i++) {
            Chromosome<T> c = factory.createChromosome(type, geneLength, null, null);
            population.add(c);
        }
    }
    // for integer and floating point
    public void initializePopulation(ChromosomeType type, int geneLength, T min, T max) {
        population = new ArrayList<>();
        for (int i = 0; i < params.getPopulationSize(); i++) {
            Chromosome<T> c = factory.createChromosome(type, geneLength, min, max);
            population.add(c);
        }
    }

    public void run() {
        // Initialize population
        Population<T> currentPopulation = new Population<>();
        for (Chromosome<T> c : population) {
            currentPopulation.addIndividual(c);
        }

        Chromosome<T> bestChromosome = null;
        double bestFitness = 0;

        for (int gen = 0; gen < params.getMaxGenerations(); gen++) {

            for (Chromosome<T> c : currentPopulation.getIndividuals()) {
                double fit = fitness.evaluate((Chromosome<T>) c);
                c.setFitness(fit);

                // Track best solution
                if (fit > bestFitness) {
                    bestFitness = fit;
                    bestChromosome = c.copy();
                }
            }

            // Selection
            List<Chromosome<T>> parents = selection.select(currentPopulation, params.getPopulationSize() / 2);

            // Crossover & Mutation -> generate offspring
            Population<T> offspring = new Population<>();
            for (int i = 0; i < parents.size(); i += 2) {
                Chromosome<T> p1 = parents.get(i);
                Chromosome<T> p2 = parents.get(i + 1);
                Chromosome<T> c1 = p1.copy();
                Chromosome<T> c2 = p2.copy();

                crossover.operate(p1, p2, c1, c2);
                mutation.mutate(c1, params.getMutationRate());
                mutation.mutate(c2, params.getMutationRate());

                offspring.addIndividual(c1);
                offspring.addIndividual(c2);
            }

            // Replacement: replace current population with offspring
            currentPopulation = replacement.replace(currentPopulation, offspring, params.getEliteCount());

            System.out.println("Generation " + gen + " - Best Fitness: " + bestFitness);
        }

        System.out.println("\nBest Solution Found:");
        System.out.println("Fitness: " + bestFitness);
        System.out.println("Chromosome: " + bestChromosome);
    }
}