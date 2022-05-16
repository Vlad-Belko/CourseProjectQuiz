package com.example.courseproject;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level8 extends AppCompatActivity {

    Dialog dialog, dialog_end;
    public int numLeft, numRight; // Переменные для левой и правой картинок
    Array array = new Array(); // Новый объект класса Array
    Random random = new Random();
    public int count = 0; // Счётчик правильных ответов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        // Создаём переменную text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level8);

        final ImageView img_left = findViewById(R.id.img_left);
        //Код, который скругляет углы левой картинки
        img_left.setClipToOutline(true);
        final ImageView img_right = findViewById(R.id.img_right);
        //Код, который скругляет углы правой картинки
        img_right.setClipToOutline(true);
        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);

        //Развернуть игру на весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //Развернуть игру на весь экран - конец

        //Вызов диалогового окна
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialog.setContentView(R.layout.preview_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон
        dialog.setCancelable(false); //окно нельзя закрыть кнопкой "Назад"

        // Устанавливаем картинку в диалоговое окно - начало
        ImageView previewImage = dialog.findViewById(R.id.preview_img);
        previewImage.setImageResource(R.drawable.preview_img_8);
        // Устанавливаем картинку в диалоговое окно - конец

        // Устанавливеаем описание задания - начало
        TextView textDescription = dialog.findViewById(R.id.text_description);
        textDescription.setText(R.string.level_8);
        // Устанавливеаем описание задания - конец

        // Устанавливаем фон диалогового окна - начало
        LinearLayout dialog_back = dialog.findViewById(R.id.dialog_background);
        dialog_back.setBackgroundResource(R.drawable.preview_background_one);
        // Устанавливаем фон диалогового окна - конец

        //Кнопка закрывающая диалоговое окно - начало
        TextView btnclose = dialog.findViewById(R.id.btn_close);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Обрабатываем нажатие кнопки - начало
                try {
                    //Вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level8.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                    //Вернуться назад к выбору уровня - конец
                } catch (Exception e) {

                }
                //dialog.dismiss(); // Закрываем диалогоое окно
                //Обрабатываем нажатие кнопки - конец
            }
        });
        //Кнопка закрывающая диалоговое окно - конец

        // Кнопка "Продолжить" - начало
        Button btn_continue = dialog.findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        // Кнопка "Продолжить" - конец

        dialog.show();

        //___________________________________________________________________
        //Вызов диалогового окна в конце игры
        dialog_end = new Dialog(this);
        dialog_end.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialog_end.setContentView(R.layout.dialog_end);
        dialog_end.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон
        dialog_end.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog_end.setCancelable(false); //окно нельзя закрыть кнопкой "Назад"

        // Текст после уровня - начало
        TextView textDescriptionEnd = dialog_end.findViewById(R.id.text_description_end);
        textDescriptionEnd.setText(R.string.level_8_end);
        // Текст после уровня - конец

        img_left.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        img_right.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        //Кнопка закрывающая диалоговое окно - начало
        TextView btnclose2 = dialog_end.findViewById(R.id.btn_close);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Обрабатываем нажатие кнопки - начало
                try {
                    //Вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level8.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                    //Вернуться назад к выбору уровня - конец
                } catch (Exception e) {

                }
                dialog_end.dismiss(); // Закрываем диалогоое окно
                //Обрабатываем нажатие кнопки - конец
            }
        });
        //Кнопка закрывающая диалоговое окно - конец

        // Кнопка "Продолжить" - начало
        Button btn_continue2 = dialog_end.findViewById(R.id.btn_continue);
        btn_continue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level8.this, Level9.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e){

                }
                dialog_end.dismiss();
            }
        });
        // Кнопка "Продолжить" - конец

        //___________________________________________________________________

        //Кнопка "Назад" - начало
        Button btn_back = findViewById(R.id.buttonBack);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //Вернуться к выбору уровня - начало
                    Intent intent = new Intent(Level8.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                    //Вернуться к выбору уровня - конец
                } catch (Exception e) {

                }
            }
        });
        //Кнопка "Назад" - конец

        // Массив для прогресса игры
        final int[] progress = {
                R.id.point1,  R.id.point2,  R.id.point3,  R.id.point4,  R.id.point5,
                R.id.point6,  R.id.point7,  R.id.point8,  R.id.point9,  R.id.point10,
                R.id.point11,  R.id.point12,  R.id.point13,  R.id.point14,  R.id.point15,
                R.id.point16,  R.id.point17,  R.id.point18,  R.id.point19,  R.id.point20,
        };

        //  Подключаем анимацию - начало
        final Animation a = AnimationUtils.loadAnimation(Level8.this, R.anim.alpfa);
        //  Подключаем анимацию - конец

        numLeft = random.nextInt(20);
        img_left.setImageResource(array.images8[numLeft]);
        text_left.setText(array.texts8[numLeft]);

        numRight = random.nextInt(20);

        while (array.strong8[numLeft] == array.strong8[numRight]){
            numRight = random.nextInt(20);
        }
        img_right.setImageResource(array.images8[numRight]);
        text_right.setText(array.texts8[numRight]);

        // Обработка нажатия на левую картинку - начало
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // Условие нажатия на картинку - начало
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    // Касание - начало
                    if(array.strong8[numLeft] < array.strong8[numRight]){
                        img_left.setImageResource(R.drawable.img_true);
                    } else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                    img_right.setEnabled(false);
                    // Касание - конец

                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    // Отпустил - начало
                    if (array.strong8[numLeft] < array.strong8[numRight]){
                        if (count < 20) {
                            count++;
                        }

                        // Закрашиваем прогресс серым цветом - начало
                        for (int i = 0; i < 20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашиваем прогресс серым цветом - конец

                        // Определяем правильные ответы и закрашиваем зелёным - начало
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        // Определяем правильные ответы и закрашиваем зелёным - конец
                    } else {
                        if (count > 0){
                           if (count == 1){
                               count = 0;
                           } else {
                               count = count - 2;
                           }
                        }
                        // Закрашиваем прогресс серым цветом - начало
                        for (int i = 0; i < 19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашиваем прогресс серым цветом - конец

                        // Определяем правильные ответы и закрашиваем зелёным - начало
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        // Определяем правильные ответы и закрашиваем зелёным - конец
                    }
                    // Отпустил - конец
                    if (count ==  20) {
                        // Выход из уровня
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if (level > 8){
                            //пусто
                        } else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 9);
                            editor.commit();
                        }
                        dialog_end.show();
                    } else {
                        numLeft = random.nextInt(20);
                        img_left.setImageResource(array.images8[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts8[numLeft]);

                        numRight = random.nextInt(20);

                        while (array.strong8[numLeft] == array.strong8[numRight]){
                            numRight = random.nextInt(20);
                        }
                        img_right.setImageResource(array.images8[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts8[numRight]);

                        img_right.setEnabled(true); // Включаем снова правую картинку
                    }
                }
                // Условие нажатия на картинку - конец
                return true;
            }
        });
        // Обработка нажатия на левую картинку - конец

        // Обработка нажатия на правую картинку - начало
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // Условие нажатия на картинку - начало
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    // Касание - начало
                    if(array.strong8[numLeft] > array.strong8[numRight]){
                        img_right.setImageResource(R.drawable.img_true);
                    } else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                    img_left.setEnabled(false); // Блокируем левую картинку
                    // Касание - конец

                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    // Отпустил - начало
                    if (array.strong8[numLeft] > array.strong8[numRight]){
                        if (count < 20) {
                            count++;
                        }

                        // Закрашиваем прогресс серым цветом - начало
                        for (int i = 0; i < 20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашиваем прогресс серым цветом - конец

                        // Определяем правильные ответы и закрашиваем зелёным - начало
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        // Определяем правильные ответы и закрашиваем зелёным - конец
                    } else {
                        if (count > 0){
                            if (count == 1){
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                        }
                        // Закрашиваем прогресс серым цветом - начало
                        for (int i = 0; i < 19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашиваем прогресс серым цветом - конец

                        // Определяем правильные ответы и закрашиваем зелёным - начало
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        // Определяем правильные ответы и закрашиваем зелёным - конец
                    }
                    // Отпустил - конец
                    if (count ==  20) {
                        // Выход из уровня
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if (level > 8){
                            //пусто
                        } else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 9);
                            editor.commit();
                        }
                        dialog_end.show();
                    } else {
                        numLeft = random.nextInt(20);
                        img_left.setImageResource(array.images8[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts8[numLeft]);

                        numRight = random.nextInt(20);

                        while (array.strong8[numLeft] == array.strong8[numRight]){
                            numRight = random.nextInt(20);
                        }
                        img_right.setImageResource(array.images8[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts8[numRight]);

                        img_left.setEnabled(true); // Включаем снова левую картинку
                    }
                }
                // Условие нажатия на картинку - конец
                return true;
            }
        });
        // Обработка нажатия на правую картинку - конец
    }

    // Системная кнопка "Назад" - начало
    @Override
    public void onBackPressed(){
        try {
            //Вернуться к выбору уровня - начало
            Intent intent = new Intent(Level8.this, GameLevels.class);
            startActivity(intent);
            finish();
            //Вернуться к выбору уровня - конец
        } catch (Exception e){

        }
    }
    // Системная кнопка "Назад" - конец
}