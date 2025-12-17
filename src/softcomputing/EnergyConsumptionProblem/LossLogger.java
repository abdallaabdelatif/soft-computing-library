package softcomputing.EnergyConsumptionProblem;

import java.io.*;
import java.util.List;

public class LossLogger {
    public static void save(List<Double> lossHistory, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write("Epoch,Loss\n");
        for (int i = 0; i < lossHistory.size(); i++) {
            writer.write(i + "," + lossHistory.get(i));
            writer.newLine();
        }
        writer.close();
    }
}
