package cl.dewebsic.marketplacedeventas;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import cl.dewebsic.marketplacedeventas.interfaces.IComunationFragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class dashboard extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    View view;
    Activity activity;
    CardView cardSesion,cardSuper,cardLibreria;
    IComunationFragments  comunationFragments;
    public dashboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static dashboard newInstance(String param1, String param2) {
        dashboard fragment = new dashboard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        cardSesion = view.findViewById(R.id.cardSesion);
        cardSuper = view.findViewById(R.id.cardSuper);
        cardLibreria = view.findViewById(R.id.cardLibrerias);

        cardSesion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                comunationFragments.iniciarSesion();
            }
        });



        cardSuper.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                comunationFragments.supermercados();
            }
        });

        cardLibreria.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                comunationFragments.librerias();
            }
        });



        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.activity=(Activity) context;
            comunationFragments= (IComunationFragments) this.activity;
        }

    }

}