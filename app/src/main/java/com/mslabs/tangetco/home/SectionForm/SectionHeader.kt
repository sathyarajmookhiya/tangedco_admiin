package com.mslabs.tangetco.home.SectionForm

import com.intrusoft.sectionedrecyclerview.Section

class SectionHeader(var headName: String, var childList: List<Child>) : Section<Child> {

    fun SectionHeader(childList: List<Child>, sectionText: String) {
        this.childList = childList
        this.headName = sectionText
    }

    override fun getChildItems(): List<Child> {
        return childList
    }

    fun getSectionText(): String {
        return headName
    }

}