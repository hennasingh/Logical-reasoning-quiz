package artist.web.logicalreasoningquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import static artist.web.logicalreasoningquiz.LogicQuizActivity.USER_NAME;
import static artist.web.logicalreasoningquiz.VerbalQuiz.TIMER;

/**
 * Created by User on 4/9/2017.
 */

public class NumberQuiz extends AppCompatActivity {

    TextView user_name;
    TextView timer;
    String name;

    CheckBox ques1_opt1, ques1_opt2, ques1_opt3, ques1_opt4;
    CheckBox ques2_opt1, ques2_opt2, ques2_opt3, ques2_opt4;
    CheckBox ques3_opt1, ques3_opt2, ques3_opt3, ques3_opt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_quiz_start);
        user_name = (TextView)findViewById(R.id.hello_user);
        timer = (TextView)findViewById(R.id.timing);

        ques1_opt1 =(CheckBox)findViewById(R.id.Q1_opt1);
        ques1_opt2 = (CheckBox)findViewById(R.id.Q1_opt2);
        ques1_opt3 =(CheckBox)findViewById(R.id.Q1_opt3);
        ques1_opt4 = (CheckBox)findViewById(R.id.Q1_opt4);

        ques2_opt1 =(CheckBox)findViewById(R.id.Q2_opt1);
        ques2_opt2 = (CheckBox)findViewById(R.id.Q2_opt2);
        ques2_opt3 =(CheckBox)findViewById(R.id.Q2_opt3);
        ques2_opt4 = (CheckBox)findViewById(R.id.Q2_opt4);

        ques3_opt1 =(CheckBox)findViewById(R.id.Q3_opt1);
        ques3_opt2 = (CheckBox)findViewById(R.id.Q3_opt2);
        ques3_opt3 =(CheckBox)findViewById(R.id.Q3_opt3);
        ques3_opt4 = (CheckBox)findViewById(R.id.Q3_opt4);



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
                Toast.makeText(NumberQuiz.this,"Time's Up!!" +"\n"+" Try Again!!", Toast.LENGTH_LONG);
               restart();

            }
        };
        countDownTimer.start();
    }

    public void showScore(View view){

        int score=0;

        Boolean firstQues_opt1 = ques1_opt1.isChecked();
        Boolean firstQues_opt2 = ques1_opt2.isChecked();
        Boolean firstQues_opt3 = ques1_opt3.isChecked();
        Boolean firstQues_opt4 = ques1_opt4.isChecked();

        Boolean secondQues_opt1 = ques2_opt1.isChecked();
        Boolean secondQues_opt2 = ques2_opt2.isChecked();
        Boolean secondQues_opt3 = ques2_opt3.isChecked();
        Boolean secondQues_opt4 = ques2_opt4.isChecked();

        Boolean thirdQues_opt1 = ques3_opt1.isChecked();
        Boolean thirdQues_opt2 = ques3_opt2.isChecked();
        Boolean thirdQues_opt3 = ques3_opt3.isChecked();
        Boolean thirdQues_opt4 = ques3_opt4.isChecked();


        if(!firstQues_opt1 &&firstQues_opt2 &&!firstQues_opt3 &&firstQues_opt4){
            score+=5;
        }
        if(thirdQues_opt1 &&thirdQues_opt2 &&thirdQues_opt3 &&!thirdQues_opt4){
            score+=5;
        }
        if(!secondQues_opt1&&secondQues_opt2&&!secondQues_opt3&&secondQues_opt4){
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

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(USER_NAME, user_name.getText().toString());
        outState.putString(TIMER, timer.getText().toString());
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {

        user_name = (TextView)findViewById(R.id.hello_user);
        timer = (TextView)findViewById(R.id.timing);
        user_name.setText(savedInstanceState.getString(USER_NAME));
        timer.setText(savedInstanceState.getString(TIMER));

    }


}
