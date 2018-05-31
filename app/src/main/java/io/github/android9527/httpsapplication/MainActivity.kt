package io.github.android9527.httpsapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import io.github.android9527.httpsapplication.okhttp.OkHttp3util
import io.github.android9527.httpsapplication.volley.YKNetInterface
import io.github.android9527.httpsapplication.volley.YKTrustManager

class MainActivity : AppCompatActivity(), Response.Listener<String?>, Response.ErrorListener {

    val url = "https://10.104.51.214:8443/Test/comments.json"
    val list = "https://mobile.caifuxq.com/app/loan/invest/transferprojectlist"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val view = findViewById<View>(R.id.btn_action_1)
        view.setOnClickListener {
            val request = StringRequest(Request.Method.GET, url, this@MainActivity, this@MainActivity)
            YKTrustManager.allowAllSSL(this@MainActivity)
            YKNetInterface.getInstance().addRequest(request)
        }

        findViewById<View>(R.id.okhttp_action_1).setOnClickListener {
            OkHttp3util().get(url)
        }
    }

    override fun onResponse(result: String?) {
        YKLogUtil.e("MainActivity", result)
    }

    override fun onErrorResponse(volleyError: VolleyError) {
        volleyError.printStackTrace()
        YKLogUtil.e("MainActivity", volleyError.stackTrace)
        YKLogUtil.e("MainActivity", volleyError.message)
    }
}
