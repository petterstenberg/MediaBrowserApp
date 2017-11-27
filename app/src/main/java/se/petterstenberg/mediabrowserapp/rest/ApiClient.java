package se.petterstenberg.mediabrowserapp.rest;

import se.petterstenberg.mediabrowserapp.models.ProgramsResponse;

public interface ApiClient {

	void getPrograms(int page, Callback<ProgramsResponse> callback);

    interface Callback<T>{
		void onSuccess(T response);
		void onFailure(Throwable t);
	}
}
