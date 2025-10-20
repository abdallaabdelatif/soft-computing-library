package softcomputing.makespanProblem;

import java.util.ArrayList;
import java.util.List;

public class Machine {
    private final int id;
    private final List<Job> assignedJobs = new ArrayList<>();

    public Machine(int id) {
        this.id = id;
    }

    public void assignJob(Job job) {
        assignedJobs.add(job);
    }

    public int getTotalProcessingTime() {
        int makespan = 0;
        for(int i = 0 ; i < assignedJobs.size() ; i++){
            Job currJob = assignedJobs.get(i);
            makespan += currJob.getProcessingTime();
        }
        return makespan;
    }

    public List<Job> getAssignedJobs() {
        return assignedJobs;
    }

    @Override
    public String toString() {
        return "Machine{id=" + id + ", totalTime=" + getTotalProcessingTime() +
                ", jobs=" + assignedJobs + "}";
    }

}
