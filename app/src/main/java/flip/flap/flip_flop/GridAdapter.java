package flip.flap.flip_flop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridAdapter extends BaseAdapter {

    Context context;
    private  final  int[]  cards;
    View view;
    LayoutInflater layoutInflater;

    public GridAdapter(Context context, int[] cards) {
        this.context = context;
        this.cards = cards;
    }

    @Override
    public int getCount() {

        return 4;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_item, null);
            ImageView imageView = view.findViewById(R.id.imageview);
            //Set the image of the cards
            imageView.setImageResource(cards[position]);

        }

        return view;
    }
}
