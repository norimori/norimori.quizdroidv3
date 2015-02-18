package edu.washington.norimori.quizdroidv3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    private List<Topic> everything = QuizAppSingleton.getInstance().getEverything();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create list of topics with accompanying short description.
        ListView listView = (ListView) findViewById(R.id.listView);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Topic topic : everything) {
            Map<String, String> pair = new HashMap<String, String>(2);
            pair.put("title", topic.getName());
            pair.put("descShort", topic.getDescriptionShort());
            list.add(pair);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_expandable_list_item_2,
                new String[] {"title", "descShort"}, new int[] {android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nextActivity = new Intent(MainActivity.this, ActionActivity.class);
                nextActivity.putExtra("chosenTopic", everything.get(position).getName());
                startActivity(nextActivity);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
