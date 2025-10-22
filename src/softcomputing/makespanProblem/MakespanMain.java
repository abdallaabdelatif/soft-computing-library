package softcomputing.makespanProblem;

import softcomputing.genetic.chromosome.Chromosome;
import softcomputing.genetic.chromosome.ChromosomeFactory;
import softcomputing.genetic.chromosome.ChromosomeType;
import softcomputing.genetic.engine.GAParameters;
import softcomputing.genetic.engine.GeneticAlgorithm;
import softcomputing.genetic.fitness.FitnessFunction;
import softcomputing.genetic.fitness.JobSchedulingFitness;
import softcomputing.genetic.operators.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MakespanMain {

    public static void main(String[] args) {
        GAParameters params = configureParametersFromUser();
        GeneticAlgorithm ga = buildAlgorithm(params);
        ga.run();
    }

    private static GAParameters configureParametersFromUser() {
        Scanner sc = new Scanner(System.in);
        GAParameters params = new GAParameters();

        System.out.print("Enter population size (default = 100): ");
        params.setPopulationSize(getOrDefault(sc, 100));

        System.out.print("Enter max generations (default = 200): ");
        params.setMaxGenerations(getOrDefault(sc, 200));

        System.out.print("Enter crossover rate (default = 0.8): ");
        params.setCrossoverRate(getOrDefault(sc, 0.8f));

        System.out.print("Enter mutation rate (default = 0.05): ");
        params.setMutationRate(getOrDefault(sc, 0.05f));

        System.out.print("Enter elites count (default = 2): ");
        params.setEliteCount(getOrDefault(sc, 2));
        return params;
    }

    private static int getOrDefault(Scanner sc, int defaultValue) {
        String input = sc.nextLine().trim();
        if (input.isEmpty()) {
            return defaultValue;
        }
        return Integer.parseInt(input);
    }

    private static float getOrDefault(Scanner sc, float defaultValue) {
        String input = sc.nextLine().trim();
        if (input.isEmpty()) {
            return defaultValue;
        }
        return Float.parseFloat(input);
    }

    private static GeneticAlgorithm buildAlgorithm(GAParameters params) {
        Scanner sc = new Scanner(System.in);

        // =============================================================
        // Selection method
        // =============================================================
        System.out.println("Choose selection method:");
        System.out.println("1. Tournament Selection");
        System.out.println("2. Roulette Wheel Selection");
        int selChoice = sc.nextInt();

        SelectionStrategy selection;
        if (selChoice == 1) {
            selection = new TournamentSelection();
        } else {
            selection = new RouletteWheelSelection();
        }

        // =============================================================
        // Crossover method
        // =============================================================
        System.out.println("Choose crossover method:");
        System.out.println("1. Single Point");
        System.out.println("2. N Point");
        System.out.println("3. Uniform");
        int crossChoice = sc.nextInt();

        CrossoverStrategy crossover;
        if (crossChoice == 1) {
            crossover = new SinglePointCrossover();
        } else if (crossChoice == 2) {
            crossover = new UniformCrossover();
        } else {
            crossover = new UniformCrossover();
        }

        // =============================================================
        // Mutation method
        // =============================================================
        System.out.println("Choose mutation method:");
        System.out.println("1. Bit Flip (Binary)");
        System.out.println("2. Swap (Integer, for permutations)");
        System.out.println("3. Insert (Integer, for permutations)");
        System.out.println("4. Inversion (Integer, for permutations)");
        System.out.println("5. Uniform (Floating-Point)");
        System.out.println("6. Non-Uniform (Floating-Point)");

        int mutChoice = sc.nextInt();
        MutationStrategy<?> mutation;
        ChromosomeType type = null; 

        if (mutChoice == 1) {
            mutation = new BitFlipMutation();
        } else if (mutChoice == 2) {
            mutation = new SwapMutation();
        } else if (mutChoice == 3) {
            mutation = new InsertMutation();
        } else if (mutChoice == 4) {
            mutation = new InversionMutation();
        } else if (mutChoice == 5) {
            if (type == ChromosomeType.FLOATING_POINT) {
                System.out.print("Enter min gene value: ");
                double min = sc.nextDouble();
                System.out.print("Enter max gene value: ");
                double max = sc.nextDouble();
                mutation = new UniformMutation(min, max);
            } else {
                System.out.println("Invalid for non-floating-point. Defaulting to Swap.");
                mutation = new SwapMutation();
            }
        } else if (mutChoice == 6) {
            if (type == ChromosomeType.FLOATING_POINT) {
                System.out.print("Enter min gene value: ");
                double min = sc.nextDouble();
                System.out.print("Enter max gene value: ");
                double max = sc.nextDouble();
                mutation = new NonUniformMutation(min, max);
            } else {
                System.out.println("Invalid for non-floating-point. Defaulting to Swap.");
                mutation = new SwapMutation();
            }
        } else {
            mutation = new SwapMutation(); // default
        }

        
        // =============================================================
        // Replacement method
        // =============================================================
        System.out.println("Choose replacement method:");
        System.out.println("1. Survival of the fittest");
        System.out.println("2. Steady-State");
        System.out.println("3. Elitism");

        int replaceChoice = sc.nextInt();
        ReplacementStrategy<?> replacement;

        if (replaceChoice == 1) {
            replacement = new SurvivalOfFittestReplacement();
        } else if (replaceChoice == 2) {
            replacement = new SteadyStateReplacement();
        } else if (replaceChoice == 3) {
            replacement = new ElitismReplacement();
        }
        
        // =============================================================
        // Chromosome representation
        // =============================================================
        System.out.println("Choose chromosome representation:");
        System.out.println("1. Binary");
        System.out.println("2. Integer");
        System.out.println("3. Floating Point");
        int chromChoice = sc.nextInt();

        System.out.print("Enter number of genes (jobs): ");
        int geneLength = sc.nextInt();

        ChromosomeFactory factory = new ChromosomeFactory();

        type = switch (chromChoice) {
            case 1 -> ChromosomeType.BINARY;
            case 2 -> ChromosomeType.INTEGER;
            default -> ChromosomeType.FLOATING_POINT;
        };

        Chromosome<?> chromosome;
        if (type == ChromosomeType.BINARY) {
            chromosome = factory.createChromosome(type, geneLength, null, null);
        } else if (type == ChromosomeType.INTEGER) {
            System.out.print("Enter minimum integer gene value: ");
            int min = sc.nextInt();
            System.out.print("Enter maximum integer gene value: ");
            int max = sc.nextInt();
            chromosome = factory.createChromosome(type, geneLength, min, max);
        } else {
            System.out.print("Enter minimum floating-point gene value: ");
            double min = sc.nextDouble();
            System.out.print("Enter maximum floating-point gene value: ");
            double max = sc.nextDouble();
            chromosome = factory.createChromosome(type, geneLength, min, max);
        }

        // =============================================================
        // Job & Machine setup
        // =============================================================
        System.out.print("Enter number of jobs: ");
        int numJobs = sc.nextInt();

        System.out.print("Enter number of machines: ");
        int numMachines = sc.nextInt();

        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < numJobs; i++) {
            System.out.print("Enter processing time for Job " + i + ": ");
            int time = sc.nextInt();
            jobs.add(new Job(i, time));
        }

        List<Machine> machines = new ArrayList<>();
        for (int i = 0; i < numMachines; i++) {
            machines.add(new Machine(i));
        }

        // =============================================================
        // Fitness and GA setup
        // =============================================================
        FitnessFunction<Integer> fitness = new JobSchedulingFitness(jobs, machines);

        GeneticAlgorithm<Integer> ga = new GeneticAlgorithm<>(
                factory,
                selection,
                crossover,
                mutation,
                replacement,
                fitness,
                params
        );

        return ga;
    }
}
