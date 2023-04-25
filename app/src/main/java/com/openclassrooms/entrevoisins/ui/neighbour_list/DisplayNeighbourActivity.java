package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.display_neighbour_return_previous_activity)
    ImageView nReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_neighbour);
        Intent intent = getIntent();
        if (intent != null){
            Neighbour neighbour = intent.getParcelableExtra("neighbour");
            if (neighbour != null){

                String nameNeighbour = neighbour.getName();
                String addressNeighbour = neighbour.getAddress();
                String phoneNumberNeighbour= neighbour.getPhoneNumber();
                String aboutNeighbour = neighbour.getAboutMe();
                String avatarNeighbour = neighbour.getAvatarUrl();
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
    }


    @OnClick(R.id.display_neighbour_return_previous_activity)
    void NavBack() {
        Intent ReturnPreviousActivityIntent = new Intent(this, ListNeighbourActivity.class);
        startActivity(ReturnPreviousActivityIntent);
    }




}