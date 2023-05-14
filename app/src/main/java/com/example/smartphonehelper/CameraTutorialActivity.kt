package com.example.smartphonehelper

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.viewpager.widget.ViewPager
import kotlinx.coroutines.NonCancellable.start

class CameraTutorialActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var dotsLayout: LinearLayout
    private lateinit var dots: Array<TextView?>
    private lateinit var layouts: IntArray
    private lateinit var btnSkip: Button
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        setContentView(R.layout.activity_camera_tutorial)
        viewPager = findViewById(R.id.view_pager)
        dotsLayout = findViewById(R.id.layoutDots)
        btnSkip = findViewById(R.id.btn_skip)
        btnNext = findViewById(R.id.btn_next)

        // 변화될 레이아웃들 주소
        // 원하는 경우 레이아웃을 몇 개 더 추가
        layouts = intArrayOf(
            R.layout.activity_camera_page1,
            R.layout.activity_camera_page2,
            R.layout.activity_camera_page3,
            R.layout.activity_camera_page4
        )

        // 하단 점 추가
        addBottomDots(0)

        // 알림 표시줄을 투명하게 만들기
        changeStatusBarColor()
        pagerAdapter = PagerAdapter()
        viewPager.setAdapter(pagerAdapter)
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener)


        // 건너띄기 버튼 클릭시 메인화면으로 이동
        btnSkip.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@CameraTutorialActivity, MainActivity::class.java))
            finish()
        })

        // 조건문을 통해 버튼 하나로 두개의 상황을 실행
        btnNext.setOnClickListener(View.OnClickListener {
            val current = getItem(+1)
            if (current < layouts.size) {
                // 마지막 페이지가 아니라면 다음 페이지로 이동
                viewPager.setCurrentItem(current)
            } else {
                // 마지막 페이지라면 메인페이지로 이동
                startActivity(Intent(this@CameraTutorialActivity, MainActivity::class.java))
                finish()
            }
        })
    }

    // 하단 점(선택된 점, 선택되지 않은 점) 구현
    private fun addBottomDots(currentPage: Int) {
        dots = arrayOfNulls(layouts.size) // 레이아웃 크기만큼 하단 점 배열에 추가
        val colorsActive = resources.getIntArray(R.array.array_dot_active)
        val colorsInactive = resources.getIntArray(R.array.array_dot_inactive)
        dotsLayout!!.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]!!.text = Html.fromHtml("&#8226;")
            dots[i]!!.textSize = 35f
            dots[i]!!.setTextColor(colorsInactive[currentPage])
            dotsLayout!!.addView(dots[i])
        }
        if (dots.size > 0) dots[currentPage]!!.setTextColor(colorsActive[currentPage])
    }

    private fun getItem(i: Int): Int {
        return viewPager!!.currentItem + i
    }

    // 뷰페이저 변경 리스너
    var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object :
        ViewPager.OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            addBottomDots(position)

            // 다음 / 시작 버튼 바꾸기
            if (position == layouts.size - 1) {
                // 마지막 페이지에서는 다음 버튼을 시작버튼으로 교체
                btnNext!!.text = getString(R.string.start) // 다음 버튼을 시작버튼으로 글자 교체
                btnSkip!!.visibility = View.GONE
            } else {

                // 마지막 페이지가 아니라면 다음과 건너띄기 버튼 출력
                btnNext!!.text = getString(R.string.next)
                btnSkip!!.visibility = View.VISIBLE
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    // 알림 표시줄을 투명하게 만들기
    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    // 호출기 어댑터
    inner class PagerAdapter : androidx.viewpager.widget.PagerAdapter() {
        private var layoutInflater: LayoutInflater? = null
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater!!.inflate(layouts[position], container, false)
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return layouts.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }
}