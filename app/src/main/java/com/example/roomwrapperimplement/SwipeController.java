package com.example.roomwrapperimplement;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.recyclerview.widget.ItemTouchHelper.Callback.makeMovementFlags;


public class SwipeController extends ItemTouchHelper {

    /**
     * Creates an ItemTouchHelper that will work with the given Callback.
     * <p>
     * You can attach ItemTouchHelper to a RecyclerView via
     * {@link #attachToRecyclerView(RecyclerView)}. Upon attaching, it will add an item decoration,
     * an onItemTouchListener and a Child attach / detach listener to the RecyclerView.
     *
     * @param callback The Callback which controls the behavior of this touch helper.
     */
    public SwipeController(@NonNull Callback callback) {
        super(callback);
    }


    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, LEFT | RIGHT);
    }


    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }


    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}