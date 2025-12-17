package softcomputing.EnergyConsumptionProblem;

import java.io.*;
import java.util.*;

public class CSVLoader {
    private static final int[] FEATURE_INDICES = {
        2, // Global_active_power
        4, // Voltage
        5, // Global_intensity
        6, // Sub_metering_1
        7, // Sub_metering_2
        8  // Sub_metering_3
    };

    public static double[][] load(String path) throws IOException {
        List<double[]> rows = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = br.readLine();

        while ((line = br.readLine()) != null) {
            String delimiter = line.contains(";") ? ";" : ",";
            String[] tokens = line.split(delimiter);

            double[] row = new double[FEATURE_INDICES.length];
            boolean valid = true;

            for (int i = 0; i < FEATURE_INDICES.length; i++) {
                String value = tokens[FEATURE_INDICES[i]];
                if (value.equals("?")) {
                    valid = false;
                    break;
                }
                row[i] = Double.parseDouble(value);
            }

            if (valid) {
                rows.add(row);
            }
        }
        br.close();

        return rows.toArray(new double[0][]);
    }
}
