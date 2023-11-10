package com.example.sigacorfilkom.entity_remove_this_later

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class Admin {
    private var nip:String
    private var password:String

    constructor(
        nip:String,
        password:String
    ){
        this.nip = nip
        this.password = password
    }
    fun getNip() = nip

    fun authenticate() = callbackFlow<Boolean> {
        //TODO Authenticate admin data in DB
        awaitClose()
    }
}