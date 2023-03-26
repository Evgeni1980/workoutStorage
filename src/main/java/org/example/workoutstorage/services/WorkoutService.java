package org.example.workoutstorage.services;

import lombok.RequiredArgsConstructor;
import org.example.workoutstorage.dtos.WorkoutDto;
import org.example.workoutstorage.entities.Workout;
import org.example.workoutstorage.exceptions.ResourceNotFoundException;
import org.example.workoutstorage.repositories.WorkoutRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public Page<Workout> findAll(int pageIndex, int pageSize) {
        return workoutRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public Optional<Workout> findById(Long id) {
        return workoutRepository.findById(id);
    }


    public void save(Workout workout) {
        workoutRepository.save(workout);
    }



    public void updateWorkoutFromDto (WorkoutDto workoutDto) {
        Workout workout = findById(workoutDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Тренировки с id=" + workoutDto.getId() + " не существует"));
        workout.setUrl(workoutDto.getUrl());
        workout.setAuthor(workoutDto.getAuthor());
        workout.setDescription(workoutDto.getDescription());
        workoutRepository.save(workout);
    }

}
