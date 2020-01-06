package com.example.mybaseappbarexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class M2Activity extends BaseActivity {
    private AsyncTask<String, String, String> mRecipientTask;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m2);

        final Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        mRecipientTask = new RecipientTask().execute();

    }

    public class RecipientTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... strings) {
            if (isCancelled())
                return (null);
            recipiquery(name);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView tv_name = findViewById(R.id.tv_name);
                    tv_name.setText(rating);
                }
            });


            return null;
        }

        protected void onPostExecute(String result) {
        }


        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
