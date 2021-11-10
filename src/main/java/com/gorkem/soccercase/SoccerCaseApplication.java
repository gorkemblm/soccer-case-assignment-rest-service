package com.gorkem.soccercase;

import com.gorkem.soccercase.constants.Message;
import com.gorkem.soccercase.model.Footballer;
import com.gorkem.soccercase.model.Nationality;
import com.gorkem.soccercase.model.Position;
import com.gorkem.soccercase.model.Team;
import com.gorkem.soccercase.repository.FootballerRepository;
import com.gorkem.soccercase.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@SpringBootApplication
public class SoccerCaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(SoccerCaseApplication.class, args);
    }
}

@Component
class CreateDataCommandLineRunner implements CommandLineRunner {

    private final TeamRepository teamRepository;
    private final FootballerRepository footballerRepository;
    private static List<String> tobeUsedIds;
    private static Random random;

    @Value("${teams_file_name}")
    protected String teamsFileName;

    @Value("${footballers_file_name}")
    protected String footballersFileName;

    CreateDataCommandLineRunner(TeamRepository teamRepository, FootballerRepository footballerRepository) {
        this.teamRepository = teamRepository;
        this.footballerRepository = footballerRepository;
    }

    @Override
    public void run(String... args) {
        tobeUsedIds = new ArrayList<>();
        random = new Random();

        for (String filePath : Arrays.asList(teamsFileName, footballersFileName)) {

            insertData(filePath, random, teamRepository, footballerRepository);
        }
    }

    private static void insertData(String filePath, Random random, TeamRepository teamRepository, FootballerRepository footballerRepository) {
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);

            String nextString;

            while (myReader.hasNextLine()) {
                if (filePath.contains("teams")) {
                    nextString = myReader.nextLine();
                    String teamName = nextString;

                    Team team = Team.builder()
                            .name(teamName)
                            .build();

                    tobeUsedIds.add(teamRepository.save(team).getId());

                } else if (filePath.contains("footballers")) {
                    nextString = myReader.nextLine();

                    List<String> nameAndSurname = List.of(nextString.split(" "));

                    Footballer footballer = Footballer.builder()
                            .firstName(nameAndSurname.get(0))
                            .lastName(nameAndSurname.get(1))
                            .age(random.ints(18, 40)
                                    .findFirst()
                                    .getAsInt())
                            .nationality(Arrays.stream(Nationality.values()).toList().get(random.nextInt(Nationality.values().length)))
                            .position(Arrays.stream(Position.values()).toList().get(random.nextInt(Position.values().length)))
                            .team(teamRepository.getById((tobeUsedIds.get(random.nextInt(tobeUsedIds.size())))))
                            .build();

                    footballerRepository.save(footballer);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(Message.PUBLIC_AN_ERROR_OCCURED);
            e.printStackTrace();
        }
    }
}
