package softcomputing.makespanProblem;

public class Job {
    private final int id;
    private final int processingTime;

    public Job(int id, int processingTime) {
        this.id = id;
        this.processingTime = processingTime;
    }

    public int getId() {
        return id;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    @Override
    public String toString() {
        return "Job{id=" + id + ", time=" + processingTime + "}";
    }
}
