package com.head.dialog.interfaces;

/**
*
* 类名称：ProgressViewInterface.java <br/>
* 类描述：进度条生命周期<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 8:57 PM <br/>
* @version
*/
public interface ProgressViewInterface {
    
    //停止加载动画
    void noLoading();
    
    //切换至完成状态
    void success();
    
    //切换至警告状态
    void warning();
    
    //切换至错误状态
    void error();
    
    //切换至进度（取值 0f-1f）
    void progress(float progress);
    
    //切换至加载状态
    void loading();
    
    //不同状态切换时，衔接动画完成后执行
    ProgressViewInterface whenShowTick(Runnable runnable);
    
    //设置颜色
    ProgressViewInterface setColor(int color);
}
