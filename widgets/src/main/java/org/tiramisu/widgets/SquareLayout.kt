package org.tiramisu.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * @author felixxfwang
 */
class SquareLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, style: Int = 0
): FrameLayout(context, attrs, style) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredWidth)
    }
}