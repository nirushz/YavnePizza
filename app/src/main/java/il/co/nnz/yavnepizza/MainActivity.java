package il.co.nnz.yavnepizza;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.graphics.Rect;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private PizzaAdapter adapter;
    private List<Pizza> pizzaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        pizzaList = new ArrayList<>();
        adapter = new PizzaAdapter(this, pizzaList);

        RecyclerView.LayoutManager myLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        int[] pizzasCovers = new int[]{
                R.drawable.pizzahut,
                R.drawable.pizzashemesh,
                R.drawable.express,
                R.drawable.fabios,
                R.drawable.beanak,
                R.drawable.adeso,
                R.drawable.agvania,
                R.drawable.domino,
                R.drawable.bambola,
                R.drawable.kimathinam

        };

        pizzaList.add(new Pizza(0, "פיצה האט", "1700506070" , "הנחשול 22 יבנה", pizzasCovers[0], 31.8632358, 34.7395355));
        pizzaList.add(new Pizza(1, "פיצה שמש", "089332006" , "אבוחצירא 18, יבנה", pizzasCovers[1], 31.8729964, 34.7474636));
        pizzaList.add(new Pizza(2, "פיצה אקספרס", "089333833" , "הזית 5 יבנה", pizzasCovers[2], 31.8769323, 34.7390152));
        pizzaList.add(new Pizza(3, "פיצה פביוס", "08-9420960" , "דואני 20, יבנה", pizzasCovers[3], 31.8732233, 34.7397287));
        pizzaList.add(new Pizza(4, "פיצה בענק", "089331213" , "דואני 18, יבנה", pizzasCovers[4], 31.8738209, 34.7389165));
        pizzaList.add(new Pizza(5, "פיצה אדסו", "1700500570" , "העצמאות 15, יבנה", pizzasCovers[5], 31.8720723, 34.7488788));
        pizzaList.add(new Pizza(6, "פיצה עגבניה", "086520320" , "הקישון 1 מתחם G, יבנה", pizzasCovers[6], 31.8861375, 34.733551));
        pizzaList.add(new Pizza(7, "פיצה דומינו", "08-9420460" , "הדרור 14, יבנה", pizzasCovers[7], 31.8724081, 34.7427846));
        pizzaList.add(new Pizza(8, "פיצה במבולה", "08-9436770" , "אגוז 2, מרכז מסחרי נוה אילן, יבנה", pizzasCovers[8], 31.8782056, 34.7357708));
        pizzaList.add(new Pizza(9, "פיצה כמעט חינם", "08-9334332" , "דואני 18, יבנה", pizzasCovers[9], 31.8738209, 34.7389165));

        try {
            Glide.with(this).load(R.drawable.coverb).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e){
            e.printStackTrace();
        }

        adapter.notifyDataSetChanged();



    }


    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }



    private class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }
}


    //Converting dp to pixel
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }



    /*
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent phoneCall;
        String phone= adapter.getItem(position).getPhone();

        phoneCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
        startActivity(phoneCall);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(MainActivity.this, "open menu Pizza Shemesh", Toast.LENGTH_SHORT).show();

        return true;
    }*/
}
