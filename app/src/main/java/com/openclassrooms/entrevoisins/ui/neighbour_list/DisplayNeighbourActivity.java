package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DisplayNeighbourActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_neighbour);

        String nameNeighbour = getIntent().getStringExtra("Name_Neighbour");
        String addressNeighbour = getIntent().getStringExtra("Address_Neighbour");
        String phoneNumberNeighbour= getIntent().getStringExtra("Phone_Number");
        String aboutNeighbour = getIntent().getStringExtra("About_Me");
        String avatarNeighbour = getIntent().getStringExtra("Avatar_neighbour");
        String socialNetNeighbour = ("www.facebook.fr/"+nameNeighbour);

        ButterKnife.bind(this);

        //nAvatar.setImageURI(Uri.parse(avatarNeighbour));
        Glide.with(this).load(avatarNeighbour).into(nAvatar);
        nName.setText(nameNeighbour);
        nPhone.setText(phoneNumberNeighbour);
        nAddress.setText(addressNeighbour);
        nAboutMe.setText(aboutNeighbour);
        nSocialNet.setText(socialNetNeighbour);

    }
}