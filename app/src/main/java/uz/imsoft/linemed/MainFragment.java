package uz.imsoft.linemed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MainFragment extends Fragment {


    private ProductAdapter productAdapter;
    private SectionAdapter sectionAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<SectionNote> list = new ArrayList<>();
        list.add(new SectionNote("1", "Yurak", R.drawable.icon_yurak));
        list.add(new SectionNote("1", "Tishlar", R.drawable.icon_tishlar));
        list.add(new SectionNote("1", "Oshqozon", R.drawable.icon_oshqozon));
        list.add(new SectionNote("1", "Yurak", R.drawable.icon_yurak));
        list.add(new SectionNote("1", "Oshqozon", R.drawable.icon_oshqozon));
        list.add(new SectionNote("1", "Oshqozon", R.drawable.icon_oshqozon));

        RecyclerView recyclerView = view.findViewById(R.id.orderRecycler);
        sectionAdapter = new SectionAdapter();
        sectionAdapter.swapData(list);
        recyclerView.setAdapter(sectionAdapter);
    }
}