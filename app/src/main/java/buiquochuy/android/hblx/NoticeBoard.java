package buiquochuy.android.hblx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NoticeBoard extends AppCompatActivity {
    public NoticeBoardAdapter noticeBoardAdapter ;
    private List<NoticeBoardItem> names = new ArrayList<>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);
        ListView listView = (ListView) findViewById(R.id.lvItems);
        NoticeBoardItem noticeBoard = new NoticeBoardItem();
        noticeBoard.setBoardName("Bien Bao");
        noticeBoard.setBoardDescription("oker");
        for (int i = 0; i < 10; i++) {
            names.add(noticeBoard);
        }

        noticeBoardAdapter = new NoticeBoardAdapter(NoticeBoard.this, 1,names );

        listView.setAdapter(noticeBoardAdapter);


    }
}