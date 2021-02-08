package com.nuveq.data_entry.service;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.nuveq.data_entry.appdata.room.RoomDataRepository;

public class RoomDataRetriveSaveToServerTask extends AsyncTask<Void, Void, Void> {
    RoomDataRepository roomDataRepository;
    Context context;
    int i = 0;
    DeleteDataTask deleteDataTask;
    Gson gson = new Gson();

    public RoomDataRetriveSaveToServerTask(RoomDataRepository roomDataRepository, Context context) {
        this.roomDataRepository = roomDataRepository;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        return null;
    }

    public void destroyTask() {
        if (deleteDataTask != null) {
            deleteDataTask.cancel(true);
            deleteDataTask = null;
        }
    }
}
