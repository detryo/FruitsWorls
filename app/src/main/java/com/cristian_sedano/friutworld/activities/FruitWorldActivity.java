package com.cristian_sedano.friutworld.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.cristian_sedano.friutworld.R;
import com.cristian_sedano.friutworld.adapters.FruitAdapter;
import com.cristian_sedano.friutworld.models.Fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitWorldActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private GridView gridView;
    private FruitAdapter adapterListView;
    private FruitAdapter adapterGridView;

    private List<Fruit> fruits;

    private MenuItem itemListView;
    private MenuItem itemGridView;

    private int counter = 0;
    private final int SWITCH_TO_LISTVIEW = 0;
    private final int SWITCH_TO_GRIDVIEW = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_world);

        this.enforceIconBar();

        this.fruits = getAllFruit();

        this.listView = (ListView) findViewById(R.id.listView);
        this.gridView = (GridView) findViewById(R.id.gridView);

        this.listView.setOnItemClickListener(this);
        this.gridView.setOnItemClickListener(this);

        this.adapterListView = new FruitAdapter(this, R.layout.list_view_item_fruit, fruits);
        this.adapterGridView = new FruitAdapter(this, R.layout.grid_view_item_fruit, fruits);

        this.listView.setAdapter(adapterListView);
        this.gridView.setAdapter(adapterGridView);

        registerForContextMenu(this.listView);
        registerForContextMenu(this.gridView);


    }

    private void enforceIconBar() {

        getSupportActionBar().setIcon(R.mipmap.ic_world_fruit);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        this.clickFruit(fruits.get(position));
    }

    private void clickFruit(Fruit fruit) {
        if (fruit.getOrigin().equals("Unknown")) {
            Toast.makeText(this, "Sorry, we don't have many info abaout" + fruit.getName(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "The best fruit from" + fruit.getOrigin() + "is" + fruit.getName(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        this.itemListView = menu.findItem(R.id.list_view);
        this.itemGridView = menu.findItem(R.id.grid_view);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_Fruit:
                this.addFruit(new Fruit("Add number" + (++counter), R.mipmap.ic_launcher_round, "Unknown"));
                return true;
            case R.id.list_view:
                this.switchListGridView(this.SWITCH_TO_LISTVIEW);
                return true;
            case R.id.grid_view:
                this.switchListGridView(this.SWITCH_TO_GRIDVIEW);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.fruits.get(info.position).getName());
        inflater.inflate(R.menu.context_menu_fruits, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.item_delete:
                this.deleteFruit(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void switchListGridView(int option) {

        if (option == SWITCH_TO_LISTVIEW) {
            if (this.listView.getVisibility() == View.INVISIBLE) {

                this.gridView.setVisibility(View.INVISIBLE);
                this.itemGridView.setVisible(true);

                this.listView.setVisibility(View.VISIBLE);
                this.itemListView.setVisible(false);
            }
        } else if (option == SWITCH_TO_GRIDVIEW) {
            if (this.gridView.getVisibility() == View.INVISIBLE) {

                this.listView.setVisibility(View.INVISIBLE);
                this.itemListView.setVisible(true);

                this.gridView.setVisibility(View.VISIBLE);
                this.itemGridView.setVisible(false);
            }
        }
    }


    private List<Fruit> getAllFruit() {

        List<Fruit> list = new ArrayList<Fruit>() {{
            add(new Fruit("Banana", R.mipmap.ic_banana, "Canary Island"));
            add(new Fruit("Apple", R.mipmap.ic_apple, "Madrid"));
            add(new Fruit("Cherry", R.mipmap.ic_cherry, "Galicia"));
            add(new Fruit("orange", R.mipmap.ic_orange, "Valencia"));
            add(new Fruit("Pear", R.mipmap.ic_pear, "Zaragoza"));
            add(new Fruit("Strawberry", R.mipmap.ic_strawberry, "Barcelona"));

        }};

        return list;
    }

    private void addFruit(Fruit fruit) {
        this.fruits.add(fruit);

        this.adapterListView.notifyDataSetChanged();
        this.adapterGridView.notifyDataSetChanged();
    }

    private void deleteFruit(int position) {

        this.fruits.remove(position);

        this.adapterListView.notifyDataSetChanged();
        this.adapterGridView.notifyDataSetChanged();
    }

}
