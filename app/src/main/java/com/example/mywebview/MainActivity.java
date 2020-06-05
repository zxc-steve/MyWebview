package com.example.mywebview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        Log.d("zyc Log", "signInWithEmail:User already sign-in");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webview = (WebView) findViewById(R.id.webview);
        final TextView textview = findViewById(R.id.textView);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        String email="message1@gmail.com";
        String password="123456";
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            user = mAuth.getCurrentUser();
                            Log.d("zyc Log", user.getEmail());
                            textview.setText("email: "+user.getEmail()+"\n"+
                                            "UID: "+user.getUid()+"\n"+
                                            "name: "+user.getDisplayName());

                            //updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("zyc Log", "signInWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    //Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                            // ...
                        }

                        // ...
                    }
                });
        //while(user==null){;}
        //TimeUnit.MINUTES.sleep(5);
        //Log.d("zyc Log", user.getEmail());

        //https://stackoverflow.com/questions/51124050/android-webview-not-logging-in
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        //webview.loadUrl("https://zxc-0429-js.web.app");

        //https://developer.chrome.com/multidevice/android/customtabs
        /*
        String url = "https://zxc-0429-js.web.app";
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
        */
    }
}
