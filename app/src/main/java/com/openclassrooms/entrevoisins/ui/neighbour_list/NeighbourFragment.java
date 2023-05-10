package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.OnClickNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class NeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
    public static final String KEY_POSITION = "position";
    private static final int REQUEST_UPDATE_NEIGHBOUR = 1 ;
    private int position;

    FragmentActivity listener;


    /**
     * Create and return a new instance
     *
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(int position) {
        NeighbourFragment fragment = new NeighbourFragment();
        Bundle args = new Bundle(); // ajout
        args.putInt(KEY_POSITION, position); //ajout
        fragment.setArguments(args); //ajout

        return fragment;
    }
    /**
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DisplayNeighbourActivity){
            this.listener = (FragmentActivity) context;
        }
    }
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
        if (getArguments() != null) {
            position = getArguments().getInt(KEY_POSITION); //ajout
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }

    /**
     * Init the List of neighbours
     *
     * mise place du view pager
     */
    private void initList() {
        //int  position = getArguments().getInt(KEY_POSITION, -1);
        switch(position){
            case 0:
                mNeighbours = mApiService.getNeighbours();
                break;
            case 1:
                mNeighbours = mApiService.getFavoriteNeighbours();
                break;
        }
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours));

    }

    @Override
    public void onResume() {
        super.onResume();
        initList();

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }

    /**
     * update list after a favorite status change
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_UPDATE_NEIGHBOUR && resultCode == ListNeighbourActivity.RESULT_OK) {
            Neighbour updatedNeighbour = data.getParcelableExtra("updated_neighbour");
            int index = mNeighbours.indexOf(updatedNeighbour);
            if (index !=-1) {
                mNeighbours.set(index, updatedNeighbour);
                MyNeighbourRecyclerViewAdapter adapter = (MyNeighbourRecyclerViewAdapter) mRecyclerView.getAdapter();
                adapter.notifyItemChanged(index);

            }
        }
    }




}

