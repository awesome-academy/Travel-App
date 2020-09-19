package com.sunasterisk.travelapp.ui.custom

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Build
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.adapter.CheckInViewPagerAdapter
import com.sunasterisk.travelapp.utils.getToolbarNavigationButton
import com.sunasterisk.travelapp.utils.screenHeight
import kotlinx.android.synthetic.main.activity_check_in.view.*
import kotlinx.android.synthetic.main.layout_view_pager_check_in.view.*

class BackdropViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private val animatorSet = AnimatorSet()
    private var translateY: Float = 0.0f
    private var backdropShown = false
    private var toolbar: androidx.appcompat.widget.Toolbar? = null
    private var toolbarNavigation: ImageButton? = null
    private var viewPager: SwipeControllableViewPager? = null
    private val checkInViewPagerAdapter = CheckInViewPagerAdapter((context as AppCompatActivity).supportFragmentManager)
    private var currentPos = 0

    init {
        val view = View.inflate(context, R.layout.layout_view_pager_check_in, this)
        viewPager = view.swipeControllableViewPager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            translateY = resources.getFloat(R.dimen.float_0_75) * context.screenHeight()
        }
        viewPager?.swipeEnable = true
        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) = Unit
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit
            override fun onPageSelected(position: Int) {
                viewPager?.apply {
                    setCurrentItem(position, true)
                    currentPos = position
                    toolbar?.textViewTitleSteps?.text =
                        resources.getQuantityString(R.plurals.title_check_in_steps,
                            resources.getInteger(R.integer.integer_1), position + 1, adapter?.count)
                    toolbar?.title = checkInViewPagerAdapter.getTitle(position)
                }
            }
        })
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.imageViewStep -> slideFrontLayout()
            R.id.materialButtonContinue -> onNextPage()
        }
        if (view == toolbarNavigation) {
            handBackPress()
        }
    }

    private fun onNextPage() {
        if ((currentPos >= checkInViewPagerAdapter.count - 1)) return
        if (backdropShown) {
            Handler().postDelayed({
                slideFrontLayout()
            }, context.resources.getInteger(R.integer.integer_500).toLong())
        }
        viewPager?.setCurrentItem(currentPos + 1, true)
    }

    private fun handBackPress() {
        if (currentPos > 0) {
            Handler().postDelayed({
                slideFrontLayout()
            }, context.resources.getInteger(R.integer.integer_500).toLong())
            viewPager?.setCurrentItem(currentPos - 1, true)
        } else if (currentPos == 0) {
            (context as AppCompatActivity).onBackPressed()
        }
    }

    private fun slideFrontLayout() {
        backdropShown = !backdropShown

        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()

        val animator = ObjectAnimator.ofFloat(
            viewPagerMainCheckInContent, context.resources.getString(R.string.title_transition_type_y),
            (if (backdropShown) translateY else 0.0F))
        animator.duration = (context.resources.getInteger(R.integer.integer_500)).toLong()
        animator.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.play(animator)
        animator.start()

        toolbar?.imageViewStep?.let { updateIcon(it) }
        viewPager?.swipeEnable = !backdropShown
    }

    private fun updateIcon(view: View) {
        if (view !is ImageView) {
            throw IllegalArgumentException("updateIcon() must be called on an ImageView")
        }
        if (backdropShown) {
            view.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_up, null))
        } else {
            view.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_step, null))
        }
    }

    fun setToolbar(toolbar: androidx.appcompat.widget.Toolbar) {
        this.toolbar = toolbar
        toolbar.setNavigationOnClickListener(this)
        toolbar.imageViewStep.setOnClickListener(this)
        toolbar.textViewTitleSteps.setOnClickListener(this)
        toolbarNavigation = toolbar.getToolbarNavigationButton() as ImageButton?
    }

    fun updateListPager(listFragment: List<Fragment>, listTitles: List<String>) {
        viewPager?.adapter = checkInViewPagerAdapter
        checkInViewPagerAdapter.updateList(listFragment)
        checkInViewPagerAdapter.updateTitles(listTitles)
    }
}
