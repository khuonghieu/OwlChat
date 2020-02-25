package edu.temple.vsowlnetchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        final EditText editIpAddress = findViewById(R.id.editIpAddress);
        final EditText editPort = findViewById(R.id.editPort);
        Button confirmChange = findViewById(R.id.confirmIPandPort);
        Intent fromMain = getIntent();

        editIpAddress.setText(fromMain.getStringExtra("ip"));
        editPort.setText(fromMain.getStringExtra("port"));

        confirmChange.setOnClickListener(new View.OnClickListener() {

            String ipAddr = editIpAddress.getText().toString();
            int port = Integer.parseInt(editPort.getText().toString());

            @Override
            public void onClick(View v) {
                if (Patterns.IP_ADDRESS.matcher(ipAddr).matches() && port >= 0 && port <= 65535) {
                    Intent toMain = new Intent(SettingActivity.this, MainActivity.class);
                    toMain.putExtra("ip", ipAddr);
                    toMain.putExtra("port", String.valueOf(port));
                    SettingActivity.this.startActivity(toMain);
                } else {
                    Toast.makeText(SettingActivity.this, "IP or port not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}