package com.mehmetkurt.kotlincatchthekenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mehmetkurt.kotlincatchthekenny.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import java.util.logging.Handler as Handler1
import kotlin.random.Random as Random

class MainActivity : AppCompatActivity() {
    var score=0
    var imageArray=ArrayList<ImageView>()        ////////////image array
    var handler : Handler = Handler(Looper.getMainLooper())
    var runnable : Runnable = Runnable {  }


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ////IMAGEARRAY
        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        hideImages()

        Toast.makeText(applicationContext,"GAME START!!!",Toast.LENGTH_LONG).show()
        /////////////////////////////////////////////////////COUNT TİME TİMER.
        object : CountDownTimer(10000, 1000) { //sayaclarda mili saniye kullanılır.
            //milissinfuture sayacın başlama noktası
            //ınterval ise azalacağı miktar (1sn,1sn azalacak)

            override fun onFinish() {   //burası sayım bitince ne göstersin kısmı
                timeText.text = "Left: 0"
                handler.removeCallbacks(runnable)
                for(image  in imageArray){
                    image.visibility=View.INVISIBLE
                }
                //////////////////////////////ALERT
                val alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Do you want play again?")
                alert.setPositiveButton("Yes")

                {dialog,which ->// dialog bölgesi yes  yada no butonuna tıklayınca ne yapılacağını gösterdiğimiz yer.

                    val intent=intent //bu 3 satır kod ile önce activityeyi kapatıp yeniden başlatıyoruz.
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No"){dialog,which->
                    Toast.makeText(applicationContext,"Game Over",Toast.LENGTH_LONG).show()
                    finish()

                }
                alert.show()
                ///////////////////////ALERT
            }

            override fun onTick(millisUntilFinished: Long) { //burası ise her biten periyodda(saymada ne olacak)
                timeText.text = "Left: ${millisUntilFinished / 1000}"//millisuntilfinished bitmeye kalan saniye demek
                //saniye olsun diye 1000'e böldük.
            }

        }.start()

//////////////////////////////////////////////////////////
    }
    fun hideImages(){ // hide images fonksiyonunu yukarda çağırmazsak çağrılmaz.Runnable resimlerin yerini sürekli değiştirmemizi sağladı.
        runnable = object : Runnable {
            override fun run() {

                for(image in imageArray) {
                    image.visibility = View.INVISIBLE //TÜM RESİMLERİ AYNI ANDA GÖRÜNMEZ YAPMAK
                }
                val random= Random
                val randomIndex=random.nextInt(9) //9 a kadar rast gele sayı üret demek.
                imageArray[randomIndex].visibility=View.VISIBLE
                handler.postDelayed(runnable,500)
            }
            }
        handler.post(runnable)


    }

////////////////////////ONCREAT SONRASI ALAN.
    fun increaseScore(view: View){
        score++
    scoreText.text="Score:"+score
    }



}
