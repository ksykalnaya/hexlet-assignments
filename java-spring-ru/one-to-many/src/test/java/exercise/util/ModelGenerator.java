package exercise.util;

import exercise.model.Task2;
import exercise.model.User;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ModelGenerator {
    private Model<Task2> taskModel;
    private Model<User> userModel;

    @Autowired
    private Faker faker;

    @PostConstruct
    private void init() {
        userModel = Instancio.of(User.class)
                .ignore(Select.field(User::getId))
                .ignore(Select.field(User::getTasks))
                .supply(Select.field(User::getName), () -> faker.name().fullName())
                .supply(Select.field(User::getEmail), () -> faker.internet().emailAddress())
                .toModel();


        taskModel = Instancio.of(Task2.class)
                .ignore(Select.field(Task2::getId))
                .supply(Select.field(Task2::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task2::getDescription), () -> faker.gameOfThrones().quote())
                .toModel();
    }
}
