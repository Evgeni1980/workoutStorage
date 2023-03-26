package org.example.workoutstorage.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.workoutstorage.entities.Workout;

@Data
@NoArgsConstructor
public class WorkoutDto {

    private Long id;
    private String url;
    private String author;
    private String description;

    public WorkoutDto(Workout workout) {
        this.id = workout.getId();
        this.url = workout.getUrl();
        this.author = workout.getAuthor();
        this.description = workout.getDescription();
    }

}
