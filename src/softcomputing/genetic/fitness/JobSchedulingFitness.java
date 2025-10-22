package softcomputing.genetic.fitness;

import java.util.Arrays;
import java.util.List;
import softcomputing.genetic.chromosome.Chromosome;
import softcomputing.makespanProblem.Job;
import softcomputing.makespanProblem.Machine;

public class JobSchedulingFitness<T> implements FitnessFunction<T> {
    private final List<Job> jobs;
    private final List<Machine> machines;

    public JobSchedulingFitness(List<Job> jobs, List<Machine> machines) {
        this.jobs = jobs;
        this.machines = machines;
    }

    @Override
    public double evaluate(Chromosome<T> chromosome) {
        T[] genes = chromosome.getGenes();
        
        for (Machine m : machines) {
            m.getAssignedJobs().clear();
        }

        for (Job job : jobs) {
            Machine bestMachine = machines.get(0);
            for (Machine m : machines) {
                if (m.getTotalProcessingTime() < bestMachine.getTotalProcessingTime()) {
                    bestMachine = m;
                }
            }
            bestMachine.assignJob(job);
        }

        int makespan = 0;
        for (Machine m : machines) {
            makespan = Math.max(makespan, m.getTotalProcessingTime());
        }
        chromosome.setFitness((double) 1 /makespan);
        boolean feasible = genes.length == jobs.size() && Arrays.stream(genes).distinct().count() == genes.length;
        if (!feasible) {
            return Double.MAX_VALUE;  
        }

        return (double) 1 /makespan;
    }
}