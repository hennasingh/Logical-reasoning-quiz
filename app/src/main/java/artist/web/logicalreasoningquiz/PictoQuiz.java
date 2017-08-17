package artist.web.logicalreasoningquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import artist.web.logicalreasoningquiz.databinding.PictoQuizStartBinding;

import static artist.web.logicalreasoningquiz.LogicQuizActivity.USER_NAME;
import static artist.web.logicalreasoningquiz.NumberQuiz.MY_PREFS_FILE;
import static artist.web.logicalreasoningquiz.VerbalQuiz.TIMER;

/**
 * Created by User on 4/9/2017.
 */

public class PictoQuiz extends AppCompatActivity {

    //EditText ques1, ques2, ques3, ques4, ques5;
    TextView timer, user_name;
    String name;

    PictoQuizStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.picto_quiz_start);
        user_name = (TextView)findViewById(R.id.hello_user);
        timer = (TextView)findViewById(R.id.timing);

        //ques1 =(EditText)findViewById(R.id.ques1);
        //ques2 =(EditText)findViewById(R.id.ques2);
        //ques3 =(EditText)findViewById(R.id.ques3);
        //ques4 =(EditText)findViewById(R.id.ques4);
        //ques5 =(EditText)findViewById(R.id.ques5);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_FILE,MODE_PRIVATE);
        name= prefs.getString(USER_NAME,"No Name Defined");
        user_name.setText(name);

        CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText("Timer: " +l / 1000 + " " + "sec");
            }

            @Override
            public void onFinish() {
                timer.setText(R.string.time_out);
                Toast.makeText(PictoQuiz.this,"Time's Up!!" +"\n"+" Try Again!!", Toast.LENGTH_LONG).show();
                restart();

            }
        };
        countDownTimer.start();
    }

    public void showScore(View view){

        int score=0;

        String firstQues = binding.ques1.getText().toString().toLowerCase();
        String secondQues = binding.ques2.getText().toString().toLowerCase();
        String thirdQues = binding.ques3.getText().toString().toLowerCase();
        String fourthQues = binding.ques4.getText().toString().toLowerCase();
        String fifthQues = binding.ques5.getText().toString().toLowerCase();

        if(firstQues.equals("hotdog")){
            score+=5;
        }
        if(secondQues.equals("cupcake")){
            score+=5;
        }
        if(thirdQues.equals("icebox")){
            score+=5;
        }
        if(fourthQues.equals("jellyfish")){
            score+=5;
        }
        if(fifthQues.equals("handshake")){
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
        restartQuiz.putExtra(USER_NAME,name);
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
