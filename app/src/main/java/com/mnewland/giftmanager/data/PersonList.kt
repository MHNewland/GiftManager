package com.mnewland.giftmanager.data

import com.mnewland.giftmanager.model.Person

object PersonList {
    private val personList: MutableList<Person> = emptyList<Person>().toMutableList()

    val defaultPerson =
        if (getPersonList().isEmpty()) {
            Person()
        } else {
            getPersonList()[0]
        }

    fun getPersonList(): List<Person> {
        return personList
    }

}