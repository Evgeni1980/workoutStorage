package org.example.workoutstorage.controllers;

import lombok.RequiredArgsConstructor;
import org.example.workoutstorage.dtos.WorkoutDto;
import org.example.workoutstorage.entities.Workout;
import org.example.workoutstorage.exceptions.ResourceNotFoundException;
import org.example.workoutstorage.exceptions.ValidationException;
import org.example.workoutstorage.services.WorkoutService;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping
    public Page<WorkoutDto> findAll(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return workoutService.findAll(pageIndex - 1, 6).map(WorkoutDto::new);
    }

    @GetMapping("/{id}")
    public WorkoutDto findById(@PathVariable Long id) {
        return new WorkoutDto(workoutService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Тренировки с id=" + id + " не существует")));
    }


    @PostMapping
    public WorkoutDto addWorkout(@RequestBody @Validated WorkoutDto workoutDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        Workout workout = new Workout();
        workout.setId(workoutDto.getId());
        workout.setUrl(workoutDto.getUrl());
        workout.setAuthor(workoutDto.getAuthor());
        workout.setDescription(workoutDto.getDescription());
        workoutService.save(workout);
        return new WorkoutDto(workout);
    }


    @PutMapping
    public void updateWorkout(@RequestBody WorkoutDto workoutDto) {
        workoutService.updateWorkoutFromDto(workoutDto);

    }

}
