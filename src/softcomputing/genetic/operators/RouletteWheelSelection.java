package softcomputing.genetic.operators;

import softcomputing.genetic.chromosome.Chromosome;
import softcomputing.genetic.population.Population;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class RouletteWheelSelection <T> implements SelectionStrategy <T>{
    private final Random random = new Random();

    public RouletteWheelSelection() {
    }

    @Override
    public List<Chromosome<T>> select(Population<T> population, int numberOfParents) {
        List<Chromosome<T>> selected = new ArrayList<>();
        double totalFitness = 0;

        List<Chromosome<T>> individuals = population.getIndividuals();
        for (Chromosome<T> individual : individuals) {
            totalFitness += individual.getFitness();
        }

        for (int i = 0; i < numberOfParents; i++) {
            double rand = random.nextDouble() * totalFitness;
            double cumulative = 0.0;
            for (Chromosome<T> c : individuals) {
                cumulative += c.getFitness();
                if (cumulative > rand) {
                    selected.add(c.copy());
                    break;
                }
            }
        }
        return selected;
    }
}
