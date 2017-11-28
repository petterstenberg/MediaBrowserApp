package se.petterstenberg.mediabrowserapp.rest;

import android.support.annotation.NonNull;

import se.petterstenberg.mediabrowserapp.models.ProgramsResponse;

public interface ApiClient {

	void getPrograms(int page, @NonNull Callback<ProgramsResponse> callback);

    interface Callback<T>{
		void onSuccess(@NonNull T response);
		void onFailure(@NonNull Throwable t);
	}
}
