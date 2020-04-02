package com.adyabukhari.tunaikuasessment.utils.helper.appresource

import android.app.Application
import javax.inject.Inject

class AppResourceImpl @Inject constructor(private val application: Application) : AppResource {

    override fun getStringRes(res: Int): String {
        return application.getString(res)
    }

    override fun getArrayString(res: Int): Array<String> {
        return application.resources.getStringArray(res)
    }

}