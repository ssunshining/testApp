# testApp
1以建造者模式 封装dialog
简单好用，几行代码就可以设置弹出一个通用信息展示框

 new DialogBuilder(MainActivity.this)
                        .setTitle("测试")
                        .setContent("内容")
                        .setLeftVisible(false)
                        .setContentViewId(R.layout.dialog_tip)
                        .createTip()
                        .setDialogInterface(new com.example.testapp.dialog.DialogInterface.onClickConfirmDialog() {
                            @Override
                            public void onClickCofirm(Dialog dialog) {
                                dialog.dismiss();
                            }
                        });
            
