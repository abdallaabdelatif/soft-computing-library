package softcomputing.genetic.population;

import java.util.ArrayList;
import java.util.List;
import softcomputing.genetic.chromosome.Chromosome;

public class Population<T> {
    private final List<Chromosome<T>> individuals = new ArrayList<>();

    public void addIndividual(Chromosome<T> chromosome) {
        individuals.add(chromosome);
    }

    public List<Chromosome<T>> getIndividuals() {
        return individuals;
    }

    public int size() {
        return individuals.size();
    }
}
