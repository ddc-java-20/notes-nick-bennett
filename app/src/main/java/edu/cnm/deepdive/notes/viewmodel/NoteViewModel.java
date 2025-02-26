package edu.cnm.deepdive.notes.viewmodel;

import android.net.Uri;
import android.util.Log;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.cnm.deepdive.notes.model.entity.Note;
import edu.cnm.deepdive.notes.service.NoteRepository;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import java.util.List;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

@HiltViewModel
public class NoteViewModel extends ViewModel implements DefaultLifecycleObserver {

  private final NoteRepository noteRepository;
  private final MutableLiveData<Long> noteId;
  private final LiveData<Note> note;
  private final MutableLiveData<Uri> captureUri; 
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  
  private Uri pendingCaptureUri;

  @Inject
  NoteViewModel(NoteRepository noteRepository) {
    this.noteRepository = noteRepository;
    noteId = new MutableLiveData<>();
    note = Transformations.switchMap(noteId, noteRepository::get);
    captureUri = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  public void save(Note note) {
    throwable.setValue(null); // Will be invoked from controller on UI thread.
    noteRepository
        .save(note)
        .ignoreElement()
        .subscribe(
            () -> {},
            this::postThrowable,
            pending
        );
  }

  public void fetch(long noteId) {
    throwable.setValue(null);
    // TODO: 2025-02-18 Consider this.note.setValue(null) 
    this.noteId.setValue(noteId);
  }

  public void delete(Note note) {
    throwable.setValue(null);
    noteRepository
        .remove(note)
        .subscribe(
            () ->{},
            this::postThrowable,
            pending
        );
  }

  public void confirmCapture(boolean success) {
    captureUri.setValue(success ? pendingCaptureUri : null);
    pendingCaptureUri = null;
  }
  
  public void clearCaptureUri() {
    captureUri.setValue(null);
  }
  
  public LiveData<Long> getNoteId() {
    return noteId;
  }

  public LiveData<Note> getNote() {
    return note;
  }

  public LiveData<List<Note>> getNotes() {
    return noteRepository.getAll();
  }

  public LiveData<Uri> getCaptureUri() {
    return captureUri;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void setPendingCaptureUri(Uri pendingCaptureUri) {
    this.pendingCaptureUri = pendingCaptureUri;
  }

  @Override
  public void onStop(@NotNull LifecycleOwner owner) {
    pending.clear();
    DefaultLifecycleObserver.super.onStop(owner);
  }

  private void postThrowable(Throwable throwable) {
    Log.e(NoteViewModel.class.getSimpleName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
  }

}
