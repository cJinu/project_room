package com.example.project_room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Stage1 extends Activity {
    Button btn, portal, sword, monster, safe, inven;
    TextView str1, str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.stage1);

        portal = (Button) findViewById(R.id.portal);
        portal.setVisibility(View.INVISIBLE); //포털 버튼은 안 보이게 초기 설정

        str1 = (TextView) findViewById(R.id.textView1);
        str1.setText("문을 열었더니 또 이상한 곳으로 들어왔다.\n여긴 어디지...?");
        str1.setVisibility(View.VISIBLE);
        str1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1.setVisibility(View.GONE);
            }
        });

        str2 = (TextView) findViewById(R.id.textView2);

        btn = (Button) findViewById(R.id.button); //버튼을 눌렀을 때 반응
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.st3_foundKey) { //열쇠를 발견했을 경우
                    portal.setVisibility(View.VISIBLE);
                    str2.setText("열쇠가 돌아간다!");
                    str2.setVisibility(View.VISIBLE);
                    str2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            portal.setVisibility(View.VISIBLE);
                            str2.setText("버튼을 누르니 포털이 나타났다.\n들어가보자");
                            str2.setVisibility(View.VISIBLE);
                            str2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    str2.setVisibility(View.GONE);
                                }
                            });
                        }
                    });
                } else { //버튼을 눌렀을 때 초기 반응
                    str2.setText("버튼이 작동하지 않는다.\n버튼을 작동시키려면 열쇠가 필요한 것 같다.\n근데 무슨 버튼이지..?");
                    str2.setVisibility(View.VISIBLE);
                    str2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            str2.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });

        sword = (Button) findViewById(R.id.sword); //검 버튼을 눌렀을 때 반응
        sword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str2.setText("검이 있다. 혹시 모르니 챙겨둬야 겠다.");
                str2.setVisibility(View.VISIBLE);
                str2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        str2.setVisibility(View.GONE);
                    }
                });
                sword.setVisibility(View.GONE);
                data.st3_foundSword = true;
            }
        });


        inven = (Button) findViewById(R.id.inventory); //인벤토리(아이템창) 버튼을 눌렀을 때 반응
        inven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Stage1.this, Inven_st3.class);
                startActivity(intent);
            }
        });

        portal.setOnClickListener(new View.OnClickListener() { //포털 버튼을 누르면 스테이지 클리어
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Stage1.this, Stage_final.class);
                startActivity(intent);
                finish();
            }
        });

        monster = (Button) findViewById(R.id.monster); // 몬스터 버튼을 눌렀을 때 반응
        monster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.st3_foundSword) { //검을 주웠을 경우 반응
                    monster.setVisibility(View.GONE);
                    data.st3_monsterDefeat = true;
                    data.st3_foundBone = true;

                    str2.setText("해골을 해치웠다.");
                    str2.setVisibility(View.VISIBLE);
                    str2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            str2.setText("숫자가 적혀 있는 뼈를 주웠다.\n\"1634\"\n무언가의 비밀번호인 듯 하다.");
                            str2.setVisibility(View.VISIBLE);
                            str2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    str2.setVisibility(View.GONE);
                                }
                            });
                        }
                    });

                } else { //검을 획득하지 않고 몬스터 버튼을 눌렀을 경우
                    str2.setText("해골이다!\n팔에 무언가 적혀있다.");
                    str2.setVisibility(View.VISIBLE);
                    str2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            str2.setText("팔에 적혀있는 것을 보려면 해골을 해치워야 할 거 같다.\n근처에 무기로 쓸만한 게 없을까?");
                            str2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    str2.setVisibility(View.GONE);
                                }
                            });
                        }
                    });
                }
            }
        });

        safe = (Button) findViewById(R.id.safe); //금고 버튼을 눌렀을 때 반응
        safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.st3_safeOpened) { //비밀번호가 맞아 금고를 열었을 경우
                    data.st3_foundKey = true; //열쇠 획득
                    str2.setText("금고를 열었다. 열쇠를 얻었다.");
                    str2.setVisibility(View.VISIBLE);
                    str2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            str2.setVisibility(View.GONE);
                        }
                    });
                } else { //초기 금고 버튼 반응 + 비밀번호 입력창으로 넘어감
                    str2.setText("비밀번호를 입력해야 금고를 열 수 있다.");
                    str2.setVisibility(View.VISIBLE);
                    str2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            str2.setVisibility(View.GONE);
                        }
                    });
                    Intent intent = new Intent(Stage1.this, st3_password.class);
                    startActivity(intent);
                }
            }
        });
    }
}

