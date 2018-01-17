package com.sinfeeloo.library.utils

import android.content.Context
import android.widget.Toast

/**
 * @author: mhj
 * @date: 2018/1/17 17:03
 * @desc:
 */
object ToastUtils {

    private var toast: Toast? = null

    /**
     * 显示单例的吐司，能连续快速弹的吐司
     *
     * @param text
     */
    fun showToast(context: Context, text: String) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        }
        toast!!.setText(text)
        toast!!.show()
    }
}