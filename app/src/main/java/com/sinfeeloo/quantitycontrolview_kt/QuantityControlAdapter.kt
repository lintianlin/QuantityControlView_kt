package com.sinfeeloo.quantitycontrolview_kt

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sinfeeloo.library.Constant
import com.sinfeeloo.library.OnAmountButtonListener
import com.sinfeeloo.library.QuantityControlView
import kotlinx.android.synthetic.main.item_quantity_contorl.view.*
import java.util.*

/**
 * @author: mhj
 * @date: 2018/1/17 18:41
 * @desc:
 */
class QuantityControlAdapter(context: Context, list: ArrayList<AmountBean>) : RecyclerView.Adapter<QuantityControlAdapter.QuantityControlViewHolder>() {
    private var mDatas = ArrayList<AmountBean>()
    private var mContext: Context? = null
    var listener: OnChangeCountListener? = null

    init {
        this.mDatas = list
        this.mContext = context
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun onBindViewHolder(holder: QuantityControlViewHolder?, position: Int) {
        var item = mDatas[position]
        holder?.quantityControlView?.setAmount(item.count)
        holder?.quantityControlView?.setMaxAmount(5)
        holder?.quantityControlView?.setOnAmountButtonListener(object : OnAmountButtonListener {
            override fun onAmountChange(type: Int, count: Int) {
                if (Constant.DECREASE === type) {

                } else if (Constant.INCREASE === type) {

                } else {

                }
                item.count = count
            }

        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): QuantityControlViewHolder {
        val viewHoler = QuantityControlViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_quantity_contorl, parent, false))
        return viewHoler
    }


    class QuantityControlViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var quantityControlView: QuantityControlView? = null

        init {
            quantityControlView = itemView?.quantity_control_view
        }
    }


    interface OnChangeCountListener {
        fun onModifyCount(position: Int, bean: AmountBean)
    }

    fun setOnChangeCountListener(onInCountListener: OnChangeCountListener) {
        this.listener = onInCountListener
    }
}