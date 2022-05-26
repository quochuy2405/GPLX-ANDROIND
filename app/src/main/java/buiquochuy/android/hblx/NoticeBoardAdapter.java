package buiquochuy.android.hblx;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

class NoticeBoardItem {

    private String boardName;
    private String boardDescription;
    public NoticeBoardItem( String boardName) {
        this.boardName = boardName;

    }
    public NoticeBoardItem() {
        this.boardName="No name";
    }
    public NoticeBoardItem(String boardName,String boardDescription) {
        this.boardName=boardName;
        this.boardDescription=boardDescription;
    }
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardName() {
        return boardName;
    }

    public String getBoardDescription() {
        return boardDescription;
    }

    public void setBoardDescription(String boardDescription) {
        this.boardDescription = boardDescription;
    }
}

public  class NoticeBoardAdapter extends ArrayAdapter<NoticeBoardItem> {

    private Activity context;
    public NoticeBoardAdapter(Activity context, int layoutID, List<NoticeBoardItem>
            objects) {
        super(context, layoutID, objects);
        this.context = context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

            convertView =
                    LayoutInflater.from(context).inflate(R.layout.listview_notice_board_item, null,
                            false);

        NoticeBoardItem noticeBoardItem = getItem(position);
        TextView tvBroadName = (TextView)
                convertView.findViewById(R.id.tv_board_title);
        tvBroadName.setText(noticeBoardItem.getBoardName());
// Get item
        return convertView;
    }
}
