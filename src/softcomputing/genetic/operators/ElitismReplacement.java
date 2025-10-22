package softcomputing.genetic.operators;
import java.util.List;
import softcomputing.genetic.chromosome.Chromosome;
import softcomputing.genetic.population.Population;

public class ElitismReplacement<T> implements ReplacementStrategy<T>{
    @Override
    public Population<T> replace(Population<T> currentPopulation, Population<T> offsprings, int eliteCount){
        Population<T> newPopulation = new Population<>();
        int popSize = currentPopulation.size();

        List<Chromosome<T>> parents = currentPopulation.getIndividuals();
        parents.sort((a, b) -> Double.compare(b.getFitness(), a.getFitness()));

        List<Chromosome<T>> children = offsprings.getIndividuals();
        children.sort((a, b) -> Double.compare(b.getFitness(), a.getFitness()));
        
        for (int i = 0; i < eliteCount && i < parents.size(); i++) {
            newPopulation.addIndividual(parents.get(i));
        }
        int i = 0;
        while (newPopulation.size() < popSize && i < children.size()) {
            newPopulation.addIndividual(children.get(i));
            i++;
        }
        int j = eliteCount;
        while (newPopulation.size() < popSize && j < parents.size()) {
            newPopulation.addIndividual(parents.get(j));
            j++;
        }
        return newPopulation;
    }
}
