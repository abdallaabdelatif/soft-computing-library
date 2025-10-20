package softcomputing.genetic.fitness;
import softcomputing.genetic.chromosome.Chromosome;
import softcomputing.makespanProblem.Job;
import softcomputing.makespanProblem.Machine;

import java.util.List;


public class JobSchedulingFitness implements FitnessFunction{
    private final List<Job> jobs;
    private final List<Machine> machines;

    public JobSchedulingFitness(List<Job> jobs, List<Machine> machines) {
        this.jobs = jobs;
        this.machines = machines;
    }
    @Override
    public double evaluate(Chromosome<Integer> chromosome) {
        return 0;
    }
}
