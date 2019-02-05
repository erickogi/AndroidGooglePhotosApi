package ke.co.calista.googlephotos


import android.os.Handler

import androidx.multidex.MultiDexApplication

class Application : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()


    }

    companion object {


        val TAG = Application::class.java
                .simpleName

        @Volatile
        lateinit var applicationHandler: Handler

        @Volatile
        var application: Application? = null
    }

}
