package com.sinfeeloo.library

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.sinfeeloo.library.utils.ToastUtils
import kotlinx.android.synthetic.main.layout_quantity_control.view.*

/**
 * @author: mhj
 * @date: 2018/1/17 17:56
 * @desc:
 */
class QuantityControlView : LinearLayout, View.OnClickListener {

    private var amount = 0 //购买数量
    private var minAmount = 0 //最小数量
    private var maxAmount = 999999 //商品库存
    private val minWarnStr = "当前已是最小购买数量"
    private val maxWarnStr = "已达到最大购买数量"

    //是否可以点击编辑
    private var editable = false
    private var tvTextSize: Int = 0
    private var tvTextColor: Int = 0
    private var bg: Int = 0
    private var decrease_bg: Int = 0
    private var increase_bg: Int = 0
    private var unable_increase_bg: Int = 0
    private var unable_decrease_bg: Int = 0

    private var onAmountButtonListener: OnAmountButtonListener? = null
    internal lateinit var editCountDialog: EditCountDialog

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        LayoutInflater.from(context).inflate(R.layout.layout_quantity_control, this)
        btnDecrease.setOnClickListener(this)
        btnIncrease.setOnClickListener(this)
        tvAmount.setOnClickListener(this)

        val obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.QuantityControlView)
        var btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.QuantityControlView_qc_btnWidth, LayoutParams.WRAP_CONTENT)
        val btnHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.QuantityControlView_qc_btnHeight, LinearLayout.LayoutParams.WRAP_CONTENT)
        val tvWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.QuantityControlView_qc_tvWidth, 80)
        tvTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.QuantityControlView_qc_tvTextSize, 15)
        tvTextColor = obtainStyledAttributes.getColor(R.styleable.QuantityControlView_qc_tvTextColor, resources.getColor(R.color.textColor))
        editable = obtainStyledAttributes.getBoolean(R.styleable.QuantityControlView_qc_editable, false)
        minAmount = obtainStyledAttributes.getInteger(R.styleable.QuantityControlView_qc_minAmount, 0)
        bg = obtainStyledAttributes.getResourceId(R.styleable.QuantityControlView_qc_bg, R.drawable.bg_default)
        decrease_bg = obtainStyledAttributes.getResourceId(R.styleable.QuantityControlView_qc_decrease_bg, R.mipmap.icon_decrease)
        increase_bg = obtainStyledAttributes.getResourceId(R.styleable.QuantityControlView_qc_increase_bg, R.mipmap.icon_increase)
        unable_decrease_bg = obtainStyledAttributes.getResourceId(R.styleable.QuantityControlView_qc_decrease_unable_bg, R.mipmap.icon_unable_decrease)
        unable_increase_bg = obtainStyledAttributes.getResourceId(R.styleable.QuantityControlView_qc_increase_unable_bg, R.mipmap.icon_unable_increase)
        obtainStyledAttributes.recycle()

        //设置按钮属性
        val btnParams = LayoutParams(btnWidth, btnHeight)
        btnDecrease.layoutParams = btnParams
        btnIncrease.layoutParams = btnParams
        ll_quantity_control.setBackgroundResource(bg)
        //检测按钮状态
        checkButtonStatus();
        //设置文字属性
        val textParams = LayoutParams(tvWidth, LayoutParams.MATCH_PARENT)
        //是否可以编辑
        if (editable) {//可以编辑
            tvAmount.visibility = View.GONE
            etAmount.visibility = View.VISIBLE
            //设置文字属性
            etAmount.setText(amount.toString())
            etAmount.layoutParams = textParams
            etAmount.textSize = tvTextSize.toFloat()
            etAmount.setTextColor(tvTextColor)
        } else {//不可编辑
            tvAmount.visibility = View.VISIBLE
            etAmount.visibility = View.GONE
            //设置文字属性
            tvAmount.setText(amount.toString())
            tvAmount.layoutParams = textParams
            tvAmount.textSize = tvTextSize.toFloat()
            tvAmount.setTextColor(tvTextColor)
        }

    }

    override fun onClick(v: View?) {
        val i = v!!.id
        if (i == R.id.btnDecrease) {//减少
            if (amount > minAmount) {
                amount--
                //当数量等于最小数量时减少按钮变成灰色
                if (amount == minAmount) {
                    btnDecrease.setImageResource(unable_decrease_bg)
                }
                //恢复添加按钮正常颜色
                if (amount == maxAmount - 1) {
                    btnIncrease.setImageResource(increase_bg)
                }
                onAmountButtonListener?.onAmountChange(Constant.DECREASE, amount)
            } else {
                ToastUtils.showToast(context, minWarnStr)
            }
        } else if (i == R.id.btnIncrease) {//增加
            if (amount < maxAmount) {
                amount++
                //恢复减少按钮的颜色
                if (amount == minAmount + 1) {
                    btnDecrease.setImageResource(decrease_bg)
                }
                //当数量等于最大数量时增加按钮成灰色
                if (amount == maxAmount) {
                    btnIncrease.setImageResource(unable_increase_bg)
                }
                onAmountButtonListener?.onAmountChange(Constant.INCREASE, amount)
            } else {
                ToastUtils.showToast(context, maxWarnStr)
            }

        } else if (i == R.id.tvAmount) {
            onEditModifyCount(amount)
        }

        notifyAmount();
    }

    private fun onEditModifyCount(count: Int) {
        editCountDialog = EditCountDialog(context, R.style.DialogStyle, count, object : EditCountDialog.OnDialogListener {
            override fun onGoodsCount(count: Int) {
                setAmount(count)
                onAmountButtonListener?.onAmountChange(Constant.EDIT, getAmount())
            }
        })
        editCountDialog.show()
    }

    private fun checkButtonStatus() {
        if (amount == minAmount) {
            btnDecrease.setImageResource(unable_decrease_bg)
        } else {
            btnDecrease.setImageResource(decrease_bg)
        }

        if (amount == maxAmount) {
            btnIncrease.setImageResource(unable_increase_bg)
        } else {
            btnIncrease.setImageResource(increase_bg)
        }
    }

    /**
     * 设置数量
     *
     * @param tempAmount
     */
    fun setAmount(tempAmount: Int) {
        if (tempAmount > maxAmount) {
            this.amount = maxAmount
            ToastUtils.showToast(context, maxWarnStr)
        } else {
            this.amount = tempAmount
        }
        //检测按钮状态
        checkButtonStatus()
        notifyAmount()
    }

    /**
     * 获得数量
     */
    fun getAmount(): Int {
        return this.amount
    }

    fun notifyAmount() {
        tvAmount.text = amount.toString()
    }

    /**
     * 设置最大数量（库存/上限）
     */
    fun setMaxAmount(tempMaxAmount: Int) {
        this.maxAmount = tempMaxAmount
    }

    fun setMinAmount(tempMinAmount: Int) {
        this.minAmount = tempMinAmount
    }

    fun getMinAmount(): Int {
        return this.minAmount
    }


    fun setOnAmountButtonListener(listener: OnAmountButtonListener) {
        this.onAmountButtonListener = listener
    }
}