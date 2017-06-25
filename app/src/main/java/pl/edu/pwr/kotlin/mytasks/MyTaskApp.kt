package pl.edu.pwr.kotlin.mytasks

import android.app.Application
import com.mcxiaoke.koi.KoiConfig
import kotlin.properties.Delegates

/**
 * Created by mzc on 6/24/2017.
 */
class MyTaskApp : Application() {

    companion object {
        var app: MyTaskApp by Delegates.notNull<MyTaskApp>()
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        KoiConfig.logEnabled = true
    }
}