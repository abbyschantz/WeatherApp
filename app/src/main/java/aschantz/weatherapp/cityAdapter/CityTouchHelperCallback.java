package aschantz.weatherapp.cityAdapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by aschantz on 11/27/16.
 */
public class CityTouchHelperCallback extends ItemTouchHelper.Callback{

    private CityInterfaceTouchHelperAdapter cityTouchHelperAdapter;

    //wrote the private above then command n, constructor and implemented it
    public CityTouchHelperCallback(CityInterfaceTouchHelperAdapter cityTouchHelperAdapter) {
        this.cityTouchHelperAdapter = cityTouchHelperAdapter;
    }

    @Override
    public boolean isLongPressDragEnabled () {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled () {
        return true;
    }

    @Override
    public int getMovementFlags (RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder){
        //sets what directions are allowed
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove (RecyclerView recyclerView, RecyclerView.ViewHolder
            viewHolder, RecyclerView.ViewHolder target){

        //send back message to adapter that location of todo items has been changed
        cityTouchHelperAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());

        return true;
    }

    @Override
    public void onSwiped (RecyclerView.ViewHolder viewHolder,int direction){
        cityTouchHelperAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
