package com.head.dialog.impl;

import android.animation.Animator;

/**   
*
* 类名称：AnimatorListenerEndCallBack.java <br/>
* 类描述：动画监听<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 9:02 PM <br/>
* @version 
*/
public abstract class AnimatorListenerEndCallBack implements Animator.AnimatorListener {
    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public abstract void onAnimationEnd(Animator animation);

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
