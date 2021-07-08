package com.epam.training.tasks.stoss.entities;

/**
 * The interface declares it's successors must have an id field
 *
 * @author Anton Tomashevich
 */
public interface Entity {

    /**
     * The method returns an identifier of object
     *
     * @return Long - unique identifier of instance
     */
    Long getId();

}
