package com.example.smartclass

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost

class http_req {
    var initUrl: String = "http://192.168.100.79:8080"

        // getter
        get() = field

        // setter
        set(value) {
            field = value
        }

    init {
        FuelManager.instance.basePath = initUrl
    }

    fun http_get(end_point: String ): String?{
        var res : String=""
        end_point.httpGet()
            .responseString { request, response, result ->
                res = result.toString()
            }
        return res
    }

    fun http_post(data: String, end_point: String): String?{
       var res : String = ""
        end_point.httpPost().
        header("Content-Type", "application/json").
        body(data).
        responseString { request, response, result ->
            res = result.toString()
        }
        return res
    }



}