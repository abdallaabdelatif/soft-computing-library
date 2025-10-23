package softcomputing.genetic.infeasibilityCheck;

import softcomputing.genetic.chromosome.Chromosome;

public class JobConstraintHandler<T> implements ConstraintHandler<T> {
    private final int numberOfMachines;
    private final int numberOfJobs;

    public JobConstraintHandler(int numberOfMachines, int numberOfJobs) {
        this.numberOfMachines = numberOfMachines;
        this.numberOfJobs = numberOfJobs;
    }

    @Override
    public boolean isFeasible(Chromosome<T> chromosome) {
        T[] genes = chromosome.getGenes();

        if (genes.length != numberOfJobs) return false;

        for (T gene : genes) {
            int machineIndex = 0;
            if (gene instanceof Integer)
                machineIndex = (Integer) gene;
            else if (gene instanceof Double)
                machineIndex = (int) ((Double) gene * numberOfMachines);
            else if (gene instanceof Boolean)
                machineIndex = (Boolean) gene ? 1 : 0;

            if (machineIndex < 0 || machineIndex >= numberOfMachines)
                return false;
        }

        return true;
    }
}

