package il.co.nnz.yavnepizza;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by User on 22/09/2016.
 */

// 1> add the RecyclerView library
// 2> create the adapter class
public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.MyViewHolder> {

    Pizza clickedPizza;
    private Context context;
    private List<Pizza> pizzaList;

    // 3> create ViewHolder class
    // 5> create the item layout xml file
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // 6> define the views in the holder
        private TextView textName, textAdress;
        private ImageView thumbnail ,overflow;

        // 4> add ctor
        public MyViewHolder(View itemView) {
            super(itemView);
            textName = (TextView)itemView.findViewById(R.id.name);
            textAdress = (TextView)itemView.findViewById(R.id.address);
            overflow = (ImageView)itemView.findViewById(R.id.overflow);
            thumbnail = (ImageView)itemView.findViewById(R.id.thumbnail);

            thumbnail.setOnClickListener(this);
            textName.setOnClickListener(this);
            textAdress.setOnClickListener(this);
            overflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedPizza = pizzaList.get(getAdapterPosition());
                    showPopupMenu(v);
                }
            });
        }

        @Override
        public void onClick(View v) {
            clickedPizza = pizzaList.get(getAdapterPosition());
            Intent phoneCall;
            String phone= clickedPizza.getPhone();
            phoneCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
            context.startActivity(phoneCall);

        }

    }


    // 9> ctor that gets data
    public PizzaAdapter(Context context, List<Pizza> pizzaList) {
        this.context = context;
        this.pizzaList = pizzaList;
    }

    // 10> what to do when creating a new ViewHolder for a new item
    @Override
    public PizzaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizza_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    // 11> what to do when binding data (pizza) to an item
    @Override
    public void onBindViewHolder(final PizzaAdapter.MyViewHolder holder, int position) {

        Pizza pizza = pizzaList.get(position);
        holder.textName.setText(pizza.getName());
        holder.textAdress.setText(pizza.getAddress());

        // loading album cover using Glide library
        Glide.with(context).load(pizza.getThumbnail()).into(holder.thumbnail);

        /*
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopupMenu(holder.overflow);

            }
        });*/
    }

    //Showing popup menu when tapping on 3 dots
    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_pizza, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    // 12> return how many items in the data
    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    //Click listener for popup menu items
    private class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()){

                case R.id.action_call_pizza:
                    Intent phoneCall;
                    String phone= clickedPizza.getPhone();
                    phoneCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                    context.startActivity(phoneCall);
                    return true;

                case R.id.action_order_pizza:
                    Intent orderIntent = new Intent(context, OrderActivity.class);
                    context.startActivity(orderIntent);
                    Toast.makeText(context, "האופציה להזמנות תפתח בקרוב", Toast.LENGTH_LONG).show();
                    return true;

                case R.id.action_navigate_pizza:
                    String uri="geo:"+clickedPizza.getLat()+","+clickedPizza.getLng();
                    Intent shareIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));

                    if (shareIntent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(shareIntent);
                    }
                    return true;
                default:

            }
            return false;
        }

    }
}
