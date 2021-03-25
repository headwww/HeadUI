package com.head.dialog.style;

import android.content.Context;
import android.content.res.Resources;

import com.head.dialog.R;
import com.head.dialog.interfaces.ProgressViewInterface;
import com.head.dialog.util.views.IosProgressView;

/**   
*
* 类名称：IOSStyle.java <br/>
* 类描述：IOS风格<br/>
* 创建人：舒文 <br/>
* 创建时间：3/9/21 1:56 PM <br/>
* @version 
*/
public class IOSStyle implements DialogStyle {

    public static IOSStyle style() {
        return new IOSStyle();
    }

    @Override
    public int layout(boolean light) {
        return light ? R.layout.layout_dialog_ios : R.layout.layout_dialog_ios_dark;
    }

    @Override
    public int enterAnimResId() {
        return R.anim.anim_dialog_ios_enter;
    }

    @Override
    public int exitAnimResId() {
        return 0;
    }

    @Override
    public int[] verticalButtonOrder() {
        return new int[]{BUTTON_OK, SPLIT, BUTTON_OTHER, SPLIT, BUTTON_CANCEL};
    }

    @Override
    public int[] horizontalButtonOrder() {
        return new int[]{BUTTON_CANCEL, SPLIT, BUTTON_OTHER, SPLIT, BUTTON_OK};
    }

    @Override
    public int splitWidthPx() {
        return 1;
    }

    @Override
    public int splitColorRes(boolean light) {
        return light ? R.color.dialogIOSSplitLight : R.color.dialogIOSSplitDark;
    }

    @Override
    public BlurBackgroundSetting messageDialogBlurSettings() {
        return new BlurBackgroundSetting() {
            @Override
            public boolean blurBackground() {
                return true;
            }

            @Override
            public int blurForwardColorRes(boolean light) {
                return light ? R.color.dialogIOSBkgLight : R.color.dialogIOSBkgDark;
            }

            @Override
            public int blurBackgroundRoundRadiusPx() {
                return dip2px(15);
            }
        };
    }

    private int dip2px(float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public HorizontalButtonRes overrideHorizontalButtonRes() {
        return new HorizontalButtonRes() {
            @Override
            public int overrideHorizontalOkButtonBackgroundRes(int visibleButtonCount, boolean light) {
                if (visibleButtonCount == 1) {
                    return light ? R.drawable.button_dialog_ios_bottom_light : R.drawable.button_dialog_ios_bottom_night;
                } else {
                    return light ? R.drawable.button_dialog_ios_right_light : R.drawable.button_dialog_ios_right_night;
                }
            }

            @Override
            public int overrideHorizontalCancelButtonBackgroundRes(int visibleButtonCount, boolean light) {
                return light ? R.drawable.button_dialog_ios_left_light : R.drawable.button_dialog_ios_left_night;
            }

            @Override
            public int overrideHorizontalOtherButtonBackgroundRes(int visibleButtonCount, boolean light) {
                return light ? R.drawable.button_dialog_ios_center_light : R.drawable.button_dialog_ios_center_night;
            }
        };
    }

    @Override
    public VerticalButtonRes overrideVerticalButtonRes() {
        return new VerticalButtonRes() {
            @Override
            public int overrideVerticalOkButtonBackgroundRes(int visibleButtonCount, boolean light) {
                return light ? R.drawable.button_dialog_ios_center_light : R.drawable.button_dialog_ios_center_night;
            }

            @Override
            public int overrideVerticalCancelButtonBackgroundRes(int visibleButtonCount, boolean light) {
                return light ? R.drawable.button_dialog_ios_bottom_light : R.drawable.button_dialog_ios_bottom_night;
            }

            @Override
            public int overrideVerticalOtherButtonBackgroundRes(int visibleButtonCount, boolean light) {
                return light ? R.drawable.button_dialog_ios_center_light : R.drawable.button_dialog_ios_center_night;
            }
        };
    }

    @Override
    public WaitTipRes overrideWaitTipRes() {
        return new WaitTipRes() {
            @Override
            public int overrideWaitLayout(boolean light) {
                return 0;
            }

            @Override
            public int overrideRadiusPx() {
                return -1;
            }

            @Override
            public boolean blurBackground() {
                return true;
            }

            @Override
            public int overrideBackgroundColorRes(boolean light) {
                return light ? R.color.dialogIOSWaitBkgDark : R.color.dialogIOSWaitBkgLight;
            }

            @Override
            public int overrideTextColorRes(boolean light) {
                return 0;
            }

            @Override
            public ProgressViewInterface overrideWaitView(Context context, boolean light) {
                return new IosProgressView(context).setLightMode(light);
            }
        };
    }

    @Override
    public BottomDialogRes overrideBottomDialogRes() {
        return new BottomDialogRes() {

            @Override
            public boolean touchSlide() {
                return false;
            }

            @Override
            public int overrideDialogLayout(boolean light) {
                //return light ? R.layout.layout_dialog_bottom_material : R.layout.layout_dialog_bottom_material_dark;
                return light ? R.layout.layout_dialog_bottom_ios : R.layout.layout_dialog_bottom_ios_dark;
            }

            @Override
            public int overrideMenuDividerDrawableRes(boolean light) {
                return light ? R.drawable.rect_dialog_ios_menu_split_divider : R.drawable.rect_dialog_ios_menu_split_divider_night;
            }

            @Override
            public int overrideMenuDividerHeight(boolean light) {
                return 1;
            }

            @Override
            public int overrideMenuTextColor(boolean light) {
                return light ? R.color.dialogIOSBlue : R.color.dialogIOSBlueDark;
            }

            @Override
            public float overrideBottomDialogMaxHeight() {
                return 0f;
            }

            @Override
            public int overrideMenuItemLayout(boolean light, int index, int count, boolean isContentVisibility) {
                if (light) {
                    if (index == 0) {
                        return isContentVisibility ? R.layout.item_dialog_ios_bottom_menu_center_light : R.layout.item_dialog_ios_bottom_menu_top_light;
                    } else if (index == count - 1) {
                        return R.layout.item_dialog_ios_bottom_menu_bottom_light;
                    } else {
                        return R.layout.item_dialog_ios_bottom_menu_center_light;
                    }
                } else {
                    if (index == 0) {
                        return isContentVisibility ? R.layout.item_dialog_ios_bottom_menu_center_dark : R.layout.item_dialog_ios_bottom_menu_top_dark;
                    } else if (index == count - 1) {
                        return R.layout.item_dialog_ios_bottom_menu_bottom_dark;
                    } else {
                        return R.layout.item_dialog_ios_bottom_menu_center_dark;
                    }
                }
            }

            @Override
            public int overrideSelectionMenuBackgroundColor(boolean light) {
                return 0;
            }

            @Override
            public boolean selectionImageTint(boolean light) {
                return true;
            }

            @Override
            public int overrideSelectionImage(boolean light, boolean isSelected) {
                return 0;
            }
        };
    }

    @Override
    public PopTipSettings popTipSettings() {
        return new PopTipSettings() {
            @Override
            public int layout(boolean light) {
                return light ? R.layout.layout_dialog_poptip_ios : R.layout.layout_dialog_poptip_ios_dark;
            }

            @Override
            public ALIGN align() {
                return ALIGN.TOP;
            }

            @Override
            public int enterAnimResId(boolean b) {
                return R.anim.anim_dialog_ios_top_enter;
            }

            @Override
            public int exitAnimResId(boolean b) {
                return R.anim.anim_dialog_ios_top_exit;
            }
        };
    }
}
