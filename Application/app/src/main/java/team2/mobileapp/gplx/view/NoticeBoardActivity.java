package team2.mobileapp.gplx.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Volley.model.NoticeBoard;
import team2.mobileapp.gplx.Volley.service.NoticeBoardService;

public class NoticeBoardActivity extends AppCompatActivity {
    private NoticeBoardAdapter noticeBoardAdapter;
    private ArrayList<NoticeBoard> names = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);
        listView = findViewById(R.id.lvItems);

        final NoticeBoardService noticeBoardService = new NoticeBoardService(this);
        ShowBoard(noticeBoardService);
        try {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    NoticeBoard item = (NoticeBoard) listView.getItemAtPosition(position);
                    Intent intent = new Intent(NoticeBoardActivity.this, DetailsNoticeBoardActivity.class);
                    intent.putExtra("ID", item.getId());
                    startActivity(intent);
                }
            });
        } catch (Exception ex) {
            Log.d("Error: ", ex.getMessage());
        }
    }

    private void ShowBoard(NoticeBoardService noticeBoardService) {
        noticeBoardService.GetAll(new NoticeBoardService.GetALLBoardCallBack() {
            @Override
            public void onError(String message) {
                Toast.makeText(NoticeBoardActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<NoticeBoard> boards) {

                for (int i = 0; i < boards.size(); i++) {
                    NoticeBoard noticeBoard = new NoticeBoard();
                    noticeBoard.setId(boards.get(i).getId());
                    noticeBoard.setType(boards.get(i).getType());
                    noticeBoard.setBoardCode(boards.get(i).getBoardCode());
                    noticeBoard.setBoardName(boards.get(i).getBoardName());
                    noticeBoard.setBoardDescription(boards.get(i).getBoardDescription());
                    noticeBoard.setPhoto(boards.get(i).getPhoto());
                    Log.i("NoticeBoard", noticeBoard.toString());
                    names.add(noticeBoard);
                }
                noticeBoardAdapter = new NoticeBoardAdapter(NoticeBoardActivity.this, 1, names);

                listView.setAdapter(noticeBoardAdapter);
            }
        });
    }
}