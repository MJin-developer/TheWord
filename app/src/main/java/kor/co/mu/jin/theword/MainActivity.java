package kor.co.mu.jin.theword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AppCompatEditText editText;
    ImageView menu_favorite;
    ImageView menu_search;
    ImageView menu_flow;
    ImageView close_boardlist;
    ViewPager viewPager;
    FragmentPager fragmentPager;
    AlertDialog boardliist;

    int open_search = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);
        fragmentPager = new FragmentPager( getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT );
        viewPager.setAdapter(fragmentPager);

        CustomActionBar();
    }

    void CustomActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        View CustomActionbar = LayoutInflater.from(this).inflate(R.layout.custom_actionbar, null);
        editText = CustomActionbar.findViewById(R.id.search);
        menu_favorite = CustomActionbar.findViewById(R.id.menu_boardlist);
        menu_search = CustomActionbar.findViewById(R.id.menu_search);
        menu_flow = CustomActionbar.findViewById(R.id.menu_flow);

        actionBar.setCustomView(CustomActionbar);
    }

    public void clickSearch(View view) {
        open_search++;

        if (open_search % 2 == 0) {
            editText.setText("");
            editText.setVisibility(View.VISIBLE);
            editText.requestFocus();

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        } else {
            editText.setVisibility(View.GONE);

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }

        if (open_search > 2) open_search = 1;
    }

    public void clickSetting(View view) {
        Intent intentSetting = new Intent(MainActivity.this, SettingActivity.class);
        startActivity(intentSetting);
    }

    public void clickBoardList(View view) {
        View customview = LayoutInflater.from(this).inflate(R.layout.boardlist, null);
        close_boardlist = customview.findViewById(R.id.close_boardlist);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(customview);
        boardliist = builder.create();
        boardliist.setCancelable(false);
        boardliist.show();

        close_boardlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardliist.dismiss();
            }
        });
    }


    public void clickFamousPeople(View view) {
        viewPager.setCurrentItem(1);
        boardliist.dismiss();
    }

    public void clickPopularstroy(View view) {
        viewPager.setCurrentItem(2);
        boardliist.dismiss();
    }

    public void clickGood(View view) {
        viewPager.setCurrentItem(3);
        boardliist.dismiss();
    }

    public void clickFunny(View view) {
        viewPager.setCurrentItem(4);
        boardliist.dismiss();
    }

    public void clickMystory(View view) {
        viewPager.setCurrentItem(5);
        boardliist.dismiss();
    }
}
