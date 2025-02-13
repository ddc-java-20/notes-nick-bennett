package edu.cnm.deepdive.notes;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.notes.NotesAdapter.Holder;

public class NotesAdapter extends RecyclerView.Adapter<Holder> {
  
  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return null; // TODO: 2025-02-13 Create and return an instance of Holder. 
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int i) {
    // TODO: 2025-02-13 Invoke holder.bind with the object in position i. 
  }

  @Override
  public int getItemCount() {
    return 0; // TODO: 2025-02-13 Return the number of Note instances that can be displayed in this view. 
  }

  static class Holder extends RecyclerView.ViewHolder {
    
    public Holder(@NonNull View itemView) {
      super(itemView);
      // TODO: 2025-02-13 Initialize any fields. 
    }
    
    public void bind(Object item) {
      // TODO: 2025-02-13 Use data from item to populate widgets in itemView. 
    }
    
  }
  
}
