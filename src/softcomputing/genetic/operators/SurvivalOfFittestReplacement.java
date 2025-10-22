package softcomputing.genetic.operators;
import java.util.ArrayList;
import java.util.List;
import softcomputing.genetic.chromosome.Chromosome;
import softcomputing.genetic.population.Population;

public class SurvivalOfFittestReplacement<T> implements ReplacementStrategy<T> {
    @Override
    public Population<T> replace(Population<T> currentPopulation, Population<T> offsprings, int eliteCount) {
        Population<T> newPopulation = new Population<>();
        int popSize = currentPopulation.size();

        List<Chromosome<T>> combined = new ArrayList<>();
        combined.addAll(currentPopulation.getIndividuals());
        combined.addAll(offsprings.getIndividuals());
        
        combined.sort((a, b) -> Double.compare(b.getFitness(), a.getFitness()));

        int i = 0;
        while (newPopulation.size() < popSize && i < combined.size()) {
            newPopulation.addIndividual(combined.get(i));
            i++;
        }

        return newPopulation;
    }
}
