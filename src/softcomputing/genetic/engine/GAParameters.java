package softcomputing.genetic.engine;

public class GAParameters {
    private int populationSize;
    private int maxGenerations;
    private float crossoverRate;
    private float mutationRate;
    private int eliteCount;

    public GAParameters() {
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getMaxGenerations() {
        return maxGenerations;
    }

    public void setMaxGenerations(int maxGenerations) {
        this.maxGenerations = maxGenerations;
    }

    public float getCrossoverRate() {
        return crossoverRate;
    }

    public void setCrossoverRate(float crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public float getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(float mutationRate) {
        this.mutationRate = mutationRate;
    }

    public int getEliteCount() {
        return eliteCount;

    }

    public void setEliteCount(int eliteCount) {
        this.eliteCount = eliteCount;
    }
}
