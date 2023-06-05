package com.openclassrooms.entrevoisins.utils;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;

public class NoMoreFavoriteViewAction implements ViewAction {
    @Override
    public String getDescription() { return "Click on favorite button";}

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.item_list_fav_button);
        // Maybe check for null
        button.performClick();

    }
}
