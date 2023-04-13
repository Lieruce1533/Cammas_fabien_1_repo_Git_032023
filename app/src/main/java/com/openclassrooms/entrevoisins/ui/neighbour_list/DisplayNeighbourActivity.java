package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.avatar)
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_neighbour);
        ButterKnife.bind(this);

    }
}