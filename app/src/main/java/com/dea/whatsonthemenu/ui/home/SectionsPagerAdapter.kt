package com.dea.whatsonthemenu.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dea.whatsonthemenu.R
import com.dea.whatsonthemenu.ui.pasta.PastaFragment
import com.dea.whatsonthemenu.ui.pizza.PizzaFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.Pizza,
            R.string.Pasta
        )
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> PizzaFragment()
            1 -> PastaFragment()
//            1 -> instantiateFragment(className)
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size

    private val className: String
        get() = "com.dea.whatsonthemenu.favourite.ui.favourite.FavouriteFragment"

//    private fun instantiateFragment(className: String): Fragment {
//        return Class.forName(className).newInstance() as Fragment
//    }

}