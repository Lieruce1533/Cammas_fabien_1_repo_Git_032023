package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class OnClickNeighbourEvent {


    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */

    public OnClickNeighbourEvent (Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
