package com.head.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.head.dialog.HeadDialog;
import com.head.dialog.dialogs.BottomDialog;
import com.head.dialog.dialogs.BottomMenu;
import com.head.dialog.dialogs.CustomDialog;
import com.head.dialog.dialogs.FullScreenDialog;
import com.head.dialog.dialogs.InputDialog;
import com.head.dialog.dialogs.MessageDialog;
import com.head.dialog.dialogs.PopTip;
import com.head.dialog.dialogs.TimePickerDialog;
import com.head.dialog.dialogs.TipDialog;
import com.head.dialog.dialogs.WaitDialog;
import com.head.dialog.interfaces.OnBackPressedListener;
import com.head.dialog.interfaces.OnBindView;
import com.head.dialog.interfaces.OnDialogButtonClickListener;
import com.head.dialog.interfaces.OnInputDialogButtonClickListener;
import com.head.dialog.interfaces.OnMenuItemClickListener;
import com.head.dialog.interfaces.OnTimeSelectChangeListener;
import com.head.dialog.interfaces.OnTimeSelectListener;
import com.head.dialog.style.IOSStyle;
import com.head.dialog.style.MaterialStyle;
import com.head.dialog.util.InputInfo;
import com.head.ui.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class DialogFragment extends Fragment implements View.OnClickListener {


    protected View rootView;
    protected RadioButton rbMaterial;
    protected RadioButton rbIos;
    protected RadioGroup rGStyle;
    protected RadioButton rbLight;
    protected RadioButton rbDark;
    protected RadioGroup rgTheme;
    protected TextView btMessageDialog;
    protected TextView btnSelectDialog;
    protected TextView btnInputDialog;
    protected TextView btnWaitDialog;
    protected TextView btnWaitAndTipDialog;
    protected TextView btnTipSuccess;
    protected TextView btnTipWarning;
    protected TextView btnTipError;
    protected TextView btnTipProgress;
    protected TextView btnPoptip;
    protected TextView btnPoptipBigMessage;
    protected TextView btnBottomDialog;
    protected TextView btnBottomMenu;
    protected TextView btnBottomReply;
    protected TextView btnBottomSelectMenu;
    protected TextView btnCustomMessageDialog;
    protected TextView btnCustomInputDialog;
    protected TextView btnCustomBottomMenu;
    protected TextView btnCustomDialog;
    protected TextView btnFullScreenDialogLogin;
    protected TextView btnTime;
    float progress;
    private int selectMenuIndex;

    private TextView btnReplyCommit;
    private EditText editReplyCommit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HeadDialog.globalStyle = MaterialStyle.style();
        HeadDialog.globalTheme = HeadDialog.THEME.LIGHT;
        HeadDialog.onlyOnePopTip = false;
        initEvents();
    }

    private void initEvents() {
        rgTheme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbLight:
                        HeadDialog.globalTheme = HeadDialog.THEME.LIGHT;
                        break;
                    case R.id.rbDark:
                        HeadDialog.globalTheme = HeadDialog.THEME.DARK;
                        break;
                }
            }
        });

        rGStyle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                HeadDialog.cancelButtonText = "取消";
                switch (checkedId) {
                    case R.id.rbMaterial:
                        HeadDialog.globalStyle = MaterialStyle.style();
                        HeadDialog.cancelButtonText = "";
                        break;
                    case R.id.rbIos:
                        HeadDialog.globalStyle = IOSStyle.style();
                        break;
                }
            }
        });


    }


    private void initView(View rootView) {
        rbMaterial = (RadioButton) rootView.findViewById(R.id.rbMaterial);
        rbIos = (RadioButton) rootView.findViewById(R.id.rbIos);
        rGStyle = (RadioGroup) rootView.findViewById(R.id.rGStyle);
        rbLight = (RadioButton) rootView.findViewById(R.id.rbLight);
        rbDark = (RadioButton) rootView.findViewById(R.id.rbDark);
        rgTheme = (RadioGroup) rootView.findViewById(R.id.rgTheme);
        btMessageDialog = (TextView) rootView.findViewById(R.id.btMessageDialog);
        btMessageDialog.setOnClickListener(DialogFragment.this);
        btnSelectDialog = (TextView) rootView.findViewById(R.id.btn_selectDialog);
        btnSelectDialog.setOnClickListener(DialogFragment.this);
        btnInputDialog = (TextView) rootView.findViewById(R.id.btn_inputDialog);
        btnInputDialog.setOnClickListener(DialogFragment.this);
        btnWaitDialog = (TextView) rootView.findViewById(R.id.btn_waitDialog);
        btnWaitDialog.setOnClickListener(DialogFragment.this);
        btnWaitAndTipDialog = (TextView) rootView.findViewById(R.id.btn_waitAndTipDialog);
        btnWaitAndTipDialog.setOnClickListener(DialogFragment.this);
        btnTipSuccess = (TextView) rootView.findViewById(R.id.btn_tipSuccess);
        btnTipSuccess.setOnClickListener(DialogFragment.this);
        btnTipWarning = (TextView) rootView.findViewById(R.id.btn_tipWarning);
        btnTipWarning.setOnClickListener(DialogFragment.this);
        btnTipError = (TextView) rootView.findViewById(R.id.btn_tipError);
        btnTipError.setOnClickListener(DialogFragment.this);
        btnTipProgress = (TextView) rootView.findViewById(R.id.btn_tipProgress);
        btnTipProgress.setOnClickListener(DialogFragment.this);
        btnPoptip = (TextView) rootView.findViewById(R.id.btn_poptip);
        btnPoptip.setOnClickListener(DialogFragment.this);
        btnPoptipBigMessage = (TextView) rootView.findViewById(R.id.btn_poptip_bigMessage);
        btnPoptipBigMessage.setOnClickListener(DialogFragment.this);
        btnBottomDialog = (TextView) rootView.findViewById(R.id.btn_bottom_dialog);
        btnBottomDialog.setOnClickListener(DialogFragment.this);
        btnBottomMenu = (TextView) rootView.findViewById(R.id.btn_bottom_menu);
        btnBottomMenu.setOnClickListener(DialogFragment.this);
        btnBottomReply = (TextView) rootView.findViewById(R.id.btn_bottom_reply);
        btnBottomReply.setOnClickListener(DialogFragment.this);
        btnBottomSelectMenu = (TextView) rootView.findViewById(R.id.btn_bottom_select_menu);
        btnBottomSelectMenu.setOnClickListener(DialogFragment.this);
        btnCustomMessageDialog = (TextView) rootView.findViewById(R.id.btn_customMessageDialog);
        btnCustomMessageDialog.setOnClickListener(DialogFragment.this);
        btnCustomInputDialog = (TextView) rootView.findViewById(R.id.btn_customInputDialog);
        btnCustomInputDialog.setOnClickListener(DialogFragment.this);
        btnCustomBottomMenu = (TextView) rootView.findViewById(R.id.btn_customBottomMenu);
        btnCustomBottomMenu.setOnClickListener(DialogFragment.this);
        btnCustomDialog = (TextView) rootView.findViewById(R.id.btn_customDialog);
        btnCustomDialog.setOnClickListener(DialogFragment.this);
        btnFullScreenDialogLogin = (TextView) rootView.findViewById(R.id.btn_fullScreenDialog_login);
        btnFullScreenDialogLogin.setOnClickListener(DialogFragment.this);
        btnTime = (TextView) rootView.findViewById(R.id.btn_Time);
        btnTime.setOnClickListener(DialogFragment.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btMessageDialog) {

            MessageDialog.show("标题", "这里是正文内容。", "确定").setOkButton(new OnDialogButtonClickListener<MessageDialog>() {
                @Override
                public boolean onClick(MessageDialog baseDialog, View v) {
                    PopTip.show("点击确定按钮");
                    return false;
                }
            });

        } else if (view.getId() == R.id.btn_selectDialog) {

            MessageDialog messageDialog = new MessageDialog("多选对话框", "移除App会将它从主屏幕移除并保留其所有数据。", "删除App", "取消", "移至App资源库")
                    .setButtonOrientation(LinearLayout.VERTICAL);
            messageDialog.show();

        } else if (view.getId() == R.id.btn_inputDialog) {
            InputInfo inputInfo = new InputInfo();
            inputInfo.setSelectAllText(true);
            new InputDialog("标题", "正文内容", "确定", "取消", "正在输入的文字")
                    .setInputText("Hello World")
                    .setCancelable(false)
                    .setInputInfo(inputInfo)
                    .setOkButton(new OnInputDialogButtonClickListener<InputDialog>() {
                        @Override
                        public boolean onClick(InputDialog baseDialog, View v, String inputStr) {
                            PopTip.show("输入的内容：" + inputStr);
                            return false;
                        }
                    })
                    .show();

        } else if (view.getId() == R.id.btn_waitDialog) {
            WaitDialog.show("Please Wait!").setOnBackPressedListener(new OnBackPressedListener() {
                @Override
                public boolean onBackPressed() {
                    PopTip.show("按下返回");
                    return false;
                }
            });
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    WaitDialog.dismiss();
                }
            }, 2000);

        } else if (view.getId() == R.id.btn_waitAndTipDialog) {
            WaitDialog.show("Please Wait!").setOnBackPressedListener(new OnBackPressedListener() {
                @Override
                public boolean onBackPressed() {
                    PopTip.show("按下返回");
                    return false;
                }
            });
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    TipDialog.show("Success!", WaitDialog.TYPE.SUCCESS);
                }
            }, 2000);

        } else if (view.getId() == R.id.btn_tipSuccess) {
            TipDialog.show("Success!", WaitDialog.TYPE.SUCCESS);

        } else if (view.getId() == R.id.btn_tipWarning) {
            TipDialog.show("Warning!", WaitDialog.TYPE.WARNING);

        } else if (view.getId() == R.id.btn_tipError) {
            TipDialog.show("Error!", WaitDialog.TYPE.ERROR);

        } else if (view.getId() == R.id.btn_tipProgress) {
            // 参数1 = 事件序列起始点 ；
            // 参数2 = 事件数量；
            // 参数3 = 第1次事件延迟发送时间；
            // 参数4 = 间隔时间数字；
            // 参数5 = 时间单位
            Observable.intervalRange(1, 10, 1000, 300, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            WaitDialog.show("假装连接...");
                            progress = 0;
                        }

                        @Override
                        public void onNext(Long value) {
                            progress = progress + 0.1f;

                            if (progress < 1f) {
                                WaitDialog.show("假装加载" + ((int) (progress * 100)) + "%", progress);
                            } else {
                                TipDialog.show("加载完成", WaitDialog.TYPE.SUCCESS);
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            TipDialog.show("加载失败", WaitDialog.TYPE.ERROR);

                        }

                        @Override
                        public void onComplete() {
                        }

                    });

        } else if (view.getId() == R.id.btn_poptip) {

            PopTip.show("这是一个提示");

        } else if (view.getId() == R.id.btn_poptip_bigMessage) {
            PopTip.show(R.mipmap.ic_launcher, "谢谢").setAutoTintIconInLightOrDarkMode(false).showLong();


        } else if (view.getId() == R.id.btn_bottom_dialog) {
            String s = "你可以点击空白区域或返回键来关闭这个对话框";
            new BottomDialog("标题", "这里是对话框内容。\n" + s + "。\n底部对话框也支持自定义布局扩展使用方式。",
                    new OnBindView<BottomDialog>(R.layout.layout_custom_view) {
                        @Override
                        public void onBind(BottomDialog dialog, View v) {

                        }
                    }).setCancelButton("取消").show();

        } else if (view.getId() == R.id.btn_bottom_menu) {
            String[] strings = new String[]{"添加", "查看", "编辑", "删除", "分享", "评论", "下载", "收藏", "赞！", "不喜欢", "所属专辑", "复制链接", "类似推荐", "添加", "查看", "编辑", "删除", "分享", "评论", "下载", "收藏", "赞！", "不喜欢", "所属专辑", "复制链接", "类似推荐"};
            BottomMenu.build().setStyle(MaterialStyle.style()).setMenuList(strings)
//                    .setOnIconChangeCallBack(new OnIconChangeCallBack(true) {
//                        @Override
//                        public int getIcon(BottomMenu bottomMenu, int index, String menuText) {
//                            return R.mipmap.ic_launcher;
////                            switch (menuText) {
////                                case "添加":
////                                    return R.mipmap.img_dialogx_demo_add;
////                                case "查看":
////                                    return R.mipmap.img_dialogx_demo_view;
////                                case "编辑":
////                                    return R.mipmap.img_dialogx_demo_edit;
////                                case "删除":
////                                    return R.mipmap.img_dialogx_demo_delete;
////                                case "分享":
////                                    return R.mipmap.img_dialogx_demo_share;
////                                case "评论":
////                                    return R.mipmap.img_dialogx_demo_comment;
////                                case "下载":
////                                    return R.mipmap.img_dialogx_demo_download;
////                                case "收藏":
////                                    return R.mipmap.img_dialogx_demo_favorite;
////                                case "赞！":
////                                    return R.mipmap.img_dialogx_demo_good;
////                                case "不喜欢":
////                                    return R.mipmap.img_dialogx_demo_dislike;
////                                case "所属专辑":
////                                    return R.mipmap.img_dialogx_demo_album;
////                                case "复制链接":
////                                    return R.mipmap.img_dialogx_demo_link;
////                                case "类似推荐":
////                                    return R.mipmap.img_dialogx_demo_recommend;
////                            }
////                            return 0;
//                        }
//                    })
                    .setCancelButton("取消")
                    .setOnMenuItemClickListener(new OnMenuItemClickListener<BottomMenu>() {
                        @Override
                        public boolean onClick(BottomMenu dialog, CharSequence text, int index) {
                            PopTip.show(text);
                            return false;
                        }
                    }).show();

        } else if (view.getId() == R.id.btn_bottom_reply) {
            BottomDialog.show(new OnBindView<BottomDialog>(R.layout.layout_custom_reply) {
                @Override
                public void onBind(final BottomDialog dialog, View v) {
                    btnReplyCommit = v.findViewById(R.id.btn_reply_commit);
                    editReplyCommit = v.findViewById(R.id.edit_reply_commit);
                    btnReplyCommit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            PopTip.show("提交内容：\n" + editReplyCommit.getText().toString());
                        }
                    });
                    editReplyCommit.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            editReplyCommit.requestFocus();
                            editReplyCommit.setFocusableInTouchMode(true);
                            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(editReplyCommit, InputMethodManager.RESULT_UNCHANGED_SHOWN);
                        }
                    }, 300);
                }
            }).setAllowInterceptTouch(false);


        } else if (view.getId() == R.id.btn_bottom_select_menu) {
            BottomMenu.show(new String[]{"拒绝", "询问", "始终允许", "仅在使用中允许"})
                    .setMessage("这里是权限确认的文本说明，这是一个演示菜单")
                    .setTitle("获得权限标题")
                    .setOnMenuItemClickListener(new OnMenuItemClickListener<BottomMenu>() {
                        @Override
                        public boolean onClick(BottomMenu dialog, CharSequence text, int index) {
                            selectMenuIndex = index;
                            PopTip.show(text);
                            return false;
                        }
                    })
                    .setSelection(selectMenuIndex);

        } else if (view.getId() == R.id.btn_customMessageDialog) {
            MessageDialog.show("这里是标题", "此对话框演示的是自定义对话框内部布局的效果", "确定", "取消")
                    .setCustomView(new OnBindView<MessageDialog>(R.layout.layout_custom_view) {
                        @Override
                        public void onBind(MessageDialog dialog, View v) {

                        }
                    });

        } else if (view.getId() == R.id.btn_customInputDialog) {
            InputDialog.show("这里是标题", "此对话框演示的是自定义对话框内部布局的效果", "确定", "取消")
                    .setCustomView(new OnBindView<MessageDialog>(R.layout.layout_custom_view) {
                        @Override
                        public void onBind(MessageDialog dialog, View v) {

                        }
                    });

        } else if (view.getId() == R.id.btn_customBottomMenu) {
            BottomMenu.show(new String[]{"新标签页中打开", "稍后阅读", "复制链接网址"})
                    .setMessage("http://www.kongzue.com/DialogX")
                    .setOnMenuItemClickListener(new OnMenuItemClickListener<BottomMenu>() {
                        @Override
                        public boolean onClick(BottomMenu dialog, CharSequence text, int index) {
                            PopTip.show(text);
                            return false;
                        }
                    })
                    .setCustomView(new OnBindView<BottomDialog>(R.layout.layout_custom_view) {
                        @Override
                        public void onBind(BottomDialog dialog, View v) {

                        }
                    });

        } else if (view.getId() == R.id.btn_customDialog) {
            CustomDialog.show(new OnBindView<CustomDialog>(R.layout.layout_custom_dialog) {
                @Override
                public void onBind(final CustomDialog dialog, View v) {
                }
            }).setFullScreen(true)
                    .setMaskColor(getResources().getColor(R.color.black30));

        } else if (view.getId() == R.id.btn_fullScreenDialog_login) {
            FullScreenDialog.show(new OnBindView<FullScreenDialog>(R.layout.layout_custom_view) {
                @Override
                public void onBind(FullScreenDialog dialog, View v) {
                }
            });


        } else if (view.getId() == R.id.btn_Time) {
            TimePickerDialog.show(new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format1 =
                            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    PopTip.show(format1.format(date));
                }
            }).setOnTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                @Override
                public void onTimeSelectChanged(Date date) {
                    SimpleDateFormat format1 =
                            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    PopTip.show(format1.format(date));
                }
            })
            ;

        }
    }
}
