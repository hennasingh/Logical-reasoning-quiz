package artist.web.logicalreasoningquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


/**
 * Created by User on 4/8/2017.
 */

public class LogicQuizActivity extends AppCompatActivity {

    TextView user_name;
     public static final String USER_NAME ="user_name";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_category);
        user_name = (TextView)findViewById(R.id.hello_user);

        Intent intent = getIntent();
        String name= intent.getStringExtra(USER_NAME);
        user_name.setText(name);

    }

    public void startNumberQuiz(View view){

        Intent numberIntent = new Intent(this,NumberQuiz.class);
        numberIntent.putExtra(USER_NAME,user_name.getText().toString());
        startActivity(numberIntent);

    }

    public void startVerbalQuiz(View view){
        Intent verbalIntent = new Intent(this,VerbalQuiz.class);
        verbalIntent.putExtra(USER_NAME,user_name.getText().toString());
        startActivity(verbalIntent);

    }

    public void startPictoQuiz(View view){
        Intent pictoIntent = new Intent(this,PictoQuiz.class);
        pictoIntent.putExtra(USER_NAME,user_name.getText().toString());
        startActivity(pictoIntent);
    }

    public void startAnalogyQuiz(View view){
        Intent analogyIntent = new Intent(this,AnalogyQuiz.class);
        analogyIntent.putExtra(USER_NAME,user_name.getText().toString());
        startActivity(analogyIntent);

    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(USER_NAME, user_name.getText().toString());
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {

        user_name = (TextView)findViewById(R.id.hello_user);
        user_name.setText(savedInstanceState.getString(USER_NAME));

    }

}
