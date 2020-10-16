package dit.ksu.ac.appminisqlite;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseAdapter;
    import android.widget.TextView;
/**
 * Created by suksun on 7/1/2017 AD.
 */
public class Showmem extends BaseAdapter {
    private Context context;
    private String[] titleString, detailString, dateString;

    public Showmem(Context context, String[] titleString, String[] detailString,
                   String[] dateString) {
        this.context = context;
        this.titleString = titleString;
        this.detailString = detailString;
        this.dateString = dateString;
    }

    @Override
    public int getCount() {
        return titleString.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.litmemory,viewGroup,false);
        TextView titleTextView = (TextView)view1.findViewById(R.id.memtitle);
        titleTextView.setText(titleString[i]);
        TextView detailTextView = (TextView)view1.findViewById(R.id.memdetail);
        detailTextView.setText(detailString[i]);
        TextView dateTextView = (TextView)view1.findViewById(R.id.memdate);
        dateTextView.setText(dateString[i]);
        return view1;
    }
}
