package com.example.and_p_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.and_p_test.model.AlarmDTO
import com.example.and_p_test.repository.AlarmRepository
import com.google.firebase.firestore.ListenerRegistration


class AlarmViewModel: ViewModel() {

    private val alarmRepository = AlarmRepository()

    private var _alarmsLiveData = MutableLiveData<List<AlarmDTO>>()
    val alarmsLiveData: LiveData<List<AlarmDTO>>
        get() = _alarmsLiveData

    fun remove() {
        alarmRepository.remove()
    }

    fun getAll(destinationUid: String) {
        alarmRepository.getAll(destinationUid) { querySnapshot, _ ->
            val value = arrayListOf<AlarmDTO>()

            querySnapshot?.let { qs ->
                for(snapshot in qs.documents) {
                    val item = snapshot.toObject(AlarmDTO::class.java)
                    value.add(item!!)
                }
            }

            _alarmsLiveData.value = value
        }
    }

}