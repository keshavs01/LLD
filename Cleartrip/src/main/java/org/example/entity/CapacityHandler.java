package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.exception.InvalidCapacityException;

@Data
@AllArgsConstructor
public class CapacityHandler {

    private final Integer maxCapacity;
    private Integer currentCapacity;

    public void validateAndIncreaseCapacity(Integer capacity){
        if(currentCapacity+capacity <= maxCapacity){
            this.currentCapacity+=capacity;
        } else{
            throw new InvalidCapacityException();
        }
    }

    public void validateAndDecreaseCapacity(Integer capacity){
        if(maxCapacity-capacity >= 0){
            this.currentCapacity-=capacity;
        } else{
            throw new InvalidCapacityException();
        }
    }
}
