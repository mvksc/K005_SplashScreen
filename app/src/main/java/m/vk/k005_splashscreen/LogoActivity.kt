package m.vk.k005_splashscreen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

class LogoActivity : AppCompatActivity() {

    lateinit var runnable : Runnable
    lateinit var handler : Handler
    var delay_time : Long = 0L
    var time : Long = 3000L
    var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)

        handler = Handler()
        runnable = Runnable {
           var goMain = Intent(this@LogoActivity,MainActivity::class.java)
            startActivity(goMain)
            finish()
        }

    }

    override fun onBackPressed() {
        if(!doubleBackToExitPressedOnce){
            doubleBackToExitPressedOnce = true
            Toast.makeText(this@LogoActivity,"กรุณากดอีกครั้งเพื่อออกจากแอพ",Toast.LENGTH_LONG).show()
            Handler().postDelayed({
                doubleBackToExitPressedOnce = false
            },2000)
        }else{
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        delay_time = time
        handler.postDelayed(runnable,delay_time)
        time = System.currentTimeMillis()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
        time = delay_time - (System.currentTimeMillis()-time)
    }
}
