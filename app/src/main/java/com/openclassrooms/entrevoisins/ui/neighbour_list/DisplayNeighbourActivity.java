package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DisplayNeighbourActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;


    @BindView(R.id.display_neighbour_avatar)
    ImageView nAvatar;
    @BindView(R.id.display_neighbour_name_textview)
    TextView nName;
    @BindView(R.id.display_neighbour_phone_number_textview)
    TextView nPhone;
    @BindView(R.id.display_neighbour_address_textview)
    TextView nAddress;
    @BindView(R.id.display_neighbour_social_network_textview)
    TextView nSocialNet;
    @BindView(R.id.display_neighbour_about_me_textview)
    TextView nAboutMe;
    @BindView(R.id.display_neighbour_return_previous_activity)
    ImageView nReturn;
    @BindView(R.id.MarkAsFavorite)
    FloatingActionButton nFab;
    Boolean nIsFavorite;
    Neighbour neighbour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_neighbour);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        neighbour = intent.getParcelableExtra("neighbour");
        mApiService = DI.getNeighbourApiService();
        Glide.with(this).load(neighbour.getAvatarUrl()).into(nAvatar);
        nName.setText(neighbour.getName());
        nPhone.setText(neighbour.getPhoneNumber());
        nAddress.setText(neighbour.getAddress());
        nAboutMe.setText(neighbour.getAboutMe());
        nSocialNet.setText("www.facebook.fr/"+neighbour.getName());
        nIsFavorite = neighbour.getIsFavorite();
        SetFabStarColor();
    }

    @OnClick(R.id.display_neighbour_return_previous_activity)
    void NavBack() {

        finish();}


    /**
     * Display if neighbour is a Favorite
     */
    void SetFabStarColor() {
        if (nIsFavorite) {
            nFab.setImageTintList(getResources().getColorStateList(R.color.colorYellow));
        } else {
            nFab.setImageTintList(getResources().getColorStateList(R.color.colorWhite));
        }
    }

    @OnClick(R.id.MarkAsFavorite)

    void SetFavorite(){
        mApiService.changeStatusFavorite(neighbour);
        nIsFavorite = !nIsFavorite;
        SetFabStarColor();
    }
}