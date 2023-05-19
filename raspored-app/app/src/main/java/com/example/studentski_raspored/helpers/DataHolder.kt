package com.example.studentski_raspored.helpers

object DataHolder {
    private lateinit var userSubjects: String
    private lateinit var userId: String

    fun getUserSubjects(): String {
        return this.userSubjects
    }

    fun setUserSubjects(username: String) {
        this.userSubjects = username
    }

    fun getUserId(): String {
        return this.userId
    }

    fun setUserId(id: String) {
        this.userId = id
    }
}
