package com.example.sigacorfilkom.kontrol_remove_this_later

class KontrolSnackbar(
    showSnackbar: (String) -> Unit
) {
    private val _showSnackbar: (String) -> Unit

    init {
        this._showSnackbar = showSnackbar
    }
    fun showSnackbar(msg:String){
        _showSnackbar(msg)
    }
}