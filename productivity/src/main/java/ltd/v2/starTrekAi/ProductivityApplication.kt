package ltd.v2.starTrekAi

import android.app.Application
import ltd.v2.starTrekAi.storage.AppSessionManager

class ProductivityApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppSessionManager.init(applicationContext)
    }
}