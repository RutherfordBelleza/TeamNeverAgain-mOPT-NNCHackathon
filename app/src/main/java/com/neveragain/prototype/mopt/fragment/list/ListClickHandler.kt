package com.neveragain.prototype.mopt.fragment.list

import com.neveragain.prototype.mopt.data.Child

interface ListClickHandler {
    fun onClick(child: Child) {}
}