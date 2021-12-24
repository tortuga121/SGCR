package Model;

import java.time.LocalDateTime;

public interface IStep {
    double getCost();
    LocalDateTime getTime();
    String getDescription();
}
