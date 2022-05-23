package com.Dental.Check.Retrofit;

import com.Dental.Check.Entities.Event;
import com.Dental.Check.Entities.Patient;
import com.Dental.Check.Entities.User;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface INodeJS {
    @POST("register")
    @FormUrlEncoded
    Observable<String> registerUser ( @Field("name") String name,
                                      @Field("email") String email,
                                      @Field("password") String password,
                                      @Field("phone") String phone,
                                      @Field("role") String role
                                     );



    @POST("login")
    @FormUrlEncoded
    Observable<String> loginUser (@Field("email") String email,
                                 @Field("password") String password);

    @GET("/user/{email}")

    Call<User> getUser(@Path("email") String email);



    @POST("/addoperations")
    @FormUrlEncoded
    Observable<String> addoperations (@Field("Nompatient") String Nompatient,
                                        @Field("prenompatient") String prenompatient,
                                        @Field("numero") String numero,
                                        @Field("age") String age,
                                        @Field("operation") String operation,
                                        @Field("image") String image,
                                       @Field("state") String state

                                      );
    /*SaveEvent*/

    @POST("/addconsultationpatient")
    @FormUrlEncoded
    Observable<String> addconsultation (@Field("Nompatient") String Nompatient,
                                     @Field("prenompatient") String prenompatient,
                                     @Field("numero") String numero,
                                     @Field("time") String time,
                                     @Field("date") String date);








    @Multipart
    @POST("/upload")
    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @Part("upload") RequestBody name);

    @GET("/uploads/{upload}")
    Call<ResponseBody> getImage(@Path("upload") String n);





    @PUT("/UpdateProfil/{id}")
    @FormUrlEncoded
    Call<User> updateProfile (@Path("id") int id,
                              @Field("name") String type,
                              @Field("prenom") String model);



    @GET("/voisin")

    Call<List<User> > getAllVoisin();

    @GET("/consultationpatient")

    Call<List<Patient> > getAllpatient();


    @GET("/consultation")

    Call<List<Event>> getAllconsult();


    @GET("/consultation/{date}")

    Call<List<Event>> getconsultdate(@Path("date") String date);

    @DELETE("/delete/{id}")
    Call<Void> delete(@Path("id") int id_c);


    @PUT("/consultationupdate/{id}")

    Call<Event> updateconsult(@Path("id") int id_c, @Body Event event);
   /* @PUT("/patientupdate/{id}")
    Call<Patient> updatepatient(@Path("id") int id_c, @Body Patient patient);*/



}
