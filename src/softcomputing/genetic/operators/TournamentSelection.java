package softcomputing.genetic.operators;

import softcomputing.genetic.chromosome.Chromosome;
import softcomputing.genetic.population.Population;

import java.util.ArrayList;
import java.util.Random;

import java.util.List;

public class TournamentSelection <T> implements SelectionStrategy <T>{

    private final int tournamentSize;
    private final Random random = new Random();

    public TournamentSelection() {
        this(3);
    }

    public TournamentSelection(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }
    @Override
    public List<Chromosome<T>> select(Population<T> population, int numberOfParents) {
        List<Chromosome<T>> selected = new ArrayList<>();

        for (int i = 0; i < numberOfParents; i++) {
            Chromosome<T> winner = runTournament(population);
            selected.add(winner.copy());
        }
        return selected;
    }

    private Chromosome<T> runTournament(Population<T> population) {
        List<Chromosome<T>> individuals = population.getIndividuals();
        Chromosome<T> best = null;
        for (int i = 0; i < tournamentSize; i++) {
            Chromosome<T> candidate = individuals.get(random.nextInt(individuals.size()));
            if (best == null || candidate.getFitness() > best.getFitness()) {
                best = candidate;
            }
        }
        return best;
    }
}
