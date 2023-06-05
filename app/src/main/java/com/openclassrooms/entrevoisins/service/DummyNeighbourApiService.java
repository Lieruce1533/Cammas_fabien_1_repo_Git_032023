package com.openclassrooms.entrevoisins.service;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> list = new ArrayList<>();
        for (Neighbour neighbour : neighbours) {
            if (neighbour.getIsFavorite()) {
                Log.d("DEBUG", neighbour.getName());
                list.add(neighbour);
            }
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }



    @Override
    public void changeStatusFavorite(Neighbour neighbour){
         for (Neighbour neighbourFromList : neighbours) {
            if (neighbour.getId() == neighbourFromList.getId()) {
                neighbourFromList.setIsFavorite(!neighbourFromList.getIsFavorite());
            }
         }
    }

    @Override
    public void outOfFavorite(Neighbour neighbour){
        neighbour.setIsFavorite(false);
    }
}
