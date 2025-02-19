package com.iplandcensoranalyzer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IPLCensorAnalyzer {

    // Censorship Rules
    private static String maskTeamName(String teamName) {
        return teamName.replaceAll("\\B\\w+", "***");
    }

    private static String redactPlayerOfMatch(String player) {
        return "REDACTED";
    }

    // Process JSON Data
    private static List<Match> processJsonData(String jsonFilePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Match> matches = mapper.readValue(Paths.get(jsonFilePath).toFile(), mapper.getTypeFactory().constructCollectionType(List.class, Match.class));

        for (Match match : matches) {
            match.setTeam1(maskTeamName(match.getTeam1()));
            match.setTeam2(maskTeamName(match.getTeam2()));
            match.setWinner(maskTeamName(match.getWinner()));
            match.setPlayer_of_match(redactPlayerOfMatch(match.getPlayer_of_match()));

            Map<String, Integer> censoredScores = new HashMap<>();
            for (Map.Entry<String, Integer> entry : match.getScore().entrySet()) {
                censoredScores.put(maskTeamName(entry.getKey()), entry.getValue());
            }
            match.setScore(censoredScores);
        }

        return matches;
    }

    // Process CSV Data
    private static List<Match> processCsvData(String csvFilePath) throws IOException, CsvValidationException {
        List<Match> matches = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] header = reader.readNext(); // Skip header
            String[] line;
            while ((line = reader.readNext()) != null) {
                Match match = new Match();
                match.setMatch_id(Integer.parseInt(line[0]));
                match.setTeam1(maskTeamName(line[1]));
                match.setTeam2(maskTeamName(line[2]));
                match.setScore(new HashMap<>());
                match.getScore().put(maskTeamName(line[1]), Integer.parseInt(line[3]));
                match.getScore().put(maskTeamName(line[2]), Integer.parseInt(line[4]));
                match.setWinner(maskTeamName(line[5]));
                match.setPlayer_of_match(redactPlayerOfMatch(line[6]));
                matches.add(match);
            }
        }

        return matches;
    }

    // Write JSON Output
    private static void writeJsonOutput(List<Match> matches, String jsonOutputPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(Paths.get(jsonOutputPath).toFile(), matches);
    }

    // Write CSV Output
    private static void writeCsvOutput(List<Match> matches, String csvOutputPath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvOutputPath))) {
            String[] header = {"match_id", "team1", "team2", "score_team1", "score_team2", "winner", "player_of_match"};
            writer.writeNext(header);

            for (Match match : matches) {
                String[] line = {
                        String.valueOf(match.getMatch_id()),
                        match.getTeam1(),
                        match.getTeam2(),
                        String.valueOf(match.getScore().get(match.getTeam1())),
                        String.valueOf(match.getScore().get(match.getTeam2())),
                        match.getWinner(),
                        match.getPlayer_of_match()
                };
                writer.writeNext(line);
            }
        }
    }

    public static void main(String[] args) {
        String jsonInputPath = "src/main/java/com/iplandcensoranalyzer/ipl_matches.json";
        String csvInputPath = "src/main/java/com/iplandcensoranalyzer/ipl_matches.csv";
        String jsonOutputPath = "src/main/java/com/iplandcensoranalyzer/ipl_matches_censored.json";
        String csvOutputPath = "src/main/java/com/iplandcensoranalyzer/ipl_matches_censored.csv";

        try {
            // Process JSON Data
            List<Match> jsonMatches = processJsonData(jsonInputPath);
            writeJsonOutput(jsonMatches, jsonOutputPath);

            // Process CSV Data
            List<Match> csvMatches = processCsvData(csvInputPath);
            writeCsvOutput(csvMatches, csvOutputPath);

            System.out.println("Censored JSON and CSV files generated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}