package com.example.mybaseappbarexam;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    protected String phone;
    protected String manager;
    protected String rating;
    protected int id;
    private String pecipientName;

    protected Toolbar activateToolbar(){
        if(mToolbar == null){
            mToolbar = findViewById(R.id.app_bar);
            if(mToolbar != null){
                setSupportActionBar(mToolbar);
                ActionBar actionBar = getSupportActionBar();
                actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_home_white_24dp);
            }
        }
        return mToolbar;
    }


    protected void recipiquery(String name) {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:jtds:sqlserver://222.122.213.216/mashw08", "mashw08", "msts0850op");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Su_수급자기본정보 where 수급자명 = '"+name+"'");
            while (rs.next()) {
                phone = rs.getString("hp");
                pecipientName = name;
                manager = rs.getString("담당");
                rating = rs.getString("등급");
                id = rs.getInt("id");
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("name", pecipientName);
                startActivity(intent);
                return true;
            case R.id.action_m2:
                Intent intentM2 = new Intent(this, M2Activity.class);
                intentM2.putExtra("name", pecipientName);
                startActivity(intentM2);
                return true;
            case R.id.action_m3:

                break;
            case R.id.action_m4:

                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
