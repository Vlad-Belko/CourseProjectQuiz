package com.example.courseproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;

    Button buttonStart;
    Button buttonAgain;
    String toast, toast_already;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toast_already = getString(R.string.text_reset_already);
        toast = getString(R.string.text_reset);
        buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });

        buttonAgain = findViewById(R.id.btn_again);
        buttonAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Подтвердите сброс прогресса");
                alertDialog.setMessage("Вы уверены, что хотите это сбросить прогресс игры?");
                // Обработчик на нажатие ДА
                alertDialog.setPositiveButton("ДА", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        // Код который выполнится после закрытия окна
                        try {
                            SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                            final int level = save.getInt("Level", 1);
                            if (level > 1){
                                SharedPreferences.Editor editor = save.edit();
                                editor.putInt("Level", 1);
                                editor.commit();
                                Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), toast_already, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e){

                        }
                    }
                });

                // Обработчик на нажатие НЕТ
                alertDialog.setNegativeButton("НЕТ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Код который выполнится после закрытия окна
                        Toast.makeText(getApplicationContext(), "Прогресс не сброшен", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

                // показываем Alert
                alertDialog.show();
            }
        });

        /*buttonAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                    final int level = save.getInt("Level", 1);
                    if (level > 1){
                        SharedPreferences.Editor editor = save.edit();
                        editor.putInt("Level", 1);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), toast_already, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){

                }
            }
        });*/

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*getWindow().setNavigationBarColor(getResources().getColor(R.color.main_blue));*/
        w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    //Системная кнопка "Назад" - начало

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Нажмите ещё раз чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }


    //Системная кнопка "Назад" - конец
}