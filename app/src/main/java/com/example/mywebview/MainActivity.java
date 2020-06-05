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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        // test sorting list of map
        testSortedMaps_1();
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
        /*
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.loadUrl("https://zxc-0429-js.web.app/index_0.html");
        */
        //https://developer.chrome.com/multidevice/android/customtabs

        String url = "https://zxc-0429-js.web.app/index_0.html";
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));

    }
    public void testSortedMaps() {
        //https://stackoverflow.com/questions/5155952/sorting-a-list-of-mapstring-string
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "Josh");

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", "Anna");

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("name", "Bernie");

        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);

        Collections.sort(mapList, new Comparator<Map<String, Object>>() {
            public int compare(final Map<String, Object> o1, final Map<String, Object> o2) {
                return o1.get("name").toString().compareTo(o2.get("name").toString());
            }
        });
        /*
        Assert.assertEquals("Anna", mapList.get(0).get("name"));
        Assert.assertEquals("Bernie", mapList.get(1).get("name"));
        Assert.assertEquals("Josh", mapList.get(2).get("name"));
        */
    }
    public void testSortedMaps_1() {
        //https://stackoverflow.com/questions/5155952/sorting-a-list-of-mapstring-string
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "Josh");
        map1.put("UID","testUID");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "Anna");

        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "Bernie");

        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);

        Collections.sort(mapList, new Comparator<Map<String, Object>>() {
            public int compare(final Map<String, Object> o1, final Map<String, Object> o2) {
                return o1.get("name").toString().compareTo(o2.get("name").toString());
            }
        });
    }

    public void testSortedMaps_lambda() {
        //https://stackoverflow.com/questions/5155952/sorting-a-list-of-mapstring-string
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "Josh");
        map1.put("UID","testUID");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "Anna");

        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "Bernie");

        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);
        // lambda seems not working in this case, 2020/06/05. Give up the idea !!!
        //Comparator<Map<String,Object>> sortByName = Comparator.comparing(x -> x.get("Name").toString());
        //mapList.sort(sortByName);

        /*
        Collections.sort(mapList, new Comparator<Map<String, Object>>() {
            public int compare(final Map<String, Object> o1, final Map<String, Object> o2) {
                return o1.get("name").toString().compareTo(o2.get("name").toString());
            }
        });
        */
    }
    public void testSortedMaps_lambda_1() {
        //https://stackoverflow.com/questions/5155952/sorting-a-list-of-mapstring-string
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "Josh");
        map1.put("UID","testUID");

        Map<String, String> map2 = new HashMap<>();
        map2.put("name", "Anna");

        Map<String, String> map3 = new HashMap<>();
        map3.put("name", "Bernie");

        List<Map<String, String>> mapList = new ArrayList<>();
        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);

        //Comparator<Map<String,String>> sortByName = Comparator.comparing(x -> x.get("Name"));
        //mapList.sort(sortByName);

        /*
        Collections.sort(mapList, new Comparator<Map<String, Object>>() {
            public int compare(final Map<String, Object> o1, final Map<String, Object> o2) {
                return o1.get("name").toString().compareTo(o2.get("name").toString());
            }
        });
        */
    }
}
