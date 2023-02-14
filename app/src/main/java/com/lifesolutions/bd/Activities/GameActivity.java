package com.lifesolutions.bd.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lifesolutions.bd.Models.QuestionModel;
import com.lifesolutions.bd.R;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView question,optionOne,optionTwo,optionThree,optionFour,scoreCounter,next,quit,timer,toolbarTitle;
    private int score = 0,questionNo = 1;
    private LinearLayout optionContainer,questionLayout;
    private int count = 0,dataFromIntent;
    private QuestionModel questionModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        optionContainer = findViewById(R.id.question_container);
        questionLayout = findViewById(R.id.question_layout);
        question = findViewById(R.id.question_question);
        optionOne = findViewById(R.id.option_one);
        optionTwo = findViewById(R.id.option_two);
        optionThree = findViewById(R.id.option_three);
        optionFour = findViewById(R.id.option_four);
        next = findViewById(R.id.next_question);
        scoreCounter = findViewById(R.id.score_counter);
        quit = findViewById(R.id.quit_question);
        timer = findViewById(R.id.timer_question);
        toolbarTitle = findViewById(R.id.toolbar_title_question);

        playAnimation(questionLayout,0);
        nextButtonAction(false);

        quit.setTranslationY(500);




        //Get Data from Intent
        /*Intent myIntent = getIntent();
        if (myIntent != null) {
            dataFromIntent = myIntent.getIntExtra("data",0);
        }*/

        //Next button click listener
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                playAnimation(questionLayout,0);
                questionNo++;
                nextButtonAction(false);

            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showQuitDialog();
            }
        });

        // Options Click Listener
        for (int i = 0; i < 4; i++){
            optionContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer((TextView)view);
                }
            });
        }
    }

    // Animation Start
    private void playAnimation(final View view, final int value){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(400).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

                if (value == 0 && count < 4){

                    playAnimation(optionContainer.getChildAt(count),0);
                    plusQuestions();
                    count++;
                    scoreCounter.setText(score+" / "+questionNo);

                }

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (value == 0){
                    playAnimation(view,1);
                    quit.animate().translationY(0).setDuration(300).setStartDelay(100).start();

                    setQuestion(plusQuestions());

                    // Options enable
                    enableOption(true);

                }

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        toolbarTitle.setText("Math Games");
    }


    public QuestionModel plusQuestions(){

        Random random = new Random();
        int start = random.nextInt(60);
        int end = random.nextInt(50);
        String ques = start+" + "+ end+" = ?";
        int res = start+end;
        String resF = String.valueOf(res);
        String op1 = String.valueOf(res+5);
        String op2 = String.valueOf(res-5);
        String op3 = String.valueOf(res+9);

        if (questionNo%2 == 0){
            if (res%2 == 0){

                if (start%2 == 0){
                    questionModel = new QuestionModel(ques,op1,op2,resF,op3,resF);
                    return questionModel;
                } else {
                    questionModel = new QuestionModel(ques,op2,resF,op3,op1,resF);
                    return questionModel;
                }

            } else {

                if (end%2 == 0){
                    questionModel = new QuestionModel(ques,resF,op2,op1,op3,resF);
                    return questionModel;
                } else {
                    questionModel = new QuestionModel(ques,op3,op2,op1,resF,resF);
                    return questionModel;
                }
            }
        }else {

            if (res%2 != 0){

                if (start%2 != 0){
                    questionModel = new QuestionModel(ques,op1,op2,resF,op3,resF);
                    return questionModel;
                } else {
                    questionModel = new QuestionModel(ques,op2,resF,op3,op1,resF);
                    return questionModel;
                }

            } else {

                if (end%2 != 0){
                    questionModel = new QuestionModel(ques,resF,op2,op1,op3,resF);
                    return questionModel;
                } else {
                    questionModel = new QuestionModel(ques,op3,op2,op1,resF,resF);
                    return questionModel;
                }
            }
        }
    }

    public void setQuestion(QuestionModel questionModel){
        question.setText(questionModel.getQuestion());
        optionOne.setText(questionModel.getOptionA());
        optionTwo.setText(questionModel.getOptionB());
        optionThree.setText(questionModel.getOptionC());
        optionFour.setText(questionModel.getOptionD());
    }

    @SuppressLint("SetTextI18n")
    private void checkAnswer(TextView selectedOption){
        enableOption(false);
        nextButtonAction(true);

        if ((selectedOption.getText().toString()).equals(questionModel.getAnswer())){

            selectedOption.setBackgroundResource(R.drawable.correct_answer_background);
            selectedOption.setTextColor(getResources().getColor(R.color.white));
            selectedOption.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_black_24dp, 0);
            score++;
            scoreCounter.setText(score+" / "+questionNo);


        } else {

            selectedOption.setBackgroundResource(R.drawable.wrong_answer_background);
            selectedOption.setTextColor(getResources().getColor(R.color.white));
            selectedOption.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_cancel_black_24dp, 0);

            startVibrate();

            for (int i = 0; i < 4 ;i++){

                if (((TextView)optionContainer.getChildAt(i)).getText().toString().equals(questionModel.getAnswer())){
                    TextView correctOption = (TextView) optionContainer.getChildAt(i);
                    correctOption.setBackgroundResource(R.drawable.correct_answer_background);
                    correctOption.setTextColor(getResources().getColor(R.color.white));
                    correctOption.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_black_24dp, 0);
                }
            }

        }


    }

    private void enableOption(boolean check){
        for (int i = 0; i < 4; i++){
            optionContainer.getChildAt(i).setEnabled(check);
            if (check){
                optionContainer.getChildAt(i).setBackgroundResource(R.drawable.button_backgroun_game);
                TextView textView = (TextView) optionContainer.getChildAt(i);
                textView.setTextColor(getResources().getColor(R.color.text_color_dark));
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        }
    }

    private void nextButtonAction(boolean enable){
        if (enable){

            next.setVisibility(View.VISIBLE);
            next.animate().translationY(0).setDuration(150).setStartDelay(20).start();

        } else {

            next.setVisibility(View.GONE);
            next.animate().translationY(600).setDuration(150).setStartDelay(20).start();

            quit.animate().translationX(0).setDuration(300).setStartDelay(20).start();
        }
    }

    public QuestionModel multiplicationQuestions(){

        Random random = new Random();
        int start = random.nextInt(15);
        int end = random.nextInt(10);
        String ques = start+" x "+ end+" = ?";
        int res = start*end;
        String resF = String.valueOf(res);
        String op1 = String.valueOf(res+5);
        String op2 = String.valueOf(res-5);
        String op3 = String.valueOf(res+9);

        if (start == 0 || end == 0){
            if (start == 0 && end == 0){
                questionModel = new QuestionModel(ques,String.valueOf(start),String.valueOf(end+2),op1,"1",resF);
                return questionModel;
            } else {
                if (start == 0){
                    questionModel = new QuestionModel(ques,String.valueOf(start),String.valueOf(end),op1,"1",resF);
                    return questionModel;
                } else {
                    questionModel = new QuestionModel(ques,String.valueOf(end),op1,"1",String.valueOf(start),resF);
                    return questionModel;
                }
            }
        } else {
            if (questionNo%2 == 0){

                if (res%2 == 0){

                    if (start%2 != 0){

                        questionModel = new QuestionModel(ques,op1,op2,resF,op3,resF);
                        return questionModel;

                    } else {

                        questionModel = new QuestionModel(ques,op2,resF,op3,op1,resF);
                        return questionModel;
                    }

                } else {

                    if (end%2 != 0){

                        questionModel = new QuestionModel(ques,resF,op2,op1,op3,resF);
                        return questionModel;

                    } else {

                        questionModel = new QuestionModel(ques,op3,op2,op1,resF,resF);
                        return questionModel;
                    }
                }
            }else {
                if (res%2 != 0){

                    if (start%2 == 0){

                        questionModel = new QuestionModel(ques,op1,op2,resF,op3,resF);
                        return questionModel;

                    } else {

                        questionModel = new QuestionModel(ques,op2,resF,op3,op1,resF);
                        return questionModel;
                    }

                } else {

                    if (end%2 == 0){

                        questionModel = new QuestionModel(ques,resF,op2,op1,op3,resF);
                        return questionModel;

                    } else {

                        questionModel = new QuestionModel(ques,op3,op2,op1,resF,resF);
                        return questionModel;
                    }
                }
            }
        }
    }

    private void startVibrate(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            assert v != null;
            v.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            assert v != null;
            v.vibrate(100);
        }
    }


    private void showQuitDialog(){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_alert_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView no = dialog.findViewById(R.id.no_dialog);
        TextView yes = dialog.findViewById(R.id.yes_dialog);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dialog.show();

    }

    @Override
    public void onBackPressed() {
        showQuitDialog();
    }
}
