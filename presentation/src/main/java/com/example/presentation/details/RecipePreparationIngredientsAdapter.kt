package com.example.presentation.details

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.presentation.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RecipePreparationIngredientsAdapter @Inject constructor(@ApplicationContext private val context: Context) :
    BaseExpandableListAdapter() {

    private var expandableTitleList: List<String> = listOf()
    private var hashMapDetails: HashMap<String, List<String>> = HashMap()
    private val inflater by lazy { LayoutInflater.from(context) }

    fun setItems(titleList: List<String>, hashMapDetails: HashMap<String, List<String>>) {
        this.expandableTitleList = titleList
        this.hashMapDetails = hashMapDetails
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        val itemView: View = p2 ?: inflater.inflate(R.layout.recipe_detail_item_view, p3, false)
        itemView.findViewById<TextView>(R.id.expandable_list_label)?.run {
            setTypeface(null, Typeface.BOLD)
            text = getGroup(p0) as String
        }
        return itemView
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        val itemView: View = (p3 ?: inflater.inflate(R.layout.recipe_detail_item_view, p4, false))
        itemView.findViewById<TextView>(R.id.expandable_list_label)?.run {
            text = getChild(p0, p1) as String
        }
        return itemView
    }

    override fun getGroupCount(): Int = expandableTitleList.size

    override fun getChildrenCount(p0: Int): Int = hashMapDetails[expandableTitleList[p0]]?.size ?: 0

    override fun getGroup(p0: Int): Any = expandableTitleList[p0]

    override fun getChild(p0: Int, p1: Int): Any =
        (hashMapDetails[expandableTitleList[p0]]?.get(p1) as? Any) ?: Any()

    override fun getGroupId(p0: Int): Long = p0.toLong()

    override fun getChildId(p0: Int, p1: Int): Long = p1.toLong()

    override fun hasStableIds(): Boolean = false

    override fun isChildSelectable(p0: Int, p1: Int): Boolean = false
}
