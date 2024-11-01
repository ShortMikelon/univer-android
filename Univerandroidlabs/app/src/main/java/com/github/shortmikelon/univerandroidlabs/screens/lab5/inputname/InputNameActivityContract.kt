package com.github.shortmikelon.univerandroidlabs.screens.lab5.inputname

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class InputNameActivityContract : ActivityResultContract<Unit, String>() {

    override fun createIntent(context: Context, input: Unit): Intent {
        return Intent(context, InputNameActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String = when {
        resultCode != Activity.RESULT_OK -> ""
        else -> intent?.getStringExtra(RESULT_KEY) ?: "Data not founded"
    }

    companion object {
        const val RESULT_KEY = "result_key"
    }
}