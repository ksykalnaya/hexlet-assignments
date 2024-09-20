package exercise.daytime;
import jakarta.annotation.PostConstruct;

public class Night implements Daytime {
    private String name = "night";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    private void init(){
        System.out.println("Bean 'Night' initialized");
    }
    // END
}
