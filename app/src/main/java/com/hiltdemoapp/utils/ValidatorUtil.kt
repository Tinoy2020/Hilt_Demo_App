package com.hiltdemoapp.utils

class ValidatorUtil {

    companion object {
        fun checkInputValid(name: String?): Boolean {
            if (name == null) return false
            if (name.trim().isEmpty()) return false
            return true
        }
    }
}