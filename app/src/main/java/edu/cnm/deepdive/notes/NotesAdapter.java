package edu.cnm.deepdive.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import edu.cnm.deepdive.notes.databinding.ItemNoteBinding;
import edu.cnm.deepdive.notes.model.entity.Note;
import java.util.List;

public class NotesAdapter extends Adapter<ViewHolder> {

  private final LayoutInflater inflater;
  private final List<Note> notes;

  public NotesAdapter(Context context, List<Note> notes) {
    this.notes = notes;
    inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
     ItemNoteBinding binding = ItemNoteBinding.inflate(inflater, viewGroup, false);
     return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ((Holder) holder).bind(notes.get(position));
  }

  @Override
  public int getItemCount() {
    return notes.size();
  }

  private static class Holder extends ViewHolder {

    private final ItemNoteBinding binding;

    public Holder(@NonNull ItemNoteBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      // TODO: 2025-02-13 Initialize any fields.
    }

    public void bind(Note note) {
      binding.title.setText(note.getTitle());
      binding.modifiedOn.setText(note.getModifiedOn().toString());
    }

  }

}
