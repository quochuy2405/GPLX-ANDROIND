package buiquochuy.android.hblx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    public HistoryAdapter historyAdapter ;
    private List<HistoryItem> names = new ArrayList<>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ListView listView = (ListView) findViewById(R.id.lvItems);
        HistoryItem h = new HistoryItem();
        h.setCategoryName("Hạng A1");
        for (int i = 0; i < 10; i++) {
            names.add(h);
        }

        historyAdapter = new HistoryAdapter(History.this, 1,names );

        listView.setAdapter(historyAdapter);


    }
}