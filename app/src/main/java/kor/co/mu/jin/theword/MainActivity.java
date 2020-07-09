package kor.co.mu.jin.theword;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AppCompatEditText editText;
    ImageView menu_favorite;
    ImageView menu_search;
    ImageView menu_flow;

    int open_search = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomActionBar();
    }

    void CustomActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        View CustomActionbar = LayoutInflater.from(this).inflate(R.layout.custom_actionbar, null);
        editText = CustomActionbar.findViewById(R.id.search);
        menu_favorite = CustomActionbar.findViewById(R.id.menu_favorite);
        menu_search = CustomActionbar.findViewById(R.id.menu_search);
        menu_flow = CustomActionbar.findViewById(R.id.menu_flow);

        actionBar.setCustomView(CustomActionbar);
    }

    public void clickSearch(View view) {
        open_search ++;

        if (open_search%2 == 0) {
            editText.setVisibility(View.VISIBLE);
            editText.requestFocus();

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        }else {
            editText.setVisibility(View.GONE);

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }

        if(open_search > 10) open_search = 1;
    }
}
