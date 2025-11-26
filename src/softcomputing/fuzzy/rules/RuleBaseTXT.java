package softcomputing.fuzzy.rules;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RuleBaseTXT {

    public static void saveToTxt(RuleBase ruleBase, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (FuzzyRule rule : ruleBase.getRules()) {
                StringBuilder sb = new StringBuilder();
                rule.getAntecedent().getConditions().forEach((var, set) -> sb.append(var + "=" + set + ","));
                sb.setLength(sb.length() - 1); 
                sb.append(";");
              
                sb.append(rule.getConsequent().getOutputVariable() + "=" + rule.getConsequent().getOutputFuzzySet() + ";");
                sb.append("enabled=" + rule.isEnabled() + ";weight=" + rule.getWeight());
                writer.write(sb.toString());
                writer.newLine();
            }
        }
    }

    public static RuleBase loadFromTxt(String filename) throws IOException {
        RuleBase ruleBase = new RuleBase();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Map<String, String> antecedentMap = new HashMap<>();
                String[] conditions = parts[0].split(",");
                for (String cond : conditions) {
                    String[] kv = cond.split("=");
                    antecedentMap.put(kv[0], kv[1]);
                }
                RuleAntecedent antecedent = new RuleAntecedent(antecedentMap);
                String[] consKv = parts[1].split("=");
                RuleConsequent consequent = new RuleConsequent(consKv[0], consKv[1]);
                boolean enabled = Boolean.parseBoolean(parts[2].split("=")[1]);
                double weight = Double.parseDouble(parts[3].split("=")[1]);

                FuzzyRule rule = new FuzzyRule(antecedent, consequent);
                rule.setEnabled(enabled);
                rule.setWeight(weight);
                ruleBase.addRule(rule);
            }
        }
        return ruleBase;
    }
}
