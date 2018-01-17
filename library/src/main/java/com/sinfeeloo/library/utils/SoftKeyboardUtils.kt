package com.sinfeeloo.library.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * @author: mhj
 * @date: 2018/1/17 16:55
 * @desc:软键盘控制工具类
 */
class SoftKeyboardUtils {

    companion object Factory {
        /**
         * 显示软键盘
         */
        fun showSoftInput(context: Context, view: View) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        /**
         * 隐藏软键盘
         */
        fun hideSoftInput(context: Context, view: View) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0) //强制隐藏键盘
        }

        /**
         * 判断软键盘是否打开
         */
        fun isShowSoftInput(context: Context): Boolean {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            //获取状态信息
            return imm.isActive
        }
    }

}