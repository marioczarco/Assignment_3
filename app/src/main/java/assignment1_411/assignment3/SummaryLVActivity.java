package assignment1_411.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import assignment1_411.assignment3.adapter.SummaryListAdapter;
import assignment1_411.assignment3.model.PersistentManager;

public class SummaryLVActivity extends AppCompatActivity {
    protected Menu detailMenu;
    protected ListView mSummaryView;
    protected final String TAG = "Summary Screen";
    protected SummaryListAdapter ad;
    Boolean gf = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.student_list_lv);
        mSummaryView = (ListView) findViewById(R.id.summary_list_id);
        ad = new SummaryListAdapter();
        mSummaryView.setAdapter(ad);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Custom Menu inflation
        getMenuInflater().inflate(R.menu.detail_add_button, menu);
        menu.findItem(R.id.action_add).setVisible(true);
        detailMenu = menu;

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(mSummaryView.getContext(), studentEnrollment.class);
            mSummaryView.getContext().startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called");
        PersistentManager pm = new PersistentManager(getApplicationContext());
        pm.retrieveStudents();
        ad.notifyDataSetChanged();
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume() called");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause() called");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop() called");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
    }
}

