package com.example.aarieats;

import android.view.View;

import com.example.aarieats.models.MainMenuItems;

public interface ItemOnClickListener {

    void onItemClick(int position, MainMenuItems data, View imgView);
}
