package android.ebozkurt.com.cs308ticket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.ebozkurt.com.cs308ticket.domain.User;
import android.ebozkurt.com.cs308ticket.network.RetrofitBuilder;
import android.ebozkurt.com.cs308ticket.network.TicketApiInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.activity_login_email_edittext);
        password = (EditText) findViewById(R.id.activity_login_password_edittext);
        login = (Button) findViewById(R.id.activity_login_login_button);

        email.setText("a@a.com");
        password.setText("1");


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(email.getText().toString().trim(), password.getText().toString().trim());
                TicketApiInterface apiService = RetrofitBuilder.returnService();

                Call<ResponseBody> call = apiService.loginUser(user);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.i("dev", Integer.toString(response.code()));
                        try {

                            if (response.code() == 200) {
                                String jwt = new String(response.body().bytes());
                                Log.i("dev", jwt);

                                SharedPreferences sharedPref = getSharedPreferences("keys", Context.MODE_PRIVATE);
                                final SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("jwt", "Bearer " + jwt);

                                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                String key = "secretkey";
                                // Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();

                                String[] tokenParts = jwt.split("\\.");
                                Log.i("dev", tokenParts[1]);
                                byte[] content = Base64.decode(tokenParts[1], Base64.DEFAULT);
                                String payload = new String(content, "UTF-8");
                                JSONObject payloadObject = new JSONObject(payload);
                                String role = payloadObject.getString("roles");

                                TicketApiInterface apiService = RetrofitBuilder.returnService();

                                Call<User> call1 = apiService.getMyInfo("Bearer " + jwt);
                                call1.enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        User u = response.body();
                                        Log.i("dev", response.toString());
                                        editor.putString("id", u.getId());
                                        editor.commit();
                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {

                                    }
                                });





                                Log.i("dev", role);
                                if (role.equals("ADMIN")) {
                                    Intent i = new Intent(LoginActivity.this, AdminActivity.class);
                                    startActivity(i);
                                } else if (role.equals("USER")) {
                                    Intent i = new Intent(LoginActivity.this, UserMainActivity.class);
                                    startActivity(i);
                                }

                            } else
                                Toast.makeText(LoginActivity.this, "There was an error", Toast.LENGTH_SHORT).show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.i("dev", t.toString());

                    }
                });
            }
        });

    }
}
