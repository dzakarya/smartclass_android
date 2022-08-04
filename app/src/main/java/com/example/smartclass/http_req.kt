package com.example.smartclass

import android.widget.Toast
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.URL

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