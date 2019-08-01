package io.github.johnnynanjiang.android.moneytreelight.view

import android.os.Bundle
import com.airbnb.mvrx.BaseMvRxActivity
import io.github.johnnynanjiang.android.moneytreelight.R

class MainActivity : BaseMvRxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
