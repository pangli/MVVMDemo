package com.zorro.mvvm.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zorro.mvvm.R
import com.zorro.mvvm.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.*
import java.lang.reflect.Field

class MainFragment : Fragment() {
    private var mainFragmentBinding: MainFragmentBinding? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.main_fragment, container, false)
        mainFragmentBinding = DataBindingUtil.bind(root)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainFragmentBinding?.viewModel = viewModel
        mainFragmentBinding?.lifecycleOwner = this

        //观察者，自己处理
        viewModel.getUser().observe(this, Observer {
            Toast.makeText(
                context, String.format(
                    getString(R.string.name_and_age),
                    it.name,
                    it.age
                ), Toast.LENGTH_SHORT
            ).show()
            textView3.text =
                String.format(
                    getString(R.string.name_and_age),
                    it.name,
                    it.age
                )
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        fixInputMethodManagerLeak(context!!)
    }

    /**
     * 解决InputMethodManager引起的内存泄漏
     * 在onDestroy方法里调用
     */
    private fun fixInputMethodManagerLeak(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val arr = arrayOf("mCurRootView", "mServedView", "mNextServedView")
        var field: Field?
        var objGet: Any?
        for (i in arr.indices) {
            val param = arr[i]
            try {
                field = imm.javaClass.getDeclaredField(param)
                if (!field.isAccessible) {
                    field.isAccessible = true
                }
                objGet = field.get(imm)
                if (objGet != null && objGet is View) {
                    val view = objGet
                    if (view.context === context) {
                        // 被InputMethodManager持有引用的context是想要目标销毁的
                        field.set(imm, null) // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                        break
                    }
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }

        }

    }
}
