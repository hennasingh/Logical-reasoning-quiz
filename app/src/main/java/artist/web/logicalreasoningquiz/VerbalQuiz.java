package artist.web.logicalreasoningquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static artist.web.logicalreasoningquiz.LogicQuizActivity.USER_NAME;

/**
 * Created by User on 4/9/2017.
 */

public class VerbalQuiz extends AppCompatActivity {
    TextView user_name;
    TextView timer;
    String name;
    RadioButton ques1_opt1, ques2_opt3, ques3_opt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verbal_quiz_start);
        user_name = (TextView)findViewById(R.id.hello_user);
        timer = (TextView)findViewById(R.id.timing);

        ques1_opt1 = (RadioButton)findViewById(R.id.Q1_opt1);
        ques2_opt3 = (RadioButton)findViewById(R.id.Q2_opt3);
        ques3_opt1 = (RadioButton)findViewById(R.id.Q3_opt1);

        Intent intent = getIntent();
        name= intent.getStringExtra(USER_NAME);
        user_name.setText(name);


        CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText("Timer: " +l / 1000 + " " + "sec");
            }

            @Override
            public void onFinish() {
                timer.setText("TIME OUT");
                Toast.makeText(VerbalQuiz.this,"Time's Up!!" +"\n"+" Try Again!!", Toast.LENGTH_LONG);
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
            Toast.makeText(this,"Come on " +name+ " You scored "+ score+ " You can do better, Try Again!! ",Toast.LENGTH_LONG).show();
        }
        else if(score>=5&&score<=10){
            Toast.makeText(this, name+ " You scored "+ score+ " Better Luck Next Time ",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this," You did well " +name+ " You scored "+ score,Toast.LENGTH_LONG).show();
        }

        restart();

    }

    private void restart(){
        Intent restartQuiz = new Intent(this,LogicQuizActivity.class);
        startActivity(restartQuiz);
    }
}
