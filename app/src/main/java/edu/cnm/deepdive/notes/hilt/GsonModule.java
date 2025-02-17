package edu.cnm.deepdive.notes.hilt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class GsonModule {

  @Provides
  @Singleton
  Gson provideGson() {
    return new GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create();
  }
  
}
