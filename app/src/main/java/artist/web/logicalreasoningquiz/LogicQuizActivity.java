package artist.web.logicalreasoningquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static artist.web.logicalreasoningquiz.NumberQuiz.MY_PREFS_FILE;


/**
 * Created by User on 4/8/2017.
 */

public class LogicQuizActivity extends AppCompatActivity {

    TextView user_name;
     public static final String USER_NAME ="user_name";
    String nameValue;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_category);
        user_name = (TextView)findViewById(R.id.hello_user);

        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
             nameValue= extras.getString(USER_NAME);
            user_name.setText(nameValue);
        }

    }

    public void startNumberQuiz(View view){

        Intent numberIntent = new Intent(this,NumberQuiz.class);
        numberIntent.putExtra(USER_NAME,nameValue);
        startActivity(numberIntent);

    }

    public void startVerbalQuiz(View view){
        Intent verbalIntent = new Intent(this,VerbalQuiz.class);
        verbalIntent.putExtra(USER_NAME,nameValue);
        startActivity(verbalIntent);

    }

    public void startPictoQuiz(View view){
        Intent pictoIntent = new Intent(this,PictoQuiz.class);
        pictoIntent.putExtra(USER_NAME,nameValue);
        startActivity(pictoIntent);
    }

    public void startAnalogyQuiz(View view){
        Intent analogyIntent = new Intent(this,AnalogyQuiz.class);
        analogyIntent.putExtra(USER_NAME,nameValue);
        startActivity(analogyIntent);

    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_FILE,MODE_PRIVATE).edit();
        editor.putString(USER_NAME, nameValue);
         editor.apply();
    }

}
