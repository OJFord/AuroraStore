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
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.aurora.gplayapi.data.models.File
import com.aurora.store.R
import com.aurora.store.databinding.ViewFileBinding
import com.aurora.store.util.CommonUtil

@ModelView(
    autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT,
    baseModelClass = BaseView::class
)
class FileView : RelativeLayout {

    private lateinit var B: ViewFileBinding

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
        val view = inflate(context, R.layout.view_file, this)
        B = ViewFileBinding.bind(view)
    }

    @ModelProp
    fun file(file: File) {
        B.line1.text = file.name
        B.line2.text = CommonUtil.addSiPrefix(file.size)
    }
}