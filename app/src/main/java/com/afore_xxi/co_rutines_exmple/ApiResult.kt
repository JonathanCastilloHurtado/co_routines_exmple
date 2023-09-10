package com.afore_xxi.co_rutines_exmple

import com.google.gson.annotations.SerializedName

class ApiResult {
    @SerializedName("StatusCode")
    var StatusCode: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }

    @SerializedName("StatusMessage")
    var StatusMessage: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }

}