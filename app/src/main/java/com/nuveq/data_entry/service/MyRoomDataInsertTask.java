package com.nuveq.data_entry.service;

import android.os.AsyncTask;
import android.util.Log;

import com.nuveq.data_entry.appdata.room.RoomDataRepository;
import com.nuveq.data_entry.appdata.room.TrackingPost;

class MyRoomDataInsertTask extends AsyncTask<TrackingPost, Void, Long> {
    RoomDataRepository roomDataRepository;

    public MyRoomDataInsertTask(RoomDataRepository roomDataRepository) {
        this.roomDataRepository = roomDataRepository;
    }


    @Override
    protected Long doInBackground(TrackingPost... trackingPosts) {
        long id = roomDataRepository.insertTrackingData(trackingPosts[0]);
        Log.e("run", "location data save in local:" + id);
        return id;
    }

    @Override
    protected void onPostExecute(Long integer) {
        super.onPostExecute(integer);
    }
}
