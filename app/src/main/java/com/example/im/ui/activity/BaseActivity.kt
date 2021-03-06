package com.example.im.ui.activity

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

/**
 * @author  Mloong
 * date  2019/6/27 21:33
 */
abstract class BaseActivity :AppCompatActivity(){

    val  progressDialog by lazy {
        ProgressDialog(this)
    }


    val inputMethodManager by lazy {

        getSystemService(Context.INPUT_METHOD_SERVICE)  as InputMethodManager
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutReId())

        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.TRANSPARENT
        }

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)


        init()
    }

    open fun init() {

        //初始化一些公共的功能，比如摇一摇，子类也可以复写该方法，完成自己的初始化
    }


    //子类必须实现该方法返回一个布局资源的id
    abstract fun getLayoutReId(): Int



    fun showProgress(message:String){
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    fun dismissProgress(){

        progressDialog.dismiss()
    }


    fun  hideSoftKeyboard(){
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken,0)
    }
}