package se.petterstenberg.mediabrowserapp.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import se.petterstenberg.mediabrowserapp.models.ProgramsResponse;

/**
 * Interface where all api endpoints is located.
 */
public interface ApiService {

	@GET("/api/v2/programs")
	Call<ProgramsResponse> getPrograms(@Query("page") int page);
}
