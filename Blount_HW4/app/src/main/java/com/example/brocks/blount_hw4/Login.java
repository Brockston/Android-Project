package com.example.brocks.blount_hw4;

/**
 * Created by Brocks on 3/15/2017.
 */


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements View.OnClickListener {
    private static final String TAG = "Login";
    private static final int REQUEST_SIGNUP = 0;
    EditText email, password;
    TextView signup;
    Button signin;
    String em, pass;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.Email);
        email.setOnClickListener(this);
        password = (EditText) findViewById(R.id.Password);
        password.setOnClickListener(this);
        signin = (Button) findViewById(R.id.Signin);
        signin.setOnClickListener(this);
        signup = (TextView) findViewById(R.id.Signup);
        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.Signin) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (!isFinishing()){

                        new AlertDialog.Builder(Login.this)
                                .setTitle("Warning/Disclaimer")
                                .setMessage("This is a disclaimer stating that anything shown on this will never be shared nor used, unless notifed by Brockston Blount and Alexis Cofield")
                                .setCancelable(false)
                                .setPositiveButton("I, Agree", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        login();
                                    }
                                }).show();
                    }
                }
            });


        }
        if(view.getId() == R.id.Signup) {
            Intent intent = new Intent(getApplicationContext(), Signup.class);
            startActivityForResult(intent, REQUEST_SIGNUP);
        }
    }


    public void login() {
        if (!validate()) {
            onLoginFailed();
            return;

        }


        signin.setEnabled(false);


        final ProgressDialog progressDialog = new ProgressDialog(Login.this, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        em = email.getText().toString();
        pass = password.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        signin.setEnabled(true);
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed: Try Again", Toast.LENGTH_LONG).show();
        signin.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        em = email.getText().toString();
        pass = password.getText().toString();

        if (em.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (pass.isEmpty() || password.length() < 4 || password.length() > 8) {
            password.setError(" Must be between 4 and 8 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
}




