/*
 * Aurora Store
 *  Copyright (C) 2021, Rahul Kumar Patel <whyorean@gmail.com>
 *
 *  Aurora Store is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  Aurora Store is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Aurora Store.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.aurora.store.view.epoxy.views

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled
import com.aurora.gplayapi.data.models.App
import com.aurora.store.R
import com.aurora.store.databinding.ViewAppBinding
import com.aurora.store.util.CommonUtil
import com.aurora.store.util.extensions.clear
import com.aurora.store.util.extensions.load
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


@ModelView(
    autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT,
    baseModelClass = BaseView::class
)
class AppView : RelativeLayout {

    private lateinit var B: ViewAppBinding

    constructor(context: Context?) : super(context) {
        init(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context?, attrs: AttributeSet?) {
        val view = inflate(context, R.layout.view_app, this)
        B = ViewAppBinding.bind(view)
    }

    @ModelProp
    fun app(app: App) {
        B.txtName.text = app.displayName
        B.imgIcon.load(app.iconArtwork.url) {
            placeholder(R.drawable.bg_placeholder)
            transform(RoundedCorners(32))
        }

        if (app.size > 0)
            B.txtSize.text = CommonUtil.addSiPrefix(app.size)
    }

    @CallbackProp
    fun click(onClickListener: OnClickListener?) {
        B.root.setOnClickListener(onClickListener)
    }

    @CallbackProp
    fun longClick(onClickListener: OnLongClickListener?) {
        B.root.setOnLongClickListener(onClickListener)
    }

    @OnViewRecycled
    fun clear() {
        B.imgIcon.clear()
    }
}