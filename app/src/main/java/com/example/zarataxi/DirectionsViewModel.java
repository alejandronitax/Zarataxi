package com.example.zarataxi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class DirectionsViewModel extends ViewModel {

    private MutableLiveData<List<Expedient>> expedients;
    public LiveData<List<Expedient>> getUsers() {
        if (expedients == null) {
            expedients = new MutableLiveData<List<Expedient>>();
            loadUsers();
        }
        return expedients;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
    }

}
