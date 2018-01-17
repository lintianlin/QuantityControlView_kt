package com.sinfeeloo.quantitycontrolview_kt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.sinfeeloo.library.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mList = ArrayList<AmountBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        for (i in 0..19) {
            val bean = AmountBean()
            bean.count = 0
            mList.add(bean)
        }
        val adapter = QuantityControlAdapter(this, mList)
        val layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter

        tv_btn?.setOnClickListener {
            val str = StringBuilder()
            for (bean in mList) {
                str.append(bean.count.toString() + ",")
            }
            ToastUtils.showToast(applicationContext, str.toString())
        }
    }
}
