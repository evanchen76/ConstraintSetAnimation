package evan.chen.tutorial.constraintsetanimation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.AnticipateOvershootInterpolator
import kotlinx.android.synthetic.main.layout1.*

class MainActivity : AppCompatActivity() {

    private var isShow = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout1)

        imageView.setOnClickListener {
            if (isShow)
                hideDetail()
            else
                showDetail()
        }

    }

    private fun showDetail() {
        isShow = true

        //Clone layout2
        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.layout2)

        //設定動畫方式
        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1000

        //開始動畫
        TransitionManager.beginDelayedTransition(constraintLayout, transition)
        constraintSet.applyTo(constraintLayout)
    }

    private fun hideDetail() {
        isShow = false

        val constraintSet = ConstraintSet()

        constraintSet.clone(this, R.layout.layout1)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1000

        TransitionManager.beginDelayedTransition(constraintLayout, transition)
        constraintSet.applyTo(constraintLayout)
    }
}
