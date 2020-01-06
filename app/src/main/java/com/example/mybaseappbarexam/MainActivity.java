package com.example.mybaseappbarexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends BaseActivity {
    private AsyncTask<String, String, String> mRecipientTask;
    private TextView tv_phone;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activateToolbar();
        name = "임고성";
        mRecipientTask = new RecipientTask().execute();

        tv_phone = findViewById(R.id.tv_phone);

//        tv_phone.setText(phone);
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
                    tv_phone.setText(phone);
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
