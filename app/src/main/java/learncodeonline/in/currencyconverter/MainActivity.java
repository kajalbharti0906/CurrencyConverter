package learncodeonline.in.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Formatter;

public class MainActivity extends AppCompatActivity {

    Button euro,pound,dollar,yen,dong,bitcoin,rubel,ausdollar,candollar;
    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        euro = findViewById(R.id.euro);
        pound = findViewById(R.id.pound);
        dollar = findViewById(R.id.dollar);
        yen = findViewById(R.id.yen);
        dong = findViewById(R.id.dong);
        bitcoin = findViewById(R.id.bitcoin);
        rubel = findViewById(R.id.rubel);
        ausdollar = findViewById(R.id.ausdollar);
        candollar = findViewById(R.id.candollar);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        euro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String z = editText.getText().toString();
                if(TextUtils.isEmpty(z)){
                    editText.setError("Empty user Input");
                }else {
                    double n,k;

                    n = Double.parseDouble(z);
                    textView.setText(null);
                    Formatter formatter = new Formatter();
                    k = n*0.012;

                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    textView.setText(""+numberFormat.format(k));
                }
            }
        });
        pound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String z = editText.getText().toString();
                if(TextUtils.isEmpty(z)){
                    editText.setError("Empty user Input");
                }else {
                    double n,k;

                    n = Double.parseDouble(z);
                    textView.setText(null);
                    Formatter formatter = new Formatter();
                    k = n*0.0107;

                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    textView.setText(""+numberFormat.format(k));
            }
        }
    });
        dollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String z = editText.getText().toString();
                if (TextUtils.isEmpty(z)) {
                    editText.setError("Empty user Input");
                } else {
                    double n, k;

                    n = Double.parseDouble(z);
                    textView.setText(null);
                    Formatter formatter = new Formatter();
                    k = n * 0.014;

                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    textView.setText("" + numberFormat.format(k));
                }
            }
        });
        yen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String z = editText.getText().toString();
                if(TextUtils.isEmpty(z)){
                    editText.setError("Empty user Input");
                }else {
                    double n, k;

                    n = Double.parseDouble(z);
                    textView.setText(null);
                    Formatter formatter = new Formatter();
                    k = n * 1.54;

                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    textView.setText("" + numberFormat.format(k));
                }
            }
        });
        dong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String z = editText.getText().toString();
                if(TextUtils.isEmpty(z)){
                    editText.setError("Empty user Input");
                }else {
                    double n, k;

                    n = Double.parseDouble(z);
                    textView.setText(null);
                    Formatter formatter = new Formatter();
                    k = n * 322.58;

                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    textView.setText("" + numberFormat.format(k));
                }
            }
        });
        bitcoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String z = editText.getText().toString();
                if(TextUtils.isEmpty(z)){
                    editText.setError("Empty user Input");
                }else {
                    double n, k;

                    n = Double.parseDouble(z);
                    textView.setText(null);
                    Formatter formatter = new Formatter();
                    k = n * 0.0000017;

                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    textView.setText("" + numberFormat.format(k));
                }
            }
        });
        rubel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String z = editText.getText().toString();
                if(TextUtils.isEmpty(z)){
                    editText.setError("Empty user Input");
                }else {
                    double n, k;

                    n = Double.parseDouble(z);
                    textView.setText(null);
                    Formatter formatter = new Formatter();
                    k = n * 0.86;

                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    textView.setText("" + numberFormat.format(k));
                }
            }
        });
        ausdollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String z = editText.getText().toString();
                if(TextUtils.isEmpty(z)){
                    editText.setError("Empty user Input");
                }else {
                    double n, k;

                    n = Double.parseDouble(z);
                    textView.setText(null);
                    Formatter formatter = new Formatter();
                    k = n * 0.02;

                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    textView.setText("" + numberFormat.format(k));
                }
            }
        });
        candollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String z = editText.getText().toString();
                if(TextUtils.isEmpty(z)){
                    editText.setError("Empty user Input");
                }else {
                    double n, k;

                    n = Double.parseDouble(z);
                    textView.setText(null);
                    Formatter formatter = new Formatter();
                    k = n * 0.018;

                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    textView.setText("" + numberFormat.format(k));
                }
            }
        });
}
}
