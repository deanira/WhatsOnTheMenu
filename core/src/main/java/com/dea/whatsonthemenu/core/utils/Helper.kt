package com.dea.whatsonthemenu.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dea.whatsonthemenu.core.R

object Helper {
    fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}