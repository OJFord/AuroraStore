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

package com.aurora.store.view.ui.commons

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.fragment.app.Fragment
import com.aurora.Constants
import com.aurora.gplayapi.data.models.App
import com.aurora.gplayapi.data.models.Category
import com.aurora.store.util.ViewUtil
import com.aurora.store.view.ui.details.AppDetailsActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Modifier

open class BaseFragment : Fragment() {

    protected lateinit var app: App

    var gson: Gson = GsonBuilder().excludeFieldsWithModifiers(
        Modifier.TRANSIENT
    ).create()

    fun openDetailsActivity(app: App) {
        val intent = Intent(context, AppDetailsActivity::class.java)
        intent.putExtra(Constants.STRING_EXTRA, Gson().toJson(app))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options = ActivityOptions.makeSceneTransitionAnimation(requireActivity())
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }

    fun openCategoryBrowseActivity(category: Category) {
        val intent = Intent(context, CategoryBrowseActivity::class.java)
        intent.putExtra(Constants.STRING_EXTRA, category.title)
        intent.putExtra(Constants.BROWSE_EXTRA, category.browseUrl)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options = ActivityOptions.makeSceneTransitionAnimation(requireActivity())
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }

    fun openStreamBrowseActivity(browseUrl: String) {
        val intent = Intent(requireContext(), StreamBrowseActivity::class.java)
        intent.putExtra(Constants.BROWSE_EXTRA, browseUrl)
        startActivity(
            intent,
            ViewUtil.getEmptyActivityBundle(requireContext())
        )
    }
}