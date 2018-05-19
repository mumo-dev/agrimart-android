package com.example.mumo.agrimart;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mumo.agrimart.adapters.ProductsAdapter;
import com.example.mumo.agrimart.model.Product;
import com.example.mumo.agrimart.model.ProductApiResponse;
import com.example.mumo.agrimart.remote.ApiClient;
import com.example.mumo.agrimart.remote.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private List<Product> mProducts;
    private ActionBarDrawerToggle mToggle;
    private static final String TAG = "MainActivity";

    private ApiInterface mApiService;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProductsAdapter mAdapter;
    private ProgressBar mProgressBar;
    private TextView mErrorTextView;
    private ProductApiResponse mResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);

        mRecyclerView = findViewById(R.id.recycler_view);
        mProgressBar = findViewById(R.id.pb_loading_indicator);
        mErrorTextView = findViewById(R.id.error_text_view);
        mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ProductsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mApiService = ApiClient.getClient().create(ApiInterface.class);

        loadFirstPage();

        mErrorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFirstPage();

            }
        });

    }

    private void loadFirstPage() {
        loadingDataViewSetup();
        mApiService.getProducts().enqueue(new Callback<ProductApiResponse>() {
            @Override
            public void onResponse(Call<ProductApiResponse> call, Response<ProductApiResponse> response) {
                successLoadingDataViewSetup();
                if (response.body() == null) {
                    errorLoadingDataViewSetUp();
                    Log.i(TAG, "check network connection ");
                }

                if (response.body() != null) {
                    mResponse = response.body();
                    mProducts = response.body().getData();
                    mAdapter.setProducts(mProducts);
                }
                Log.i(TAG, "Response data loaded from api: ");
            }

            @Override
            public void onFailure(Call<ProductApiResponse> call, Throwable t) {
                Log.i(TAG, "An error occured while loading data " + t.getMessage());
                errorLoadingDataViewSetUp();
            }
        });
    }

    private void loadingDataViewSetup() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorTextView.setVisibility(View.INVISIBLE);
    }

    private void errorLoadingDataViewSetUp() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void successLoadingDataViewSetup() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorTextView.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
