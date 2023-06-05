package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void createNeighbourWithSuccess(){
        Neighbour neighbourToAdd = new Neighbour(062023,"Flanagan","https://i.pravatar.cc/150?u=153340","Saint-Pierre-du-Mont ; 5km",
                "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..",false);
        List<Neighbour> neighbours = service.getNeighbours();
        assertEquals(12,neighbours.size());
        assertFalse(neighbours.contains(neighbourToAdd));
        service.createNeighbour(neighbourToAdd);
        assertEquals(13, neighbours.size());
        assertTrue(neighbours.contains(neighbourToAdd));
    }

    @Test
    public void changeStatusFavoriteWithSuccess(){
        Neighbour neighbourToUpdateStatus = service.getNeighbours().get(0);
        Boolean statusFavoriteBefore = neighbourToUpdateStatus.getIsFavorite();
        service.changeStatusFavorite(neighbourToUpdateStatus);
        assertTrue(neighbourToUpdateStatus.getIsFavorite() == !statusFavoriteBefore);

    }

    @Test
    public void outOfFavoriteWithSuccess(){
        Neighbour neighbourToSetOut = service.getNeighbours().get(0);
        neighbourToSetOut.setIsFavorite(true);
        service.outOfFavorite(neighbourToSetOut);
        assertFalse(neighbourToSetOut.getIsFavorite());
    }

}
