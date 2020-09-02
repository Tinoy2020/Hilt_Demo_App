package com.hiltdemoapp.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.hiltdemoapp.data.model.UsersList
import com.hiltdemoapp.data.repository.ApiRepository
import com.hiltdemoapp.utils.NetworkHelper
import com.hiltdemoapp.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val apiRepository: ApiRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _users = MutableLiveData<Resource<UsersList>>()
    val users: LiveData<Resource<UsersList>>
        get() = _users

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(data = null))
            if (networkHelper.isNetworkConnected()) {
                apiRepository.getUsers("1").let {
                    if (it.isSuccessful) {
                        _users.postValue(Resource.success(data = it.body()))
                    } else {
                        _users.postValue(Resource.error(msg = it.errorBody().toString(), data = null))
                    }
                }
            } else _users.postValue(Resource.error(msg = "No internet connection", data = null))
        }
    }
}
