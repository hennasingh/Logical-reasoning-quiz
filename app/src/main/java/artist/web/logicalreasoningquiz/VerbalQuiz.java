package artist.web.logicalreasoningquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.name;
import static artist.web.logicalreasoningquiz.LogicQuizActivity.USER_NAME;
import static artist.web.logicalreasoningquiz.NumberQuiz.MY_PREFS_FILE;

/**
 * Created by User on 4/9/2017.
 */

public class VerbalQuiz extends AppCompatActivity {
    TextView user_name;
    TextView timer;
    String nameU;
    RadioButton ques1_opt1, ques2_opt3, ques3_opt1;
    public static final String TIMER = "Timing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verbal_quiz_start);
        user_name = (TextView)findViewById(R.id.hello_user);
        timer = (TextView)findViewById(R.id.timing);

        ques1_opt1 = (RadioButton)findViewById(R.id.Q1_opt1);
        ques2_opt3 = (RadioButton)findViewById(R.id.Q2_opt3);
        ques3_opt1 = (RadioButton)findViewById(R.id.Q3_opt1);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_FILE,MODE_PRIVATE);
        nameU= prefs.getString(USER_NAME,"No Name Defined");
        user_name.setText(nameU);


        CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText("Timer: " +l / 1000 + " " + "sec");
            }

            @Override
            public void onFinish() {
                timer.setText(R.string.time_out);
                Toast.makeText(VerbalQuiz.this,"Time's Up!!" +"\n"+" Try Again!!", Toast.LENGTH_LONG).show();
                restart();

            }
        };
        countDownTimer.start();
    }

    public void showScore(View view){

        int score=0;

        Boolean firstQues = ques1_opt1.isChecked();
        Boolean thirdQues = ques3_opt1.isChecked();
        Boolean secondQues = ques2_opt3.isChecked();

        if(firstQues){
            score+=5;
        }
        if(thirdQues){
            score+=5;
        }
        if(secondQues){
            score+=5;
        }
        showResult(score);

    }

    private void showResult(int score){

        if(score==0){
            Toast.makeText(this,"Come on " +nameU+ " You scored "+ score+ " You can do better, Try Again!! ",Toast.LENGTH_LONG).show();
        }
        else if(score>=5&&score<=10){
            Toast.makeText(this, nameU+ " You scored "+ score+ " Better Luck Next Time ",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this," You did well " +nameU+ " You scored "+ score,Toast.LENGTH_LONG).show();
        }

        restart();

    }

    private void restart(){
        Intent restartQuiz = new Intent(this,LogicQuizActivity.class);
        restartQuiz.putExtra(USER_NAME,nameU);
        startActivity(restartQuiz);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_FILE,MODE_PRIVATE).edit();
        editor.putString(USER_NAME, user_name.getText().toString());
        editor.putString(TIMER, timer.getText().toString());
        editor.apply();
    }

}
