package com.example.timestablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView timesTableListView;

    // Método Produção tabuada, isso evita duplicação de código
    public void generateTimesTable(int timesTable) {

        ArrayList<String> timesTableContent = new ArrayList<String>();

        // Loop for incremental
        for (int i = 1; i <= 10; i++){

            // Adicionar item no timesTableContent e converter Int p/ String
            timesTableContent.add(Integer.toString(i * timesTable));
        }

        // ArrayAdapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timesTableContent);

        //
        timesTableListView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Achar o SeekBar pelo Id
        SeekBar timesTableSeekBar = (SeekBar) findViewById(R.id.timesTableSeekBar);
        // Achar o ListView pelo Id
        timesTableListView = (ListView)findViewById(R.id.timesTableListView);

        // Especificar limite da tabuada (até 20)
        timesTableSeekBar.setMax(20);
        // Começar no meio (10);
        timesTableSeekBar.setProgress(10);

        // como não existe .setMin (infelizmente) é ncessário implementar lógica: "min-start-zero" ↓↓
        timesTableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // Declarar variáveis...
                int min = 1;
                int timesTable;
                //verificar progresso se o progresso for menor que um (min)
                if (progress < min) {

                    timesTable = min;
                    timesTableSeekBar.setProgress(min);

                }else{
                    timesTable = progress;
                }

                // Chamar o método public void Produção tabuada
                generateTimesTable(timesTable);
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Chamar o método public void Produção tabuada
        generateTimesTable(10);


    }
}