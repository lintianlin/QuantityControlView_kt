package com.sinfeeloo.library

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.annotation.StyleRes
import android.text.TextUtils
import android.view.View
import com.sinfeeloo.library.utils.SoftKeyboardUtils
import kotlinx.android.synthetic.main.dialog_setgoodscount.*


/**
 * EditCountDialog
 *
 * @author: mhj
 * @date: 2018/1/9 16:04
 * 可编辑数量框
 */
class EditCountDialog : Dialog {

    private var count: Int = 0
    private var listener: OnDialogListener? = null

    constructor(context: Context, @StyleRes themeResId: Int, count: Int, listener: OnDialogListener) : super(context, themeResId) {
        this@EditCountDialog.listener = listener
        this@EditCountDialog.count = count
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_setgoodscount)
        count_tv.setText(count.toString())
        cancel_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                SoftKeyboardUtils.hideSoftInput(context, count_tv)
                dismiss()
            }
        })

        ok_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (!TextUtils.isEmpty(count_tv.text.toString())) {
                    listener?.onGoodsCount(count_tv.text.toString().toInt())
                } else {
                    listener?.onGoodsCount(0)
                }
                SoftKeyboardUtils.hideSoftInput(context, count_tv)
                dismiss()
            }

        })

    }

    interface OnDialogListener {
        fun onGoodsCount(count: Int)
    }
}