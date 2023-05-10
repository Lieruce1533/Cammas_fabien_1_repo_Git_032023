package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    private List<Neighbour> favoriteNeighbours;
    {
        List<Neighbour> list = new ArrayList<>();
        for (Neighbour neighbour : neighbours) {
            if (neighbour.getIsFavorite()) {
                list.add(neighbour);
            }
        }
        favoriteNeighbours = list;

    }


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
    public List<Neighbour> getFavoriteNeighbours() {return favoriteNeighbours;}

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


}
