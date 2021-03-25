package com.head.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
import com.head.titlebar.HeadTitleBar;
import com.head.titlebar.utils.KeyboardConflictCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected HeadTitleBar title;
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
    protected HeadTitleBar titlebar1;
    protected HeadTitleBar titlebar2;
    protected HeadTitleBar titlebar3;
    protected HeadTitleBar titlebar4;
    protected HeadTitleBar titlebar5;
    protected HeadTitleBar titlebar6;
    protected HeadTitleBar titlebar7;
    protected HeadTitleBar titlebar8;
    protected HeadTitleBar titlebar9;
    protected HeadTitleBar titlebar10;
    protected HeadTitleBar titlebar11;
    float progress;
    private int selectMenuIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(R.layout.activity_main);
        HeadDialog.globalStyle = MaterialStyle.style();
        HeadDialog.globalTheme = HeadDialog.THEME.LIGHT;
        HeadDialog.onlyOnePopTip = false;
        initView();

        initEvents();

        initTitle();


    }


    private void initTitle() {
        titlebar1.setListener(new HeadTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == HeadTitleBar.ACTION_LEFT_BUTTON
                        || action == HeadTitleBar.ACTION_LEFT_TEXT) {
                    PopTip.show("返回");
                }

            }
        });
        titlebar2.showCenterProgress();

        titlebar2.setListener(new HeadTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == HeadTitleBar.ACTION_LEFT_BUTTON
                        || action == HeadTitleBar.ACTION_LEFT_TEXT) {
                    titlebar2.dismissCenterProgress();
                }

            }
        });
//
        ImageView imageView = titlebar3.getLeftCustomView().findViewById(R.id.selected_search);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopTip.show("自定义标题");
            }
        });

        titlebar4.setListener(new HeadTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == HeadTitleBar.ACTION_RIGHT_BUTTON
                        || action == HeadTitleBar.ACTION_RIGHT_TEXT) {
                    PopTip.show("返回");
                }

            }
        });
        titlebar5.showCenterProgress();

        titlebar5.setListener(new HeadTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == HeadTitleBar.ACTION_RIGHT_BUTTON
                        || action == HeadTitleBar.ACTION_RIGHT_TEXT) {
                    titlebar5.dismissCenterProgress();
                }

            }
        });
//
        ImageView imageView1 = titlebar6.getRightCustomView().findViewById(R.id.selected_search);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopTip.show("自定义标题");
            }
        });


        titlebar10.setListener(new HeadTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == HeadTitleBar.ACTION_SEARCH_DELETE
                        || action == HeadTitleBar.ACTION_SEARCH_SUBMIT) {
                    PopTip.show("---");
                }

            }
        });


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

    private TextView btnReplyCommit;
    private EditText editReplyCommit;

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
            }).setCancelable(false);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    WaitDialog.dismiss();
                }
            }, 5000);

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
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
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

    private void initView() {
        title = (HeadTitleBar) findViewById(R.id.title);
        rbMaterial = (RadioButton) findViewById(R.id.rbMaterial);
        rbMaterial.setOnClickListener(MainActivity.this);
        rbIos = (RadioButton) findViewById(R.id.rbIos);
        rbIos.setOnClickListener(MainActivity.this);
        rGStyle = (RadioGroup) findViewById(R.id.rGStyle);
        rGStyle.setOnClickListener(MainActivity.this);
        rbLight = (RadioButton) findViewById(R.id.rbLight);
        rbLight.setOnClickListener(MainActivity.this);
        rbDark = (RadioButton) findViewById(R.id.rbDark);
        rbDark.setOnClickListener(MainActivity.this);
        rgTheme = (RadioGroup) findViewById(R.id.rgTheme);
        rgTheme.setOnClickListener(MainActivity.this);
        btMessageDialog = (TextView) findViewById(R.id.btMessageDialog);
        btMessageDialog.setOnClickListener(MainActivity.this);
        btnSelectDialog = (TextView) findViewById(R.id.btn_selectDialog);
        btnSelectDialog.setOnClickListener(MainActivity.this);
        btnInputDialog = (TextView) findViewById(R.id.btn_inputDialog);
        btnInputDialog.setOnClickListener(MainActivity.this);
        btnWaitDialog = (TextView) findViewById(R.id.btn_waitDialog);
        btnWaitDialog.setOnClickListener(MainActivity.this);
        btnWaitAndTipDialog = (TextView) findViewById(R.id.btn_waitAndTipDialog);
        btnWaitAndTipDialog.setOnClickListener(MainActivity.this);
        btnTipSuccess = (TextView) findViewById(R.id.btn_tipSuccess);
        btnTipSuccess.setOnClickListener(MainActivity.this);
        btnTipWarning = (TextView) findViewById(R.id.btn_tipWarning);
        btnTipWarning.setOnClickListener(MainActivity.this);
        btnTipError = (TextView) findViewById(R.id.btn_tipError);
        btnTipError.setOnClickListener(MainActivity.this);
        btnTipProgress = (TextView) findViewById(R.id.btn_tipProgress);
        btnTipProgress.setOnClickListener(MainActivity.this);
        btnPoptip = (TextView) findViewById(R.id.btn_poptip);
        btnPoptip.setOnClickListener(MainActivity.this);
        btnPoptipBigMessage = (TextView) findViewById(R.id.btn_poptip_bigMessage);
        btnPoptipBigMessage.setOnClickListener(MainActivity.this);
        btnBottomDialog = (TextView) findViewById(R.id.btn_bottom_dialog);
        btnBottomDialog.setOnClickListener(MainActivity.this);
        btnBottomMenu = (TextView) findViewById(R.id.btn_bottom_menu);
        btnBottomMenu.setOnClickListener(MainActivity.this);
        btnBottomReply = (TextView) findViewById(R.id.btn_bottom_reply);
        btnBottomReply.setOnClickListener(MainActivity.this);
        btnBottomSelectMenu = (TextView) findViewById(R.id.btn_bottom_select_menu);
        btnBottomSelectMenu.setOnClickListener(MainActivity.this);
        btnCustomMessageDialog = (TextView) findViewById(R.id.btn_customMessageDialog);
        btnCustomMessageDialog.setOnClickListener(MainActivity.this);
        btnCustomInputDialog = (TextView) findViewById(R.id.btn_customInputDialog);
        btnCustomInputDialog.setOnClickListener(MainActivity.this);
        btnCustomBottomMenu = (TextView) findViewById(R.id.btn_customBottomMenu);
        btnCustomBottomMenu.setOnClickListener(MainActivity.this);
        btnCustomDialog = (TextView) findViewById(R.id.btn_customDialog);
        btnCustomDialog.setOnClickListener(MainActivity.this);
        btnFullScreenDialogLogin = (TextView) findViewById(R.id.btn_fullScreenDialog_login);
        btnFullScreenDialogLogin.setOnClickListener(MainActivity.this);
        btnTime = (TextView) findViewById(R.id.btn_Time);
        btnTime.setOnClickListener(MainActivity.this);
        titlebar1 = (HeadTitleBar) findViewById(R.id.titlebar1);
        titlebar2 = (HeadTitleBar) findViewById(R.id.titlebar2);
        titlebar3 = (HeadTitleBar) findViewById(R.id.titlebar3);
        titlebar4 = (HeadTitleBar) findViewById(R.id.titlebar4);
        titlebar5 = (HeadTitleBar) findViewById(R.id.titlebar5);
        titlebar6 = (HeadTitleBar) findViewById(R.id.titlebar6);
        titlebar7 = (HeadTitleBar) findViewById(R.id.titlebar7);
        titlebar8 = (HeadTitleBar) findViewById(R.id.titlebar8);
        titlebar9 = (HeadTitleBar) findViewById(R.id.titlebar9);
        titlebar10 = (HeadTitleBar) findViewById(R.id.titlebar10);
        titlebar11 = (HeadTitleBar) findViewById(R.id.titlebar11);
    }
}
