package disco.riva.com.disco;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



/**
 * A simple {@link Fragment} subclass.
 */
public class AplicacionesExtras extends Fragment {

    private RecyclerView mRecyclerAsesinosSeriales;
    private DatabaseReference mDatabaseAsesinosSeriales;
    private ProgressDialog mProgress;

    public AplicacionesExtras() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_aplicaciones_extras, container, false);

        mProgress = new ProgressDialog(getContext());

        mDatabaseAsesinosSeriales = FirebaseDatabase.getInstance().getReference().child("Eventos");
        mDatabaseAsesinosSeriales.keepSynced(true);

        LinearLayoutManager layoutManagerTrending
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        layoutManagerTrending.setReverseLayout(true);
        layoutManagerTrending.setStackFromEnd(true);

        mRecyclerAsesinosSeriales = (RecyclerView) view.findViewById(R.id.fragmento_Aplicaciones_Extras);
        mRecyclerAsesinosSeriales.setHasFixedSize(true);

        mRecyclerAsesinosSeriales.setLayoutManager(layoutManagerTrending);

        FirebaseRecyclerAdapter<ItemFeed, RelatoViewHolderStructure> firebaseRecyclerAdapterAsesinosSeriales = new FirebaseRecyclerAdapter<ItemFeed, RelatoViewHolderStructure>(
                ItemFeed.class,
                R.layout.design_structure_relato_menu,
                RelatoViewHolderStructure.class,
                mDatabaseAsesinosSeriales

        ) {
            @Override
            protected void populateViewHolder(RelatoViewHolderStructure viewHolder, final ItemFeed model, final int position) {
                final String post_key = getRef(position).getKey();
                viewHolder.setTitle(model.getTitle());
                viewHolder.setCatergory(model.getCategory());
                viewHolder.setAuthor(model.getAuthor());

                viewHolder.setImage(getContext(), model.getImage());


                viewHolder.mViewStructure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mProgress.setMessage("Accediendo...");
                        // mProgress.show();
                        //Toast.makeText(getContext(),"dale "+post_key,Toast.LENGTH_SHORT).show();
                       /* Intent singleBlogIntent = new Intent(getContext(), DetailsGoogle.class);
                        singleBlogIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        singleBlogIntent.putExtra("blog_id", post_key);
                        startActivity(singleBlogIntent);*/

                        Log.v("ida","id"+post_key);
                    }
                });
            }
        };

        mRecyclerAsesinosSeriales.setAdapter(firebaseRecyclerAdapterAsesinosSeriales);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mProgress.hide();
        mProgress.dismiss();
    }

}


