package com.Dental.Check.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.Dental.Check.R;

import okhttp3.WebSocket;

public class ChatActivity extends AppCompatActivity {
    private WebSocket webSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }
}